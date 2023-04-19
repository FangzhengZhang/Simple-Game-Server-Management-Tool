#!/bin/bash
# Setup file paths
cd ..
export SGSMT_Home="$(pwd)"

export ScriptFolderPath="$SGSMT_Home/scripts"
export InfoFileFolderPath="$SGSMT_Home/informationFile"

#export GetIpScriptPath="$ScriptFolderPath/GetIp.sh"
#export IpInfoFilePath="$InfoFileFolderPath/IpInfo.txt"
#export ImportantInfoFilePath="$InfoFileFolderPath/ImportantInfoFile.txt"
#export SGSMT_Host_Ip="Place Holder For reading"

# Give the exe right to other scripts
#if [ -e "$GetIpScriptPath" ]
#then
#  chmod +x "$GetIpScriptPath"
#fi
#
## Run the init setup file
#echo -n "" > "$ImportantInfoFilePath"
#source "$GetIpScriptPath"

echo "End of the StartInit.sh"