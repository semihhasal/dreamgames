package com.example.dreamgames.api;

import com.example.dreamgames.dto.CountryScoreDTO;
import com.example.dreamgames.entity.TournamentEntity;
import com.example.dreamgames.entity.UserEntity;
import com.example.dreamgames.entity.UserTournamentEntity;
import com.example.dreamgames.service.TournamentService;
import com.example.dreamgames.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TournamentService tournamentService; // Bu satırı ekleyin





    @PostMapping("/create")
    public ResponseEntity<UserEntity> createUser(@RequestBody Map<String, String> request) {
        String username = request.get("username"); // İstek gövdesinden kullanıcı adını al
        UserEntity newUser = userService.createUser(username);
        return ResponseEntity.ok(newUser); // HTTP 200 OK yanıtı ile yeni kullanıcıyı döndür
    }


    @PutMapping("/{userId}/level-up")
    public ResponseEntity<?> updateUserLevel(@PathVariable Long userId) {
        try {
            UserEntity updatedUser = userService.updateUserLevel(userId);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            // Burada daha spesifik bir hata yönetimi yapılabilir
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{userId}/enter-tournament")
    public ResponseEntity<?> enterTournament(@PathVariable Long userId) {
        try {
            // Kullanıcının turnuvaya katılım koşullarını kontrol et ve işlemi gerçekleştir
            TournamentEntity tournament = tournamentService.enterTournament(userId);

            // Grup lider tablosunu al (bu örnek için basit bir placeholder kullanılmıştır)
            // Gerçek uygulamada bu veri TournamentService tarafından sağlanacak
            List<CountryScoreDTO> groupLeaderboard = new ArrayList<>();// Bu liste gerçek veri ile doldurulmalı
            groupLeaderboard.add(new CountryScoreDTO("USA", 1000));
            groupLeaderboard.add(new CountryScoreDTO("Germany", 900));
            groupLeaderboard.add(new CountryScoreDTO("France", 800));

            // Grup lider tablosu verilerini döndür
            return ResponseEntity.ok(groupLeaderboard);
        } catch (RuntimeException e) {
            // Hata durumunda uygun hata mesajını döndür
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    // UserController içindeki endpoint
    @PostMapping("/{userId}/claim-reward")
    public ResponseEntity<?> claimReward(@PathVariable Long userId) {
        try {
            UserEntity user = tournamentService.claimReward(userId);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }



    @GetMapping("/{userId}/group-rank")
    public ResponseEntity<?> getGroupRank(@PathVariable Long userId, @RequestParam Long tournamentId) {
        try {
            int rank = tournamentService.getUserRankInTournament(userId, tournamentId);
            return ResponseEntity.ok().body(Collections.singletonMap("rank", rank));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }





    @GetMapping("/group-leaderboard")
    public ResponseEntity<?> getGroupLeaderboard(@RequestParam Long tournamentId) {
        try {
            List<UserTournamentEntity> leaderboard = tournamentService.getGroupLeaderboard(tournamentId);
            return ResponseEntity.ok(leaderboard);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }



    @GetMapping("/country-leaderboard")
    public ResponseEntity<?> getCountryLeaderboard() {
        try {
            List<CountryScoreDTO> leaderboard = tournamentService.getCountryLeaderboard();
            return ResponseEntity.ok(leaderboard);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    // Diğer metodlar ve işlevler burada tanımlanacak
}


