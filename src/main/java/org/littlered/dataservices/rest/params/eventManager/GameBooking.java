package org.littlered.dataservices.rest.params.eventManager;

import java.io.Serializable;

/**
 * Created by Jeremy on 4/30/2017.
 */
public class GameBooking implements Serializable {

	@Override
	public String toString() {
		return "GameBooking{" +
				"gameId=" + gameId +
				", uuid='" + uuid + '\'' +
				'}';
	}

	private Long gameId;
	private String uuid;

	public GameBooking() {};

	public GameBooking(Long gameId, String uuid) {
		this.gameId = gameId;
		this.uuid = uuid;
	}

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
