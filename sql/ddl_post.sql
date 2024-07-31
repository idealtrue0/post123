DROP TABLE IF EXISTS post;

CREATE TABLE post
(
    id   bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title varchar(255),
    content TEXT,
    nickname varchar(255),
    written_date varchar(255),
    update_date varchar(255),
    views int
);
