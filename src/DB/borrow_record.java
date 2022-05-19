package DB;

public class borrow_record {
private int borrow_record_id;
private int book_id;
private int reader_id;
private String start_date;
private String end_date;
public int getBorrow_record_id() {
	return borrow_record_id;
}
public void setBorrow_record_id(int borrow_record_id) {
	this.borrow_record_id = borrow_record_id;
}
public int getBook_id() {
	return book_id;
}
public void setBook_id(int book_id) {
	this.book_id = book_id;
}
public int getReader_id() {
	return reader_id;
}
public void setReader_id(int reader_id) {
	this.reader_id = reader_id;
}
public String getStart_date() {
	return start_date;
}
public void setStart_date(String start_date) {
	this.start_date = start_date;
}
public String getEnd_date() {
	return end_date;
}
public void setEnd_date(String end_date) {
	this.end_date = end_date;
}
}
