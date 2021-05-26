package com.example.tbar3ilna;

public class AdminHelper {

    private String fullName, location, bloodGrp, addedBy;
    private int numOfInteractions;

    public AdminHelper() {
    }

    public AdminHelper(String fullName, String location, String bloodGrp, String addedBy, int numOfInteractions) {
        this.fullName = fullName;
        this.location = location;
        this.bloodGrp = bloodGrp;
        this.addedBy = addedBy;
        this.numOfInteractions = numOfInteractions;
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

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public int getNumOfInteractions() {
        return numOfInteractions;
    }

    public void setNumOfInteractions(int numOfInteractions) {
        this.numOfInteractions = numOfInteractions;
    }
}
