package kr.gainsys.mc.vo;

public class IdVo {
	
	private String userId;
	private String userLanguage;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserLanguage() {
		return userLanguage;
	}
	public void setUserLanguage(String userLanguage) {
		this.userLanguage = userLanguage;
	}
	
	@Override
	public String toString() {
		return "IdVo [userId=" + userId + ", userLanguage=" + userLanguage + "]";
	}
		
}
