CREATE DATABASE smart_traffic_control;
CREATE SCHEMA violation_service;

CREATE TABLE violation_service.vehicle_owner
(
    id             INT PRIMARY KEY,
    firstname      varchar(255) NOT NULL,
    lastname       varchar(255) NOT NULL,
    dateOfBirthday DATE         NOT NULL,
    vehicle_vin    varchar(7)   NOT NULL,
    points         INT          NOT NULL
);

CREATE TABLE violation_service.vehicle_owner_address
(
    city         varchar(255)    NOT NULL,
    street       varchar(255)    NOT NULL,
    building     varchar(255)    NOT NULL,
    apartment    varchar(255),
    telephone    INT             NOT NULL,
    emailAddress varchar(30)     NOT NULL,
    address_id     INT PRIMARY KEY NOT NULL
);

ALTER TABLE violation_service.vehicle_owner_address
    ADD FOREIGN KEY (address_id) REFERENCES violation_service.vehicle_owner (id);

CREATE TABLE violation_service.vehicle
(
    mark                             varchar(30)     NOT NULL,
    model                            varchar(30)     NOT NULL,
    production_year                  DATE            NOT NULL,
    vin                              varchar(30)     NOT NULL,
    plate_numbers                    varchar(7)      NOT NULL,
    insurance_expiry_date            DATE            NOT NULL,
    tech_inspection_expiry_date      DATE            NOT NULL,
    registration_certificate_numbers varchar(7)      NOT NULL,
    photoUrl1                        varchar(500)    NOT NULL,
    photoUrl2                        varchar(500)    NOT NULL,
    violation_date_1                 DATE,
    violation_date_2                 DATE,
    owner_id                         INT PRIMARY KEY NOT NULL
);

ALTER TABLE violation_service.vehicle
    ADD FOREIGN KEY (owner_id) REFERENCES violation_service.vehicle_owner (id);

CREATE TABLE violation_service.capture
(
    id           bigserial,
    photo_URL    varchar(500) not null,
    capture_time timestamp    not null,
    plate_number varchar(7),
    place        varchar(500),
    time_stamp   timestamp,
    constraint capture_pk
        primary key (id)
);

ALTER TABLE violation_service.capture
    ADD FOREIGN KEY (id) REFERENCES violation_service.violation_report (id);

CREATE TABLE violation_service.violation_report
(
    id               INT PRIMARY KEY,
    type             varchar(10)  NOT NULL,
    price            INT          NOT NULL,
    vehicle_numbers  varchar(10)  NOT NULL,
    photoUrl1        varchar(500) NOT NULL,
    photoUrl2        varchar(500) NOT NULL,
    creation_date    DATE,
    violation_date_1 DATE,
    violation_date_2 DATE
);

ALTER TABLE violation_service.violation_report
    ADD FOREIGN KEY (id) REFERENCES violation_service.vehicle (owner_id);

ALTER TABLE violation_service.violation_report
    ADD FOREIGN KEY (id) REFERENCES violation_service.vehicle_owner (id);

-- DROP TABLE violation_schema.violation_report;