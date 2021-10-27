create table vehicle
(
    id                          bigserial
        constraint vehicle_pk
            primary key,
    plate_number                varchar(7)               not null,
    insurance_expiry_date       timestamp with time zone not null,
    tech_inspection_expiry_date timestamp with time zone not null,
    checked                     boolean default false
);

alter table vehicle
    owner to postgres;

create unique index vehicle_id_uindex
    on vehicle (id);

INSERT INTO detectors_analyzer.vehicle (id, plate_number, insurance_expiry_date, tech_inspection_expiry_date, checked)
VALUES (1, '10RQ077', '2021-10-25 11:54:37.964000 +00:00', '2021-10-28 11:54:49.907000 +00:00', false);
INSERT INTO detectors_analyzer.vehicle (id, plate_number, insurance_expiry_date, tech_inspection_expiry_date, checked)
VALUES (2, '37FA299', '2021-10-25 11:54:37.964000 +00:00', '2021-10-28 11:54:49.907000 +00:00', false);
INSERT INTO detectors_analyzer.vehicle (id, plate_number, insurance_expiry_date, tech_inspection_expiry_date, checked)
VALUES (3, '36LG838', '2021-10-25 11:54:37.964000 +00:00', '2021-10-28 11:54:49.907000 +00:00', false);
INSERT INTO detectors_analyzer.vehicle (id, plate_number, insurance_expiry_date, tech_inspection_expiry_date, checked)
VALUES (4, '37LC173', '2021-10-25 11:54:37.964000 +00:00', '2021-10-28 11:54:49.907000 +00:00', false);
INSERT INTO detectors_analyzer.vehicle (id, plate_number, insurance_expiry_date, tech_inspection_expiry_date, checked)
VALUES (5, '55NK505', '2021-10-25 11:54:37.964000 +00:00', '2021-10-28 11:54:49.907000 +00:00', false);
INSERT INTO detectors_analyzer.vehicle (id, plate_number, insurance_expiry_date, tech_inspection_expiry_date, checked)
VALUES (6, '37LN026', '2021-10-25 11:54:37.964000 +00:00', '2021-10-28 11:54:49.907000 +00:00', false);
INSERT INTO detectors_analyzer.vehicle (id, plate_number, insurance_expiry_date, tech_inspection_expiry_date, checked)
VALUES (7, '36QU946', '2021-10-25 11:54:37.964000 +00:00', '2021-10-28 11:54:49.907000 +00:00', false);
INSERT INTO detectors_analyzer.vehicle (id, plate_number, insurance_expiry_date, tech_inspection_expiry_date, checked)
VALUES (8, '02GR021', '2021-06-26 11:57:39.960000 +00:00', '2021-12-26 11:57:44.374000 +00:00', false);
INSERT INTO detectors_analyzer.vehicle (id, plate_number, insurance_expiry_date, tech_inspection_expiry_date, checked)
VALUES (9, '02GR021', '2021-06-26 11:57:39.960000 +00:00', '2021-12-26 11:57:44.374000 +00:00', false);
INSERT INTO detectors_analyzer.vehicle (id, plate_number, insurance_expiry_date, tech_inspection_expiry_date, checked)
VALUES (10, '08TT080', '2021-11-26 11:58:09.350000 +00:00', '2021-05-26 11:58:15.085000 +00:00', false);
INSERT INTO detectors_analyzer.vehicle (id, plate_number, insurance_expiry_date, tech_inspection_expiry_date, checked)
VALUES (11, '01AA030', '2021-11-26 11:58:09.350000 +00:00', '2021-05-26 11:58:15.085000 +00:00', false);
INSERT INTO detectors_analyzer.vehicle (id, plate_number, insurance_expiry_date, tech_inspection_expiry_date, checked)
VALUES (12, '36QD070', '2021-11-26 11:58:09.350000 +00:00', '2021-05-26 11:58:15.085000 +00:00', false);
INSERT INTO detectors_analyzer.vehicle (id, plate_number, insurance_expiry_date, tech_inspection_expiry_date, checked)
VALUES (13, '34FA443', '2021-11-26 11:58:09.350000 +00:00', '2021-05-26 11:58:15.085000 +00:00', false);
INSERT INTO detectors_analyzer.vehicle (id, plate_number, insurance_expiry_date, tech_inspection_expiry_date, checked)
VALUES (14, '99OG998', '2021-11-26 11:58:09.350000 +00:00', '2021-05-26 11:58:15.085000 +00:00', false);
INSERT INTO detectors_analyzer.vehicle (id, plate_number, insurance_expiry_date, tech_inspection_expiry_date, checked)
VALUES (15, '37LM439', '2021-11-26 11:58:09.350000 +00:00', '2021-05-26 11:58:15.085000 +00:00', false);
INSERT INTO detectors_analyzer.vehicle (id, plate_number, insurance_expiry_date, tech_inspection_expiry_date, checked)
VALUES (16, '35MR929', '2021-11-26 11:58:09.350000 +00:00', '2021-05-26 11:58:15.085000 +00:00', false);
INSERT INTO detectors_analyzer.vehicle (id, plate_number, insurance_expiry_date, tech_inspection_expiry_date, checked)
VALUES (17, '36ZR173', '2021-11-26 11:58:09.350000 +00:00', '2021-05-26 11:58:15.085000 +00:00', false);
INSERT INTO detectors_analyzer.vehicle (id, plate_number, insurance_expiry_date, tech_inspection_expiry_date, checked)
VALUES (18, '11VL011', '2021-11-26 11:58:09.350000 +00:00', '2021-05-26 11:58:15.085000 +00:00', false);
INSERT INTO detectors_analyzer.vehicle (id, plate_number, insurance_expiry_date, tech_inspection_expiry_date, checked)
VALUES (19, '01LS100', '2021-11-26 11:58:09.350000 +00:00', '2021-05-26 11:58:15.085000 +00:00', false);
INSERT INTO detectors_analyzer.vehicle (id, plate_number, insurance_expiry_date, tech_inspection_expiry_date, checked)
VALUES (20, '36CQ484', '2021-11-26 11:58:09.350000 +00:00', '2021-05-26 11:58:15.085000 +00:00', false);
INSERT INTO detectors_analyzer.vehicle (id, plate_number, insurance_expiry_date, tech_inspection_expiry_date, checked)
VALUES (21, '11LQ211', '2021-11-26 11:58:09.350000 +00:00', '2021-05-26 11:58:15.085000 +00:00', false);
INSERT INTO detectors_analyzer.vehicle (id, plate_number, insurance_expiry_date, tech_inspection_expiry_date, checked)
VALUES (22, '88SM089', '2021-11-26 11:58:09.350000 +00:00', '2021-05-26 11:58:15.085000 +00:00', false);
INSERT INTO detectors_analyzer.vehicle (id, plate_number, insurance_expiry_date, tech_inspection_expiry_date, checked)
VALUES (23, '11RP911', '2021-11-26 11:58:09.350000 +00:00', '2021-05-26 11:58:15.085000 +00:00', false);
INSERT INTO detectors_analyzer.vehicle (id, plate_number, insurance_expiry_date, tech_inspection_expiry_date, checked)
VALUES (24, '35LC549', '2021-11-26 11:58:09.350000 +00:00', '2021-05-26 11:58:15.085000 +00:00', false);
INSERT INTO detectors_analyzer.vehicle (id, plate_number, insurance_expiry_date, tech_inspection_expiry_date, checked)
VALUES (25, '36OF108', '2021-11-26 11:58:09.350000 +00:00', '2021-05-26 11:58:15.085000 +00:00', false);
INSERT INTO detectors_analyzer.vehicle (id, plate_number, insurance_expiry_date, tech_inspection_expiry_date, checked)
VALUES (26, '17UL280', '2021-11-26 11:58:09.350000 +00:00', '2021-05-26 11:58:15.085000 +00:00', false);
INSERT INTO detectors_analyzer.vehicle (id, plate_number, insurance_expiry_date, tech_inspection_expiry_date, checked)
VALUES (27, '34AP804', '2021-11-26 11:58:09.350000 +00:00', '2021-05-26 11:58:15.085000 +00:00', false);
INSERT INTO detectors_analyzer.vehicle (id, plate_number, insurance_expiry_date, tech_inspection_expiry_date, checked)
VALUES (28, '36VU501', '2021-11-26 11:58:09.350000 +00:00', '2021-05-26 11:58:15.085000 +00:00', false);
INSERT INTO detectors_analyzer.vehicle (id, plate_number, insurance_expiry_date, tech_inspection_expiry_date, checked)
VALUES (29, '34CT478', '2022-10-26 12:04:07.815000 +00:00', '2021-05-26 11:58:15.085000 +00:00', false);
INSERT INTO detectors_analyzer.vehicle (id, plate_number, insurance_expiry_date, tech_inspection_expiry_date, checked)
VALUES (30, '36VU501', '2022-10-26 12:04:07.815000 +00:00', '2021-05-26 11:58:15.085000 +00:00', false);