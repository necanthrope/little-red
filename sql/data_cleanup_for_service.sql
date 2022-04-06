select * from wp_tuiny5_em_bookings where event_id not in (select event_id from wp_tuiny5_em_events);
delete from wp_tuiny5_em_bookings where event_id not in (select event_id from wp_tuiny5_em_events);

select * from wp_tuiny5_em_bookings where person_id not in (select ID from wp_tuiny5_users);
delete from wp_tuiny5_em_bookings where person_id not in (select ID from wp_tuiny5_users);

select * from wp_tuiny5_em_events where event_owner not in (select ID from wp_tuiny5_users);
update wp_tuiny5_em_events set event_owner = 1 where event_owner not in (select ID from wp_tuiny5_users);

select * from wp_tuiny5_em_tickets_bookings where booking_id not in (select booking_id from wp_tuiny5_em_bookings);
delete from wp_tuiny5_em_tickets_bookings where booking_id not in (select booking_id from wp_tuiny5_em_bookings);

UPDATE wp_tuiny5_options SET option_value = replace(option_value, 'https://www.bigbadcon.com', 'https://www.logictwine.com') WHERE option_name = 'home' OR option_name = 'siteurl';

UPDATE wp_tuiny5_posts SET guid = replace(guid, 'https://www.bigbadcon.com','https://www.logictwine.com');

UPDATE wp_tuiny5_posts SET post_content = replace(post_content, 'https://www.bigbadcon.com', 'https://www.logictwine.com');

UPDATE wp_tuiny5_postmeta SET meta_value = replace(meta_value,'https://www.bigbadcon.com','https://www.logictwine.com') where meta_key != 'game_image';
