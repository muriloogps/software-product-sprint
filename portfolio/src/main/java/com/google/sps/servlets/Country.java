package com.google.sps.servlets;

class Country {
    private final String countryName;
    private final String countryCapital;

    public Country(String countryName, String countryCapital) {
        this.countryName = countryName;
        this.countryCapital = countryCapital;
    }

    public String toString() {
        return countryName;
    }

}