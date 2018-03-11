package cn.EMS.Model;

public class NewsModel {

	private long NewsId;
	private String Title;
	private String Context;
	private String AuthorId;
	private int AuthorType;

	public long getNewsId() {
		return NewsId;
	}

	public void setNewsId(long newsId) {
		NewsId = newsId;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getContext() {
		return Context;
	}

	public void setContext(String context) {
		Context = context;
	}

	public String getAuthorId() {
		return AuthorId;
	}

	public void setAuthorId(String authorId) {
		AuthorId = authorId;
	}

	public int getAuthorType() {
		return AuthorType;
	}

	public void setAuthorType(int authorType) {
		AuthorType = authorType;
	}

}
