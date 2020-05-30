import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class AccountSelection extends JFrame 
{
	JPanel JSelectionPanel, JProceedPanel;
	JLabel JMessage;
	JRadioButton JSaving, JCurrent;
	ButtonGroup Grp1;
	JButton JNext, JCancel;
	int count = 0;
	public AccountSelection()
	{
		this.setVisible(true);
		this.setBounds(100, 100, 1000, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JNext = new JButton("Next");
		JCancel = new JButton("Cancel");
		JNext.setFont(new Font("Calibri", Font.BOLD, 25));
		JNext.setBackground(Color.black);
		JNext.setForeground(Color.orange);
		JCancel.setFont(new Font("Calibri", Font.BOLD, 25));
		JCancel.setBackground(Color.black);
		JCancel.setForeground(Color.orange);
		JNext.addActionListener(e -> actionOnClickingNext(e));
		JCancel.addActionListener(e -> actionOnClickingCancel(e));
				
		JSelectionPanel = new JPanel();
		JProceedPanel = new JPanel();
		JSelectionPanel.setLayout(new GridLayout(4, 3));
		JProceedPanel.setLayout(new GridLayout(1, 2));
		
		JMessage = new JLabel("Select your type of Account: ");
		JMessage.setFont(new Font("Calibri", Font.BOLD, 60));
		JMessage.setForeground(Color.green);
		JSelectionPanel.setBackground(Color.black);
		
		JSaving = new JRadioButton("Saving Account");
		JCurrent = new JRadioButton("Current Account");
		JSaving.setFont(new Font("Calibri", Font.BOLD, 30));
		JCurrent.setFont(new Font("Calibri", Font.BOLD, 30));
		JSaving.setForeground(Color.green);
		JCurrent.setForeground(Color.green);
		JSaving.setBackground(Color.black);
		JCurrent.setBackground(Color.black);
		
		Grp1 = new ButtonGroup();
		Grp1.add(JSaving);
		Grp1.add(JCurrent);
		
		JProceedPanel.add(JNext);
		JProceedPanel.add(JCancel);
			
		JSelectionPanel.add(JMessage);
		JSelectionPanel.add(JSaving);
		JSelectionPanel.add(JCurrent);
		JSelectionPanel.add(JProceedPanel);
	   
		
		getContentPane().setBackground(Color.black);
		this.setTitle("Choose Account Type");
		getContentPane().add(JSelectionPanel);
	}
	
	public void actionOnClickingNext(ActionEvent e)
	{
		count = count + 1;
		if(count > 3)
		{
			System.exit(0);
		}
		new Verification();
	}
	
	public void actionOnClickingCancel(ActionEvent e)
	{
		JOptionPane.showMessageDialog(null, "Happy to help you(Bye Bye)");
		System.exit(0);
	}
}
