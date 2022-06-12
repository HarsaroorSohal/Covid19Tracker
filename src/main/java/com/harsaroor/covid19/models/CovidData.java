package com.harsaroor.covid19.models;

public class CovidData {

    private String country;
    private int latestTotalCases;
    private int newCases;

    public CovidData(String country, int latestTotalCases, int newCases) {
        this.country = country;
        this.latestTotalCases = latestTotalCases;
        this.newCases = newCases;
    }

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
