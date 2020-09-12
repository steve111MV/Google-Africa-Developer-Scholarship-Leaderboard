package cg.stevendende.gadsleaderboard.models;

import androidx.annotation.Keep;

@Keep
public class HoursTopLearner {
    public String name;
    public String country;
    public int hours;
    public String badgeUrl;

    public HoursTopLearner(String name, String country, int hours, String badgeUrl) {
        this.name = name;
        this.country = country;
        this.hours = hours;
        this.badgeUrl = badgeUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }
}
