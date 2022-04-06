package org.littlered.dataservices.rest.params.eventManager;

/**
 * Created by Jeremy on 4/2/2017.
 */
public class EventFind {

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "EventFind{" +
				"id=" + id +
				'}';
	}
}
