CREATE DATABASE violation_db;

CREATE TABLE car_owner
(
    id                               INT PRIMARY KEY,
    car_owner_name                   varchar(255) NOT NULL,
    car_numbers                      varchar(7)   NOT NULL,
    registration_certificate_numbers varchar(7)   NOT NULL,
    offense_report                   INT REFERENCES car_owner (id),
    car_owner_address                INT REFERENCES car_owner (id)
);

CREATE TABLE car_owner_address
(
    address           varchar(255) NOT NULL,
    email             varchar(30)  NOT NULL,
    telephone         INT          NOT NULL,
    car_owner_address INT REFERENCES car_owner (id)
);

CREATE TABLE car
(
    id                               INT PRIMARY KEY,
    car_numbers                      varchar(7)   NOT NULL,
    registration_certificate_numbers varchar(7)   NOT NULL,
    photo_URL1                       varchar(500) NOT NULL,
    photo_URL2                       varchar(500) NOT NULL,
    date                             date,
    offense_report                   INT REFERENCES car_owner (id)
);

CREATE TABLE offense_report
(
    id             INT PRIMARY KEY,
    car_numbers    varchar(7)   NOT NULL,
    photo_URL1     varchar(500) NOT NULL,
    photo_URL2     varchar(500) NOT NULL,
    date           date,
    offense_report INT REFERENCES car_owner (id)
);

ALTER TABLE car_owner
    ADD FOREIGN KEY (id) REFERENCES offense_report (id);