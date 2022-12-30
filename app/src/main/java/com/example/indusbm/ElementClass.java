package com.example.indusbm;

public class ElementClass {
    private String name, description, etat;
    private float temperature, vibration , frenquence, debit, puissance;
    private String editertime, editerinfo;

    public ElementClass(String name, String description) {
        this.name = name;
        this.description = description;
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

    public float getPuissance() {
        return puissance;
    }

    public void setPuissance(float puissance) {
        this.puissance = puissance;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getVibration() {
        return vibration;
    }

    public void setVibration(float vibration) {
        this.vibration = vibration;
    }

    public float getFrenquence() {
        return frenquence;
    }

    public void setFrenquence(float frenquence) {
        this.frenquence = frenquence;
    }

    public float getDebit() {
        return debit;
    }

    public void setDebit(float debit) {
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
}
