package cn.EMS.Model;

public class PersonalCourseModel {
	
	private long ID;
	private long CourseId;
	private String StudentId;
	private boolean IsInUse;
	private int Term;
	private int Score;
	
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
	public String getStudentId() {
		return StudentId;
	}
	public void setStudentId(String studentId) {
		StudentId = studentId;
	}
	public boolean isIsInUse() {
		return IsInUse;
	}
	public void setIsInUse(boolean isInUse) {
		IsInUse = isInUse;
	}
	

}
