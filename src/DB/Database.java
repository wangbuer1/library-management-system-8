package DB;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;




public class Database {
	String driverName="com.mysql.jdbc.Driver";
	String userName="root";
	String userPasswd="123456";
	String dbName="library";
	String url="jdbc:mysql://localhost:3306/"+dbName+"?&serverTimezone=GMT%2B8";
	Connection con=null; 
	Statement s; 
	ResultSet rs;
	private static Database db=null;//单件模式
	private Database() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
	{
	try//与数据库建立连接
		{
			Class.forName(driverName).getDeclaredConstructor().newInstance();
		}catch(ClassNotFoundException e)
		{System.out.print("Error loading Driver,不能加载驱动程序！");}
		try
		{con=DriverManager.getConnection(url,userName,userPasswd);
		}
		catch(SQLException er)
		{System.out.print("Error getConnection,不能连接数据库！");}
	}
//  用于执行各种SQL语句的方法
  private ResultSet execSQL(String sql , Object ...args) 
          throws SQLException
  {
      PreparedStatement pstmt = con.prepareStatement(sql) ;
//      为PreparedStatement对象设置SQL参数
      for (int i = 0 ; i < args.length ; ++ i)
      {
          pstmt.setObject(1 + i, args[i]) ; 
      }
//      运行
      pstmt.execute() ; 
      return  pstmt.getResultSet(); 
  }
  //查找违规记录
  public ArrayList<punish_record> search_punish_record(String reader_id) throws SQLException {
	
   rs=execSQL("SELECT\r\n" + 
   		"	* \r\n" + 
   		"FROM\r\n" + 
   		"	punish_record,\r\n" + 
   		"	borrow_record,\r\n" + 
   		"	book \r\n" + 
   		"WHERE\r\n" + 
   		"	punish_record.borrow_record_id = borrow_record.borrow_record_id \r\n" + 
   		"	AND borrow_record.book_id = book.book_id \r\n" + 
   		"	AND borrow_record.reader_id like ? ORDER BY extra_days ASC; ","%"+reader_id+"%");
   ArrayList<punish_record> a=new ArrayList();
   while(rs.next())
   {
	   punish_record p=new punish_record();
	   p.setPunish_record_id(Integer.parseInt(rs.getString("punish_record_id")));
	   p.reader_id=Integer.parseInt(rs.getString("reader_id"));
	   p.start_date=rs.getString("start_date");
	   if(rs.getString("end_date")==null)
		   p.end_date="尚未归还";
	   else p.end_date=rs.getString("end_date");
	   p.bookid=Integer.parseInt(rs.getString("book_id"));
	   p.book_name=rs.getString("book_name");
	   p.author=rs.getString("author");
	   p.publisher=rs.getString("publisher");
	   if(rs.getString("extra_days")!=null)
	   p.setExtra_days(Integer.parseInt(rs.getString("extra_days")));
	   else p.setExtra_days(-1);
	   a.add(p);
   }
	  return a;
  }
//查找借阅记录
 public ArrayList<punish_record> search_borrow_record(String reader_id,String s) throws SQLException {
		
	  s="%"+s+"%";
	  rs=execSQL("SELECT\r\n" + 
	  		"	* \r\n" + 
	  		"FROM\r\n" + 
	  		"	borrow_record,\r\n" + 
	  		"	book \r\n" + 
	  		"WHERE\r\n" + 
	  		"	reader_id = ? \r\n" + 
	  		"	AND borrow_record.book_id = book.book_id \r\n" + 
	  		"	AND ( book.book_id LIKE ? OR book_name LIKE ? OR publisher LIKE ? OR book.author LIKE ? )\r\n" + 
	  		"	ORDER BY start_date DESC;",reader_id,s,s,s,s);
	   ArrayList<punish_record> a=new ArrayList();
	   while(rs.next())
	   {
		   punish_record p=new punish_record();
		   p.setPunish_record_id(Integer.parseInt(rs.getString("borrow_record_id")));
		   p.reader_id=Integer.parseInt(rs.getString("reader_id"));
		   p.start_date=rs.getString("start_date");
		   if(rs.getString("end_date")==null)
			   p.end_date="尚未归还";
		   else p.end_date=rs.getString("end_date");
		   p.bookid=Integer.parseInt(rs.getString("book_id"));
		   p.book_name=rs.getString("book_name");
		   p.author=rs.getString("author");
		   p.publisher=rs.getString("publisher");
		   a.add(p);
	   }
		  return a;
	  }
  public ArrayList<reader> search_reader(String reader_id) throws SQLException {
		
	   reader_id="%"+reader_id+"%";
	   rs=execSQL("SELECT * FROM reader WHERE reader_id LIKE ? OR reader_name LIKE ?;",reader_id,reader_id);
	   ArrayList<reader> a=new ArrayList();
	   while(rs.next())
	   {
		   reader p=new reader();
		   p.setReader_id(Integer.parseInt(rs.getString("reader_id")));
		   p.setReader_name(rs.getString("reader_name"));
		   p.setPwd(rs.getString("pwd"));
		   p.setReader_sex(rs.getString("reader_sex"));
		   p.setReader_tel(rs.getString("reader_tel"));
           if(contain_blacklist())
		   {
        	   if(rs.getString("blacklist")==null)
        		   p.setBlacklist("0");
        	   else
        	   p.setBlacklist(rs.getString("blacklist"));}
           else {p.setBlacklist("0");}
		   a.add(p);
	   }
		  return a;
	  }
  public ArrayList<reader> search_admin(int id) throws SQLException {
		
	  if(id<=0)
		  rs=execSQL("SELECT * FROM admin;");
	  else
	   rs=execSQL("SELECT * FROM admin WHERE admin_id=?;",id);
	   ArrayList<reader> a=new ArrayList();
	   while(rs.next())
	   {
		   reader p=new reader();
		   p.setReader_id(Integer.parseInt(rs.getString("admin_id")));
		   p.setReader_name(rs.getString("admin_name"));
		   p.setPwd(rs.getString("pwd"));
		   p.setReader_sex(rs.getString("admin_sex"));
		   p.setReader_tel(rs.getString("admin_tel"));
		   a.add(p);
	   }
		  return a;
	  }
  public ArrayList<book> search_book(String s) throws SQLException {
		
	 s="%"+s+"%";
	   rs=execSQL("SELECT * FROM book WHERE book_id LIKE ?\r\n" + 
	   		"	OR book_name LIKE ?\r\n" + 
	   		"	OR publisher LIKE ?\r\n" + 
	   		"	OR author LIKE ?;",s,s,s,s);
	   ArrayList<book> a=new ArrayList();
	   while(rs.next())
	   {
		   book p=new book();
		   p.setBookid(Integer.parseInt(rs.getString("book_id")));
		   p.setBook_name(rs.getString("book_name"));
		   p.setAuthor(rs.getString("author"));
		   p.setPublisher(rs.getString("publisher"));
		   p.setState(rs.getString("state"));
		   if(rs.getString("other_info")!=null)
		   p.setOther_info(rs.getString("other_info"));
		   else p.setOther_info("");
		   a.add(p);
	   }
		  return a;
	  }
//恢复借阅权利
  public void recover_rights(String s) throws SQLException {
	  execSQL("call recover_rights(?)",s);
	  
  }
 //删除读者
 public void delete(String s) throws SQLException {
	  execSQL("delete from reader where reader_id=?;",s); 
  }
  public void delete_book(String s) throws SQLException {
	  execSQL("delete from book where book_id=?;",s);
	  
  }
  
 
  public String checklogin(String id,String pwd,String identity)
  {
	  try
      {
		  String sql;
		  if(identity.equals("reader"))
           sql = "select * from reader where reader_id = ? and  pwd = ? " ;
		  else sql="select * from admin where admin_id = ? and  pwd = ?";
          ResultSet rs = execSQL(sql,id,pwd) ; 
     
          while (rs.next())
          {    
        	  if(contain_blacklist()&&identity.equals("reader"))
        	  {
        		  if(rs.getString("blacklist")!=null&&rs.getString("blacklist").equals("1"))
        		  return "";
        	  }
        	  if(identity.equals("reader"))
              return rs.getString("reader_name");
        	  else return rs.getString("admin_name");
          } 
      }
      catch(Exception e)
      {
          e.printStackTrace();
          return "" ; 
      }
return "";
  }
 //是否有未处理的违规记录 
  public boolean is_punished(String id) throws SQLException
  {
	  rs=execSQL("SELECT\r\n" + 
	  		"	* \r\n" + 
	  		"FROM\r\n" + 
	  		"	punish_record,\r\n" + 
	  		"	borrow_record \r\n" + 
	  		"WHERE\r\n" + 
	  		"	punish_record.borrow_record_id = borrow_record.borrow_record_id \r\n" + 
	  		"	AND reader_id = ? \r\n" + 
	  		"	AND extra_days IS NULL;",id);
	  while(rs.next()) return true;
	  return false;
	  
  }
 //借阅量是否超标
 public boolean is_full(String id) throws SQLException
  {
	  rs=execSQL("SELECT\r\n" + 
	  		"	COUNT(*) max \r\n" + 
	  		"FROM\r\n" + 
	  		"	borrow_record \r\n" + 
	  		"WHERE\r\n" + 
	  		"	borrow_record.reader_id =? \r\n" + 
	  		"	AND borrow_record.end_date IS NULL;",id);
	  while(rs.next()) 
		  {
		  if(Integer.parseInt(rs.getString("max"))>=3)
		  return true;
		  }
	  return false;
	  
  }
  public int insertreader(String pwd, String name,String sex,String tel) throws SQLException
{
	int id=-1;
	rs=execSQL("select max(reader_id) m from reader");
	while(rs.next())
	{
		id=Integer.parseInt(rs.getString("m"));
		id=id+1;
	}
	if(id!=-1)
	{
		rs=execSQL("insert into reader(reader_id,pwd,reader_name,reader_sex,reader_tel) values(?,?,?,?,?)",id,pwd,name,sex,tel);
	}
	return id;
	
}
  public void modify_info(String id,String pwd, String name,String sex,String tel,String identity) throws SQLException
{
	  if(identity.equals("reader"))
	  {
		  execSQL("Update reader set reader_name=? WHERE reader_id=?;",name,id);
		  execSQL("Update reader set reader_sex=? WHERE reader_id=?;",sex,id);
		  execSQL("Update reader set reader_tel=? WHERE reader_id=?;",tel,id);
		  execSQL("Update reader set pwd=? WHERE reader_id=?;",pwd,id);
	  }
	  else
	  {
		  execSQL("Update admin set admin_name=? WHERE admin_id=?;",name,id);
		  execSQL("Update admin set admin_sex=? WHERE admin_id=?;",sex,id);
		  execSQL("Update admin set admin_tel=? WHERE admin_id=?;",tel,id);
		  execSQL("Update admin set pwd=? WHERE admin_id=?;",pwd,id);
	  }
	
}
 //修改最大借阅时长
 public void max_keeping_days(String time) throws SQLException, InterruptedException
  {
	  
	  execSQL("ALTER EVENT punish\r\n" + 
	  		"DO call update_punish(?);",time);
  }
 
