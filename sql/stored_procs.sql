DELIMITER //
DROP PROCEDURE schedule_event;
//
CREATE PROCEDURE schedule_event
(
  IN p_event_id BIGINT(20) UNSIGNED,
  IN p_start_date VARCHAR(50),
  IN p_length BIGINT(2) UNSIGNED
)
BEGIN
  UPDATE  wp_tuiny5_postmeta pm
  LEFT OUTER JOIN wp_tuiny5_em_events ev ON ev.post_id = pm.post_id
  SET pm.meta_value = DATE_FORMAT(STR_TO_DATE(p_start_date, '%Y-%m-%d %h:%i %p'), '%Y-%m-%d %H:%i:%s')
  WHERE pm.meta_key = '_event_start_local' and ev.event_id = p_event_id;

  UPDATE  wp_tuiny5_postmeta pm
  LEFT OUTER JOIN wp_tuiny5_em_events ev ON ev.post_id = pm.post_id
  SET pm.meta_value = DATE_FORMAT(DATE_ADD(STR_TO_DATE(p_start_date, '%Y-%m-%d %h:%i %p'), INTERVAL p_length HOUR), '%Y-%m-%d %H:%i:%s')
  WHERE pm.meta_key = "_event_end_local" and ev.event_id = p_event_id;

  UPDATE  wp_tuiny5_postmeta pm
  LEFT OUTER JOIN wp_tuiny5_em_events ev ON ev.post_id = pm.post_id
  SET pm.meta_value = DATE_FORMAT(STR_TO_DATE(p_start_date, '%Y-%m-%d %h:%i %p'), '%Y-%m-%d %H:%i:%s')
  WHERE pm.meta_key = '_event_start' and ev.event_id = p_event_id;

  UPDATE  wp_tuiny5_postmeta pm
  LEFT OUTER JOIN wp_tuiny5_em_events ev ON ev.post_id = pm.post_id
  SET pm.meta_value = DATE_FORMAT(DATE_ADD(STR_TO_DATE(p_start_date, '%Y-%m-%d %h:%i %p'), INTERVAL p_length HOUR), '%Y-%m-%d %H:%i:%s')
  WHERE pm.meta_key = "_event_end" and ev.event_id = p_event_id;
  UPDATE  wp_tuiny5_postmeta pm



  LEFT OUTER JOIN wp_tuiny5_em_events ev ON ev.post_id = pm.post_id
  SET pm.meta_value = DATE_FORMAT(STR_TO_DATE(p_start_date, '%Y-%m-%d %h:%i %p'), '%Y-%m-%d')
  WHERE pm.meta_key = '_event_start_date' and ev.event_id = p_event_id;

  UPDATE  wp_tuiny5_postmeta pm
  LEFT OUTER JOIN wp_tuiny5_em_events ev ON ev.post_id = pm.post_id
  SET pm.meta_value = DATE_FORMAT(DATE_ADD(STR_TO_DATE(p_start_date, '%Y-%m-%d %h:%i %p'), INTERVAL p_length HOUR), '%Y-%m-%d')
  WHERE pm.meta_key = "_event_end_date" and ev.event_id = p_event_id;

  UPDATE wp_tuiny5_em_events set event_start_date = date(STR_TO_DATE(p_start_date, '%Y-%m-%d %h:%i %p')), event_end_date = date(DATE_ADD(STR_TO_DATE(p_start_date, '%Y-%m-%d %h:%i %p'), INTERVAL p_length HOUR)) WHERE event_id = p_event_id;
  UPDATE wp_tuiny5_em_events set event_start_time = time(STR_TO_DATE(p_start_date, '%Y-%m-%d %h:%i %p')), event_end_time = time(DATE_ADD(STR_TO_DATE(p_start_date, '%Y-%m-%d %h:%i %p'), INTERVAL p_length HOUR)) WHERE event_id = p_event_id;

  UPDATE  wp_tuiny5_postmeta pm
  LEFT OUTER JOIN wp_tuiny5_em_events ev ON ev.post_id = pm.post_id
  SET pm.meta_value = DATE_FORMAT(STR_TO_DATE(p_start_date, '%Y-%m-%d %h:%i %p'), '%H:%i:%s')
  WHERE pm.meta_key = '_event_start_time' and ev.event_id = p_event_id;

  UPDATE  wp_tuiny5_postmeta pm
  LEFT OUTER JOIN wp_tuiny5_em_events ev ON ev.post_id = pm.post_id
  SET pm.meta_value = DATE_FORMAT(DATE_ADD(STR_TO_DATE(p_start_date, '%Y-%m-%d %h:%i %p'), INTERVAL p_length HOUR), '%H:%i:%s')
  WHERE pm.meta_key = "_event_end_time" and ev.event_id = p_event_id;

  UPDATE wp_tuiny5_em_events set event_start_time = STR_TO_DATE(p_start_date, '%h:%i %p'), event_end_time = DATE_ADD(STR_TO_DATE(p_start_date, '%h:%i %p'), INTERVAL p_length HOUR) WHERE event_id = 769;

