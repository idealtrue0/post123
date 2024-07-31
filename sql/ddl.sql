DROP TABLE IF EXISTS member;

CREATE TABLE member
(
    id   bigint NOT NULL AUTO_INCREMENT,
    userId varchar(255),
    password varchar(255),
    nickname varchar(255),

    PRIMARY KEY (id)
);
