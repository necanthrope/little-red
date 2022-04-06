package org.littlered.dataservices.rest.controller.eventManager;

import org.littlered.dataservices.entity.eventManager.EmEvents;
import org.littlered.dataservices.service.EventsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Jeremy on 3/25/2017.
 */
@RestController
@RequestMapping("/events/year")
@Api(description = "Operations pertaining to events for previous years.")
public class EventsForYearController {

	@Autowired
	private EventsService eventsService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@ApiOperation(value = "Count of events for a given year", response = Long.class)
	@RequestMapping(value = "/{year}/count", method = RequestMethod.GET, produces = "application/json")
	public Long getCountForYear(@PathVariable("year") Long year,
								HttpServletResponse response) throws Exception {
		return eventsService.getCountForYear(year);
	}

	@ApiOperation(value = "Find all events for the current user for a given year.", response = Long.class, responseContainer = "List")
	@RequestMapping(value = "/{year}/me", method = RequestMethod.GET, produces = "application/json")
	public Iterable<Long> findForMeForYear(@PathVariable("year") Long year, HttpServletResponse response) throws Exception {
		Iterable<Long> events = eventsService.findForMe(year);
		return events;
	}

	@ApiOperation(value = "Find all events for a given year.", response = EmEvents.class, responseContainer = "Iterable")
	@RequestMapping(value = "/{year}/page/{length}/{offset}", method = RequestMethod.GET, produces = "application/json")
	public Iterable<EmEvents> findByYear(@PathVariable("year") Long year,
										 @PathVariable("length") Long length,
										 @PathVariable("offset") Long offset,
										 HttpServletResponse response) throws Exception {
		Iterable<EmEvents> events = eventsService.findAllForYearPaginated(year, length, offset);
		return events;
	}

	@ApiOperation(value = "Find all events for a given year.", response = EmEvents.class, responseContainer = "Iterable")
	@RequestMapping(value = "/{year}/all", method = RequestMethod.GET, produces = "application/json")
	public Iterable<EmEvents> findByYear(@PathVariable("year") Long year, HttpServletResponse response) throws Exception {
		Iterable<EmEvents> events = eventsService.findAllForYear(year);
		return events;
	}

	@ApiOperation(value = "Find all events for a given year, for a category.", response = EmEvents.class, responseContainer = "Iterable")
	@RequestMapping(value = "/{year}/category/{category}", method = RequestMethod.GET, produces = "application/json")
	public Iterable<EmEvents> findByCategory(@PathVariable("year") Long year,
											 @PathVariable("category") String category, HttpServletResponse response) throws Exception {
		Iterable<EmEvents> events = eventsService.findAllForCategory(category);
		return events;
	}

}
