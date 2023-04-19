package cat.frank.SimpleGameServerManagementTool.config;

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
    private String serverIp;
    private Boolean isEmailEnabled;
    private String emailReceiver;
    
    // constructors
    public ImportantDataModel() {
    }

    public ImportantDataModel(String appRootPath, String scriptsPath, String infoFilePath,
                              String emailReceiver, Boolean isEmailEnabled, String serverIp) {
        this.appRootPath = appRootPath;
        this.scriptsPath = scriptsPath;
        this.infoFilePath = infoFilePath;
        this.serverIp = serverIp;
        this.emailReceiver = emailReceiver;
        this.isEmailEnabled = isEmailEnabled;
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

    @Override
    public String toString() {
        return "ImportantDataModel{" +
                "id=" + id +
                ", appRootPath='" + appRootPath + '\'' +
                ", scriptsPath='" + scriptsPath + '\'' +
                ", infoFilePath='" + infoFilePath + '\'' +
                ", serverIp='" + serverIp + '\'' +
                ", isEmailEnabled=" + isEmailEnabled +
                ", emailReceiver='" + emailReceiver + '\'' +
                '}';
    }
}
