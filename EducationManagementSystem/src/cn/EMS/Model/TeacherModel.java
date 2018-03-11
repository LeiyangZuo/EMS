package cn.EMS.Model;

public class TeacherModel {
	
	private String TeacherId;
	private String Name;
	private String Password;
	private int DepartmentId;
	
	public String getTeacherId() {
		return TeacherId;
	}
	public void setTeacherId(String teacherId) {
		TeacherId = teacherId;
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
	public int getDepartmentId() {
		return DepartmentId;
	}
	public void setDepartmentId(int departmentId) {
		DepartmentId = departmentId;
	}

	
	
}
