package com.example.dreamgames.dto;

public class CountryScoreDTO {
    private String country;
    private long totalScore;

    public CountryScoreDTO(String country, long totalScore) {
        this.country = country;
        this.totalScore = totalScore;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(long totalScore) {
        this.totalScore = totalScore;
    }

    public CountryScoreDTO() {
    }

}

