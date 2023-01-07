package com.example.indusbm;

public class ElementClass {
    private String name, description;
    private String temperature, vibration , frenquence, debit, puissance, uploader;
    private String temperatureRef, vibrationRef , frenquenceRef, debitRef, puissanceRef;
    private String editertime, editerinfo;
    private String imageUrl;

    public ElementClass() {
        //required
    }

    public ElementClass(String name, String description) {
        this.name = name;
        this.description = description;
    }

    //REFRENCES CONSTRUCTOR

    public ElementClass(String name, String description, String temperatureRef, String vibrationRef, String frenquenceRef, String debitRef, String puissanceRef, String editerinfo) {
        this.name = name;
        this.description = description;
        this.temperatureRef = temperatureRef;
        this.vibrationRef = vibrationRef;
        this.frenquenceRef = frenquenceRef;
        this.debitRef = debitRef;
        this.puissanceRef = puissanceRef;
        this.editerinfo = editerinfo;
    }

    //INFO CONSTRUCTOR
    public ElementClass(String temperature, String vibration, String frenquence, String debit, String puissance, String uploader, String editertime) {
        this.temperature = temperature;
        this.vibration = vibration;
        this.frenquence = frenquence;
        this.debit = debit;
        this.puissance = puissance;
        this.uploader = uploader;
        this.editertime = editertime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public String getEditertime() {
        return editertime;
    }

    public void setEditertime(String editertime) {
        this.editertime = editertime;
    }

    public String getEditerinfo() {
        return editerinfo;
    }

    public void setEditerinfo(String editerinfo) {
        this.editerinfo = editerinfo;
    }

    public String getPuissance() {
        return puissance;
    }

    public void setPuissance(String puissance) {
        this.puissance = puissance;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getVibration() {
        return vibration;
    }

    public void setVibration(String vibration) {
        this.vibration = vibration;
    }

    public String getFrenquence() {
        return frenquence;
    }

    public void setFrenquence(String frenquence) {
        this.frenquence = frenquence;
    }

    public String getDebit() {
        return debit;
    }

    public void setDebit(String debit) {
        this.debit = debit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemperatureRef() {
        return temperatureRef;
    }

    public void setTemperatureRef(String temperatureRef) {
        this.temperatureRef = temperatureRef;
    }

    public String getVibrationRef() {
        return vibrationRef;
    }

    public void setVibrationRef(String vibrationRef) {
        this.vibrationRef = vibrationRef;
    }

    public String getFrenquenceRef() {
        return frenquenceRef;
    }

    public void setFrenquenceRef(String frenquenceRef) {
        this.frenquenceRef = frenquenceRef;
    }

    public String getDebitRef() {
        return debitRef;
    }

    public void setDebitRef(String debitRef) {
        this.debitRef = debitRef;
    }

    public String getPuissanceRef() {
        return puissanceRef;
    }

    public void setPuissanceRef(String puissanceRef) {
        this.puissanceRef = puissanceRef;
    }
}
