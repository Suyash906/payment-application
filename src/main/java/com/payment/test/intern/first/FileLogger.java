package com.glassdoor.test.intern.first;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileLogger implements ILoggerService{

    ILoggerService nextHandler;

    /**
     * Log message in File with timestamp
     * @param
     * @param timestamp
     */
    @Override
    public void log(String message, String timestamp) {
        try {
            FileOutputStream outputStream = new FileOutputStream("PaymentLogs.txt", true);
            StringBuffer buffer = new StringBuffer();
            buffer.append("[");
            buffer.append(timestamp);
            buffer.append("] : ");
            buffer.append(message);
            buffer.append("\n");
            byte[] strToBytes = buffer.toString().getBytes();
            outputStream.write(strToBytes);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            System.out.println("Exception occurred while creating logs");
        }

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
