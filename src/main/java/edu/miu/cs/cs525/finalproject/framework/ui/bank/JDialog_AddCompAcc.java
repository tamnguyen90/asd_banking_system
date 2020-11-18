package edu.miu.cs.cs525.finalproject.framework.ui.bank;
import edu.miu.cs.cs525.finalproject.banking.domain.CompanyCheckingsAccount;
import edu.miu.cs.cs525.finalproject.banking.domain.CompanySavingsAccount;
import edu.miu.cs.cs525.finalproject.framework.domain.Account;
import edu.miu.cs.cs525.finalproject.framework.domain.Address;
import edu.miu.cs.cs525.finalproject.framework.domain.Company;
import edu.miu.cs.cs525.finalproject.framework.domain.Customer;
import edu.miu.cs.cs525.finalproject.framework.service.AccountService;

import java.awt.*;
import javax.swing.*;



public class JDialog_AddCompAcc extends JDialog
{
    private BankFrm parentframe;
    
	public JDialog_AddCompAcc(BankFrm parent)
	{
		super(parent);
		parentframe=parent;
		
		// This code is automatically generated by Visual Cafe when you add
		// components to the visual environment. It instantiates and initializes
		// the components. To modify the code, only use code syntax that matches
		// what Visual Cafe can generate, or Visual Cafe may be unable to back
		// parse your Java file into its visual environment.
		//{{INIT_CONTROLS
		setTitle("Add compamy account");
		setModal(true);
		getContentPane().setLayout(null);
		setSize(298,339);
		setVisible(false);
		JRadioButton_Chk.setText("Checkings");
		JRadioButton_Chk.setActionCommand("Checkings");
		getContentPane().add(JRadioButton_Chk);
		JRadioButton_Chk.setBounds(36,12,84,24);
		JRadioButton_Sav.setText("Savings");
		JRadioButton_Sav.setActionCommand("Savings");
		getContentPane().add(JRadioButton_Sav);
		JRadioButton_Sav.setBounds(36,36,84,24);
		JLabel1.setText("Name");
		getContentPane().add(JLabel1);
		JLabel1.setForeground(Color.black);
		JLabel1.setBounds(12,96,48,24);
		JLabel2.setText("Street");
		getContentPane().add(JLabel2);
		JLabel2.setForeground(Color.black);
		JLabel2.setBounds(12,120,48,24);
		JLabel3.setText("City");
		getContentPane().add(JLabel3);
		JLabel3.setForeground(Color.black);
		JLabel3.setBounds(12,144,48,24);
		JLabel4.setText("State");
		getContentPane().add(JLabel4);
		JLabel4.setForeground(Color.black);
		JLabel4.setBounds(12,168,48,24);
		JLabel5.setText("Zip");
		getContentPane().add(JLabel5);
		JLabel5.setForeground(Color.black);
		JLabel5.setBounds(12,192,48,24);
		JLabel6.setText("No of employees");
		getContentPane().add(JLabel6);
		JLabel6.setForeground(Color.black);
		JLabel6.setBounds(12,216,96,24);
		JLabel7.setText("Email");
		getContentPane().add(JLabel7);
		JLabel7.setForeground(Color.black);
		JLabel7.setBounds(12,240,48,24);
		getContentPane().add(JTextField_NAME);
		JTextField_NAME.setBounds(120,96,156,20);
		getContentPane().add(JTextField_CT);
		JTextField_CT.setBounds(120,144,156,20);
		getContentPane().add(JTextField_ST);
		JTextField_ST.setBounds(120,168,156,20);
		getContentPane().add(JTextField_STR);
		JTextField_STR.setBounds(120,120,156,20);
		getContentPane().add(JTextField_ZIP);
		JTextField_ZIP.setBounds(120,192,156,20);
		getContentPane().add(JTextField_NoOfEmp);
		JTextField_NoOfEmp.setBounds(120,216,156,20);
		getContentPane().add(JTextField_EM);
		JTextField_EM.setBounds(120,240,156,20);
		JButton_OK.setText("OK");
		JButton_OK.setActionCommand("OK");
		getContentPane().add(JButton_OK);
		JButton_OK.setBounds(48,276,84,24);
		JButton_Calcel.setText("Cancel");
		JButton_Calcel.setActionCommand("Cancel");
		getContentPane().add(JButton_Calcel);
		JButton_Calcel.setBounds(156,276,84,24);
		JLabel8.setText("Acc Nr");
		getContentPane().add(JLabel8);
		JLabel8.setForeground(Color.black);
		JLabel8.setBounds(12,72,48,24);
		getContentPane().add(JTextField_ACNR);
		JTextField_ACNR.setBounds(120,72,156,20);
		//}}
	
		//{{REGISTER_LISTENERS
		SymAction lSymAction = new SymAction();
		JButton_OK.addActionListener(lSymAction);
		JButton_Calcel.addActionListener(lSymAction);
		//}}
	}


