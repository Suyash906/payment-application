package com.glassdoor.test.intern.first;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PaymentProcessor {
    private static PaymentProcessor paymentProcessor;

    private ArrayList<ILoggerService> loggers = new ArrayList<ILoggerService>() ;
    private ILoggerService chain ;
    private ILoggerService fileLogger;
    private ILoggerService consoleLogger;
    private Map<Integer, UserDetails> userMap = new HashMap<>();

    /**
     * PaymentProcessor Singleton Instance
     * @return app
     */
    public static PaymentProcessor getInstance() {
        if (paymentProcessor == null) {
            paymentProcessor = new PaymentProcessor();
        }
        return paymentProcessor;
    }

    /**
     * Delete Old PaymentProcessor Instance
     */
    public static void clearInstance(){
        paymentProcessor = null;
    }

    public ILoggerService getChain(){
        return chain;
    }


    private PaymentProcessor(){
        fileLogger = new FileLogger();
        consoleLogger = new ConsoleLogger();
        loggers.add(fileLogger);
        loggers.add(consoleLogger);
        buildLoggerChain();
    }

    private void buildLoggerChain(){
        int loggersSize = loggers.size();
        chain = loggers.get(0);
        for(int i=1;i<loggersSize;i++){
            ILoggerService prev = loggers.get(i-1) ;
            prev.setNext(loggers.get(i));
        }
    }



  public boolean process_payment(IncomingRequest incomingrequest) {
      userMap = UserDatabaseFactory.getDatabaseReader("TXT").readDB(); // TextReader factory called
      if(userMap.containsKey(incomingrequest.userId)){
          UserDetails currentUser = userMap.get(incomingrequest.userId);
          if(compareValues(incomingrequest.userName, currentUser.getUserName()) &&
                  compareValues(incomingrequest.billingAddress, currentUser.getAddress()) &&
                  compareValues(incomingrequest.cardnumber, currentUser.getCardNumber())
          ){
              try {
                  if(incomingrequest.amount > currentUser.getCardBalance())
                      return false;
                  submitPayment(incomingrequest.cardnumber, incomingrequest.amount);
                  return true;
              } catch (Exception e){
                  return false;
              }
          }
      }
      return false;
  }

  private boolean compareValues(String requestValue, String dbValue){
        if (requestValue == null || dbValue == null)
            return false;
        return dbValue.equals(requestValue);
  }

  public void submitPayment(String card, int amount) {
    //Don't implement this.
  }
}