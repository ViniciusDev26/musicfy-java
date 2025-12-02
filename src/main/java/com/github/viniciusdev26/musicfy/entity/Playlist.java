package com.github.viniciusdev26.musicfy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "playlists")
@Getter
@NoArgsConstructor
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    public Playlist(String name, Long userId) {
        validateName(name);
        if (userId != null) {
            validateUserId(userId);
        }

        this.name = name;
        this.userId = userId;
        this.createdAt = LocalDateTime.now();
    }

    public void changeName(String name) {
        validateName(name);
        this.name = name;
    }

    public boolean isSystemPlaylist() {
        return userId == null;
    }

    public boolean belongsToUser(Long userId) {
        return this.userId != null && this.userId.equals(userId);
    }

    public boolean canBeEditedBy(Long userId) {
        if (isSystemPlaylist()) {
            return false;
        }
        return belongsToUser(userId);
    }

    public boolean canBeDeletedBy(Long userId) {
        if (isSystemPlaylist()) {
            return false;
        }
        return belongsToUser(userId);
    }

    public static Playlist createSystemPlaylist(String name) {
        return new Playlist(name, null);
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (name.length() < 3) {
            throw new IllegalArgumentException("Name must have at least 3 characters");
        }
        if (name.length() > 100) {
            throw new IllegalArgumentException("Name cannot exceed 100 characters");
        }
    }

    private void validateUserId(Long userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("User ID must be positive");
        }
    }
}