	//{{DECLARE_CONTROLS
	JRadioButton JRadioButton_Chk = new JRadioButton();
	JRadioButton JRadioButton_Sav = new JRadioButton();
	JLabel JLabel1 = new JLabel();
	JLabel JLabel2 = new JLabel();
	JLabel JLabel3 = new JLabel();
	JLabel JLabel4 = new JLabel();
	JLabel JLabel5 = new JLabel();
	JLabel JLabel6 = new JLabel();
	JLabel JLabel7 = new JLabel();
	JTextField JTextField_NAME = new JTextField();
	JTextField JTextField_CT = new JTextField();
	JTextField JTextField_ST = new JTextField();
	JTextField JTextField_STR = new JTextField();
	JTextField JTextField_ZIP = new JTextField();
	JTextField JTextField_NoOfEmp = new JTextField();
	JTextField JTextField_EM = new JTextField();
	JButton JButton_OK = new JButton();
	JButton JButton_Calcel = new JButton();
	JLabel JLabel8 = new JLabel();
	JTextField JTextField_ACNR = new JTextField();
	//}}


	class SymAction implements java.awt.event.ActionListener
	{
		public void actionPerformed(java.awt.event.ActionEvent event)
		{
			Object object = event.getSource();
			if (object == JButton_OK)
				JButtonOK_actionPerformed(event);
			else if (object == JButton_Calcel)
				JButtonCalcel_actionPerformed(event);
		}
	}

	void JButtonOK_actionPerformed(java.awt.event.ActionEvent event)
	{
       parentframe.accountnr=JTextField_ACNR.getText();
       parentframe.clientName=JTextField_NAME.getText();
       parentframe.street=JTextField_STR.getText();
       parentframe.city=JTextField_CT.getText();
       parentframe.zip=JTextField_ZIP.getText();
       parentframe.state=JTextField_ST.getText();
       Address address = new Address(JTextField_STR.getText(), JTextField_CT.getText(), JTextField_ST.getText(), JTextField_ZIP.getText());
       Customer customer = new Company(JTextField_NAME.getText(), address, JTextField_EM.getText(), Integer.parseInt(JTextField_NoOfEmp.getText()));
       String type = "";
		Account account = null;
       if (JRadioButton_Chk.isSelected()) {
		   parentframe.accountType="Ch";
		   type = "Ch";
		   account = new CompanyCheckingsAccount(JTextField_ACNR.getText(), type, customer);
	   } else {
		   parentframe.accountType="S";
		   type = "S";
		   account = new CompanySavingsAccount(JTextField_ACNR.getText(), type, customer);
	   }
	   parentframe.newaccount=true;
       AccountService accountService = parentframe.getAccountService();
	   accountService.createAccount(customer, account);
	   Account account1 = accountService.getAccount(JTextField_ACNR.getText());
		System.out.println(account1.getAccountNumber() + " - " + account1.getType());
	   dispose();
			 
	}

	void JButtonCalcel_actionPerformed(java.awt.event.ActionEvent event)
	{
		dispose();
			 
	}
}