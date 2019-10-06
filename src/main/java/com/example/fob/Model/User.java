package com.example.fob.Model;

import javax.persistence.Id;
import javax.persistence.UniqueConstraint;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "idcode"})})
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "fname")
    private String fname;

    @Column(name = "lname")
    private String lname;

    @Column(name = "idcode")
    private int idcode;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "bday")
    private Date birthday;

    public String getFirstName() {
        return fname;
    }

    public String getLastName() {
        return lname;
    }

    public String getEmail() {
        return email;
    }

    public int getIdCode() { return idcode; }

    public Date getBirthday() {
        return birthday;
    }

    public void setFname(String firstNameValue) { fname = firstNameValue; }

    public void setLname(String lastNameValue) { lname = lastNameValue; }

    public void setIdCode(int idCodeValue) { idcode = idCodeValue; }

    public void setEmail(String emailValue) { email = emailValue; }

    public void setPassword(String passwordValue) { password = passwordValue; }

    public void setBirthday(Date bdayValue) { birthday = bdayValue; }
}
