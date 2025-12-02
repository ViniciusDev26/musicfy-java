package com.github.viniciusdev26.musicfy.graphql;

import com.github.viniciusdev26.musicfy.dto.playlist.*;
import com.github.viniciusdev26.musicfy.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class PlaylistResolver {

    private final PlaylistService playlistService;

    @QueryMapping(name = "Playlists")
    public ListPlaylistsOutput playlists(@Argument("input") ListPlaylistsInput input) {
        return playlistService.listPlaylists(input);
    }

    @QueryMapping(name = "PlaylistsByMusic")
    public ListPlaylistsByMusicOutput playlistsByMusic(@Argument("input") ListPlaylistsByMusicInput input) {
        return playlistService.listPlaylistsByMusic(input);
    }

    @MutationMapping(name = "CreatePlaylist")
    public CreatePlaylistOutput createPlaylist(@Argument("input") CreatePlaylistInput input) {
        return playlistService.createPlaylist(input);
    }
}
