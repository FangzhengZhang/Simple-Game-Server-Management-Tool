#!/bin/bash

StopCronJob(){
  # check if the cron job is already set up, if it is, remove it
  if [ "$(crontab -l | grep 'StartSGSMT.sh')" ]
  then
    echo "The cron job StartSGSMT is already set up, remove it"
    crontab -l | grep -v 'StartSGSMT.sh' | crontab -
  else
    echo "No cron job running"
  fi
}

StopApplication(){
  # Use Jar name in the informationFile/SGSMTJarName.txt to stop the app
  if [ -e "$InfoFileFolderPath/SGSMTJarName.txt" ]
  then
    echo "Stop the app with Jar name in the $InfoFileFolderPath/SGSMTJarName.txt"
    # read the Jar name from file
    JarName=$(cat "$InfoFileFolderPath/SGSMTJarName.txt")
    # if the application is not running, do nothing
    if [ ! "$(ps -ef | grep "$JarName" | grep -v grep)" ]
    then
      echo "The app is not running"
      return
    fi

    # get the PID of the app by the process name
    App_PID=$(ps -ef | grep "$JarName" | grep -v grep | awk '{print $2}')
    echo "The PID of $JarName is $App_PID"
    echo "Run kill -9 $App_PID"
    kill -9 "$App_PID"
    # sleep 5 seconds
    sleep 2
    # Check if the PID is still running
    if [ "$(ps -p $App_PID | grep java)" ]
    then
      echo "The app is still running"
    else
      echo "The app is stopped"
      # Remove the PID file
      rm "$InfoFileFolderPath/SGSMT.pid"
      rm "$InfoFileFolderPath/SGSMTJarName.txt"
    fi
  else
    echo "No SGSMTJarName.txt file found"
  fi
}

RemoveLogRotationWhenSudo() {
  # Remove the log rotation setup for SGSMT log file
  if [ -e "/etc/logrotate.d/SGSMT" ]
  then
    echo "Remove the log rotation setup for SGSMT log file"
    rm /etc/logrotate.d/SGSMT
  else
    echo "No log rotation setup for SGSMT log file"
  fi
}

source ./StartInit.sh
StopCronJob
StopApplication