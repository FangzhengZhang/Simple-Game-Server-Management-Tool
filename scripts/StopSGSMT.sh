#!/bin/bash

# check if the cron job is already set up, if it is, remove it
if [ "$(crontab -l | grep 'StartSGSMT.sh')" ]
then
  echo "The cron job StartSGSMT is already set up, remove it"
  crontab -l | grep -v 'StartSGSMT.sh' | crontab -
else
  echo "No cron job running"
fi

# Stop the app if it is running by check the process name
# and kill the process
if [ "$(ps -ef | grep 'java -jar SGSMT-*.jar' | grep -v grep)" ]
then
  echo "The app is running, stop it"
  ps -ef | grep 'java -jar SGSMT-*.jar' | grep -v grep | awk '{print $2}' | xargs kill -9
else
  echo "The app is not running"
fi

# Remove the log rotation setup for SGSMT log file
if [ -e "/etc/logrotate.d/SGSMT" ]
then
  echo "Remove the log rotation setup for SGSMT log file"
  rm /etc/logrotate.d/SGSMT
else
  echo "No log rotation setup for SGSMT log file"
fi