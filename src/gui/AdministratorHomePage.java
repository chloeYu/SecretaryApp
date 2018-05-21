package gui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
@SuppressWarnings("serial")
public class AdministratorHomePage extends JFrame implements ActionListener
{
	private JButton b1,b2,b3,b4,b5,b6,b7;
	
	public AdministratorHomePage()
	{
		Container con=getContentPane();
		setLayout(new FlowLayout());
		b1=new JButton("CUSTOMER DETAILS");
		b2=new JButton("ADD NEW BUS SERVICE");
		b3=new JButton("REMOVE BUS");
		b4=new JButton("REMOVE BUS ROUTE");
	
		b5=new JButton("BUS BOOKING DETAILS");
		
		b6=new JButton("CONFIRM AND BOOK DETAILS");
		b7=new JButton("Exit");
		con.add(b1);
		con.add(b2);
		con.add(b3);
		con.add(b4);
		con.add(b5);
		con.add(b6);
		con.add(b7);
		
		setVisible(true);
		setSize(200,300);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		
		
	}
	public static void main(String args[])
	{
		new AdministratorHomePage();
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		
	
		
	}
	
	
	
	
	
	
	

}
