package com.glassdoor.test.intern.first;

public class ConsoleLogger implements ILoggerService{
    ILoggerService nextHandler;

    /**
     * Log message on Console with timestamp
     * @param message
     * @param timestamp
     */
    @Override
    public void log(String message, String timestamp) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        buffer.append(timestamp);
        buffer.append("] : ");
        buffer.append(message);
        System.out.println(buffer.toString());
        if(nextHandler!=null)
            nextHandler.log(message, timestamp);
    }

    /**
     * Set next Log handler
     * @param next
     */
    @Override
    public void setNext(ILoggerService next) {
        nextHandler = next;
    }
}
