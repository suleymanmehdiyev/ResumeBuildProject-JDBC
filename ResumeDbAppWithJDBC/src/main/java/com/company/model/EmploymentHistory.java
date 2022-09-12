package com.company.model;

import java.sql.Date;

public class EmploymentHistory {
    private Integer id;
    private String companyName;
    private String position;
    private Date beginDate;
    private Date endDate;
    private String jobDesc;
    private User userId;

    public EmploymentHistory() {
    }

    public EmploymentHistory(Integer id, String companyName, String position, Date beginDate, Date endDate, String jobDesc, User userId) {
        this.id = id;
        this.companyName = companyName;
        this.position = position;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.jobDesc = jobDesc;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "EmploymentHistory{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", position='" + position + '\'' +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", jobDesc='" + jobDesc + '\'' +
                ", userId=" + userId +
                '}';
    }
}
