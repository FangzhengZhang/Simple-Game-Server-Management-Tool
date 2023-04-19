#!/bin/bash

# Check if the application is already running, if so, do nothing
if [ "$(ps -ef | grep 'java -jar SGSMT-*.jar' | grep -v grep)" ]
then
  echo "The app is already running"
  exit 0
fi

# Warn the user that they have to configure the application.properties file
echo "Please configure the application.properties file before running this script"

# Stop the app if it is running
./StopSGSMT.sh

# Set the path to the root folder of the project
source ./StartInit.sh

echo "$SGSMT_Home"
echo "$ScriptFolderPath"
echo "$InfoFileFolderPath"

# Go to the root folder of the project
cd ..

# find the right jar file with version number
jarFile=$(find . -name "SGSMT-*.jar")


# crontab -e
# */5 * * * * $ScriptFolderPath/StartSGSMT.sh

# check if the cron job is already set up, if it is, do nothing
if [ "$(crontab -l | grep 'StartSGSMT.sh')" ]
then
  echo "The cron job is already set up"
  exit 0
fi

# use shell script to setup a cron job to run the script every 5 minutes
# to start the app if it is not running
(crontab -l ; echo "*/5 * * * * $ScriptFolderPath/StartSGSMT.sh") | crontab -

# Setup the application.properties file name
applicationPropertiesFile="$SGSMT_Home/application.properties"

echo "applicationPropertiesFile is $applicationPropertiesFile"

# Start the Springboot app with the jar file and the application.properties file.
java -jar $jarFile --spring.config.location="$applicationPropertiesFile"