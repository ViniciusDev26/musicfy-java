package com.github.viniciusdev26.musicfy.dto.playlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListPlaylistsByMusicInput {
    private Long musicId;
    private Long userId;
    private Boolean includeSystemPlaylists;
}
