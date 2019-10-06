# Java Test Task

# Pre requirment
- Java SDK 11
- Intellij IDE
- MySQL

# Setup instruction
- Clone project 
````
git clone https://github.com/SoumenITMO/Java-Test-Task.git
````
- Change Database Connection
````
To change the database connection and application default configuration 
edit "Pom.xml" file in project folder. Locate "<properties>" and edit 
following property.
````
- port
- dbname
- dbport
- dbhost
- dbusername
- dbpassword

# Default Configurations are bellow 
- Application port : 85
- Database Username : root
- Database Password : 
- Database : db1
- Database port : 3306
- Database hostname : localhost 

# How to run the project ?
- Open Intellij
- Go to File -> Open 
- Locate cloned folder
- Look at top right corner and locate "Green Play Button" after "FobApplication". Click the play button.

# NOTE :
- Application will automatically fill database. 

# Navigate to this address if configuration didn't change
- http://localhost:85

# Default Login 
- email : soumen@soumen.com
- idcode : 5123124
- password : test123