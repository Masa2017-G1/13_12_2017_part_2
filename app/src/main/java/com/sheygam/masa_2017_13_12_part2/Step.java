package com.sheygam.masa_2017_13_12_part2;

import java.io.Serializable;

/**
 * Created by gregorysheygam on 13/12/2017.
 */

public class Step implements Serializable{
    private int numberOfStep;
    private String title;
    private String description;
    private String need;

    public Step() {
    }

    public Step(int numberOfStep, String title, String description, String need) {
        this.numberOfStep = numberOfStep;
        this.title = title;
        this.description = description;
        this.need = need;
    }

    public int getNumberOfStep() {
        return numberOfStep;
    }

    public void setNumberOfStep(int numberOfStep) {
        this.numberOfStep = numberOfStep;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNeed() {
        return need;
    }

    public void setNeed(String need) {
        this.need = need;
    }

    @Override
    public String toString() {
        return "\n\tStep{" +
                "numberOfStep=" + numberOfStep +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", need='" + need + '\'' +
                '}';
    }
}
