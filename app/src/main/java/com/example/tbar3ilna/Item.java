package com.example.tbar3ilna;

public class Item {

    String BLOODGRP, NAME, DESCRIPTION, ID;

    public Item(String BLOODGRP, String NAME, String DESCRIPTION, String ID) {
        this.BLOODGRP = BLOODGRP;
        this.NAME = NAME;
        this.DESCRIPTION = DESCRIPTION;
        this.ID = ID;
    }

    public String getBLOODGRP() {
        return BLOODGRP;
    }

    public void setBLOODGRP(String BLOODGRP) {
        this.BLOODGRP = BLOODGRP;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
