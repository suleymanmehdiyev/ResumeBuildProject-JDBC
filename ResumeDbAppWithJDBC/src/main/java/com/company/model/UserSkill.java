package com.company.model;

public class UserSkill {
    private Integer id;
    private User user;
    private Skill skill;

    public UserSkill() {
    }

    public UserSkill(Integer id, User user, Skill skill) {
        this.id = id;
        this.user = user;
        this.skill = skill;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "UserSkill{" +
                "id=" + id +
                ", skill=" + skill +
                '}';
    }
}
