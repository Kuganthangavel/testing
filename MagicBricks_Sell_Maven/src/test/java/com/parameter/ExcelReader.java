package com.parameter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	private static String getExcelPath() {
		// Uses your requested path and file name:
		// //src//test//resource//ExcelData//Data.xlsx
		return System.getProperty("user.dir") + "\\src\\test\\resources\\Exceldata\\ExcelData.xlsx";
	}

	public static String getCellData(String sheetName, int colNum, int rowNum) {

		if (rowNum <= 0)
			return "";

		File excel = new File(getExcelPath());
		if (!excel.exists())
			return "";

		try (FileInputStream fis = new FileInputStream(excel); Workbook workbook = new XSSFWorkbook(fis)) {

			int sheetIndex = workbook.getSheetIndex(sheetName);
			if (sheetIndex == -1)
				return "";

			Sheet sheet = workbook.getSheetAt(sheetIndex);
			Row row = sheet.getRow(rowNum - 1);
			if (row == null)
				return "";

			Cell cell = row.getCell(colNum - 1);
			if (cell == null)
				return "";

			switch (cell.getCellType()) {
			case STRING:
				return cell.getStringCellValue();

			case NUMERIC:
			case FORMULA:
				if (DateUtil.isCellDateFormatted(cell)) {
					return cell.getDateCellValue().toString();
				} else {
					double val = cell.getNumericCellValue();
					DecimalFormat df = new DecimalFormat("#");
					df.setMaximumFractionDigits(0);
					return df.format(val);
				}

			case BOOLEAN:
				return String.valueOf(cell.getBooleanCellValue());

			case BLANK:
			default:
				return "";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "row " + rowNum + " or column " + colNum + " does not exist in xls";
		}
	}

	/**
	 * Reads a two-column sheet where: A = key (e.g., Name, City, Mobile, Question)
	 * B = value Stops when key is blank.
	 */
	public static Map<String, String> readEntityValueSheet(String sheetName) {
		Map<String, String> map = new LinkedHashMap<>();

		int row = 2; // start from row 2 (row 1 is header)
		while (true) {
			String key = safe(getCellData(sheetName, 1, row));
			String val = safe(getCellData(sheetName, 2, row));

			if (key.isEmpty())
				break;

			map.put(key.trim(), val.trim());
			row++;
		}
		return map;
	}

	private static String safe(String s) {
		return s == null ? "" : s;
	}

	// -------- Normalization methods kept as-is (not used by this form) --------

	public static Map<String, String> normalizeForPatientDetails(Map<String, String> excel) {
		Map<String, String> normalized = new HashMap<>();

		excel.forEach((rawKey, val) -> {
			String k = rawKey.trim().toLowerCase();
			switch (k) {
			case "validname":
			case "fullname":
			case "name":
				normalized.put("name", val);
				break;
			case "validemail":
			case "email":
			case "mail":
			case "emailaddress":
				normalized.put("email", val);
				break;
			case "phoneno":
			case "phone":
			case "mobile":
			case "mobileno":
				normalized.put("phone", normalizePhone(val));
				break;
			default:
				System.out.println("Ignoring unsupported entity key: " + rawKey);
			}
		});

		return normalized;
	}

	private static String normalizePhone(String raw) {
		if (raw == null)
			return "";
		raw = raw.trim();
		if (raw.startsWith("+")) {
			return "+" + raw.substring(1).replaceAll("\\D", "");
		}
		return raw.replaceAll("\\D", "");
	}

	// Convenience getters for PatientDetails sheet (kept)
	public static String getFullName() {
		Map<String, String> normalized = normalizeForPatientDetails(readEntityValueSheet("PatientDetails"));
		return normalized.getOrDefault("name", "");
	}

	public static String getEmail() {
		Map<String, String> normalized = normalizeForPatientDetails(readEntityValueSheet("PatientDetails"));
		return normalized.getOrDefault("email", "");
	}

	public static String getPhone() {
		Map<String, String> normalized = normalizeForPatientDetails(readEntityValueSheet("PatientDetails"));
		return normalized.getOrDefault("phone", "");
	}
}