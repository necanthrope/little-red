package org.littlered.dataservices.unitTests;

import org.openapitools.client.api.OrderApi;
import org.openapitools.client.auth.HttpBearerAuth;
import org.openapitools.client.model.ListOrdersbyEventIDresponse;
import org.openapitools.client.model.Order;
import org.testng.annotations.Test;

import java.util.logging.Logger;

public class EventbriteTests {

	private String eventID = "391474790637";
	private String apiKey = "XDAPRWGTYCXDSNF723";
	private String eventbritePrivateToken = "IFNRDYFWDM7J3RPNUQPK";

	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Test
	public void testEventbrite() throws Exception {
		OrderApi orderApi = new OrderApi();
		HttpBearerAuth auth = (HttpBearerAuth) orderApi.getApiClient().getAuthentications().get("httpBearer");
		auth.setBearerToken(eventbritePrivateToken);
		orderApi.getApiClient().setDebugging(true);
		ListOrdersbyEventIDresponse response = orderApi.listOrdersbyEventID(eventID, null, null, null,  null,
				null, null, "attendees");

		for (Order order : response.getOrders()) {
			Order fullOrder = orderApi.retrieveOrderbyID(order.getId());
			logger.info("got order " + fullOrder.getId());
		}

		logger.info("Done!");
	}

}
