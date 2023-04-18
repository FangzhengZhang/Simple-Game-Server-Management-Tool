#!/bin/bash

# Use curl command to get public IP address
public_ip=$(curl -s http://whatismyip.akamai.com/)

echo "$public_ip"

# Print the public IP address to the console
echo "Host_ip:$public_ip" >> "$ImportantInfoFilePath"
echo "$public_ip" > "$IpInfoFilePath"
export SGSMT_Host_Ip="$public_ip"