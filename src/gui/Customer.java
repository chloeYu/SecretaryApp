 package gui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Customer extends JFrame implements ActionListener {
	private JButton button1,button2,button3;

	public Customer()
	{
		Container con=getContentPane();
		setLayout(new FlowLayout());
		button1=new JButton("BookTicket");
		button2=new JButton("CanceTicket");
		button3=new JButton("Exit");
		con.add(button1);
		con.add(button2);
		con.add(button3);
		setVisible(true);
		setSize(150,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		button3.addActionListener(this);
	}
	public static void main(String args[])
	{
		new Customer();
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==button3)
		{
			setVisible(false);
		}
		if(ae.getSource()==button2)
		{
			
		}
	}
}
 