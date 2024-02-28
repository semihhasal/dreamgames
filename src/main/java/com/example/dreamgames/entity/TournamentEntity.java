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

    // Turnuva başlangıç ve bitiş saatlerini UTC olarak saklayabilirsiniz.
    @Column(name = "StartTime")
    private String startTime;
    private String endTime;



}