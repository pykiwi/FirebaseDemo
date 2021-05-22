package com.example.smile;



public class User {
    private String id;
    private String email;
    private String name;
    private String password;
    private int happy, unhappy, normal;

    public User(String email, String name, String password, int happy, int unhappy, int normal) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.happy = happy;
        this.unhappy = unhappy;
        this.normal = normal;
    }
    public User(){

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public int getUnhappy() {
        return unhappy;
    }

    public void setUnhappy(int unhappy) {
        this.unhappy = unhappy;
    }

    public int getHappy() {
        return happy;
    }

    public void setHappy(int happy) {
        this.happy = happy;
    }

    public int getNormal() {
        return normal;
    }

    public void setNormal(int normal) {
        this.normal = normal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", happy=" + happy +
                ", unhappy=" + unhappy +
                ", normal=" + normal +
                '}';
    }
}
