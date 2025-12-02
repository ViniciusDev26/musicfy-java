CREATE TABLE users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(200) NOT NULL,
    birth_date DATE NOT NULL,
    email VARCHAR(200) NOT NULL
);

CREATE UNIQUE INDEX idx_users_email ON users(email);
