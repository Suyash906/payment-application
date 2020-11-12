package com.glassdoor.test.intern.first;

/**
 *
 */
public interface ILoggerService {
    /**
     *  Log message with timestamp
     * @param message
     * @param timestamp
     */
    void log(String message, String timestamp);

    /**
     * Set Next Logger in Event Chain
     * @param next
     */
    void setNext(ILoggerService next);
}
