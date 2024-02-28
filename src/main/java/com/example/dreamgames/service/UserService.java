package com.example.dreamgames.service;

import com.example.dreamgames.entity.UserEntity;
import com.example.dreamgames.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity createUser() {
        UserEntity newUser = new UserEntity();
        newUser.setCoins(5000); // Başlangıç para miktarı
        newUser.setLevel(1); // Başlangıç seviyesi
        newUser.setCountry(assignRandomCountry()); // Rastgele ülke ataması
        userRepository.save(newUser); // Kullanıcıyı veritabanına kaydet
        return newUser; // Oluşturulan kullanıcıyı döndür
    }

    public UserEntity updateUserLevel(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        user.setLevel(user.getLevel() + 1); // Kullanıcının seviyesini 1 arttır
        user.setCoins(user.getCoins() + 25); // Her seviye için kullanıcıya 25 altın ekle
        return userRepository.save(user);
    }


    private String assignRandomCountry() {
        String[] countries = {"Turkey", "United States", "United Kingdom", "France", "Germany"};
        return countries[new Random().nextInt(countries.length)];
    }
    public UserEntity claimReward(Long userId) {
        // Find the user based on userId
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Perform the logic to claim the reward
        // For example, determine the user's rank and allocate coins accordingly
        // This is just a placeholder logic
        int rank = determineUserRank(userId);
        int reward = calculateReward(rank);
        user.setCoins(user.getCoins() + reward);

        // Save the updated user entity
        userRepository.save(user);

        return user;
    }

    private int determineUserRank(Long userId) {
        // Implement the logic to determine the user's rank in the tournament
        // This could involve querying the database and applying business logic
        return 1; // Placeholder for user rank
    }

    private int calculateReward(int rank) {
        // Implement the logic to calculate the reward based on user rank
        // The actual reward calculation would be based on your business rules
        if (rank == 1) {
            return 10000; // Example reward for 1st place
        }
        // Add more conditions for different ranks
        return 0; // Default no reward
    }
}


