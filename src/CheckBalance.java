import java.sql.*;

import javax.swing.JOptionPane;
public class CheckBalance
{
	public int checkAndReturnBalance(String customerId)
	{
		int balance = 0;
		DatabaseConnection connect = new DatabaseConnection();
		try
		{	
			connect.connection();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.toString());
		}
		try
		{
			ResultSet rs = connect.executeAndReturn("select balance from atmdatabase where customerid = '" + customerId + "'");
			rs.next();
		    balance = Integer.parseInt(rs.getString(1));
		   // new ChoiceScreen(customerId);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.toString());
		}
		return balance;
	}	
			
}
