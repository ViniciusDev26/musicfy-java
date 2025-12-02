package com.github.viniciusdev26.musicfy.dto.music;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListMusicsOutput {
    private List<MusicDto> musics;
    private Integer totalCount;
    private Integer page;
    private Integer pageSize;
    private Integer totalPages;
    private String filteredByArtist;
    private String searchedTerm;
    private Long filteredByPlaylistId;
}
