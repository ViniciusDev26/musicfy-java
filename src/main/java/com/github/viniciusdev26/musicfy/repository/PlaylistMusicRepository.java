package com.github.viniciusdev26.musicfy.repository;

import com.github.viniciusdev26.musicfy.entity.PlaylistMusic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaylistMusicRepository extends JpaRepository<PlaylistMusic, Long> {
    List<PlaylistMusic> findByPlaylistIdOrderByOrderAsc(Long playlistId);

    List<PlaylistMusic> findByMusicId(Long musicId);

    Long countByPlaylistId(Long playlistId);

    Long countByMusicId(Long musicId);

    boolean existsByPlaylistIdAndMusicId(Long playlistId, Long musicId);

    Optional<PlaylistMusic> findByPlaylistIdAndMusicId(Long playlistId, Long musicId);

    @Query("SELECT COALESCE(MAX(pm.order), -1) FROM PlaylistMusic pm WHERE pm.playlistId = :playlistId")
    Integer findMaxOrderByPlaylistId(Long playlistId);

    List<PlaylistMusic> findByPlaylistIdAndOrderBetween(Long playlistId, Integer startOrder, Integer endOrder);
}
