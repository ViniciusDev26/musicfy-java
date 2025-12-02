package com.github.viniciusdev26.musicfy.dto.playlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListPlaylistsByMusicOutput {
    private Long musicId;
    private String musicName;
    private String musicArtist;
    private List<PlaylistWithMusicDto> playlists;
    private Integer totalCount;
}
