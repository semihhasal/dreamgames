package com.example.dreamgames.repository;

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
}


