package com.example.androidproject;

public class UserHelper {

    String name, Regno, desti, SSD, SED;


    public UserHelper(){

    }




    public UserHelper(String name, String regno, String desti, String SSD, String SED) {
        this.name = name;
        Regno = regno;
        this.desti = desti;
        this.SSD = SSD;
        this.SED = SED;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegno() {
        return Regno;
    }

    public void setRegno(String regno) {
        Regno = regno;
    }

    public String getDesti() {
        return desti;
    }

    public void setDesti(String desti) {
        this.desti = desti;
    }

    public String getSSD() {
        return SSD;
    }

    public void setSSD(String SSD) {
        this.SSD = SSD;
    }

    public String getSED() {
        return SED;
    }

    public void setSED(String SED) {
        this.SED = SED;
    }
}
