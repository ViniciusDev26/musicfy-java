package com.github.viniciusdev26.musicfy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.net.URISyntaxException;

@Entity
@Table(name = "music")
@Getter
@NoArgsConstructor
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(nullable = false, length = 200)
    private String artist;

    @Column(nullable = false, length = 500)
    private String audioUrl;

    public Music(String name, String artist, String audioUrl) {
        validateName(name);
        validateArtist(artist);
        validateAudioUrl(audioUrl);

        this.name = name;
        this.artist = artist;
        this.audioUrl = audioUrl;
    }

    public void changeName(String name) {
        validateName(name);
        this.name = name;
    }

    public void changeArtist(String artist) {
        validateArtist(artist);
        this.artist = artist;
    }

    public void changeAudioUrl(String audioUrl) {
        validateAudioUrl(audioUrl);
        this.audioUrl = audioUrl;
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (name.length() > 200) {
            throw new IllegalArgumentException("Name cannot exceed 200 characters");
        }
    }

    private void validateArtist(String artist) {
        if (artist == null || artist.trim().isEmpty()) {
            throw new IllegalArgumentException("Artist cannot be empty");
        }
        if (artist.length() > 200) {
            throw new IllegalArgumentException("Artist cannot exceed 200 characters");
        }
    }

    private void validateAudioUrl(String audioUrl) {
        if (audioUrl == null || audioUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("Audio URL cannot be empty");
        }

        try {
            URI uri = new URI(audioUrl);
            if (!uri.isAbsolute()) {
                throw new IllegalArgumentException("Audio URL must be an absolute URI");
            }
            String scheme = uri.getScheme();
            if (scheme == null || (!scheme.equalsIgnoreCase("http") && !scheme.equalsIgnoreCase("https"))) {
                throw new IllegalArgumentException("Audio URL must use HTTP or HTTPS protocol");
            }
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Audio URL is not valid: " + e.getMessage());
        }
    }
}
