package com.github.viniciusdev26.musicfy.soap.endpoint;

import com.github.viniciusdev26.musicfy.dto.playlist.CreatePlaylistInput;
import com.github.viniciusdev26.musicfy.dto.playlist.ListPlaylistsInput;
import com.github.viniciusdev26.musicfy.exception.BusinessException;
import com.github.viniciusdev26.musicfy.exception.NotFoundException;
import com.github.viniciusdev26.musicfy.service.PlaylistService;
import com.github.viniciusdev26.musicfy.soap.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class PlaylistSoapEndpoint {

    private static final String NAMESPACE_URI = "http://musicfy.viniciusdev26.github.com/soap/playlists";

    private final PlaylistService playlistService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetPlaylistsRequest")
    @ResponsePayload
    public GetPlaylistsResponse getPlaylists(@RequestPayload GetPlaylistsRequest request) {
        var input = new ListPlaylistsInput(
                request.getPage(),
                request.getPageSize(),
                request.getUserId(),
                request.getSystemOnly(),
                null
        );
        var result = playlistService.listPlaylists(input);

        var response = new GetPlaylistsResponse();
        result.getPlaylists().forEach(p -> {
            var playlistDto = new PlaylistSoapDto();
            playlistDto.setId(p.getId());
            playlistDto.setName(p.getName());
            playlistDto.setUserId(p.getUserId());
            playlistDto.setIsSystemPlaylist(p.getIsSystemPlaylist());
            playlistDto.setCreatedAt(p.getCreatedAt());
            playlistDto.setOwnerName(p.getOwnerName());
            response.getPlaylists().add(playlistDto);
        });
        response.setTotalCount(result.getTotalCount());
        response.setPage(result.getPage());
        response.setPageSize(result.getPageSize());
        response.setTotalPages(result.getTotalPages());

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreatePlaylistRequest")
    @ResponsePayload
    public PlaylistSoapDto createPlaylist(@RequestPayload CreatePlaylistRequest request) {
        try {
            var input = new CreatePlaylistInput(
                    request.getName(),
                    request.getUserId(),
                    request.getMusicIds()
            );

            var result = playlistService.createPlaylist(input);

            var response = new PlaylistSoapDto();
            response.setId(result.getId());
            response.setName(result.getName());
            response.setUserId(result.getUserId());
            response.setIsSystemPlaylist(result.getIsSystemPlaylist());
            response.setCreatedAt(result.getCreatedAt());

            return response;
        } catch (BusinessException ex) {
            throw new RuntimeException("Business Error: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("Validation Error: " + ex.getMessage());
        } catch (NotFoundException ex) {
            throw new RuntimeException("Not Found Error: " + ex.getMessage());
        }
    }
}
