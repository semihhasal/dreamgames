package com.example.dreamgames.repository;

import com.example.dreamgames.dto.CountryScoreDTO;
import com.example.dreamgames.entity.UserTournamentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserTournamentRepository extends JpaRepository<UserTournamentEntity, Long> {
    @Query("SELECT ut FROM UserTournamentEntity ut WHERE ut.user.id = :userId ORDER BY ut.score DESC")
    List<UserTournamentEntity> findUserRankingsInTournaments(@Param("userId") Long userId);

    List<UserTournamentEntity> findByTournamentIdOrderByScoreDesc(Long tournamentId);

    Optional<UserTournamentEntity> findByUserId(Long userId);


    List<UserTournamentEntity> findByTournamentId(Long id);

    @Query("SELECT new com.example.dreamgames.dto.CountryScoreDTO(ue.country, SUM(ute.score)) " +
            "FROM UserTournamentEntity ute " +
            "JOIN ute.user ue " +
            "GROUP BY ue.country " +
            "ORDER BY SUM(ute.score) DESC")
    List<CountryScoreDTO> calculateScoresByCountry();



}

