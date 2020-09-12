package cg.stevendende.gadsleaderboard.models;

import androidx.annotation.Keep;

@Keep
public class ScoreLeader {
    public String name;
    public int score;
    public String country;
    public String badgeUrl;

    public ScoreLeader(String name, String country, int score, String badgeUrl) {
        this.name = name;
        this.country = country;
        this.score = score;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }
}
