package com.glassdoor.test.intern.first;

public class UserDatabaseFactory {
    public static IUserDatabaseFactory getDatabaseReader(String readerType){
        IUserDatabaseFactory reader = null;
        if(readerType.equalsIgnoreCase("EXCEL")){
            reader = new ExcelReader();
        } else if(readerType.equalsIgnoreCase("TXT")){
            reader = new TextReader();
        }
        return reader;
    }
}
