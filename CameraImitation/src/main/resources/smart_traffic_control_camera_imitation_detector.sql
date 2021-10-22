-- Cyclic dependencies found

create table detector
(
    id          int8 default nextval('camera_imitation.detector_id_seq'::regclass) not null,
    name        varchar(100)                                                       not null,
    next_cam_id int8
        constraint detector_detector_id_fk
            references detector
);

create unique index detector_id_uindex
    on detector (id);

create unique index detector_pk
    on detector (id);

alter table detector
    add constraint detector_pk
        primary key (id);

INSERT INTO camera_imitation.detector (id, name, next_cam_id) VALUES (5, 'ghfghf', null);
INSERT INTO camera_imitation.detector (id, name, next_cam_id) VALUES (6, 'ffgfg', 5);
INSERT INTO camera_imitation.detector (id, name, next_cam_id) VALUES (7, 'fgf', 5);
INSERT INTO camera_imitation.detector (id, name, next_cam_id) VALUES (8, 'fgf', 5);
INSERT INTO camera_imitation.detector (id, name, next_cam_id) VALUES (10, 'fgfg', 5);
INSERT INTO camera_imitation.detector (id, name, next_cam_id) VALUES (9, 'fgfg', 5);