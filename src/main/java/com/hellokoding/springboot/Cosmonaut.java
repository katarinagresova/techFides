package com.hellokoding.springboot;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;


public class Cosmonaut {
    private int id;
    private String name;
    private String surname;
    //@NotNull
    //@DateTimeFormat(pattern = "dd/MM/YY")
    private String birthday;
    private String superpower;

    public int getId() {
        return this.id;
    }
    public void setId(int id) { this.id = id; }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() { return this.surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getBirthday() { return this.birthday; }
    public void setBirthday(String birthday) { this.birthday = birthday; }

    public String getSuperpower() { return this.superpower; }
    public void setSuperpower(String superpower) { this.superpower = superpower; }

}
