package com.github.viniciusdev26.musicfy.controller;

import com.github.viniciusdev26.musicfy.dto.playlist.*;
import com.github.viniciusdev26.musicfy.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/playlists")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;

    @GetMapping
    public ResponseEntity<ListPlaylistsOutput> getPlaylists(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Boolean systemOnly,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize
    ) {
        ListPlaylistsInput input = new ListPlaylistsInput(page, pageSize, userId, systemOnly, null);
        ListPlaylistsOutput output = playlistService.listPlaylists(input);
        return ResponseEntity.ok(output);
    }

    @GetMapping("/by-music/{musicId}")
    public ResponseEntity<ListPlaylistsByMusicOutput> getPlaylistsByMusic(@PathVariable Long musicId) {
        ListPlaylistsByMusicInput input = new ListPlaylistsByMusicInput(musicId, null, null);
        ListPlaylistsByMusicOutput output = playlistService.listPlaylistsByMusic(input);
        return ResponseEntity.ok(output);
    }

    @PostMapping
    public ResponseEntity<CreatePlaylistOutput> createPlaylist(@RequestBody CreatePlaylistInput input) {
        CreatePlaylistOutput output = playlistService.createPlaylist(input);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(output.getId())
                .toUri();

        return ResponseEntity.created(location).body(output);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Long id) {
        playlistService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
