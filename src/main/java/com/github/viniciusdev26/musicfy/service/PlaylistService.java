package com.github.viniciusdev26.musicfy.service;

import com.github.viniciusdev26.musicfy.dto.playlist.*;
import com.github.viniciusdev26.musicfy.entity.Music;
import com.github.viniciusdev26.musicfy.entity.Playlist;
import com.github.viniciusdev26.musicfy.entity.PlaylistMusic;
import com.github.viniciusdev26.musicfy.entity.User;
import com.github.viniciusdev26.musicfy.exception.NotFoundException;
import com.github.viniciusdev26.musicfy.repository.MusicRepository;
import com.github.viniciusdev26.musicfy.repository.PlaylistMusicRepository;
import com.github.viniciusdev26.musicfy.repository.PlaylistRepository;
import com.github.viniciusdev26.musicfy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final PlaylistMusicRepository playlistMusicRepository;
    private final MusicRepository musicRepository;
    private final UserRepository userRepository;

    @Transactional
    public CreatePlaylistOutput createPlaylist(CreatePlaylistInput input) {
        if (input.getUserId() != null && !userRepository.existsById(input.getUserId())) {
            throw new NotFoundException("User", input.getUserId());
        }

        Playlist playlist = new Playlist(input.getName(), input.getUserId());
        playlist = playlistRepository.save(playlist);

        List<MusicInPlaylistDto> musicDtos = new ArrayList<>();

        if (input.getMusicIds() != null && !input.getMusicIds().isEmpty()) {
            int order = 0;
            for (Long musicId : input.getMusicIds()) {
                if (!musicRepository.existsById(musicId)) {
                    throw new NotFoundException("Music", musicId);
                }

                PlaylistMusic playlistMusic = new PlaylistMusic(
                        playlist.getId(),
                        musicId,
                        order,
                        input.getUserId()
                );
                playlistMusicRepository.save(playlistMusic);

                Music music = musicRepository.findById(musicId).get();
                musicDtos.add(new MusicInPlaylistDto(
                        music.getId(),
                        music.getName(),
                        music.getArtist(),
                        order
                ));

                order++;
            }
        }

        return new CreatePlaylistOutput(
                playlist.getId(),
                playlist.getName(),
                playlist.getUserId(),
                playlist.isSystemPlaylist(),
                playlist.getCreatedAt(),
                musicDtos.size(),
                musicDtos
        );
    }

    @Transactional(readOnly = true)
    public ListPlaylistsOutput listPlaylists(ListPlaylistsInput input) {
        List<PlaylistDto> playlists;
        int totalCount;
        Integer totalPages = null;

        Pageable pageable = null;
        if (input.getPage() != null && input.getPageSize() != null) {
            pageable = PageRequest.of(input.getPage(), input.getPageSize());
        }

        if (input.getSystemOnly() != null && input.getSystemOnly()) {
            if (pageable != null) {
                Page<Playlist> page = playlistRepository.findByUserIdIsNull(pageable);
                playlists = page.getContent().stream()
                        .map(this::toPlaylistDto)
                        .collect(Collectors.toList());
                totalCount = (int) page.getTotalElements();
                totalPages = page.getTotalPages();
            } else {
                List<Playlist> playlistList = playlistRepository.findByUserIdIsNull();
                playlists = playlistList.stream()
                        .map(this::toPlaylistDto)
                        .collect(Collectors.toList());
                totalCount = playlistList.size();
            }
        } else if (input.getUserId() != null) {
            if (pageable != null) {
                Page<Playlist> page = playlistRepository.findByUserId(input.getUserId(), pageable);
                playlists = page.getContent().stream()
                        .map(this::toPlaylistDto)
                        .collect(Collectors.toList());
                totalCount = (int) page.getTotalElements();
                totalPages = page.getTotalPages();
            } else {
                List<Playlist> playlistList = playlistRepository.findByUserId(input.getUserId());
                playlists = playlistList.stream()
                        .map(this::toPlaylistDto)
                        .collect(Collectors.toList());
                totalCount = playlistList.size();
            }
        } else {
            if (pageable != null) {
                Page<Playlist> page = playlistRepository.findAll(pageable);
                playlists = page.getContent().stream()
                        .map(this::toPlaylistDto)
                        .collect(Collectors.toList());
                totalCount = (int) page.getTotalElements();
                totalPages = page.getTotalPages();
            } else {
                List<Playlist> allPlaylists = playlistRepository.findAll();
                playlists = allPlaylists.stream()
                        .map(this::toPlaylistDto)
                        .collect(Collectors.toList());
                totalCount = allPlaylists.size();
            }
        }

        return new ListPlaylistsOutput(
                playlists,
                totalCount,
                input.getPage(),
                input.getPageSize(),
                totalPages,
                input.getUserId(),
                input.getSystemOnly(),
                input.getUserOnly()
        );
    }

    @Transactional(readOnly = true)
    public ListPlaylistsByMusicOutput listPlaylistsByMusic(ListPlaylistsByMusicInput input) {
        Music music = musicRepository.findById(input.getMusicId())
                .orElseThrow(() -> new NotFoundException("Music", input.getMusicId()));

        List<PlaylistMusic> playlistMusics = playlistMusicRepository.findByMusicId(input.getMusicId());

        List<PlaylistWithMusicDto> playlistDtos = playlistMusics.stream()
                .map(pm -> {
                    Playlist playlist = playlistRepository.findById(pm.getPlaylistId())
                            .orElseThrow(() -> new NotFoundException("Playlist", pm.getPlaylistId()));

                    if (input.getUserId() != null && playlist.getUserId() != null
                            && !playlist.getUserId().equals(input.getUserId())) {
                        return null;
                    }

                    if (input.getIncludeSystemPlaylists() != null
                            && !input.getIncludeSystemPlaylists()
                            && playlist.isSystemPlaylist()) {
                        return null;
                    }

                    String ownerName = null;
                    if (playlist.getUserId() != null) {
                        User user = userRepository.findById(playlist.getUserId()).orElse(null);
                        if (user != null) {
                            ownerName = user.getName();
                        }
                    }

                    return new PlaylistWithMusicDto(
                            playlist.getId(),
                            playlist.getName(),
                            playlist.getUserId(),
                            playlist.isSystemPlaylist(),
                            pm.getOrder(),
                            pm.getAddedAt(),
                            ownerName
                    );
                })
                .filter(dto -> dto != null)
                .collect(Collectors.toList());

        return new ListPlaylistsByMusicOutput(
                music.getId(),
                music.getName(),
                music.getArtist(),
                playlistDtos,
                playlistDtos.size()
        );
    }

    @Transactional(readOnly = true)
    public Playlist findById(Long id) {
        return playlistRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Playlist", id));
    }

    @Transactional
    public void delete(Long id) {
        if (!playlistRepository.existsById(id)) {
            throw new NotFoundException("Playlist", id);
        }
        playlistRepository.deleteById(id);
    }

    private PlaylistDto toPlaylistDto(Playlist playlist) {
        String ownerName = null;
        if (playlist.getUserId() != null) {
            User user = userRepository.findById(playlist.getUserId()).orElse(null);
            if (user != null) {
                ownerName = user.getName();
            }
        }

        return new PlaylistDto(
                playlist.getId(),
                playlist.getName(),
                playlist.getUserId(),
                playlist.isSystemPlaylist(),
                playlist.getCreatedAt(),
                ownerName
        );
    }
}
