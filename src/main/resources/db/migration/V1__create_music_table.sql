CREATE TABLE music (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(200) NOT NULL,
    artist VARCHAR(200) NOT NULL,
    audio_url VARCHAR(500) NOT NULL
);

CREATE INDEX idx_music_name_artist ON music(name, artist);
