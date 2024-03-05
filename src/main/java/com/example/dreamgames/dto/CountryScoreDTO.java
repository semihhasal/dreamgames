package com.example.dreamgames.dto;

public class CountryScoreDTO {
    private String country;
    private int totalScore;

    public CountryScoreDTO(String country, int totalScore) {
        this.country = country;
        this.totalScore = totalScore;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public CountryScoreDTO() {
    }

}

