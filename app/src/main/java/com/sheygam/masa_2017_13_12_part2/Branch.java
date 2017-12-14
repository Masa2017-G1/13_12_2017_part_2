package com.sheygam.masa_2017_13_12_part2;

import java.util.ArrayList;

/**
 * Created by gregorysheygam on 14/12/2017.
 */

public class Branch {
    private String lang;
    private String category;
    private String name;
    private String placeId;
    private double latitude;
    private double longitude;
    private String url;
    private String[] phones;
    private String[] schedule = new String[7];

    public Branch() {
    }

    public Branch(String lang, String category, String name, String placeId, double latitude, double longitude, String url, String[] phones, String[] schedule) {
        this.lang = lang;
        this.category = category;
        this.name = name;
        this.placeId = placeId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.url = url;
        this.phones = phones;
        this.schedule = schedule;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String[] getPhones() {
        return phones;
    }

    public void setPhones(String[] phones) {
        this.phones = phones;
    }

    public String[] getSchedule() {
        return schedule;
    }

    public void setSchedule(String[] schedule) {
        this.schedule = schedule;
    }
}
