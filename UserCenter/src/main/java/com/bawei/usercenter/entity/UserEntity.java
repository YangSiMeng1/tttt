package com.bawei.usercenter.entity;

public class UserEntity {
    private int id;
    private String phoneNumber;
    private String pwd;
    private int age;
    private String address;

    public UserEntity() {
    }

    public UserEntity(int id, String phoneNumber, String pwd, int age, String address) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.pwd = pwd;
        this.age = age;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", pwd='" + pwd + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
