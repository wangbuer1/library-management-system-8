package DB;

public class punish_record {
private int punish_record_id;
private int borrow_record_id;
private int extra_days;
public int reader_id;
public String start_date;
public String end_date;
public int bookid;
public String book_name;
public String author;
public String publisher;

public int getPunish_record_id() {
	return punish_record_id;
}
public void setPunish_record_id(int punish_record_id) {
	this.punish_record_id = punish_record_id;
}
public int getBorrow_record_id() {
	return borrow_record_id;
}
public void setBorrow_record_id(int borrow_record_id) {
	this.borrow_record_id = borrow_record_id;
}
public int getExtra_days() {
	return extra_days;
}
public void setExtra_days(int extra_days) {
	this.extra_days = extra_days;
}

}
