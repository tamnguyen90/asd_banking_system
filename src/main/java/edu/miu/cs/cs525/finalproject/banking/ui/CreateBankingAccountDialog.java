package edu.miu.cs.cs525.finalproject.banking.ui;

import edu.miu.cs.cs525.finalproject.framework.domain.Account;
import edu.miu.cs.cs525.finalproject.framework.domain.Address;
import edu.miu.cs.cs525.finalproject.framework.domain.Customer;
import edu.miu.cs.cs525.finalproject.framework.ui.CreateAccountDialog;
import edu.miu.cs.cs525.finalproject.framework.ui.MainFrame;
import edu.miu.cs.cs525.finalproject.framework.ui.command.CreateAccountCommand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class CreateBankingAccountDialog extends CreateAccountDialog {
    protected JRadioButton radioCheckings;
    protected JRadioButton radioSavings;
    protected JTextField txtName;
    protected JTextField txtCity;
    protected JTextField txtState;
    protected JTextField txtStreet;
    protected JTextField txtZip;
    protected JTextField txtEmail;
    protected JTextField txtAccountNbr;
    protected JButton btnOk;
    protected JButton btnCancel;


    public CreateBankingAccountDialog(MainFrame mainFrame, String title, String[] labels) {
        super(mainFrame, title, labels);
    }

    @Override
    protected void populateInputFields() {

        this.radioCheckings = new JRadioButton();
        this.radioSavings = new JRadioButton();
        this.txtName = new JTextField();
        this.txtCity = new JTextField();
        this.txtState = new JTextField();
        this.txtStreet = new JTextField();
        this.txtZip = new JTextField();
        this.txtEmail = new JTextField();
        this.txtAccountNbr = new JTextField();
        this.btnOk = new JButton();
        this.btnCancel = new JButton();

        radioCheckings.setText("Checkings");
        radioCheckings.setActionCommand("Checkings");
        getContentPane().add(radioCheckings);
        radioCheckings.setBounds(36,12,100,24);
        radioSavings.setText("Savings");
        radioSavings.setActionCommand("Savings");
        getContentPane().add(radioSavings);
        radioSavings.setBounds(36,36,100,24);

        getContentPane().add(txtAccountNbr);
        txtAccountNbr.setBounds(120,72,156,20);

        getContentPane().add(txtName);
        txtName.setBounds(120,96,156,20);

        getContentPane().add(txtCity);
        txtCity.setBounds(120,144,156,20);

        getContentPane().add(txtState);
        txtState.setBounds(120,168,156,20);

        getContentPane().add(txtStreet);
        txtStreet.setBounds(120,120,156,20);

        getContentPane().add(txtZip);
        txtZip.setBounds(120,192,156,20);

        getContentPane().add(txtEmail);
        txtEmail.setBounds(120,216,156,20);

        //In case, there are specific fields of each dialog
        populateAdditionalFields();

        //Buttons
        btnOk.setText("OK");
        btnOk.setActionCommand("OK");
        getContentPane().add(btnOk);
        btnOk.setBounds(48,276,84,24);
        btnCancel.setText("Cancel");
        btnCancel.setActionCommand("Cancel");
        getContentPane().add(btnCancel);
        btnCancel.setBounds(156,276,84,24);


        radioCheckings.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                radioCheckings.setSelected(true);
                radioSavings.setSelected(false);
            }
        });

        radioSavings.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                radioSavings.setSelected(true);
                radioCheckings.setSelected(false);
            }
        });

        //Button listener
        btnOk.addActionListener(new CreateAccountAction());
        btnCancel.addActionListener(new CancelAction());
    }

    protected abstract void populateAdditionalFields();
    protected abstract Customer getCustomer();
}
