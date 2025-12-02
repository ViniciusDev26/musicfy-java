package com.github.viniciusdev26.musicfy.dto.playlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListPlaylistsOutput {
    private List<PlaylistDto> playlists;
    private Integer totalCount;
    private Integer page;
    private Integer pageSize;
    private Integer totalPages;
    private Long filteredByUserId;
    private Boolean systemOnly;
    private Boolean userOnly;
}
