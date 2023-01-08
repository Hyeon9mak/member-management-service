CREATE TABLE member
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    email        VARCHAR(255)          NOT NULL,
    password     VARCHAR(20)           NOT NULL,
    name         VARCHAR(100)          NOT NULL,
    nickname     VARCHAR(10)           NOT NULL,
    phone_number VARCHAR(11)           NOT NULL,
    CONSTRAINT pk_member PRIMARY KEY (id)
);

ALTER TABLE member
    ADD CONSTRAINT uc_member_email UNIQUE (email);

ALTER TABLE member
    ADD CONSTRAINT uc_member_nickname UNIQUE (nickname);

ALTER TABLE member
    ADD CONSTRAINT uc_member_phone_number UNIQUE (phone_number);

CREATE TABLE member_authentication_code
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    phone_number  VARCHAR(11)           NOT NULL,
    code          VARCHAR(8)            NOT NULL,
    authenticated BIT(1)                NOT NULL,
    created_at    datetime              NOT NULL,
    expired_at    datetime              NOT NULL,
    CONSTRAINT pk_member_authentication_code PRIMARY KEY (id)
);

CREATE INDEX idx_member_authentication_code_phone_number ON member_authentication_code (phone_number);
