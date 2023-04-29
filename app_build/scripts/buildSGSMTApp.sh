#!/bin/bash

# ask for this build version
echo "Please enter the build version: "
read buildVersion

# go to the root folder of the project
cd ..

# Check if the build folder exists, if not, create it
if [ ! -d "app_build" ]
then
  mkdir app_build
fi

# Go to the build folder
cd app_build

# Check if the build folder is empty, if not, delete all the files
if [ "$(ls -A .)" ]
then
  rm -rf *
fi

# copy the scripts folder to the build folder
cp -r ../scripts .

# go to the app folder and build the app
cd ../Simple-Game-Server-Management-Tool

# build the app with gradlew build
./gradlew build

# copy the jar file to the build folder
cp build/libs/Simple-Game-Server-Management-Tool-0.0.1-SNAPSHOT.jar ../app_build

# copy application.properties to the build folder
cp src/main/resources/application.properties ../app_build

# Change the name of the jar file
mv ../app_build/Simple-Game-Server-Management-Tool-0.0.1-SNAPSHOT.jar ../app_build/SGSMT-$buildVersion.jar

# go to the build folder
cd ../app_build

# check the application.properties file, if there is a sgsmt.root.path, replace it with ${SGSMT_Home}/
sed -i 's/sgsmt.root.path=.*/sgsmt.root.path=${SGSMT_Home}\//' application.properties

