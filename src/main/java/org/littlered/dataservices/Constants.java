package org.littlered.dataservices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Jeremy on 7/2/2017.
 */
public final class Constants {

	// Privilege bundles for endpoint security checks
	public static ArrayList<String> ROLE_LIST_ADMIN_ONLY = new ArrayList<>(Collections.singletonList("administrator"));
	public static ArrayList<String> ROLE_LIST_TEEN = new ArrayList<>(Collections.singletonList("teen"));
	public static ArrayList<String> ROLE_LIST_HAS_BADGE = new ArrayList<>(
			Arrays.asList("gm", "volunteer", "paidattendee", "staff", "volunteer", "editor", "administrator", "comp"));
	public static ArrayList<String> ROLE_LIST_VOLUNTEER = new ArrayList<>(Collections.singletonList("volunteer"));
	public static ArrayList<String> ROLE_LIST_VENDOR = new ArrayList<>(Collections.singletonList("vendor"));

	public static final String TEEN_CATEGORY_SLUG = "teen";
	public static final String VOLUNTEER_SHIFT_POSTMETA = "volunteer_shift";
	public static final String VENDOR_SHIFT_POSTMETA = "vendor_shift";

	public static final int UNLIMITED_QUOTA = 100;

	// Endpoint error messages
	public static String unauthorizedMessage = "User not authorized to use this service.";

	public static final String STATUS_SUCCESS = "SUCCESS";
	public static final String STATUS_FAILURE = "FAILURE";
	public static final String STATUS_UNKNOWN = "UNKNOWN";

	public static final String MESSAGE_BOOKED = "BOOKED";
	public static final String MESSAGE_OVERBOOKED = "OVERBOOKED";
	public static final String MESSAGE_ALREADY_BOOKED = "ALREADY_BOOKED";
	public static final String MESSAGE_ALREADY_OVERBOOKED = "ALREADY_OVERBOOKED";
	public static final String MESSAGE_EVENT_NOT_OPEN = "EVENT_NOT_OPEN";
	public static final String MESSAGE_OVER_QUOTA = "OVER_QUOTA";
	public static final String MESSAGE_NO_BADGE_ROLE = "NO_BADGE_ROLE";
	public static final String MESSAGE_CONFLICTING_SCHEDULE = "CONFLICTING_SCHEDULE";
	public static final String MESSAGE_TEEN_RESTRICTION = "TEEN_RESTRICTION";
	public static final String MESSAGE_VOLUNTEER_RESTRICTION = "VOLUNTEER_RESTRICTION";
	public static final String MESSAGE_VENDOR_RESTRICTION = "VENDOR_RESTRICTION";

	public static final Integer STATUS_CODE_BOOKED = 1;
	public static final Integer STATUS_CODE_CANCELLED = 2;
	public static final Integer STATUS_CODE_DELETED = 3;
	public static final Integer STATUS_CODE_OVERBOOKED = 4;
	public static final Integer STATUS_CODE_ALREADY_BOOKED = 5;
	public static final Integer STATUS_CODE_ALREADY_OVERBOOKED = 6;

	public static final String DEFAULT_BOOKING_DATE_FORMAT = "yyyy-MM-dd";
	public static final String DEFAULT_BOOKING_START_DATE = "2021-10-17";
	public static final String DEFAULT_BOOKING_END_DATE = "2021-10-17";
	public static final String DEFAULT_BOOKING_RSVP_DATE = "2021-10-21";

	public static final String DEFAULT_BOOKING_TIME_FORMAT = "hh:mm a";
	public static final String DEFAULT_BOOKING_START_TIME = "12:00 AM";
	public static final String DEFAULT_BOOKING_END_TIME = "12:00 AM";

	public static final String DEFAULT_WP_BOOKING_START_TIME = "12:00 AM";
	public static final String DEFAULT_WP_BOOKING_END_TIME = "12:00 AM";
	public static final String DEFAULT_WP_BOOKING_RSVP_TIME = "12:00 AM";

}
