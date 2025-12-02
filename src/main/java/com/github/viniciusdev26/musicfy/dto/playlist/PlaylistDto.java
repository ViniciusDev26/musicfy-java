package com.github.viniciusdev26.musicfy.dto.playlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDto {
    private Long id;
    private String name;
    private Long userId;
    private Boolean isSystemPlaylist;
    private LocalDateTime createdAt;
    private String ownerName;
}
