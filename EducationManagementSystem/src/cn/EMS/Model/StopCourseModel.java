package cn.EMS.Model;

public class StopCourseModel {
	
	private long StopCourseId;
	private long CourseId;
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

	public long getStopCourseId() {
		return StopCourseId;
	}

	public void setStopCourseId(long stopCourseId) {
		StopCourseId = stopCourseId;
	}

	public long getCourseId() {
		return CourseId;
	}

	public void setCourseId(long courseId) {
		CourseId = courseId;
	}

}
