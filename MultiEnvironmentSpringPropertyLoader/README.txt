This application shows how you can create a custom PropertyPlaceholderConfigurer for handling properties file for multiple environments.


You can specify the location of the external file such as a configuration folder that is on the system where the application will be 
deployed in the applicationContext.xml.

This is how the application works. You have two configuration files. One is internal to the application and the other one is external. 
External file represents the production version of the properties file when the application is deployed. If the key exists in the external 
file then the application will read the value from the external file. Otherwise, it will get the value from the internal file.
Therefore, this application allows you to specify key-value pairs to specific environment.

To run this application, modify the following entry in applicationContext.xml whatever folder and file name you would like to have and 
create the folder and the file on the your machine.
file:C:/app/config/external.properties

Enter the following key-value pairs in your external file for testing. 
db.schema=prod
db.url=proddatabase

The application will read the username from the internal file since it is not specified in the external file. 
