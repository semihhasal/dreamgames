package com.example.dreamgames.repository;

import com.example.dreamgames.entity.TournamentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRepository extends JpaRepository<TournamentEntity, Long> {
    // Gerekirse özel sorgularınızı burada tanımlayabilirsiniz.
}


