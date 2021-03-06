DROP TABLE IF EXISTS CITIZEN;
CREATE TABLE CITIZEN
(
    ID           BIGINT AUTO_INCREMENT PRIMARY KEY,
    NAME         VARCHAR(255) NOT NULL,
    HAS_FREEDOM  BOOL         NOT NULL,
    INCOME_VALUE BIGINT(255) NOT NULL
);

DROP TABLE IF EXISTS CITIZENSHIP;
CREATE TABLE CITIZENSHIP
(
    ID           BIGINT AUTO_INCREMENT PRIMARY KEY,
    ID_CITIZEN   BIGINT(255) NOT NULL,
    COUNTRY_CODE VARCHAR(50) NOT NULL,
    foreign key (ID_CITIZEN) references CITIZEN (ID)
);

DROP TABLE IF EXISTS HOUSING;
CREATE TABLE HOUSING
(
    ID         BIGINT AUTO_INCREMENT PRIMARY KEY,
    ID_CITIZEN BIGINT(255) NOT NULL,
    ADDRESS    VARCHAR(200) NOT NULL,
    foreign key (ID_CITIZEN) references CITIZEN (ID)
);