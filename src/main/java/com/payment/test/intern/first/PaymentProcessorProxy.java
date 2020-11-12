package com.glassdoor.test.intern.first;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Authentication and Request Validator for PaymentProcessor
 */
public class PaymentProcessorProxy {
    private boolean isAuthenticated = true;

    private PaymentProcessor paymentProcessor;
    public PaymentProcessorProxy(){
        PaymentProcessor.clearInstance();
        paymentProcessor = PaymentProcessor.getInstance();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        ILoggerService chain = paymentProcessor.getChain();
        chain.log( "Proxy Payment Class Instantiated", dtf.format(now));
    }

    /**
     *
     * @param incomingrequest
     * @return
     */
    public boolean process_payment(IncomingRequest incomingrequest) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        ILoggerService chain = paymentProcessor.getChain();
        chain.log( "Class: PaymentProcessorProxy Method : process_payment authentication and parameter validation", dtf.format(now));
        if(isAuthenticated && validateRequestParameters(incomingrequest))
            return paymentProcessor.process_payment(incomingrequest);
        dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        now = LocalDateTime.now();
        chain.log( "Class: PaymentProcessorProxy Method : process_payment validation failed TERMINATE", dtf.format(now));
        return false;
    }

    /**
     * Check if the request parameter are in the right format
     * @param incomingrequest
     * @return boolean flag
     */
    private boolean validateRequestParameters(IncomingRequest incomingrequest){
        return validateUserName(incomingrequest.userName) &&
                validateBillingAddress(incomingrequest.billingAddress) &&
                validateCardNumber(incomingrequest.cardnumber) &&
                validateAmount(incomingrequest.amount);
    }

    /**
     * Check if the billing address is alphaneumeric or not
     * @param billingAddress
     * @return boolean flag
     */
    private boolean validateBillingAddress(String billingAddress){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ILoggerService chain = paymentProcessor.getChain();
        if (billingAddress != null){
            String[] billingAddressComponets = billingAddress.split(",");
            for(String component : billingAddressComponets){
                if(!component.trim().matches("^[a-zA-Z0-9 ]*$")) {
                    chain.log( "Class: PaymentProcessorProxy Method : validateBillingAddress validation FAILED", dtf.format(LocalDateTime.now()));
                    return false;
                }
            }
            LocalDateTime now = LocalDateTime.now();
            chain.log( "Class: PaymentProcessorProxy Method : validateBillingAddress validation PASSED", dtf.format(LocalDateTime.now()));
            return true;
        }
        chain.log( "Class: PaymentProcessorProxy Method : validateBillingAddress validation FAILED", dtf.format(LocalDateTime.now()));
        return false;
    }

    /**
     * Check if userName contains only alphabets
     * @param userName
     * @return boolean flag
     */
    private boolean validateUserName(String userName){
        return userName != null && userName.matches("^[a-zA-Z]*$");
    }

    /**
     * Check if card number has 16 digits
     * @param cardNumber
     * @return
     */
    public boolean validateCardNumber(String cardNumber){
        return cardNumber != null && cardNumber.matches("^[0-9]*$") && cardNumber.length() == 16;
    }

    /**
     * Check if amount is positive
     * @param amount
     * @return
     */
    private boolean validateAmount(int amount){
        return amount >= 0 ? true : false;
    }
}
