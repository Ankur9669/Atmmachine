import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
public class ChoiceScreen extends JFrame
{
	JPanel JMainPanel, JChoicePanel, JWelcomePanel, JProceedPanel;
	JButton JWelcomeButton, JNext, JCancel;
	JRadioButton JCheckBalance, JWithdrawMoney, JDepositMoney;
	ButtonGroup Grp1;
	String name = "";
	public ChoiceScreen(String customerId)
	{
	
		this.setVisible(true);
		this.setBounds(100, 100, 1000, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try
		{
			name = getName(customerId);
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.toString());
		}
		
		
		JWelcomeButton = new JButton("Welcome: " + name);
		JNext = new JButton("Next");
		JCancel = new JButton("Cancel");
		JWelcomeButton.setFont(new Font("Calibri", Font.BOLD, 80));
		JNext.setFont(new Font("Calibri", Font.BOLD, 40));
		JCancel.setFont(new Font("Calibri", Font.BOLD, 40));
		JWelcomeButton.setBackground(Color.black);
		JWelcomeButton.setForeground(Color.GREEN);
		JNext.setBackground(Color.black);
		JNext.setForeground(Color.green);
		JCancel.setBackground(Color.black);
		JCancel.setForeground(Color.green);
		JWelcomeButton.setBackground(Color.black);
		JWelcomeButton.setForeground(Color.green);
		JNext.addActionListener(e -> actionOnClickingNext(e, customerId));
		JCancel.addActionListener(e -> actionOnClickingCancel(e));
		
		JCheckBalance = new JRadioButton("Check Balance");
		JWithdrawMoney = new JRadioButton("Withdraw Money");
		JDepositMoney = new JRadioButton("Deposit Money");
		JCheckBalance.setBackground(Color.black);
		JCheckBalance.setForeground(Color.green);
		JWithdrawMoney.setBackground(Color.black);
		JWithdrawMoney.setForeground(Color.green);
		JDepositMoney.setBackground(Color.black);
		JDepositMoney.setForeground(Color.green);
		JCheckBalance.setFont(new Font("Calibri", Font.BOLD, 40));
		JWithdrawMoney.setFont(new Font("Calibri", Font.BOLD, 40));
		JDepositMoney.setFont(new Font("Calibri", Font.BOLD, 40));
		
		Grp1 = new ButtonGroup();
		Grp1.add(JCheckBalance);
		Grp1.add(JWithdrawMoney);
		Grp1.add(JDepositMoney);
		
		JMainPanel = new JPanel();
		JChoicePanel = new JPanel();
		JProceedPanel = new JPanel();
		//JMainPanel.setLayout(new GridLayout(3, 1));
		JMainPanel.setLayout(new BorderLayout());
		JChoicePanel.setLayout(new GridLayout(3, 1));
		JProceedPanel.setLayout(new GridLayout(1, 2));
		
		JChoicePanel.add(JCheckBalance);
		JChoicePanel.add(JWithdrawMoney);
		JChoicePanel.add(JDepositMoney);
		
		JProceedPanel.add(JNext);
		JProceedPanel.add(JCancel);

		JMainPanel.add("North", JWelcomeButton);
		JMainPanel.add("Center", JChoicePanel);
		JMainPanel.add("South", JProceedPanel);

		getContentPane().add(JMainPanel);
		this.setTitle("Choose your task");
	}
	
	public String getName(String customerId) throws Exception
	{
		String name = "";
		DatabaseConnection connect = new DatabaseConnection();
		connect.connection();
		ResultSet rs = connect.executeAndReturn("select name from atmdatabase "
				+ "where customerid = '" + customerId + "'");
		rs.next();			
		name = rs.getString(1);
		return name;
	}
	public void actionOnClickingNext(ActionEvent e, String customerId)
	{
		if(JCheckBalance.isSelected())
		{
			CheckBalance check = new CheckBalance();
			int balance = check.checkAndReturnBalance(customerId);
			JOptionPane.showMessageDialog(null, name + "balance is" + balance);
			new ChoiceScreen(customerId);
		}
		if(JWithdrawMoney.isSelected())
		{
			new WithdrawMoney(customerId, name);
		}
		if(JDepositMoney.isSelected())
		{
			new DepositMoney(customerId, name);
		}
	}
	public void actionOnClickingCancel(ActionEvent e)
	{
		JOptionPane.showMessageDialog(null, "Happy to help you(Bye Bye)");
		System.exit(0);
	}
}
