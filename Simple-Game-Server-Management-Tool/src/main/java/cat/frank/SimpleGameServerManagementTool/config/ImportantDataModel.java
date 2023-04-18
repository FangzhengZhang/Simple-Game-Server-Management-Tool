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
    
    // constructors
    public ImportantDataModel() {
    }

    public ImportantDataModel(String appRootPath, String scriptsPath, String infoFilePath, String serverIp) {
        this.appRootPath = appRootPath;
        this.scriptsPath = scriptsPath;
        this.infoFilePath = infoFilePath;
        this.serverIp = serverIp;
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

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", appRootPath='" + getAppRootPath() + "'" +
            ", scriptsPath='" + getScriptsPath() + "'" +
            ", infoFilePath='" + getInfoFilePath() + "'" +
            ", serverIp='" + getServerIp() + "'" +
            "}";
    }



}
