package com.github.viniciusdev26.musicfy.grpc.service;

import com.github.viniciusdev26.musicfy.dto.playlist.*;
import com.github.viniciusdev26.musicfy.grpc.playlist.*;
import com.github.viniciusdev26.musicfy.service.PlaylistService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Collections;

@GrpcService
@RequiredArgsConstructor
public class PlaylistGrpcService extends PlaylistServiceGrpc.PlaylistServiceImplBase {

    private final PlaylistService playlistService;

    @Override
    public void createPlaylist(CreatePlaylistRequest request, StreamObserver<CreatePlaylistResponse> responseObserver) {
        try {
            CreatePlaylistInput input = new CreatePlaylistInput(
                    request.getName(),
                    request.getUserId(),
                    Collections.emptyList()
            );

            CreatePlaylistOutput output = playlistService.createPlaylist(input);

            CreatePlaylistResponse response = CreatePlaylistResponse.newBuilder()
                    .setId(output.getId())
                    .setName(output.getName())
                    .setUserId(output.getUserId())
                    .setCreatedAt(output.getCreatedAt() != null ? output.getCreatedAt().toString() : "")
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void getPlaylists(GetPlaylistsRequest request, StreamObserver<GetPlaylistsResponse> responseObserver) {
        try {
            ListPlaylistsInput input = new ListPlaylistsInput(
                    request.getPage(),
                    request.getSize(),
                    request.getUserId(),
                    null,
                    null
            );

            ListPlaylistsOutput output = playlistService.listPlaylists(input);

            GetPlaylistsResponse.Builder responseBuilder = GetPlaylistsResponse.newBuilder()
                    .setTotal(output.getTotalCount())
                    .setPage(output.getPage() != null ? output.getPage() : 0)
                    .setSize(output.getPageSize() != null ? output.getPageSize() : 0);

            output.getPlaylists().forEach(playlist -> {
                com.github.viniciusdev26.musicfy.grpc.playlist.PlaylistDto.Builder playlistBuilder =
                        com.github.viniciusdev26.musicfy.grpc.playlist.PlaylistDto.newBuilder()
                        .setId(playlist.getId())
                        .setName(playlist.getName())
                        .setUserId(playlist.getUserId())
                        .setCreatedAt(playlist.getCreatedAt() != null ? playlist.getCreatedAt().toString() : "");

                responseBuilder.addPlaylists(playlistBuilder.build());
            });

            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void getPlaylistById(GetPlaylistByIdRequest request, StreamObserver<PlaylistResponse> responseObserver) {
        try {
            responseObserver.onError(io.grpc.Status.UNIMPLEMENTED
                    .withDescription("Not implemented yet")
                    .asRuntimeException());
        } catch (Exception e) {
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void updatePlaylist(UpdatePlaylistRequest request, StreamObserver<PlaylistResponse> responseObserver) {
        try {
            responseObserver.onError(io.grpc.Status.UNIMPLEMENTED
                    .withDescription("Not implemented yet")
                    .asRuntimeException());
        } catch (Exception e) {
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void deletePlaylist(DeletePlaylistRequest request, StreamObserver<DeletePlaylistResponse> responseObserver) {
        try {
            responseObserver.onError(io.grpc.Status.UNIMPLEMENTED
                    .withDescription("Not implemented yet")
                    .asRuntimeException());
        } catch (Exception e) {
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void addMusicToPlaylist(AddMusicToPlaylistRequest request, StreamObserver<AddMusicToPlaylistResponse> responseObserver) {
        try {
            responseObserver.onError(io.grpc.Status.UNIMPLEMENTED
                    .withDescription("Not implemented yet")
                    .asRuntimeException());
        } catch (Exception e) {
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void removeMusicFromPlaylist(RemoveMusicFromPlaylistRequest request, StreamObserver<RemoveMusicFromPlaylistResponse> responseObserver) {
        try {
            responseObserver.onError(io.grpc.Status.UNIMPLEMENTED
                    .withDescription("Not implemented yet")
                    .asRuntimeException());
        } catch (Exception e) {
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void getPlaylistsByMusicId(GetPlaylistsByMusicIdRequest request, StreamObserver<GetPlaylistsByMusicIdResponse> responseObserver) {
        try {
            ListPlaylistsByMusicInput input = new ListPlaylistsByMusicInput(
                    request.getMusicId(),
                    null,
                    true
            );

            ListPlaylistsByMusicOutput output = playlistService.listPlaylistsByMusic(input);

            GetPlaylistsByMusicIdResponse.Builder responseBuilder = GetPlaylistsByMusicIdResponse.newBuilder()
                    .setTotal(output.getTotalCount())
                    .setPage(0)
                    .setSize(0);

            output.getPlaylists().forEach(playlist -> {
                com.github.viniciusdev26.musicfy.grpc.playlist.PlaylistWithMusicDto playlistDto =
                        com.github.viniciusdev26.musicfy.grpc.playlist.PlaylistWithMusicDto.newBuilder()
                        .setId(playlist.getPlaylistId())
                        .setName(playlist.getPlaylistName())
                        .setUserId(playlist.getUserId())
                        .setCreatedAt(playlist.getAddedAt() != null ? playlist.getAddedAt().toString() : "")
                        .setPosition(playlist.getOrderInPlaylist() != null ? playlist.getOrderInPlaylist() : 0)
                        .build();
                responseBuilder.addPlaylists(playlistDto);
            });

            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void sharePlaylist(SharePlaylistRequest request, StreamObserver<SharePlaylistResponse> responseObserver) {
        try {
            responseObserver.onError(io.grpc.Status.UNIMPLEMENTED
                    .withDescription("Not implemented yet")
                    .asRuntimeException());
        } catch (Exception e) {
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void getSharedPlaylists(GetSharedPlaylistsRequest request, StreamObserver<GetPlaylistsResponse> responseObserver) {
        try {
            responseObserver.onError(io.grpc.Status.UNIMPLEMENTED
                    .withDescription("Not implemented yet")
                    .asRuntimeException());
        } catch (Exception e) {
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
}
