drop table if exists TRANSLATIONS;

create table TRANSLATIONS
(
    IP       VARCHAR(255),
    FROMLANG VARCHAR(255),
    TOLANG   VARCHAR(255),
    TEXT     VARCHAR(255)
);