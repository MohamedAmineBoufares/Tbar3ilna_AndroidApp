package com.example.tbar3ilna;

public class AdminHelper {

    private String fullName, location, bloodGrp;

    public AdminHelper() {
    }

    public AdminHelper(String fullName, String location, String bloodGrp) {
        this.fullName = fullName;
        this.location = location;
        this.bloodGrp = bloodGrp;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBloodGrp() {
        return bloodGrp;
    }

    public void setBloodGrp(String bloodGrp) {
        this.bloodGrp = bloodGrp;
    }
}
