package com.company.model;

import java.sql.Date;

public class Education {
    private Integer id;
    private String degree;
    private String speciality;
    private Date beginDate;
    private Date endDate;
    private University universityId;
    private User userId;

    public Education() {
    }

    public Education(Integer id, String degree, String speciality, Date beginDate, Date endDate, University universityId, User userId) {
        this.id = id;
        this.degree = degree;
        this.speciality = speciality;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.universityId = universityId;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
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

    public University getUniversityId() {
        return universityId;
    }

    public void setUniversityId(University universityId) {
        this.universityId = universityId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Education{" +
                "id=" + id +
                ", degree='" + degree + '\'' +
                ", speciality='" + speciality + '\'' +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", universityId=" + universityId +
                '}';
    }
}
