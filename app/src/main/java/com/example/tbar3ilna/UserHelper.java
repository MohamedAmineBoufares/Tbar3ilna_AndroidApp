package com.example.tbar3ilna;

public class UserHelper {

    private String fullName, birthDate, cinNum, bloodGrp;

    public UserHelper() {
    }

    public UserHelper(String fullName, String birthDate, String cinNum, String bloodGrp) {

        this.fullName = fullName;
        this.birthDate = birthDate;
        this.cinNum = cinNum;
        this.bloodGrp = bloodGrp;

    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCinNum() {
        return cinNum;
    }

    public void setCinNum(String cinNum) {
        this.cinNum = cinNum;
    }

    public String getBloodGrp() {
        return bloodGrp;
    }

    public void setBloodGrp(String bloodGrp) {
        this.bloodGrp = bloodGrp;
    }
}
