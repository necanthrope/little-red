DROP VIEW IF EXISTS bbc_booking_count_view;

CREATE VIEW bbc_booking_count_view AS
select b.booking_id AS booking_id,
b.event_id AS event_id,
b.person_id AS person_id,
b.booking_spaces AS booking_spaces,
b.booking_comment AS booking_comment,
b.booking_date AS booking_date,
b.booking_status AS booking_status,
b.booking_price AS booking_price,
b.booking_meta AS booking_meta,
b.booking_tax_rate AS booking_tax_rate,
b.booking_taxes AS booking_taxes,
e.event_name AS event_name,
e.event_start_time AS event_start_time,
e.event_end_time AS event_end_time,
e.event_start_date AS event_start_date,
e.event_end_date AS event_end_date,
case when ((select count(*) from wp_tuiny5_postmeta where post_id = e.post_id and meta_key = 'exempt') > 0)  THEN 1 ELSE 0 END as exempt,
case when ((select count(*) from wp_tuiny5_postmeta where post_id = e.post_id and meta_key = 'volunteer_shift') > 0)  THEN 1 ELSE 0 END as volunteer,
case when ((select count(*) from wp_tuiny5_postmeta where post_id = e.post_id and meta_key = 'vendor_shift') > 0)  THEN 1 ELSE 0 END as vendor
from ((wp_tuiny5_users u
left join wp_tuiny5_em_bookings b on((u.ID = b.person_id)))
left join wp_tuiny5_em_events e on((b.event_id = e.event_id)))
where ((b.booking_comment is null)
and (b.event_id <> 694)
and (b.booking_status = 1)
and isnull(e.booking_exempt)
and (not((e.event_name regexp '^[V|v]erify.*[B|b]adge$')))) order by u.ID;

CREATE VIEW bbc_event_categories AS
select p.ID AS post_id,
tax.taxonomy AS taxonomy,
t.term_id AS term_id,
t.slug AS slug,
t.name AS name,
e.event_id AS event_id,
e.event_name AS event_name
from ((((wp_tuiny5_posts p
left join wp_tuiny5_term_relationships rel on((rel.object_id = p.ID)))
left join wp_tuiny5_term_taxonomy tax on(((tax.term_taxonomy_id = rel.term_taxonomy_id)
and (tax.taxonomy = 'favoriteEvent-categories'))))
left join wp_tuiny5_terms t on((t.term_id = tax.term_id)))
left join wp_tuiny5_em_events e on((e.post_id = p.ID)))
where (t.name is not null);

CREATE VIEW bbc_event_view AS
select u.ID AS ID,
u.display_name AS display_name,
u.user_email AS user_email,
b.event_id AS event_id,
e.event_name AS event_name,
timestamp(e.event_start_date,
e.event_start_time) AS start_time,
timestamp(e.event_end_date,
e.event_end_time) AS end_time
from ((wp_tuiny5_em_bookings b
join wp_tuiny5_em_events e)
join wp_tuiny5_users u)
where ((e.event_id = b.event_id)
and (year(e.event_start_date) = year(curdate()))
and (u.ID = b.person_id)
and (b.booking_status = 1)) order by u.ID,
timestamp(e.event_start_date,
e.event_start_time);

CREATE VIEW bbc_event_overlap_bookings AS
SELECT
b.person_id,
e.event_slug,
timestamp(e.event_start_date, e.event_start_time) as start1,
timestamp(e.event_end_date, e.event_end_time) as end1,
e2.event_slug,
timestamp(e2.event_start_date, e2.event_start_time) as start2,
timestamp(e2.event_end_date, e2.event_end_time) as end2
FROM wp_tuiny5_em_bookings b, wp_tuiny5_em_events e, wp_tuiny5_em_bookings b2, wp_tuiny5_em_events e2
WHERE b.event_id = e.event_id
AND b2.event_id = e2.event_id
AND e.event_start_date = e2.event_start_date
AND timestamp(e.event_start_date, e.event_start_time) <= timestamp(e2.event_start_date, e2.event_start_time)
AND date_sub(timestamp(e.event_end_date, e.event_end_time), interval 5 minute) > timestamp(e2.event_start_date, e2.event_start_time)
AND e.event_id != e2.event_id
-- AND b.person_id = 362
AND b2.person_id = b.person_id
AND b.booking_status = 1
AND b2.booking_status = 1
and year(e.event_start_date) = year(curdate())

UNION DISTINCT

SELECT
b.person_id,
e.event_slug,
timestamp(e.event_start_date, e.event_start_time) as start1,
timestamp(e.event_end_date, e.event_end_time) as end1,
e2.event_slug,
timestamp(e2.event_start_date, e2.event_start_time) as start2,
timestamp(e2.event_end_date, e2.event_end_time) as end2
FROM wp_tuiny5_em_bookings b, wp_tuiny5_em_events e, wp_tuiny5_em_bookings b2, wp_tuiny5_em_events e2
WHERE b.event_id = e.event_id
AND b2.event_id = e2.event_id
AND e.event_start_date = e2.event_start_date
AND timestamp(e.event_end_date, e.event_end_time) > timestamp(e2.event_start_date, e2.event_start_time)
AND timestamp(e.event_end_date, e.event_end_time) <= timestamp(e2.event_end_date, e2.event_end_time)
AND e.event_id != e2.event_id
-- AND b.person_id = 362
AND b2.person_id = b.person_id
AND b.booking_status = 1
AND b2.booking_status = 1
and year(e.event_start_date) = year(curdate());
