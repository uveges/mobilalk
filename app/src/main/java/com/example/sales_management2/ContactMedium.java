package com.example.sales_management2;

import java.time.LocalTime;

public class ContactMedium {

    private String mediumType;
    private Boolean preferred;
    private LocalTime validFor;
    private MediumCharacteristic mediumCharacteristic;

    public MediumCharacteristic getMediumCharacteristic() {
        return mediumCharacteristic;
    }

    public void setMediumCharacteristic(MediumCharacteristic mediumCharacteristic) {
        this.mediumCharacteristic = mediumCharacteristic;
    }

    public String getMediumType() {
        return mediumType;
    }

    public void setMediumType(String mediumType) {
        this.mediumType = mediumType;
    }

    public Boolean getPreferred() {
        return preferred;
    }

    public void setPreferred(Boolean preferred) {
        this.preferred = preferred;
    }

    public LocalTime getValidFor() {
        return validFor;
    }

    public void setValidFor(LocalTime validFor) {
        this.validFor = validFor;
    }
}
