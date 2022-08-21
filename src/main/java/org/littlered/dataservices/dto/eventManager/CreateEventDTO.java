package org.littlered.dataservices.dto.eventManager;

import org.littlered.dataservices.dto.wordpress.PostmetaDTO;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by Jeremy on 3/19/2017.
 */
public class CreateEventDTO implements Serializable {
	private String eventName;
	private String eventDescription;
	private Long eventCategoryId;
	private String system;
	private String gm;
	private String characters;
	private String minPlayers;
	private String players;
	private String length;
	private String playerAge;
	private String gmAge;
	private String playtest;
	private String schedulingPref;
	private String safetyTools;
	private String format;

	private String userDisplayName;
	private String otherInfo; 				// large string, make LOB
	private String requestPrivateRoom;
	private boolean requestMediaRoom = false;
	private String additionalGms; 			// large string, make LOB
	private String requestMediaEquipment;
	private String additionalRequirements; 	// large string, make LOB
	private String tableType;				// board game thingy
	private boolean contentAdvisory = false;
	private String triggerWarnings;
	private String accessabilityOptions;
	private String eventFacilitators;

	private Integer runNumberOfTimes = 1; 	// FIGURE OUT THE LOGIC FOR THIS




	//TODO add image upload



	private Set<Long> eventMetadataIds;

	private Set<PostmetaDTO> metadata;

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String postContent) {
		this.eventDescription = postContent;
	}

	public Long getEventCategoryId() {
		return eventCategoryId;
	}

	public void setEventCategoryId(Long eventCategoryId) {
		this.eventCategoryId = eventCategoryId;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getGm() {
		return gm;
	}

	public void setGm(String gm) {
		this.gm = gm;
	}

	public String getPlayers() {
		return players;
	}

	public void setPlayers(String players) {
		this.players = players;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getCharacters() {
		return characters;
	}

	public void setCharacters(String characters) {
		this.characters = characters;
	}

	public String getMinPlayers() {
		return minPlayers;
	}

	public void setMinPlayers(String minPlayers) {
		this.minPlayers = minPlayers;
	}

	public String getPlayerAge() {
		return playerAge;
	}

	public void setPlayerAge(String playerAge) {
		this.playerAge = playerAge;
	}

	public String getGmAge() {
		return gmAge;
	}

	public void setGmAge(String gmAge) {
		this.gmAge = gmAge;
	}

	public String getPlaytest() {
		return playtest;
	}

	public void setPlaytest(String playtest) {
		this.playtest = playtest;
	}

	public String getSchedulingPref() {
		return schedulingPref;
	}

	public void setSchedulingPref(String schedulingPref) {
		this.schedulingPref = schedulingPref;
	}

	public String getSafetyTools() {
		return safetyTools;
	}

	public void setSafetyTools(String safetyTools) {
		this.safetyTools = safetyTools;
	}

	public Set<Long> getEventMetadataIds() {
		return eventMetadataIds;
	}

	public void setEventMetadataIds(Set<Long> eventMetadataIds) {
		this.eventMetadataIds = eventMetadataIds;
	}

	public String getUserDisplayName() {
		return userDisplayName;
	}

	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public String getPrivateRoom() {
		return requestPrivateRoom;
	}

	public void setRequestPrivateRoom(String requestPrivateRoom) {
		this.requestPrivateRoom = requestPrivateRoom;
	}

	public boolean isRequestMediaRoom() {
		return requestMediaRoom;
	}

	public void setRequestMediaRoom(boolean requestMediaRoom) {
		this.requestMediaRoom = requestMediaRoom;
	}

	public String getAdditionalGms() {
		return additionalGms;
	}

	public void setAdditionalGms(String additionalGms) {
		this.additionalGms = additionalGms;
	}

	public String getRequestMediaEquipment() {
		return requestMediaEquipment;
	}

	public void setRequestMediaEquipment(String requestMediaEquipment) {
		this.requestMediaEquipment = requestMediaEquipment;
	}

	public String getAdditionalRequirements() {
		return additionalRequirements;
	}

	public void setAdditionalRequirements(String additionalRequirements) {
		this.additionalRequirements = additionalRequirements;
	}

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public boolean isContentAdvisory() {
		return contentAdvisory;
	}

	public void setContentAdvisory(boolean contentAdvisory) {
		this.contentAdvisory = contentAdvisory;
	}

	public String getTriggerWarnings() {
		return triggerWarnings;
	}

	public void setTriggerWarnings(String triggerWarnings) {
		this.triggerWarnings = triggerWarnings;
	}

	public String getAccessabilityOptions() {
		return accessabilityOptions;
	}

	public void setAccessabilityOptions(String accessabilityOptions) {
		this.accessabilityOptions = accessabilityOptions;
	}

	public Integer getRunNumberOfTimes() {
		return runNumberOfTimes;
	}

	public void setRunNumberOfTimes(Integer runNumberOfTimes) {
		this.runNumberOfTimes = runNumberOfTimes;
	}

	public String getEventFacilitators() {
		return eventFacilitators;
	}

	public void setEventFacilitators(String eventFacilitators) {
		this.eventFacilitators = eventFacilitators;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
}
