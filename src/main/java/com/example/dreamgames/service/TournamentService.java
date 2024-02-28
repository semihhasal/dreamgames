package com.example.dreamgames.service;

import com.example.dreamgames.CountryScoreDTO;
import com.example.dreamgames.entity.TournamentEntity;
import com.example.dreamgames.entity.UserEntity;
import com.example.dreamgames.entity.UserTournamentEntity;
import com.example.dreamgames.repository.TournamentRepository;
import com.example.dreamgames.repository.UserRepository;
import com.example.dreamgames.repository.UserTournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTournamentRepository userTournamentRepository;

    public TournamentEntity enterTournament(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        // Kullanıcı seviye ve altın şartlarını kontrol et
        if (user.getLevel() < 20 || user.getCoins() < 1000) {
            throw new RuntimeException("User does not meet the requirements to enter the tournament.");
        }

        // Kullanıcıdan katılım ücreti al
        user.setCoins(user.getCoins() - 1000);
        userRepository.save(user);

        // Turnuvaya katılım işlemleri...
        // Bu kısımda, turnuva oluşturma veya mevcut bir turnuvaya kullanıcıyı ekleme işlemleri gerçekleştirilir
        // Grup lider tablosunu hesapla veya getir

        // Örnek bir TournamentEntity nesnesi döndürülüyor
        // Gerçek uygulamada bu, turnuva ve grup lider tablosu verileriyle dolu olmalıdır
        return new TournamentEntity();
    }


    public UserEntity claimReward(Long userId) {
        // Kullanıcıyı bul
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        // Kullanıcı sıralamasını hesapla ve ödülü atama
        // Bu örnek bir iş mantığıdır, gerçek uygulamada turnuva detaylarına bağlı olarak değişiklik gösterebilir
        int rank = calculateUserRankInTournament(userId);
        int reward = getRewardBasedOnRank(rank);
        user.setCoins(user.getCoins() + reward);

        // Kullanıcıyı güncelle
        return userRepository.save(user);
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
        // Kullanıcının turnuvadaki skorlarına göre sıralamasını hesaplayan sorgu
        // Bu sorgu UserTournamentEntity üzerinden yapılmalıdır
        return calculateRank(userId, tournamentId);
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
        // Bu metod, UserTournamentEntity üzerinden her ülkenin toplam skorunu hesaplar.
        // Sonuçları CountryScoreDTO listesi olarak döndürür.
        // CountryScoreDTO, ülke ismi ve toplam skoru içeren bir DTO olmalıdır.
        List<CountryScoreDTO> leaderboard = new ArrayList<>();
        // Veritabanı sorgusu ile her ülkenin toplam skorunu hesapla ve leaderboard listesine ekle.
        return leaderboard;
    }




    // Lider tablosu sorguları için metodlar eklenebilir
}


