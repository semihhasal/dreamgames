package com.example.dreamgames.service;

import com.example.dreamgames.dto.CountryScoreDTO;
import com.example.dreamgames.entity.TournamentEntity;
import com.example.dreamgames.entity.UserEntity;
import com.example.dreamgames.entity.UserTournamentEntity;
import com.example.dreamgames.repository.TournamentRepository;
import com.example.dreamgames.repository.UserRepository;
import com.example.dreamgames.repository.UserTournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTournamentRepository userTournamentRepository;

    private final String[] countries = {"Turkey", "United States", "United Kingdom", "France", "Germany"};

    public TournamentEntity enterTournament(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        if (user.getLevel() < 20 || user.getCoins() < 1000) {
            throw new RuntimeException("User does not meet the requirements to enter the tournament.");
        }

        user.setCoins(user.getCoins() - 1000);
        userRepository.save(user);

        UserTournamentEntity existingRegistration = userTournamentRepository.findByUserId(userId).orElse(null);
        if (existingRegistration != null) {
            return tournamentRepository.findById(existingRegistration.getTournament().getId()).orElse(null);
        }

        TournamentEntity tournament = findOrCreateTournament();
        UserTournamentEntity newUserTournament = new UserTournamentEntity();
        newUserTournament.setUser(user);
        newUserTournament.setTournament(tournament);
        userTournamentRepository.save(newUserTournament);

        return tournament;
    }

    private TournamentEntity findOrCreateTournament() {
        List<TournamentEntity> allTournaments = tournamentRepository.findAll();

        TournamentSearch:
        for (TournamentEntity tournament : allTournaments) {
            List<UserTournamentEntity> participants = userTournamentRepository.findByTournamentId(tournament.getId());
            if (participants.size() < 5) {
                Set<String> participantCountries = new HashSet<>();
                for (UserTournamentEntity participant : participants) {
                    participantCountries.add(participant.getUser().getCountry());
                }

                for (String country : countries) {
                    if (participantCountries.contains(country) && participantCountries.size() == participants.size()) {
                        continue TournamentSearch;
                    }
                }

                return tournament;
            }
        }

        return createNewTournament();
    }

    private TournamentEntity createNewTournament() {
        TournamentEntity tournament = new TournamentEntity();
        LocalDateTime startTime = LocalDateTime.now().withSecond(0).withNano(0);
        LocalTime endTime = LocalTime.of(20, 0);
        LocalDateTime endDate = LocalDateTime.of(startTime.toLocalDate(), endTime).plusDays(1);

        tournament.setStartTime(startTime.toString());
        tournament.setEndTime(endDate.toString());
        return tournamentRepository.save(tournament);
    }

    public UserEntity claimReward(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        // Kullanıcının mevcut coin miktarına 5000 ekleyin
        user.setCoins(user.getCoins() + 5000);

        // Kullanıcıyı güncelle
        userRepository.save(user);

        return user;
    }

    private int calculateUserRankInTournament(Long userId) {
        // Kullanıcının turnuva içindeki sıralamasını hesapla
        // Bu metod, gerçekte nasıl bir sıralama mantığı kullanıyorsanız o şekilde implemente edilmelidir
        return 1; // Örnek olarak 1. sıra döndürüldü
    }

    private int getRewardBasedOnRank(int rank) {
        // Sıralamaya göre ödülü döndür
        if(rank == 1) {
            return 10000; // 1. sıraya 10000 altın
        } else if(rank == 2) {
            return 5000; // 2. sıraya 5000 altın
        }
        // Diğer sıralamalar için ödüller...
        return 0;
    }

    public int getUserRankInTournament(Long userId, Long tournamentId) {
        List<UserTournamentEntity> rankings = userTournamentRepository.findByTournamentIdOrderByScoreDesc(tournamentId);

        int rank = 1; // Sıralama 1'den başlar
        for (UserTournamentEntity ranking : rankings) {
            if (ranking.getUser().getId().equals(userId)) {
                return rank; // Kullanıcının sırasını döndür
            }
            rank++; // Bir sonraki sıraya geç
        }

        return 0; // Kullanıcı bu turnuvada bulunmuyorsa 0 döndür
    }

    private int calculateRank(Long userId, Long tournamentId) {
        // Bu metod, UserTournamentEntity üzerinde bir sorgu çalıştırarak kullanıcının sıralamasını hesaplar
        // Örnek olarak basit bir sıralama döndürüldü
        return 1;
    }

    public List<UserTournamentEntity> getGroupLeaderboard(Long tournamentId) {
        return userTournamentRepository.findByTournamentIdOrderByScoreDesc(tournamentId);
    }

    public List<CountryScoreDTO> getCountryLeaderboard() {
        // userRepository ya da userTournamentRepository'de tanımlı bir sorgu ile ülkelerin toplam skorlarını getir
        return userTournamentRepository.calculateScoresByCountry();
    }



    // Lider tablosu sorguları için metodlar eklenebilir
}


