package com.glassdoor.test.intern.first;

import java.util.Map;

public interface IUserDatabaseFactory {
    Map<Integer, UserDetails> readDB();
}
