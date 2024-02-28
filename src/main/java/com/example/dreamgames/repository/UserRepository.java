package com.example.dreamgames.repository;

import com.example.dreamgames.entity.UserEntity;
import com.example.dreamgames.entity.UserTournamentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // Özel sorgularınızı burada tanımlayabilirsiniz, örneğin:
     List<UserEntity> findByCountry(String country);
    @Query("SELECT ut FROM UserTournamentEntity ut WHERE ut.user.id = :userId AND ut.tournament.id = :tournamentId ORDER BY ut.score DESC")
    List<UserTournamentEntity> findUserRankInTournament(@Param("userId") Long userId, @Param("tournamentId") Long tournamentId);

}

