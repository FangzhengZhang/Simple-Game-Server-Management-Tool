package cat.frank.SimpleGameServerManagementTool.sgsmtConfig;

import jakarta.persistence.*;

import static cat.frank.SimpleGameServerManagementTool.utility.StaticVariables.Important_Data_Model_Table_Name;

@Entity
@Table(name = Important_Data_Model_Table_Name)
public class ImportantDataModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String appRootPath;
    private String scriptsPath;
    private String infoFilePath;
    private String logFolderPath;
    private String serverIp;
    private Boolean isEmailEnabled;
    private String emailReceiver;
    private String emailSender;


    // constructors
    public ImportantDataModel() {
    }

    public ImportantDataModel(String appRootPath, String scriptsPath, String infoFilePath,
                              String logFolderPath,
                              String emailReceiver, Boolean isEmailEnabled, String serverIp,
                              String emailSender) {
        this.appRootPath = appRootPath;
        this.scriptsPath = scriptsPath;
        this.infoFilePath = infoFilePath;
        this.logFolderPath = logFolderPath;
        this.serverIp = serverIp;
        this.emailReceiver = emailReceiver;
        this.isEmailEnabled = isEmailEnabled;
        this.emailSender = emailSender;
    }



    // getters and setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public String getAppRootPath() {
        return this.appRootPath;
    }

    public void setAppRootPath(String appRootPath) {
        this.appRootPath = appRootPath;
    }

    public String getScriptsPath() {
        return this.scriptsPath;
    }

    public void setScriptsPath(String scriptsPath) {
        this.scriptsPath = scriptsPath;
    }

    public String getInfoFilePath() {
        return this.infoFilePath;
    }

    public void setInfoFilePath(String infoFilePath) {
        this.infoFilePath = infoFilePath;
    }

    public String getServerIp() {
        return this.serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getEmailReceiver() {
        return this.emailReceiver;
    }
    public void setEmailReceiver(String emailReceiver) {
        this.emailReceiver = emailReceiver;
    }

    public Boolean getEmailEnabled() {
        return isEmailEnabled;
    }

    public void setEmailEnabled(Boolean emailEnabled) {
        isEmailEnabled = emailEnabled;
    }

    public String getEmailSender() {
        return emailSender;
    }

    public void setEmailSender(String emailSender) {
        this.emailSender = emailSender;
    }

    public String getLogFolderPath() {
        return this.logFolderPath;
    }
    public void setLogFolderPath(String logFolderPath) {
        this.logFolderPath = logFolderPath;
    }

    @Override
    public String toString() {
        return "ImportantDataModel{" +
                "id=" + id +
                ", appRootPath='" + appRootPath + '\'' +
                ", scriptsPath='" + scriptsPath + '\'' +
                ", infoFilePath='" + infoFilePath + '\'' +
                ", logFolderPath='" + logFolderPath + '\'' +
                ", serverIp='" + serverIp + '\'' +
                ", isEmailEnabled=" + isEmailEnabled +
                ", emailReceiver='" + emailReceiver + '\'' +
                ", emailSender='" + emailSender + '\'' +
                '}';
    }
}
