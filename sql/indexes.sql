ALTER TABLE bbc_wordpress.wp_tuiny5_em_events
  ADD INDEX start_date_and_slug (event_start_date DESC, event_slug);