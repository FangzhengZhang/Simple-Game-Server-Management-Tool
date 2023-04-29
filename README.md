# Simple-Game-Server-Management-Tool
I want to build an MC and CoreKeeper game sever management tool, so I can back up, restart, and restore the game server easily.

# There are few goals I want to achieve:
- This application will send me the server IP address when it starts and when it changes. (done 0.1)
- This application will automatically restart the server when it crashes by use a corn job. (done 0.2)
- This application will print the server log into a file and rotate it every day if the file size is too big. (ongoing)
- This application will check the server hardware usage and display in UI. (ongoing)
- This application will display current Job status in UI, and allows user to stop, start or update them. (ongoing)
- I can set up the backup time and location of the game save file.
- I can select the backed file to restore it.
- I can check the resource usage of the server.
- I can update the server with command or upload file.
- I can start, stop, and restart the game server. 

# Starting idea:
This project should have the following components"
1. A page to let user check the status of the game server (in boxed review).
   - This UI should have a home page to **display the current running server** in a box looking UI.
     - This box can show users the status of the server (online / offline)
     - After user click on the box, it should take user to the server **detail page that show the running time, 
     last backup time, backup list, short server log, stop/start/restart server button, select file to download 
     or restore, server update button**
     - We need user to give little information to make the application able to control the server. 
       - Server starting file location.
       - Server start command.
       - Server backup file location.
       - Server log location.
       - Server stop command (default with kill -9).
       - Server update command for servers that do not need to upload update file by user.
       
2. The back end should support the front-end's requirement, and save those event logs into a database.

# Requirement for you pc:
1. curl
2. MySQL server 
3. Java 17

# How to run this application:
1. The jar file and script files is in app_build folder.
2. You need to give the right application path in the application.properties file. (I may write a script to do this in the future)
3. If you want to use the email function, you need to give the right email address and password in the application.properties file.
4. Make sure the MySQL is running. If you are not use the default port, you need to change the port in the application.properties file.
5. Start the application by run the StartSGSMT.sh file (sudo not required). This will set up corn job to run the application every 30 minutes.
6. Stop the application by run the StopSGSMT.sh file. This will remove the corn job.


# Starting Logic 
1. When the application starts. It will send email to tell client the IP address of this server. 
2. It will check the application running status every 30 minutes.
3. It will print the server log into /tmp folder.
4. It will check the server IP every hour.
5. It will rotate the server log every day if its size is too big.


