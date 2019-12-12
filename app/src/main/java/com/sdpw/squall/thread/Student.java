package com.sdpw.squall.thread;

public class Student {
    public Student(int imageID, String name, int age, boolean sex, int grade_CN, int grade_LG, int grade_EN) {
        this.imageID = imageID;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.grade_CN = grade_CN;
        this.grade_LG = grade_LG;
        this.grade_EN = grade_EN;
    }

    public Student() {
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getGrade_CN() {
        return grade_CN;
    }

    public void setGrade_CN(int grade_CN) {
        this.grade_CN = grade_CN;
    }

    public int getGrade_LG() {
        return grade_LG;
    }

    public void setGrade_LG(int grade_LG) {
        this.grade_LG = grade_LG;
    }

    public int getGrade_EN() {
        return grade_EN;
    }

    public void setGrade_EN(int grade_EN) {
        this.grade_EN = grade_EN;
    }

    private int imageID;
    private String name;
    private int age;
    private boolean sex;
    private int grade_CN;
    private int grade_LG;
    private int grade_EN;

    @Override
    public String toString() {
        return "Student{" +
                "imageID=" + imageID +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", grade_CN=" + grade_CN +
                ", grade_LG=" + grade_LG +
                ", grade_EN=" + grade_EN +
                '}';
    }
}
