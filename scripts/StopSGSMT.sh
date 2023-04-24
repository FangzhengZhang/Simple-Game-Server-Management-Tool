#!/bin/bash

# check if the cron job is already set up, if it is, remove it
if [ "$(crontab -l | grep 'StartSGSMT.sh')" ]
then
  echo "The cron job StartSGSMT is already set up, remove it"
  crontab -l | grep -v 'StartSGSMT.sh' | crontab -
else
  echo "No cron job running"
fi

# Use PID in the informationFile/SGSMT.pid to stop the app
if [ -e "$InfoFileFolderPath/SGSMT.pid" ]
then
  echo "Stop the app with PID in the informationFile/SGSMT.pid"
  kill -9 $(cat "$InfoFileFolderPath/SGSMT.pid")
else
  echo "No SGSMT PID file found"
fi


# Remove the log rotation setup for SGSMT log file
if [ -e "/etc/logrotate.d/SGSMT" ]
then
  echo "Remove the log rotation setup for SGSMT log file"
  rm /etc/logrotate.d/SGSMT
else
  echo "No log rotation setup for SGSMT log file"
fi