create table "capture_GI_crossroad"
(
    id           bigint default nextval('capture_id_seq'::regclass) not null
        constraint capture_pk
            primary key,
    photo_url    varchar(500)                                       not null,
    capture_time timestamp                                          not null,
    number       varchar(7)                                         not null
);

alter table "capture_GI_crossroad"
    owner to postgres;

create unique index capture_id_uindex
    on "capture_GI_crossroad" (id);

create table vehicle
(
    id                          bigserial
        constraint vehicle_pk
            primary key,
    number                      varchar(7) not null,
    registration_certificate    varchar(8) not null,
    owner_id                    bigint     not null,
    checked                     boolean default false,
    insurance_expiry_date       date       not null,
    tech_inspection_expiry_date date
);

alter table vehicle
    owner to postgres;

create unique index vehicle_number_uindex
    on vehicle (number);

create unique index vehicle_registration_certificate_uindex
    on vehicle (registration_certificate);

create table capture_ar_ar_crossroad
(
    id           bigserial
        constraint capture_ar_ar_crossroad_pk
            primary key,
    photo_url    varchar(500) not null,
    capture_time timestamp    not null,
    number       varchar(7)
);

alter table capture_ar_ar_crossroad
    owner to postgres;

create unique index capture_ar_ar_crossroad_id_uindex
    on capture_ar_ar_crossroad (id);

create table capture_seb_tich_crossroad
(
    id           bigserial
        constraint capture_seb_tich_crossroad_pk
            primary key,
    photo_url    varchar(500) not null,
    capture_time timestamp    not null,
    number       varchar(7)   not null
);

alter table capture_seb_tich_crossroad
    owner to postgres;

create unique index capture_seb_tich_crossroad_id_uindex
    on capture_seb_tich_crossroad (id);

create table capture_is_am_crossroad
(
    id           bigint default nextval('capture_ar_ar_crossroad_1_id_seq'::regclass) not null
        constraint capture_ar_ar_crossroad_1_pk
            primary key,
    photo_url    varchar(500)                                                         not null,
    capture_time timestamp                                                            not null,
    number       varchar(7)                                                           not null
);

alter table capture_is_am_crossroad
    owner to postgres;

create unique index capture_ar_ar_crossroad_1_id_uindex
    on capture_is_am_crossroad (id);

