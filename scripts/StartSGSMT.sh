#!/bin/bash

StartingCheck() {
  # Check if the application is already running, if so, do nothing
  echo "Check if the app is already running with command ps -ef | grep $jarFile | grep -v grep"
  if [ "$(ps -ef | grep "$jarFile" | grep -v grep)" ]
  then
    echo "The app is already running"
    exit 0
  fi

  # Warn the user that they have to configure the application.properties file
  echo "Please configure the application.properties file before running this script"
}

CreateImportantFolderIfNotExist() {
  # build the informationFile folder if it does not exist
  if [ ! -d "$InfoFileFolderPath" ]
  then
    mkdir "$InfoFileFolderPath"
  fi
}

findTheJarFilePath() {
  # find the right jar file with version number
  jarFile=$(find "$SGSMT_Home" -name "SGSMT-*.jar")
  echo "The jar file is $jarFile"
}

SetUpLogRotationWhenSudo() {
  # Setup a log rotation for the log file if it is not set up
  if [ ! -e "/etc/logrotate.d/SGSMT" ]
  then
    echo "Setup log rotation for SGSMT"
    echo "/tmp/SGSMT.log {
    rotate 5
    weekly
    missingok
    notifempty
    compress
    delaycompress
    copytruncate
  }" > /etc/logrotate.d/SGSMT
  fi
}

MakeScriptExecutable() {
  # Before start the application, make all scripts file in the scripts folder executable
  chmod +x "$SGSMT_Home"/scripts/*.sh
}

SetupApplicationPropertiesFile() {
  # Setup the application.properties file name
  applicationPropertiesFile="$SGSMT_Home/application.properties"

  echo "applicationPropertiesFile is $applicationPropertiesFile"
}

SetupInfoFile() {
  # if the PID file exists, remove it
  if [ -e "$InfoFileFolderPath/SGSMT.pid" ]
  then
    echo "Remove the PID file"
    rm "$InfoFileFolderPath/SGSMT.pid"
  fi
  # make a new pid file in the target folder
  touch "$InfoFileFolderPath/SGSMT.pid"

  # if the jarName file exists, remove it
  if [ -e "$InfoFileFolderPath/SGSMTJarName.txt" ]
  then
    echo "Remove the SGSMTJarName.txt file"
    rm "$InfoFileFolderPath/SGSMTJarName.txt"
  fi
  # make a new jarName file in the target folder
  touch "$InfoFileFolderPath/SGSMTJarName.txt"
}

SetupCronJob() {
  # Check if the cron job is already set up, if it is, do nothing
  if [ "$(crontab -l | grep 'StartSGSMT.sh')" ]
  then
    echo "The cron job StartSGSMT is already set up"
  else
    echo "Setup the cron job StartSGSMT"
    # set up crontab for evey 30th minute and print the log to the log file
    (crontab -l ; echo "*/30 * * * * $SGSMT_Home/scripts/StartSGSMT.sh $SGSMT_Home>> /tmp/SGSMT_Start.log 2>&1") | crontab -
  fi
}

StartApplication() {
  # Start the jar application with nohup and the application.properties file, get the return PID and write it to the info file
  echo "Start the application $jarFile..."
  echo "$jarFile" > "$InfoFileFolderPath/SGSMTJarName.txt"
  nohup java -jar $jarFile --spring.config.location="$applicationPropertiesFile" > /tmp/SGSMT.log 2>&1 &
  echo $! > "$InfoFileFolderPath/SGSMT.pid"
}

DebugPrintToFile(){
  #Print time to the log file
  echo "The time is $(date)" > /tmp/SGSMT_debug.log
  echo "SGSMT_Home is $SGSMT_Home" >> /tmp/SGSMT_debug.log
  echo "InfoFileFolderPath is $InfoFileFolderPath" >> /tmp/SGSMT_debug.log
  echo "jarFile is $jarFile" >> /tmp/SGSMT_debug.log
  echo "applicationPropertiesFile is $applicationPropertiesFile" >> /tmp/SGSMT_debug.log
  echo "ScriptFolderPath is $ScriptFolderPath" >> /tmp/SGSMT_debug.log
}

# Main

# if user give a input path as a parameter, use that path as the SGSMT_Home, else run the StartInit.sh
if [ "$1" ]
then
  export SGSMT_Home="$1"
  export ScriptFolderPath="$SGSMT_Home/scripts"
  export InfoFileFolderPath="$SGSMT_Home/informationFile"
else
  # Setup file paths
  export SGSMT_Home="$(pwd)/../"
  export ScriptFolderPath="$SGSMT_Home/scripts"
  export InfoFileFolderPath="$SGSMT_Home/informationFile"
fi


findTheJarFilePath
StartingCheck
CreateImportantFolderIfNotExist
#SetUpLogRotationWhenSudo
MakeScriptExecutable
SetupApplicationPropertiesFile
SetupInfoFile
SetupCronJob
StartApplication


# End of the StartSGSMT.sh