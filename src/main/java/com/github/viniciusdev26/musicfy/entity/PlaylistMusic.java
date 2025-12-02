package com.github.viniciusdev26.musicfy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "playlist_musics", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"playlist_id", "music_id"})
})
@Getter
@NoArgsConstructor
public class PlaylistMusic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "playlist_id", nullable = false)
    private Long playlistId;

    @Column(name = "music_id", nullable = false)
    private Long musicId;

    @Column(name = "order_position", nullable = false)
    private Integer order;

    @Column(nullable = false)
    private LocalDateTime addedAt;

    @Column(name = "added_by_user_id")
    private Long addedByUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id", insertable = false, updatable = false)
    private Playlist playlist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "music_id", insertable = false, updatable = false)
    private Music music;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "added_by_user_id", insertable = false, updatable = false)
    private User addedByUser;

    public PlaylistMusic(Long playlistId, Long musicId, Integer order, Long addedByUserId) {
        validatePlaylistId(playlistId);
        validateMusicId(musicId);
        validateOrder(order);

        this.playlistId = playlistId;
        this.musicId = musicId;
        this.order = order;
        this.addedByUserId = addedByUserId;
        this.addedAt = LocalDateTime.now();
    }

    public void changeOrder(Integer newOrder) {
        validateOrder(newOrder);
        this.order = newOrder;
    }

    public boolean wasAddedBy(Long userId) {
        return addedByUserId != null && addedByUserId.equals(userId);
    }

    private void validatePlaylistId(Long playlistId) {
        if (playlistId == null || playlistId <= 0) {
            throw new IllegalArgumentException("Playlist ID must be positive");
        }
    }

    private void validateMusicId(Long musicId) {
        if (musicId == null || musicId <= 0) {
            throw new IllegalArgumentException("Music ID must be positive");
        }
    }

    private void validateOrder(Integer order) {
        if (order == null || order < 0) {
            throw new IllegalArgumentException("Order must be non-negative");
        }
    }
}
