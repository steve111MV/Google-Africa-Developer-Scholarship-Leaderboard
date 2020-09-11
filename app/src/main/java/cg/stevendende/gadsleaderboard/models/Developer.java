package cg.stevendende.gadsleaderboard.models;

public class Developer {
    private String name;
    private String country;
    private int learningHours;
    private int skillIQ;

    public Developer(String name, String country, int learningHours, int skillIQ) {
        this.name = name;
        this.country = country;
        this.learningHours = learningHours;
        this.skillIQ = skillIQ;
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

    public int getLearningHours() {
        return learningHours;
    }

    public void setLearningHours(int learningHours) {
        this.learningHours = learningHours;
    }

    public int getSkillIQ() {
        return skillIQ;
    }

    public void setSkillIQ(int skillIQ) {
        this.skillIQ = skillIQ;
    }
}
