CREATE TABLE playlist_musics (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    playlist_id INTEGER NOT NULL,
    music_id INTEGER NOT NULL,
    order_position INTEGER NOT NULL,
    added_at TIMESTAMP NOT NULL,
    added_by_user_id INTEGER,
    FOREIGN KEY (playlist_id) REFERENCES playlists(id) ON DELETE CASCADE,
    FOREIGN KEY (music_id) REFERENCES music(id) ON DELETE CASCADE,
    FOREIGN KEY (added_by_user_id) REFERENCES users(id) ON DELETE SET NULL,
    UNIQUE (playlist_id, music_id)
);

CREATE INDEX idx_playlist_musics_playlist_order ON playlist_musics(playlist_id, order_position);
