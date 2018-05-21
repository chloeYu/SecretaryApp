package gui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import beans.CustomerBean;

@SuppressWarnings("serial")
public class CustomerLogin extends JFrame implements ActionListener{
	JTextField username;
	JPasswordField pass;
	JLabel l1,l2;
	JButton login,exit,reset;
	Container con;
	public CustomerLogin(){
		con=getContentPane();
		setLayout(new FlowLayout());
		l1=new JLabel("ENTER USERNAME :-");
		username=new JTextField(30);
		con.add(l1);
		con.add(username);
		l2=new JLabel("ENTER PASSWORD :-");
		pass=new JPasswordField(30);
		con.add(l2);
		con.add(pass);
		login=new JButton("LOG IN");
		con.add(login);
		login.addActionListener(this);
		exit=new JButton("EXIT");
		con.add(exit);
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});	
		reset=new JButton("Reset");
		con.add(reset);
		reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				username.setText("");
				pass.setText("");
			}
		});	
		setVisible(true);
    	setSize(350,180);
    	setResizable(false);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

	public static void main(String[] args) {
		new CustomerLogin();
	}

	@SuppressWarnings({  "unchecked" })
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(username.getText().equals("")||pass.getPassword().equals(""))
			JOptionPane.showConfirmDialog(this, "Fields should not be empty");
		else
		{
		
		
		ArrayList<CustomerBean> cuslist=new ArrayList<CustomerBean>();
		try{
			
			Object obj;
			FileInputStream fis=new FileInputStream("Registration.dat");
			ObjectInputStream ois=new ObjectInputStream(fis);
			List<CustomerBean> cuslist1=new ArrayList<CustomerBean>();
			while((obj=ois.readObject())!=null){
				cuslist1=(List<CustomerBean>)obj;
		        for(CustomerBean cus:cuslist1)
		        {
		        	 
		        	if(cus.getEmail().equals(username.getText())&&String.valueOf(cus.getPassword()).equals(String.valueOf( pass.getPassword())))
		        	 {
		        	 
		        	  JOptionPane.showMessageDialog(this,"Customer homepage will be displayed ");
		        	new CustomerDb();
		        	  
		        	  }
		        	else
		        		cuslist1.add(cus);
		        	
		        	
		        		JOptionPane.showMessageDialog(this, "Username or/and password incorrect");
		        	 
	
		        }
			}
		        ois.close(); 
			    fis.close();
			}
			 catch(EOFException e1){
					
				   }
				  catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
				  catch (IOException e1) {
						e1.printStackTrace();
					}
				  catch (ClassNotFoundException e1) {
						e1.printStackTrace();
				  }
		     
		        		} 
		}
	}