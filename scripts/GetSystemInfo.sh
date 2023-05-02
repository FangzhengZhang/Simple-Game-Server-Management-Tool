# This bash script is used to get system information including: osName, osVersion, osArch, Java Version, Python Version,
# CPU Model, CPU Core, CPU Frequency, Memory Size, Disk Size, Disk Type, Disk Mount Point, Disk File System, Disk Used.
# The output is in JSON format.
# Usage: bash GetSystemInfo.sh

# Get OS Name
osName=$(cat /etc/*-release | grep -w "NAME" | awk -F "=" '{print $2}' | sed 's/\"//g')
# Get OS Version
osVersion=$(cat /etc/*-release | grep -w "VERSION_ID" | awk -F "=" '{print $2}' | sed 's/\"//g')
# Get OS Architecture
osArch=$(uname -m)
# Get Java Version
javaVersion=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}')
# Get Python Version
pythonVersion=$(python -V 2>&1 | awk '{print $2}')
# Get CPU Model, take only the first line
cpuModel=$(cat /proc/cpuinfo | grep -w "model name" | awk -F ":" '{print $2}' | sed 's/^[ \t]*//g' | head -n 1)
# Get CPU Core,  take only the first line
cpuCore=$(cat /proc/cpuinfo | grep -w "cpu cores" | awk -F ":" '{print $2}' | sed 's/^[ \t]*//g' | head -n 1)
# Get CPU Frequency, take only the first line
cpuFrequency=$(cat /proc/cpuinfo | grep -w "cpu MHz" | awk -F ":" '{print $2}' | sed 's/^[ \t]*//g' | head -n 1)
# Get Memory Size
memorySize=$(cat /proc/meminfo | grep -w "MemTotal" | awk -F ":" '{print $2}' | sed 's/^[ \t]*//g')
# Get Disk Size
diskSize=$(df -h | grep -w "/" | awk '{print $2}')
# Get Disk Type
diskType=$(df -h | grep -w "/" | awk '{print $1}')
# Get Disk Mount Point
diskMountPoint=$(df -h | grep -w "/" | awk '{print $6}')
# Get Disk File System
diskFileSystem=$(df -h | grep -w "/" | awk '{print $1}')
# Get Disk Used
#diskUsed=$(df -h | grep -w "/" | awk '{print $5}')

# Output the result in JSON format
echo "{"
echo "  \"osName\": \"$osName\","
echo "  \"osVersion\": \"$osVersion\","
echo "  \"osArch\": \"$osArch\","
echo "  \"javaVersion\": \"$javaVersion\","
echo "  \"pythonVersion\": \"$pythonVersion\","
echo "  \"cpuModel\": \"$cpuModel\","
echo "  \"cpuCoreCount\": \"$cpuCore\","
echo "  \"cpuFrequency\": \"$cpuFrequency\","
echo "  \"memorySize\": \"$memorySize\","
echo "  \"diskSize\": \"$diskSize\","
echo "  \"diskType\": \"$diskType\","
echo "  \"diskMountPoint\": \"$diskMountPoint\","
echo "  \"diskFileSystem\": \"$diskFileSystem\""
#echo "  \"diskUsed\": \"$diskUsed\""
echo "}"
