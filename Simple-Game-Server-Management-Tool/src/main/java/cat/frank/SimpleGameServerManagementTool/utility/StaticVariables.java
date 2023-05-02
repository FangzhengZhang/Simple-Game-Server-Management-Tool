package cat.frank.SimpleGameServerManagementTool.utility;

public class StaticVariables {
    // File names
    public static final String Get_IP_Script_Name= "GetIp.sh";
    public static final String Get_System_Info_Script_Name= "GetSystemInfo.sh";

    // Email related
    public static final String IP_Change_Email_Subject= "Server IP Changed";

    //Data table names
    public static final String Important_Data_Model_Table_Name = "config_data";
    public static final String Ip_Check_Job_Model_Table_Name = "ip_check_job";

    // Only Record key
    public static final Long Only_Record_Key = 1L;

    // Job related variables
    public static final String IP_Check_Job_Name_Key = "IP_Check_Job_Name";
    public static final String IP_Check_Job_ID_Key = "IP_Check_Job_ID_Key";
    public static final String Log_Management_Job_Name_Key = "Log_Management_Job_Name_Key";
    public static final String Log_Management_Job_ID_Key = "Log_Management_Job_ID_Key";
    public static final String Log_Folder_Path_Key = "Log_Folder_Path_Key";
    public static final String Log_File_Start_Name = "SGSMT";
    public static final String Log_File_Type = "log";
    public static final String Log_File_Zip_Type = "zip";
    public static final String Log_Size_Limit_Key = "Log_Size_Limit_Key";
    public static final String Log_File_Max_Count_Key = "Log_File_Max_Count_Key";

    public static final Integer Log_Management_Check_Interval_Hour = 12;
}
