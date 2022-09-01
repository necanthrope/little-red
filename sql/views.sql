-- Put table prefix from application.properties here (db.table_prefix)
SET @table_prefix:='wp_goplaynw_';

SET @sql:=CONCAT(
'CREATE OR REPLACE ',
'VIEW bbc_booking_count_view AS select ',
'    b.booking_id AS booking_id, ',
'    b.event_id AS event_id, ',
'    b.person_id AS person_id, ',
'    b.booking_spaces AS booking_spaces, ',
'    b.booking_comment AS booking_comment, ',
'    b.booking_date AS booking_date, ',
'    b.booking_status AS booking_status, ',
'    b.booking_price AS booking_price, ',
'    b.booking_meta AS booking_meta, ',
'    b.booking_tax_rate AS booking_tax_rate, ',
'    b.booking_taxes AS booking_taxes, ',
'    e.event_name AS event_name, ',
'    e.event_start_time AS event_start_time, ',
'    e.event_end_time AS event_end_time, ',
'    e.event_start_date AS event_start_date, ',
'    e.event_end_date AS event_end_date, ',
'    (case ',
'        when (( ',
'            select count(*) ',
'        from ',
'            ' , @table_prefix , 'postmeta ',
'        where ',
'            ((' , @table_prefix , 'postmeta.post_id = e.post_id) and (' , @table_prefix , 'postmeta.meta_key = ''exempt''))) > 0) then 1 ',
'        else 0 ',
'    end) AS exempt, ',
'    (case ',
'        when (( ',
'            select count(*) ',
'        from ',
'            ' , @table_prefix , 'postmeta ',
'        where ',
'            ((' , @table_prefix , 'postmeta.post_id = e.post_id) and (' , @table_prefix , 'postmeta.meta_key = ''volunteer_shift''))) > 0) then 1 ',
'        else 0 ',
'    end) AS volunteer, ',
'    (case ',
'        when (( ',
'            select count(*) ',
'        from ',
'            ' , @table_prefix , 'postmeta ',
'        where ',
'            ((' , @table_prefix , 'postmeta.post_id = e.post_id) and (' , @table_prefix , 'postmeta.meta_key = ''vendor_shift''))) > 0) then 1 ',
'        else 0 ',
'    end) AS vendor ',
'from ',
'    ((' , @table_prefix , 'users u ',
'left join ' , @table_prefix , 'em_bookings b on ',
'    ((u.ID = b.person_id))) ',
'left join ' , @table_prefix , 'em_events e on ',
'    ((b.event_id = e.event_id))) ',
'where ',
'    (isnull(b.booking_comment) ',
'    and (b.booking_status = 1) ',
'    and isnull(e.booking_exempt)) ',
'order by u.ID');

PREPARE stmt from @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;



SET @sql := CONCAT(
'CREATE OR REPLACE ',
'VIEW bbc_event_categories AS select ',
'    p.ID AS post_id, ',
'    tax.taxonomy AS taxonomy, ',
'    t.term_id AS term_id, ',
'    t.slug AS slug, ',
'    t.name AS name, ',
'    e.event_id AS event_id, ',
'    e.event_name AS event_name ',
'from ',
'    ((((' , @table_prefix , 'posts p ',
'left join ' , @table_prefix , 'term_relationships rel on ',
'    ((rel.object_id = p.ID))) ',
'left join ' , @table_prefix , 'term_taxonomy tax on ',
'    (((tax.term_taxonomy_id = rel.term_taxonomy_id) and (tax.taxonomy = ''event-categories'')))) ',
'left join ' , @table_prefix , 'terms t on ',
'    ((t.term_id = tax.term_id))) ',
'left join ' , @table_prefix , 'em_events e on ',
'    ((e.post_id = p.ID))) ',
'where ',
'    (t.name is not null) ',
'; ');

PREPARE stmt from @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;



SET @sql := CONCAT(
'CREATE OR REPLACE ',
'VIEW bbc_event_view AS select ',
'    u.ID AS ID, ',
'    u.display_name AS display_name, ',
'    u.user_email AS user_email, ',
'    b.event_id AS event_id, ',
'    e.event_name AS event_name, ',
'    timestamp(e.event_start_date, e.event_start_time) AS start_time, ',
'    timestamp(e.event_end_date, e.event_end_time) AS end_time ',
'from ',
'    ((' , @table_prefix , 'em_bookings b ',
'join ' , @table_prefix , 'em_events e) ',
'join ' , @table_prefix , 'users u) ',
'where ',
'    ((e.event_id = b.event_id) ',
'    and (year(e.event_start_date) = year(curdate())) ',
'    and (u.ID = b.person_id) ',
'    and (b.booking_status = 1)) ',
'order by ',
'    u.ID, ',
'    timestamp(e.event_start_date, ',
'    e.event_start_time) ',
';') ;

PREPARE stmt from @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;


SET @sql := CONCAT(
'CREATE OR REPLACE VIEW bbc_users_short AS ',
'select ID, display_name from ', @table_prefix, 'users;' );

PREPARE stmt from @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;