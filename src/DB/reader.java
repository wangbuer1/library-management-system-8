package DB;

public class reader {
	private int reader_id;
	private String pwd;
	private String reader_name;
	private String reader_sex;
	private String reader_tel;
	private String blacklist;
	public int getReader_id() {
		return reader_id;
	}
	public void setReader_id(int reader_id) {
		this.reader_id = reader_id;
	}
	public String getReader_name() {
		return reader_name;
	}
	public void setReader_name(String reader_name) {
		this.reader_name = reader_name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getReader_sex() {
		return reader_sex;
	}
	public void setReader_sex(String reader_sex) {
		this.reader_sex = reader_sex;
	}
	public String getReader_tel() {
		return reader_tel;
	}
	public void setReader_tel(String reader_tel) {
		this.reader_tel = reader_tel;
	}
	public String getBlacklist() {
		return blacklist;
	}
	public void setBlacklist(String blacklist) {
		this.blacklist = blacklist;
	}
	
}
