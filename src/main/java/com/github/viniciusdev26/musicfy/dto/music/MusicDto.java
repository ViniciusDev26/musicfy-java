package com.github.viniciusdev26.musicfy.dto.music;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusicDto {
    private Long id;
    private String name;
    private String artist;
    private String audioUrl;
    private Integer orderInPlaylist;
    private LocalDateTime addedToPlaylistAt;
}
