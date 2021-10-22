
create table capture
(
    id bigserial,
    "photo_URL" varchar(500) not null,
    capture_time timestamp not null,
    number varchar(7),
    constraint capture_pk
        primary key (id)
);

create unique index capture_id_uindex
    on capture (id);

create table vehicle
(
    id bigint not null,
    number varchar(7) not null,
    registration_certificate varchar(8) not null,
    owner_id bigint not null,
    checked boolean default false,
    insurance_expiry_date date not null,
    tech_inspection_expiry_date integer not null,
    mark varchar(30) not null,
    model varchar(30) not null,
    production_year integer not null,
    constraint vehicle_pk
        primary key (id)
);


create unique index vehicle_id_uindex
    on vehicle (id);

create unique index vehicle_number_uindex
    on vehicle (number);

create unique index vehicle_registration_certificate_uindex
    on vehicle (registration_certificate);

create table "capture_GI_crossroad"
(
    id bigint default nextval('capture_id_seq'::regclass) not null,
    photo_url varchar(500) not null,
    capture_time timestamp not null,
    number varchar(7) not null,
    constraint capture_pk_
        primary key (id)
);

create unique index capture_id__uindex
    on "capture_GI_crossroad" (id);

create table capture_ar_ar_crossroad
(
    id bigserial,
    photo_url varchar(500) not null,
    capture_time timestamp not null,
    number varchar(7),
    constraint capture_ar_ar_crossroad_pk
        primary key (id)
);

create table capture_seb_tich_crossroad
(
    id bigserial,
    photo_url varchar(500) not null,
    capture_time timestamp not null,
    number varchar(7) not null,
    constraint capture_seb_tich_crossroad_pk
        primary key (id)
);

create unique index capture_seb_tich_crossroad_id_uindex
    on capture_seb_tich_crossroad (id);

create table capture_is_am_crossroad
(
    id bigserial,
    photo_url varchar(500) not null,
    capture_time timestamp not null,
    number varchar(7) not null,
    constraint capture_ar_ar_crossroad_1_pk
        primary key (id)
);

create table violation
(
    id bigserial,
    creation_date date not null,
    price integer not null,
    type varchar(5) not null,
    photo_url1 varchar(100) not null,
    photo_url2 varchar(100),
    constraint violation_pk
        primary key (id)
);

create unique index violation_id_uindex
    on violation (id);

create table owner
(
    id bigserial,
    firstname varchar(50) not null,
    lastname varchar(50) not null,
    birth_year integer not null,
    points integer default 9 not null,
    address varchar(100) not null,
    phone varchar(15),
    email varchar(50) not null,
    constraint owner_pk
        primary key (id)
);

create unique index owner_id_uindex
    on owner (id);

create unique index owner_id_uindex_2
    on owner (id);


