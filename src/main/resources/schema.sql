drop table if exists TRANSLATIONS;

create table TRANSLATIONS
(
    IP       VARCHAR(255),
    FROMLANG VARCHAR(255),
    TOLANG   VARCHAR(255),
    INPUT     VARCHAR(255),
    OUTPUT     VARCHAR(255)
);