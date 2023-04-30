package cat.frank.SimpleGameServerManagementTool.logManagement;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class LogManagementJobModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String jobName;
    private Long jobID;
    private String jobGroup;
    private Boolean isRunning;
    private Date previousFireTime;
    private Long repeatIntervalHour;
    private Date nextFireTime;
    private Date createTime;
    private Date startTime;

    public LogManagementJobModel(String jobName, Long jobID, String jobGroup, Boolean isRunning, Date previousFireTime,
                                 Long repeatIntervalHour, Date nextFireTime, Date createTime, Date startTime) {
        this.jobName = jobName;
        this.jobID = jobID;
        this.jobGroup = jobGroup;
        this.isRunning = isRunning;
        this.previousFireTime = previousFireTime;
        this.repeatIntervalHour = repeatIntervalHour;
        this.nextFireTime = nextFireTime;
        this.createTime = createTime;
        this.startTime = startTime;
    }

    public LogManagementJobModel(String jobName, String jobGroup, Long jobID, Long repeatIntervalHour) {
        this.jobName = jobName;
        this.jobID = jobID;
        this.jobGroup = jobGroup;
        this.isRunning = false;
        this.previousFireTime = null;
        this.repeatIntervalHour = repeatIntervalHour;
        this.createTime = new Date();
    }

    public LogManagementJobModel() {

    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Long getJobID() {
        return jobID;
    }

    public void setJobID(Long jobID) {
        this.jobID = jobID;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public Boolean getRunning() {
        return isRunning;
    }

    public void setRunning(Boolean running) {
        isRunning = running;
    }

    public Date getPreviousFireTime() {
        return previousFireTime;
    }

    public void setPreviousFireTime(Date previousFireTime) {
        this.previousFireTime = previousFireTime;
    }

    public Long getRepeatIntervalHour() {
        return repeatIntervalHour;
    }

    public void setRepeatIntervalHour(Long repeatIntervalHour) {
        this.repeatIntervalHour = repeatIntervalHour;
    }

    public Date getNextFireTime() {
        return nextFireTime;
    }

    public void setNextFireTime(Date nextFireTime) {
        this.nextFireTime = nextFireTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
