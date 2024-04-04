package com.svi.model;

public class SaveRequest {
	private String gameKey;
	private String gameUID;
	private String playerID;
    private String symbol;
    private String location;
    private String dateSave;
	
    public String getGameKey() {
		return gameKey;
	}
	public void setGameKey(String gameKey) {
		this.gameKey = gameKey;
	}
	public String getGameUID() {
		return gameUID;
	}
	public void setGameUID(String gameUID) {
		this.gameUID = gameUID;
	}
	public String getPlayerID() {
		return playerID;
	}
	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDateSave() {
		return dateSave;
	}
	public void setDateSave(String dateSave) {
		this.dateSave = dateSave;
	}
}