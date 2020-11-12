package com.glassdoor.test.intern.first;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader implements IUserDatabaseFactory {
    private Map<Integer, UserDetails> userMap = new HashMap<>();

    @Override
    public Map<Integer, UserDetails> readDB() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                ExcelReader.class.getClassLoader().getResourceAsStream("user_database.csv")))) {
            String line;
            while ((line = br.readLine()) != null) {
                String splits[] = line.split(",");
                UserDetails curr = new UserDetails();
                curr.setUserName(splits[1]);
                curr.setAddress(splits[2]);
                curr.setCardNumber(splits[3]);
                curr.setCardBalance(Integer.parseInt(splits[4]));
                userMap.put(Integer.parseInt(splits[0]),curr);
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return userMap;
    }
}
