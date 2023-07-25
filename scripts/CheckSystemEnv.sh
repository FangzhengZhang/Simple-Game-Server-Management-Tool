# This bush script is used to check the system environment

#Check if internet is connected by pinging google.com
if ping -q -c 1 -W 1 google.com >/dev/null; then
    echo "Internet is connected."
else
    echo "Internet is not connected! Please connect to the internet"
    exit 1
fi

#Check if curl is installed, if is not installed, the script will print the command to install curl
if ! curl --version 2>&1 | grep -q "curl"; then
    echo "curl is installed."
else
    echo "curl is not found! Please install curl"
    echo "sudo apt install curl"
    exit 1
fi

# Check if the java 17 environment is installed by checking the java version with java -version
# If the java 17 environment is not installed, the script will print the command to install java 17 environment
if ! java -version 2>&1 | grep -q "17\."; then
    echo "Java 17 is installed."
else
    echo "Java 17 is not found! Please install java 17 environment"
    echo "sudo apt install openjdk-17-jdk"
    exit 1
fi

# Check if MySQL is installed by checking the mysql version with mysql --version
# If MySQL is not installed, the script will print the command to install MySQL
if ! mysql --version 2>&1 | grep -q "Distrib"; then
    echo "MySQL is installed."
else
    echo "MySQL is not found! Please install MySQL"
    echo "sudo apt install mysql-server"
    echo "After install MySQL, please run the following command inside of MySQL to set up the MySQL password"
    echo "ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'root';"
    exit 1
fi