package com.github.viniciusdev26.musicfy.repository;

import com.github.viniciusdev26.musicfy.entity.PlaylistShare;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaylistShareRepository extends JpaRepository<PlaylistShare, Long> {
    List<PlaylistShare> findByPlaylistId(Long playlistId);

    List<PlaylistShare> findByPlaylistIdAndIsActive(Long playlistId, Boolean isActive);

    Long countByPlaylistId(Long playlistId);

    List<PlaylistShare> findBySharedWithUserId(Long userId);

    List<PlaylistShare> findBySharedWithUserIdAndIsActive(Long userId, Boolean isActive);

    Long countBySharedWithUserId(Long userId);

    List<PlaylistShare> findByOwnerId(Long ownerId);

    List<PlaylistShare> findByOwnerIdAndIsActive(Long ownerId, Boolean isActive);

    Long countByOwnerId(Long ownerId);

    boolean existsByPlaylistIdAndSharedWithUserId(Long playlistId, Long userId);

    Optional<PlaylistShare> findByPlaylistIdAndSharedWithUserId(Long playlistId, Long userId);

    boolean existsByPlaylistIdAndOwnerIdAndSharedWithUserId(Long playlistId, Long ownerId, Long sharedWithUserId);

    Long countByIsActive(Boolean isActive);
}