END
//
DELIMITER ;

-- CALL schedule_event(1829, '2018-10-12 8:00 PM', 4);
-- select event_id, event_start_date, event_start_time, event_end_date, event_end_time from wp_tuiny5_em_events where event_slug = 'but-not-tonight';
-- select * from wp_tuiny5_postmeta where post_id = 5498 and (meta_key like '_event_start%' or meta_key like '_event_end%');



DELIMITER //
DROP PROCEDURE allocate_space_for_event;
//
CREATE PROCEDURE allocate_space_for_event
(
  IN p_event_id BIGINT(20) UNSIGNED,
  IN p_table VARCHAR(50),
  IN p_room VARCHAR(50)
)
BEGIN
  DECLARE v_post_id BIGINT(20) UNSIGNED;
  DECLARE v_room_tag VARCHAR(20) DEFAULT 'room';
  DECLARE v_room_tag_count BIGINT(20) UNSIGNED;
  DECLARE v_table_tag VARCHAR(20) DEFAULT 'table';
  DECLARE v_table_tag_count BIGINT(20) UNSIGNED;
  SELECT post_id into v_post_id from wp_tuiny5_em_events WHERE event_id = p_event_id;
  SELECT count(*) into v_room_tag_count from wp_tuiny5_postmeta WHERE meta_key = v_room_tag AND post_id = v_post_id;
  SELECT count(*) into v_table_tag_count from wp_tuiny5_postmeta WHERE meta_key = v_table_tag AND post_id = v_post_id;

  if(v_room_tag_count = 0) THEN
    INSERT INTO wp_tuiny5_postmeta (post_id, meta_key, meta_value)
    values(v_post_id, v_room_tag, p_room);
  ELSE
    UPDATE  wp_tuiny5_postmeta pm
    LEFT OUTER JOIN wp_tuiny5_em_events ev ON ev.post_id = pm.post_id
    SET pm.meta_value = p_room
    WHERE pm.meta_key = v_room_tag and ev.event_id = p_event_id;
  END IF;

  if(v_table_tag_count = 0) THEN
    INSERT INTO wp_tuiny5_postmeta (post_id, meta_key, meta_value)
    values(v_post_id, v_table_tag, p_table);
  ELSE
    UPDATE  wp_tuiny5_postmeta pm
    LEFT OUTER JOIN wp_tuiny5_em_events ev ON ev.post_id = pm.post_id
    SET pm.meta_value = p_table
    WHERE pm.meta_key = v_table_tag and ev.event_id = p_event_id;
  END IF;

END
//
DELIMITER ;

-- CALL allocate_space_for_event(1829, '1/Larp', '214');
-- select event_id, event_start_date, event_start_time, event_end_date, event_end_time from wp_tuiny5_em_events where event_slug = 'but-not-tonight';
-- select * from wp_tuiny5_postmeta where post_id = 5498 and (meta_key like '_event_table%' or meta_key like '_event_room');
