package org.vegetto.xml;

public class RoomState {
	private String RoomCode;
	private String RoomName;
	private String RoomType;
	private String AreaID;
	private String TxnState;
	public String getRoomCode() {
		return RoomCode;
	}
	public void setRoomCode(String roomCode) {
		RoomCode = roomCode;
	}
	public String getRoomName() {
		return RoomName;
	}
	public void setRoomName(String roomName) {
		RoomName = roomName;
	}
	public String getRoomType() {
		return RoomType;
	}
	public void setRoomType(String roomType) {
		RoomType = roomType;
	}
	public String getAreaID() {
		return AreaID;
	}
	public void setAreaID(String areaID) {
		AreaID = areaID;
	}
	public String getTxnState() {
		return TxnState;
	}
	public void setTxnState(String txnState) {
		TxnState = txnState;
	}

}
