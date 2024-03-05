package com.example.dreamgames.entity;

import com.example.dreamgames.dto.CountryScoreDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "user_tournament") // Tablo ismi eksikti, eklendi.
public class UserTournamentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "tournament_id", referencedColumnName = "id")
    private TournamentEntity tournament;

    private int score; // User's score in the tournament

    // Constructors, getters, and setters
    public UserTournamentEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public TournamentEntity getTournament() {
        return tournament;
    }

    public void setTournament(TournamentEntity tournament) {
        this.tournament = tournament;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


// Existing getters and setters are correct, no change needed here.
}
