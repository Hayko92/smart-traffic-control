CREATE
DATABASE smart_traffic_control;
CREATE SCHEMA violation_service;
create table violation_service.owner
(
    id            integer      not null,
    firstname     varchar(255) not null,
    lastname      varchar(255) not null,
    date_of_birth date         not null,
    vehicle_vin   varchar(7)   not null,
    points        integer      not null,
    phone         varchar(20),
    email         varchar(50),
    address_id    integer,
    constraint vehicle_owner_pkey
        primary key (id)
);

create table violation_service.address
(
    city         varchar(255) not null,
    street       varchar(255) not null,
    building     varchar(255) not null,
    apartment    varchar(255),
    telephone    integer      not null,
    emailaddress varchar(30)  not null,
    id           integer      not null,
    zip_code     integer,
    constraint vehicle_owner_address_pkey
        primary key (id),
    constraint vehicle_owner_address_address_id_fkey
        foreign key (id) references violation_service.owner
);

alter table violation_service.owner
    add constraint owner_vehicle_owner_address_address_id_fk
        foreign key (address_id) references violation_service.address;

create table violation_service.vehicleDTO
(
    mark                             varchar(30)  not null,
    model                            varchar(30)  not null,
    production_year                  date         not null,
    vin                              varchar(30)  not null,
    plate_numbers                    varchar(7)   not null,
    insurance_expiry_date            date         not null,
    tech_inspection_expiry_date      date         not null,
    registration_certificate_numbers varchar(7)   not null,
    photourl1                        varchar(500) not null,
    photourl2                        varchar(500) not null,
    violation_date_1                 date,
    violation_date_2                 date,
    owner_id                         integer      not null,
    id                               serial,
    constraint vehicle_pk
        primary key (id),
    constraint vehicle_owner_id_fkey
        foreign key (owner_id) references violation_service.owner
);

create unique index vehicle_id_uindex
    on violation_service.vehicleDTO (id);

create table violation_service.violation
(
    id               integer      not null,
    type             varchar(10)  not null,
    price            integer      not null,
    vehicle_numbers  varchar(10)  not null,
    photourl1        varchar(500) not null,
    photourl2        varchar(500) not null,
    creation_date    date,
    violation_date_1 date,
    violation_date_2 date,
    owner_id         integer,
    vehicle_id       integer      not null,
    constraint violation_report_pkey
        primary key (id),
    constraint violation_report_vehicle_owner_id_fk
        foreign key (owner_id) references violation_service.owner,
    constraint violation_report_vehicle_id_fk
        foreign key (vehicle_id) references violation_service.vehicleDTO
);

