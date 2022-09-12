package com.company.model;

public class ContactDetail {
    private Integer id;
    private String phoneNumber;
    private String actualAddress;
    private City regAddress;
    private User userId;

    public ContactDetail() {
    }

    public ContactDetail(User userId) {
        this.userId = userId;
    }

    public ContactDetail(Integer id, String phoneNumber, String actualAddress, City regAddress, User userId) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.actualAddress = actualAddress;
        this.regAddress = regAddress;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getActualAddress() {
        return actualAddress;
    }

    public void setActualAddress(String actual_address) {
        this.actualAddress = actual_address;
    }

    public City getRegAddress() {
        return regAddress;
    }

    public void setRegAddress(City regAddress) {
        this.regAddress = regAddress;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ContactDetail{" +
                "id=" + id +
                ", phone_number='" + phoneNumber + '\'' +
                ", actualAddress='" + actualAddress + '\'' +
                ", reg_address=" + regAddress +
                '}';
    }
}
