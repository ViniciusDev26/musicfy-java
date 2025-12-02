package com.github.viniciusdev26.musicfy.repository;

import com.github.viniciusdev26.musicfy.entity.Playlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    Optional<Playlist> findByNameAndUserId(String name, Long userId);

    List<Playlist> findByUserId(Long userId);

    Page<Playlist> findByUserId(Long userId, Pageable pageable);

    List<Playlist> findByUserIdIsNull();

    Page<Playlist> findByUserIdIsNull(Pageable pageable);

    Long countByUserId(Long userId);

    Long countByUserIdIsNull();

    boolean existsByNameAndUserId(String name, Long userId);
}
