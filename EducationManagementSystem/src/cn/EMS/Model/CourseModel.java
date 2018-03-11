package cn.EMS.Model;

import java.util.Date;

public class CourseModel {
	
	private long CourseId;
	private String Name;
	private String Description;
	private String TeacherId;
	private int Period;
	private String ClassTime;
	private boolean IsCompulsoryCourse;
	private int StopWeek;
	private int AddWeek;
	
	
	public int getStopWeek() {
		return StopWeek;
	}
	public void setStopWeek(int stopWeek) {
		StopWeek = stopWeek;
	}
	public int getAddWeek() {
		return AddWeek;
	}
	public void setAddWeek(int addWeek) {
		AddWeek = addWeek;
	}
	
	public boolean isIsCompulsoryCourse() {
		return IsCompulsoryCourse;
	}
	public void setIsCompulsoryCourse(boolean isCompulsoryCourse) {
		IsCompulsoryCourse = isCompulsoryCourse;
	}
	public long getCourseId() {
		return CourseId;
	}
	public void setCourseId(long courseId) {
		CourseId = courseId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getTeacherId() {
		return TeacherId;
	}
	public void setTeacherId(String teacherId) {
		TeacherId = teacherId;
	}
	public int getPeriod() {
		return Period;
	}
	public void setPeriod(int period) {
		Period = period;
	}
	public String getClassTime() {
		return ClassTime;
	}
	public void setClassTime(String classTime) {
		ClassTime = classTime;
	}

	
}
