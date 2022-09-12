package com.company.model;


import java.sql.Date;
import java.util.List;

public class User {
    private Integer id;
    private String uname;
    private String surname;
    private String patronymic;
    private Date birthdate;
    private String gender;
    private String maritalStatus;
    private String citizenship;
    private String military;
    private String about;
    private String email;
    private String password;
    private ContactDetail contactDetail;
    private List<UserSkill> skills;
    private List<EmploymentHistory> employmentHistory;
    private List<Education> education;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String uname, String surname, String email, String password) {
        this.id = id;
        this.uname = uname;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public User(Integer id, String uname, String surname, String patronymic, Date birthdate, String gender, String maritalStatus, String citizenship, String military, String about) {
        this.id = id;
        this.uname = uname;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthdate = birthdate;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.citizenship = citizenship;
        this.military = military;
        this.about = about;
    }

    public User(Integer id, String uname, String surname, String patronymic, Date birthdate, String gender, String maritalStatus, String citizenship, String military, String about, String email, String password) {
        this.id = id;
        this.uname = uname;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthdate = birthdate;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.citizenship = citizenship;
        this.military = military;
        this.about = about;
        this.email = email;
        this.password = password;

    }

    public User(Integer id, String uname, String surname, String patronymic, Date birthdate, String gender, String maritalStatus, String citizenship, String military, String about,String email,String password, ContactDetail cd, List<UserSkill> skills, List<EmploymentHistory> employmentHistory, List<Education> education) {
        this(id,uname,surname,patronymic,birthdate,gender,maritalStatus,citizenship,military,about,email,password);
        this.skills = skills;
        this.contactDetail = cd;
        this.employmentHistory = employmentHistory;
        this.education = education;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getMilitary() {
        return military;
    }

    public void setMilitary(String military) {
        this.military = military;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ContactDetail getContactDetail() {
        return contactDetail;
    }

    public void setContactDetail(ContactDetail contactDetail) {
        this.contactDetail = contactDetail;
    }

    public List<UserSkill> getSkills() {
        return skills;
    }

    public void setSkills(List<UserSkill> skills) {
        this.skills = skills;
    }

    public List<EmploymentHistory> getEmploymentHistory() {
        return employmentHistory;
    }

    public void setEmploymentHistory(List<EmploymentHistory> employmentHistory) {
        this.employmentHistory = employmentHistory;
    }

    public List<Education> getEducation() {
        return education;
    }

    public void setEducation(List<Education> education) {
        this.education = education;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthdate=" + birthdate +
                ", gender='" + gender + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", citizenship='" + citizenship + '\'' +
                ", military='" + military + '\'' +
                ", about='" + about + '\'' +
                ", email='" + email + '\'' +
                ", contactDetail=" + contactDetail +
                ", skills=" + skills +
                ", employmentHistory=" + employmentHistory +
                ", education=" + education +
                '}';
    }
}
