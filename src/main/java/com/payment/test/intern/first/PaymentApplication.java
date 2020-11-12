package com.glassdoor.test.intern.first;

public class PaymentApplication {
  public static void main(String[] args) {

    /**
     * Test case - Amount is less than the card balance
     */
    IncomingRequest incomingRequest = new IncomingRequest();
    incomingRequest.userId = 1;
    incomingRequest.userName = "ABC";
    incomingRequest.billingAddress = "123 Some Street, City Name, ST";
    incomingRequest.cardnumber = "1234567856881234";
    incomingRequest.amount = 10;
    boolean successfulPayment = new PaymentProcessorProxy().process_payment(incomingRequest);
    System.out.println("Test case - Amount is less than the card balance");
    System.out.println("Expected Output = " + true + " Actual Output = "+ successfulPayment+"\n\n\n");

    /**
     * Test case - Amount is greater than the card balance
     */
    incomingRequest = new IncomingRequest();
    incomingRequest.userId = 1;
    incomingRequest.userName = "ABC";
    incomingRequest.billingAddress = "123 Some Street, City Name, ST";
    incomingRequest.cardnumber = "1234567856881234";
    incomingRequest.amount = 10;
    successfulPayment = new PaymentProcessorProxy().process_payment(incomingRequest);
    System.out.println("\nTest case - Amount is greater than the card balance");
    System.out.println("Expected Output = " + false + " Actual Output = "+ successfulPayment+"\n\n\n");

    /**
     * Test case - Amount is negative
     */
    incomingRequest = new IncomingRequest();
    incomingRequest.userId = 1;
    incomingRequest.userName = "ABC";
    incomingRequest.billingAddress = "123 Some Street, City Name, ST";
    incomingRequest.cardnumber = "1234567856881234";
    incomingRequest.amount = -10;
    successfulPayment = new PaymentProcessorProxy().process_payment(incomingRequest);
    System.out.println("\nTest case - Amount is negative");
    System.out.println("Expected Output = " + false + " Actual Output = "+ successfulPayment+"\n\n\n");

    /**
     * Test case - User Id not present in DB
     */
    incomingRequest = new IncomingRequest();
    incomingRequest.userId = 10;
    incomingRequest.userName = "ABC";
    incomingRequest.billingAddress = "123 Some Street, City Name, ST";
    incomingRequest.cardnumber = "1234567856881234";
    incomingRequest.amount = 10;
    successfulPayment = new PaymentProcessorProxy().process_payment(incomingRequest);
    System.out.println("\nTest case - User Id not present in DB");
    System.out.println("Expected Output = " + false + " Actual Output = "+ successfulPayment+"\n\n\n");

    /**
     * Test case - User Id in incomming request is null
     */
    incomingRequest = new IncomingRequest();
    incomingRequest.userName = "ABC";
    incomingRequest.billingAddress = "123 Some Street, City Name, ST";
    incomingRequest.cardnumber = "1234567856881234";
    incomingRequest.amount = 10;
    successfulPayment = new PaymentProcessorProxy().process_payment(incomingRequest);
    System.out.println("\nTest case - User Id in incomming request is null");
    System.out.println("Expected Output = " + false + " Actual Output = "+ successfulPayment+"\n\n\n");

    /**
     * Test case - UserName is null, everything else is valid
     */
    incomingRequest = new IncomingRequest();
    incomingRequest.userId = 1;
    incomingRequest.billingAddress = "123 Some Street, City Name, ST";
    incomingRequest.cardnumber = "1234567856881234";
    incomingRequest.amount = 10;
    successfulPayment = new PaymentProcessorProxy().process_payment(incomingRequest);
    System.out.println("\nTest case - UserName is null, everything else is valid");
    System.out.println("Expected Output = " + false + " Actual Output = "+ successfulPayment+"\n\n\n");

    /**
     * Test case - UserName does not match Database value, everything else is valid
     */
    incomingRequest = new IncomingRequest();
    incomingRequest.userId = 1;
    incomingRequest.userName = "ABCD";
    incomingRequest.billingAddress = "123 Some Street, City Name, ST";
    incomingRequest.cardnumber = "1234567856881234";
    incomingRequest.amount = 10;
    successfulPayment = new PaymentProcessorProxy().process_payment(incomingRequest);
    System.out.println("\nTest case - UserName does not match Database value, everything else is valid");
    System.out.println("Expected Output = " + false + " Actual Output = "+ successfulPayment+"\n\n\n");


    /**
     * Test case - BillingAddress is null, everything else is valid
     */
    incomingRequest = new IncomingRequest();
    incomingRequest.userId = 1;
    incomingRequest.userName = "ABC";
    incomingRequest.cardnumber = "1234567856881234";
    incomingRequest.amount = 10;
    successfulPayment = new PaymentProcessorProxy().process_payment(incomingRequest);
    System.out.println(successfulPayment);
    System.out.println("\nTest case - BillingAddress is null, everything else is valid");
    System.out.println("Expected Output = " + false + " Actual Output = "+ successfulPayment+"\n\n\n");

    /**
     * Test case - BillingAddress does not match Database value, everything else is valid
     */
    incomingRequest = new IncomingRequest();
    incomingRequest.userId = 1;
    incomingRequest.userName = "ABC";
    incomingRequest.billingAddress = "1234 Some Street, City Name, ST";
    incomingRequest.cardnumber = "1234567856881234";
    incomingRequest.amount = 10;
    successfulPayment = new PaymentProcessorProxy().process_payment(incomingRequest);
    System.out.println("\nTest case - BillingAddress does not match Database value, everything else is valid");
    System.out.println("Expected Output = " + false + " Actual Output = "+ successfulPayment+"\n\n\n");
  }
}