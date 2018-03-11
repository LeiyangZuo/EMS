package cn.EMS.Model;

public class CompulsoryCourseModel {
	
	private long ID;
	private long CourseId;
	private int DepartmentId;
	private boolean IsInUse;
	private int Term;
	private String StudentId;
	private int Score;
	
	

	public String getStudentId() {
	return StudentId;
	}
	public void setStudentId(String studentId) {
		StudentId = studentId;
	}
	public int getScore() {
		return Score;
	}
	public void setScore(int score) {
		Score = score;
	}
	public int getTerm() {
		return Term;
	}
	public void setTerm(int term) {
		Term = term;
	}
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public long getCourseId() {
		return CourseId;
	}
	public void setCourseId(long courseId) {
		CourseId = courseId;
	}
	public int getDepartmentId() {
		return DepartmentId;
	}
	public void setDepartmentId(int departmentId) {
		DepartmentId = departmentId;
	}
	public boolean isIsInUse() {
		return IsInUse;
	}
	public void setIsInUse(boolean isInUse) {
		IsInUse = isInUse;
	}
	
}
