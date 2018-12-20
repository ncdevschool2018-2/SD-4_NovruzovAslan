package com.netcracker.edu.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "userinfo")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "dateofbirth")
    private Date dateOfBirth;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    public UserInfo(String name, Date dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
//        this.user = user;
    }

    public UserInfo() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserInfo)) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(id, userInfo.id) &&
                Objects.equals(name, userInfo.name) &&
                Objects.equals(dateOfBirth, userInfo.dateOfBirth) &&
                Objects.equals(user, userInfo.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateOfBirth, user);
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
//                ", user=" + user +
                '}';
    }

}
