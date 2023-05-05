package cat.frank.SimpleGameServerManagementTool.background_hardwareInfo;

import jakarta.persistence.*;

@Entity
public class SystemInfoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String osName = System.getProperty("os.name");
    private String osVersion = System.getProperty("os.version");
    private String osArch = System.getProperty("os.arch");
    private String javaVersion = System.getProperty("java.version");
    private String pythonVersion = System.getProperty("python.version");
    private String cpuModel;
    private Integer cpuCoreCount;
    private Double cpuFrequency;
    private String memorySize;
    private String diskSize;
    private String diskType;
    private String diskMountPoint;
    private String diskFileSystem;

    public SystemInfoModel() {

    }

    public SystemInfoModel(Long id, String osName, String osVersion, String osArch, String javaVersion,
                           String pythonVersion, String cpuModel, Integer cpuCoreCount, Double cpuFrequency,
                           String memorySize, String diskSize, String diskType, String diskMountPoint,
                           String diskFileSystem) {
        this.id = id;
        this.osName = osName;
        this.osVersion = osVersion;
        this.osArch = osArch;
        this.javaVersion = javaVersion;
        this.pythonVersion = pythonVersion;
        this.cpuModel = cpuModel;
        this.cpuCoreCount = cpuCoreCount;
        this.cpuFrequency = cpuFrequency;
        this.memorySize = memorySize;
        this.diskSize = diskSize;
        this.diskType = diskType;
        this.diskMountPoint = diskMountPoint;
        this.diskFileSystem = diskFileSystem;
    }
    public SystemInfoModel(String osName, String osVersion, String osArch, String javaVersion,
                           String pythonVersion, String cpuModel, Integer cpuCoreCount, Double cpuFrequency,
                           String memorySize, String diskSize, String diskType, String diskMountPoint,
                           String diskFileSystem) {
        this.osName = osName;
        this.osVersion = osVersion;
        this.osArch = osArch;
        this.javaVersion = javaVersion;
        this.pythonVersion = pythonVersion;
        this.cpuModel = cpuModel;
        this.cpuCoreCount = cpuCoreCount;
        this.cpuFrequency = cpuFrequency;
        this.memorySize = memorySize;
        this.diskSize = diskSize;
        this.diskType = diskType;
        this.diskMountPoint = diskMountPoint;
        this.diskFileSystem = diskFileSystem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getOsArch() {
        return osArch;
    }

    public void setOsArch(String osArch) {
        this.osArch = osArch;
    }

    public String getJavaVersion() {
        return javaVersion;
    }

    public void setJavaVersion(String javaVersion) {
        this.javaVersion = javaVersion;
    }

    public String getPythonVersion() {
        return pythonVersion;
    }

    public void setPythonVersion(String pythonVersion) {
        this.pythonVersion = pythonVersion;
    }

    public String getCpuModel() {
        return cpuModel;
    }

    public void setCpuModel(String cpuModel) {
        this.cpuModel = cpuModel;
    }

    public Integer getCpuCoreCount() {
        return cpuCoreCount;
    }

    public void setCpuCoreCount(Integer cpuCoreCount) {
        this.cpuCoreCount = cpuCoreCount;
    }

    public Double getCpuFrequency() {
        return cpuFrequency;
    }

    public void setCpuFrequency(Double cpuFrequency) {
        this.cpuFrequency = cpuFrequency;
    }

    public String getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(String memorySize) {
        this.memorySize = memorySize;
    }

    public String getDiskSize() {
        return diskSize;
    }

    public void setDiskSize(String diskSize) {
        this.diskSize = diskSize;
    }

    public String getDiskType() {
        return diskType;
    }

    public void setDiskType(String diskType) {
        this.diskType = diskType;
    }

    public String getDiskMountPoint() {
        return diskMountPoint;
    }

    public void setDiskMountPoint(String diskMountPoint) {
        this.diskMountPoint = diskMountPoint;
    }

    public String getDiskFileSystem() {
        return diskFileSystem;
    }

    public void setDiskFileSystem(String diskFileSystem) {
        this.diskFileSystem = diskFileSystem;
    }

    @Override
    public String toString() {
        return "SystemInfoModel{" +
                "id=" + id +
                ", osName='" + osName + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", osArch='" + osArch + '\'' +
                ", javaVersion='" + javaVersion + '\'' +
                ", pythonVersion='" + pythonVersion + '\'' +
                ", cpuModel='" + cpuModel + '\'' +
                ", cpuCoreCount=" + cpuCoreCount +
                ", cpuFrequency=" + cpuFrequency +
                ", memorySize='" + memorySize + '\'' +
                ", diskSize='" + diskSize + '\'' +
                ", diskType='" + diskType + '\'' +
                ", diskMountPoint='" + diskMountPoint + '\'' +
                ", diskFileSystem='" + diskFileSystem + '\'' +
                '}';
    }
}
