CREATE TABLE playlist_shares (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    playlist_id INTEGER NOT NULL,
    owner_id INTEGER NOT NULL,
    shared_with_user_id INTEGER NOT NULL,
    permission INTEGER NOT NULL,
    shared_at TIMESTAMP NOT NULL,
    is_active INTEGER NOT NULL,
    FOREIGN KEY (playlist_id) REFERENCES playlists(id) ON DELETE CASCADE,
    FOREIGN KEY (owner_id) REFERENCES users(id) ON DELETE RESTRICT,
    FOREIGN KEY (shared_with_user_id) REFERENCES users(id) ON DELETE CASCADE,
    UNIQUE (playlist_id, owner_id, shared_with_user_id)
);
