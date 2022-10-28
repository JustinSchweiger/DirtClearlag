CREATE TABLE IF NOT EXISTS whitelisteditems
(
    namespaced_key VARCHAR(255) NOT NULL PRIMARY KEY,
    player_name VARCHAR(16) NOT NULL,
    date_added VARCHAR(50) NOT NULL
);