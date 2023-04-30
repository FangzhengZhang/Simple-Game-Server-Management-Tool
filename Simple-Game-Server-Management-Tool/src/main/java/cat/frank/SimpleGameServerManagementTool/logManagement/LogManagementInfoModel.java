package cat.frank.SimpleGameServerManagementTool.logManagement;

import jakarta.persistence.*;

@Entity
public class LogManagementInfoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String logFilePath;
    private Long fileMaxSizeInMB;
    private Integer fileMaxCount;


    public LogManagementInfoModel(Long id, String logFilePath, Long fileMaxSizeInMB, Integer fileMaxCount) {
        this.id = id;
        this.logFilePath = logFilePath;
        this.fileMaxSizeInMB = fileMaxSizeInMB;
        this.fileMaxCount = fileMaxCount;
    }

    public LogManagementInfoModel(String logFilePath, Long fileMaxSizeInMB, Integer fileMaxCount) {
        this.logFilePath = logFilePath;
        this.fileMaxSizeInMB = fileMaxSizeInMB;
        this.fileMaxCount = fileMaxCount;
    }

    public LogManagementInfoModel() {

    }

    public String getLogFilePath() {
        return logFilePath;
    }

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public Long getFileMaxSizeInMB() {
        return fileMaxSizeInMB;
    }

    public void setFileMaxSizeInMB(Long fileMaxSizeInMB) {
        this.fileMaxSizeInMB = fileMaxSizeInMB;
    }

    public Integer getFileMaxCount() {
        return fileMaxCount;
    }

    public void setFileMaxCount(Integer fileMaxCount) {
        this.fileMaxCount = fileMaxCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LogManagementInfoModel{" +
                "id=" + id +
                ", logFilePath='" + logFilePath + '\'' +
                ", fileMaxSizeInMB=" + fileMaxSizeInMB +
                ", fileMaxCount=" + fileMaxCount +
                '}';
    }
}
