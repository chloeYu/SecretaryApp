package gui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import beans.CustomerBean;;
@SuppressWarnings("serial")
public class RegisterCustomer extends JFrame implements ActionListener {
	ArrayList<CustomerBean> list;
	private JButton button1,button2,button3,button4;
	private JTextField f1;
	private JTextField f2;
	private  JTextArea a1;
	private JTextField password;

	private JTextField email,mobno;
	
	private JLabel label1,label2,label3,label4,label5,label6;
	public RegisterCustomer()
	{
		Container con=getContentPane();
		setLayout(new FlowLayout());
		button1 =new JButton("Register");
		f1=new JTextField(15);
		f2=new JTextField(15);
		a1=new JTextArea(2,15);
		password=new JTextField(15);
		button2=new JButton("Display");
		button3=new JButton("Reset");
		email=new JTextField(15);
		mobno=new JTextField(15);
		label1=new JLabel("Name");
		label2=new JLabel("sex");
		label3=new JLabel("Address");
		label4=new JLabel("Password");
		label5=new JLabel("Email");
		label6=new JLabel("Mobileno");
		button4 =new JButton("Delete");
		con.add(label1);
		con.add(f1);
		con.add(label2);
		con.add(f2);
		con.add(label3);
		con.add(a1);
		con.add(label4);
		con.add(password);
		con.add(label5);
		con.add(email);
		con.add(label6);
		con.add(mobno);
		
		con.add(button1);
		con.add(button2);
		con.add(button3);
		
		setVisible(true);
		setSize(200,400);
	button1.addActionListener(this);
	button2.addActionListener(this);
	button3.addActionListener(this);
	button4.addActionListener(this);
		
		
	}
	public static void main(String args[])
	{
		new RegisterCustomer();
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if((f1.getText().equals("")||f2.getText().equals("")||a1.getText().equals("")||email.getText().equals("")||mobno.getText().equals("")||password.getText().equals("")))
		{
			JOptionPane.showConfirmDialog(this, "field should not be empty");
		}
		else{
			if(ae.getSource()==button3)
			{
				
					f1.setText("");
					f2.setText("");
					a1.setText("");
					email.setText("");
					mobno.setText("");
					password.setText("");
					
			}
			
			if(ae.getSource()==button2)
			{
				new CustomerDb();
			}
			 if(ae.getSource()==button1)
			 {
				int con=JOptionPane.showConfirmDialog(this, "Are You Sure to Register?");
				if(con==JOptionPane.YES_OPTION)
				{
				String a=(f1.getText());
				String b=(f2.getText());
				String c=(a1.getText());
				String d=(password.getText());
				int e=Integer.parseInt(mobno.getText());
				String f=(email.getText());
			     
		try
		{
            try
			{
				FileInputStream fin=new FileInputStream("Registration.dat");
				ObjectInputStream oin=new ObjectInputStream(fin);
				list=(ArrayList<CustomerBean>)oin.readObject();
			}catch(Exception e1)
			{
				list=new ArrayList<CustomerBean>();
			}
			
			
			CustomerBean r= new CustomerBean(a,b,c,d,f,e);
			list.add(r);
			
			FileOutputStream fout=new FileOutputStream("Registration.dat");
			ObjectOutputStream oout=new ObjectOutputStream(fout);
			oout.writeObject(list);
			JOptionPane.showMessageDialog(this, "Data Saved Successfully...");
			
			}
		catch(Exception e1){
			System.out.println("Error : "+e1);
		}
		
	}}}}
			 
			 		
			 
				
	
	
		
	}


