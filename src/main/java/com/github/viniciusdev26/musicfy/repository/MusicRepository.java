package com.github.viniciusdev26.musicfy.repository;

import com.github.viniciusdev26.musicfy.entity.Music;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {
    Optional<Music> findByName(String name);

    List<Music> findByArtist(String artist);

    Page<Music> findByArtist(String artist, Pageable pageable);

    @Query("SELECT m FROM Music m WHERE LOWER(m.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Music> searchByName(String searchTerm);

    @Query("SELECT m FROM Music m WHERE LOWER(m.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<Music> searchByName(String searchTerm, Pageable pageable);

    boolean existsByNameAndArtist(String name, String artist);

    Long countByArtist(String artist);
}
