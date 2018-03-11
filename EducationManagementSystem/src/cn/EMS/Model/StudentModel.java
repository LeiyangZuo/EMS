package cn.EMS.Model;

import java.util.Date;

public class StudentModel {
	
	private String StudentId;
	private String Name;
	private int DepartmentId;
	private boolean Gender;
	private Date StartTime;
	private String Password;
	private int MajorId;
	
	
	public int getMajorId() {
		return MajorId;
	}
	public void setMajorId(int majorId) {
		MajorId = majorId;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getStudentId() {
		return StudentId;
	}
	public void setStudentId(String studentId) {
		StudentId = studentId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getDepartmentId() {
		return DepartmentId;
	}
	public void setDepartmentId(int departmentId) {
		DepartmentId = departmentId;
	}
	public boolean getGender() {
		return Gender;
	}
	public void setGender(boolean gender) {
		Gender = gender;
	}
	public Date getStartTime() {
		return StartTime;
	}
	public void setStartTime(Date startTime) {
		StartTime = startTime;
	}


}
