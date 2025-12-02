package com.github.viniciusdev26.musicfy.dto.playlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistWithMusicDto {
    private Long playlistId;
    private String playlistName;
    private Long userId;
    private Boolean isSystemPlaylist;
    private Integer orderInPlaylist;
    private LocalDateTime addedAt;
    private String ownerName;
}
