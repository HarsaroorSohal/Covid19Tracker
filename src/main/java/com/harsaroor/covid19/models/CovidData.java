package com.harsaroor.covid19.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CovidData {

    @Id
    private String country;
    private int latestTotalCases;
    private int newCases;

    public CovidData(){

    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestTotalCases() {
        return latestTotalCases;
    }

    public void setLatestTotalCases(int latestTotalCases) {
        this.latestTotalCases = latestTotalCases;
    }

    public int getNewCases() {
        return newCases;
    }

    public void setNewCases(int newCases) {
        this.newCases = newCases;
    }

    @Override
    public String toString() {
        return "CovidData{" +
                "country='" + country + '\'' +
                ", latestTotalCases=" + latestTotalCases +
                ", newCases=" + newCases +
                '}';
    }
}
