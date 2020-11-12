package com.glassdoor.test.intern.first;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class TextReader implements IUserDatabaseFactory {

    private Map<Integer, UserDetails> userMap = new HashMap<>();

    @Override
    public Map<Integer, UserDetails> readDB() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                TextReader.class.getClassLoader().getResourceAsStream("user_database.txt")))) {

            String line;
            while ((line = br.readLine()) != null) {
                String splits[] = line.split("\t");
                UserDetails curr = new UserDetails();
                if(splits.length == 5){
                    int key = parseWithDefault(splits[0]);
                    if(-1 == key) continue;

                    curr.setUserName(splits[1]);
                    curr.setAddress(splits[2]);
                    curr.setCardNumber(splits[3]);
                    int cardBalance = parseWithDefault(splits[4]);

                    if (-1 == cardBalance) continue;
                    curr.setCardBalance(cardBalance);
                    userMap.put(key,curr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userMap;
    }

    private static int parseWithDefault(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

}
