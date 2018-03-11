package cn.EMS.Model;

public class AdminModel {

	private String AdminId;
	private String Name;
	private String Password;
	private boolean IsSuper;

	public String getAdminId() {
		return AdminId;
	}
	public void setAdminId(String adminId) {
		AdminId = adminId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public boolean getIsSuper() {
		return IsSuper;
	}
	public void setIsSuper(boolean isSuper) {
		IsSuper = isSuper;
	}

	
}
