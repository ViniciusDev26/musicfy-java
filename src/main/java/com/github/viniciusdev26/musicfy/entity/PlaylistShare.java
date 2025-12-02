package com.github.viniciusdev26.musicfy.entity;

import com.github.viniciusdev26.musicfy.enums.SharePermission;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "playlist_shares", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"playlist_id", "owner_id", "shared_with_user_id"})
})
@Getter
@NoArgsConstructor
public class PlaylistShare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "playlist_id", nullable = false)
    private Long playlistId;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @Column(name = "shared_with_user_id", nullable = false)
    private Long sharedWithUserId;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private SharePermission permission;

    @Column(nullable = false)
    private LocalDateTime sharedAt;

    @Column(nullable = false)
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id", insertable = false, updatable = false)
    private Playlist playlist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", insertable = false, updatable = false)
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shared_with_user_id", insertable = false, updatable = false)
    private User sharedWithUser;

    public PlaylistShare(Long playlistId, Long ownerId, Long sharedWithUserId, SharePermission permission) {
        validatePlaylistId(playlistId);
        validateOwnerId(ownerId);
        validateSharedWithUserId(sharedWithUserId);
        validateNotSharingWithSelf(ownerId, sharedWithUserId);
        validatePermission(permission);

        this.playlistId = playlistId;
        this.ownerId = ownerId;
        this.sharedWithUserId = sharedWithUserId;
        this.permission = permission;
        this.sharedAt = LocalDateTime.now();
        this.isActive = true;
    }

    public void revoke() {
        this.isActive = false;
    }

    public void reactivate() {
        this.isActive = true;
    }

    public void changePermission(SharePermission newPermission) {
        validatePermission(newPermission);
        this.permission = newPermission;
    }

    public boolean canUserEdit() {
        return isActive && permission == SharePermission.EDIT;
    }

    public boolean canUserView() {
        return isActive && (permission == SharePermission.VIEW || permission == SharePermission.EDIT);
    }

    public boolean isSharedWith(Long userId) {
        return sharedWithUserId.equals(userId);
    }

    public boolean isOwnedBy(Long userId) {
        return ownerId.equals(userId);
    }

    private void validatePlaylistId(Long playlistId) {
        if (playlistId == null || playlistId <= 0) {
            throw new IllegalArgumentException("Playlist ID must be positive");
        }
    }

    private void validateOwnerId(Long ownerId) {
        if (ownerId == null || ownerId <= 0) {
            throw new IllegalArgumentException("Owner ID must be positive");
        }
    }

    private void validateSharedWithUserId(Long sharedWithUserId) {
        if (sharedWithUserId == null || sharedWithUserId <= 0) {
            throw new IllegalArgumentException("Shared with user ID must be positive");
        }
    }

    private void validateNotSharingWithSelf(Long ownerId, Long sharedWithUserId) {
        if (ownerId.equals(sharedWithUserId)) {
            throw new IllegalArgumentException("Cannot share playlist with yourself");
        }
    }

    private void validatePermission(SharePermission permission) {
        if (permission == null) {
            throw new IllegalArgumentException("Permission cannot be null");
        }
    }
}
