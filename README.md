**Assumtions**
- The test cases are written in the main function.
- If the final output is true, then the payment was done successfully
- If the final output is false, then atleast one of the following situation was encountered
  - The amount was negative or more than the card balance
  - The userId was NULL, non-numeric or was not present in Database
  - The userName was NULL or did not match with Database
  - The billingAddress was NULL or did not match with the Database
  - The cardNumber was NULL, non-numeric or did not have 16 digits

**Database Updates**
- Added two new fields cardNumber and Balance

**Design Patterns Used**
- Chain of Responsibility Pattern to create logs
  - The stater code does not have any logging.
  - If there is an need to write logs in multiple places(console, File, S3, etc), then Chain of Responsibility can be used with multiple handler in a chain writing logs in seperate places.
  - The logging has been implemented for a small section only as a sample. Following the similar format the logging can be added to entire code. 
- Factory Design Pattern to read User Details from Database
  - In the starter code, the data was read from a text file.
  - If in future the data source changes or there is a requirement to fetch data from multiple scouces, then there will be a lot of modification in the existing code.
  - The factory design pattern solved this problem sucessfully since it allows the making of objects with no exposure to instantation logic. Also it can be easily extended without modification in the existing code.
- Proxy Pattern Request Parameter Validation
  - The request parameter in the input request could be missing, incorrect format or type. In such cases, the request must not be forwarded
  - There could also be a requirement to add authentication logic to access the Payment Application. In such case we can write the authentication logic in the Proxy class.
