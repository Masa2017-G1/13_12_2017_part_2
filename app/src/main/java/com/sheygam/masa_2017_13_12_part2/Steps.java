package com.sheygam.masa_2017_13_12_part2;

import java.util.ArrayList;

/**
 * Created by gregorysheygam on 13/12/2017.
 */

public class Steps {
    private String lang;
    private ArrayList<Step> steps;

    public Steps() {
    }

    public Steps(String lang, ArrayList<Step> steps) {
        this.lang = lang;
        this.steps = steps;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {
        String str = "\nlang = " + lang + " steps: ";
        for (Step step : steps){
            str += step.toString();
        }
        return str;
    }
}
