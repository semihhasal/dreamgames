package com.example.dreamgames.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tournament")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TournamentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Kullanıcı ID'si
    @Column(name = "user_id")
    private Long userId;

    // Turnuva başlangıç ve bitiş saatleri UTC olarak saklanabilir
    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;
}