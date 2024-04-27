CREATE TABLE IF NOT EXISTS ranking
(
    id                   INT AUTO_INCREMENT PRIMARY KEY,
    date                 DATE,
    world_name           VARCHAR(255),
    ranking              INT,
    character_name       VARCHAR(255),
    character_level      INT,
    character_exp        BIGINT,
    class_name           VARCHAR(255),
    sub_class_name       VARCHAR(255),
    character_popularity INT,
    character_guildname  VARCHAR(255)
);