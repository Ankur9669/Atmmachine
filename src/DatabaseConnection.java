import java.sql.*;
public class DatabaseConnection 
{
	Connection cn = null;
	public void connection() throws Exception
	{
		Class.forName("org.postgresql.Driver");
		cn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
	}
	
	public void executeQuery(String sql) throws Exception
	{
		Statement stmt = cn.createStatement();
		stmt.execute(sql);
		
	}
	
	public ResultSet executeAndReturn(String sql) throws Exception
	{
		Statement stmt = cn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		//rs.next();
		return rs;
	}
}