  public void modify_book(String id,String book_name,String author,String publisher,String other_info) throws SQLException
  {
	  execSQL("Update book set book_name=? WHERE book_id=?;",book_name,id);
	  execSQL("Update book set author=? WHERE book_id=?;",author,id);
	  execSQL("Update book set publisher=? WHERE book_id=?;",publisher,id);
	  execSQL("Update book set other_info=? WHERE book_id=?;",other_info,id);
  }
 //是否启用了黑名单
 public boolean contain_blacklist() throws SQLException
  {
	  ResultSet rs;
	  rs=execSQL("SELECT\r\n" + 
	  		"	`COLUMN_NAME` \r\n" + 
	  		"FROM\r\n" + 
	  		"	`INFORMATION_SCHEMA`.`COLUMNS` \r\n" + 
	  		"WHERE\r\n" + 
	  		"	`TABLE_SCHEMA` = 'library' \r\n" + 
	  		"	AND `TABLE_NAME` = 'reader'\r\n" + 
	  		"	AND COLUMN_NAME LIKE 'blacklist';");
	  while(rs.next()) return true;
	  return false;
  }
 //增加黑名单字段
 public void creat_blacklist() throws SQLException
  {
	 execSQL("ALTER TABLE reader ADD blacklist INT ( 1 ) CHECK (blacklist IN ( '0', '1' ));");
  }
 //删除黑名单字段
 public void delete_blacklist() throws SQLException
  {
	 execSQL("ALTER TABLE reader DROP blacklist;");
  }
 //加入/移出黑名单
 public void  add_into_black_list(String id,String choice) throws SQLException
  {
	  execSQL("UPDATE reader SET blacklist=? WHERE reader.reader_id=?;",choice,id);
  }
  public void insert_book(String book_id,String book_name,String author,String publisher,String other_info) throws SQLException
  {
	  execSQL("INSERT book VALUES(?,?,?,?,'在库',?);",book_id,book_name,author,publisher,other_info);
  }
  public void  borrow(String reader_id,String bookid) throws SQLException
  {
	  execSQL("call borrow(?,?);",reader_id,bookid);
  }
  public void  return_book(String reader_id,String bookid) throws SQLException
  {
	  execSQL("call return_book(?,?);",reader_id,bookid);
  }
  public ArrayList<book>  search_borrowed(String reader_id) throws SQLException
  {
	   rs=execSQL("SELECT\r\n" + 
	   		"	* \r\n" + 
	   		"FROM\r\n" + 
	   		"	borrow_record,\r\n" + 
	   		"	book \r\n" + 
	   		"WHERE\r\n" + 
	   		"	borrow_record.book_id = book.book_id \r\n" + 
	   		"	AND reader_id = ? \r\n" + 
	   		"	AND end_date IS NULL;",reader_id);
	   ArrayList<book> a=new ArrayList();
	   while(rs.next())
	   {
		   book p=new book();
		   p.setBookid(Integer.parseInt(rs.getString("book_id")));
		   p.setBook_name(rs.getString("book_name"));
		   p.setAuthor(rs.getString("author"));
		   p.setPublisher(rs.getString("publisher"));
		   p.setState(rs.getString("state"));
		   if(rs.getString("other_info")!=null)
		   p.setOther_info(rs.getString("other_info"));
		   else p.setOther_info("");
		   a.add(p);
	   }
		  return a;
  }
	public static Database getDatabase() throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {//单件模式
		if(db==null)
			try {
				db=new Database();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return db;
	}

}
