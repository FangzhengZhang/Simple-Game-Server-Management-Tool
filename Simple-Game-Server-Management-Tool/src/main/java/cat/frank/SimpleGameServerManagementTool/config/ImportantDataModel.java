package cat.frank.SimpleGameServerManagementTool.config;

import org.springframework.cache.annotation.EnableCaching;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@EnableCaching
@Entity
@Table(name = "config_data")
public class ImportantDataModel {
        @Id
        @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
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

    public ImportantDataModel(Long id, String appRootPath, String scriptsPath, String infoFilePath, String serverIp) {
        this.id = id;
        this.appRootPath = appRootPath;
        this.scriptsPath = scriptsPath;
        this.infoFilePath = infoFilePath;
        this.serverIp = serverIp;
    }

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

    public ImportantDataModel id(Long id) {
        setId(id);
        return this;
    }

    public ImportantDataModel appRootPath(String appRootPath) {
        setAppRootPath(appRootPath);
        return this;
    }

    public ImportantDataModel scriptsPath(String scriptsPath) {
        setScriptsPath(scriptsPath);
        return this;
    }

    public ImportantDataModel infoFilePath(String infoFilePath) {
        setInfoFilePath(infoFilePath);
        return this;
    }

    public ImportantDataModel serverIp(String serverIp) {
        setServerIp(serverIp);
        return this;
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
