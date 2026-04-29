package com.parameter;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "callbackData")
    public static Object[][] callbackData() {
        return new Object[][]{
            // name,   mobile,      email,                       city,        message
            {"kugan", "8220067539", "kuganthangavel39@gmail.com", "Delhi", "when i will recieve my report?"}
        };
    }
}