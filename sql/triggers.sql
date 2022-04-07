alter table em_events drop column last_updated;
alter table em_events add last_updated timestamp NULL;

alter table em_bookings drop column last_updated;
alter table em_bookings add last_updated timestamp NULL;

update em_events set last_updated = utc_timestamp where last_updated is null;
update em_bookings set last_updated = utc_timestamp where last_updated is null;


DROP TRIGGER IF EXISTS events_time_upd_trg;
DROP TRIGGER IF EXISTS events_time_ins_trg;
DROP TRIGGER IF EXISTS bookings_time_upd_trg;
DROP TRIGGER IF EXISTS bookings_time_ins_trg;

DELIMITER $$
CREATE TRIGGER `events_time_upd_trg` BEFORE UPDATE
    ON `em_events`
      FOR EACH ROW BEGIN
        SET NEW.last_updated = UTC_TIMESTAMP;
END
$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER `events_time_ins_trg`
BEFORE INSERT on bbc_wordpress.em_events FOR EACH ROW
BEGIN
   SET new.last_updated = UTC_TIMESTAMP();
END
$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER `bookings_time_upd_trg` BEFORE UPDATE
    ON `em_bookings`
      FOR EACH ROW BEGIN
        SET NEW.last_updated = UTC_TIMESTAMP;
END
$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER `bookings_time_ins_trg`
BEFORE INSERT on bbc_wordpress.em_bookings FOR EACH ROW
BEGIN
   SET new.last_updated = UTC_TIMESTAMP();
END
$$
DELIMITER ;
