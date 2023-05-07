package cat.frank.SimpleGameServerManagementTool.background_ipCheck;

import jakarta.persistence.*;

import java.util.Date;

import static cat.frank.SimpleGameServerManagementTool.utility.StaticVariables.Ip_Check_Job_Model_Table_Name;

@Entity
@Table(name = Ip_Check_Job_Model_Table_Name)
public class IpCheckJobModel {
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

    public IpCheckJobModel(String jobName, String jobGroupName, Long jobID, Long repeatIntervalHour) {
        this.jobName = jobName;
        this.jobGroup = jobGroupName;
        this.jobID = jobID;
        this.repeatIntervalHour = repeatIntervalHour;
        isRunning = false;
        Date currDate = new Date();
        createTime = currDate;
    }

    public IpCheckJobModel() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
