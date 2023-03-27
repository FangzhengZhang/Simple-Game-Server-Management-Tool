#!/bin/bash

# Use curl command to get public IP address
public_ip=$(curl -s http://whatismyip.akamai.com/)

# Print the public IP address to the console
echo "$public_ip" > "$IpInfoFilePath"
echo "Host_ip:$public_ip" >> "$ImportantInfoFilePath"
export SGSMT_Host_Ip="$public_ip"