package edu.miu.cs.cs525.finalproject.creditcard.ui;

import edu.miu.cs.cs525.finalproject.creditcard.domain.BronzeCreditCardAccount;
import edu.miu.cs.cs525.finalproject.creditcard.domain.GoldCreditCardAccount;
import edu.miu.cs.cs525.finalproject.creditcard.domain.SilverCreditCardAccount;
import edu.miu.cs.cs525.finalproject.framework.domain.Account;
import edu.miu.cs.cs525.finalproject.framework.domain.Address;
import edu.miu.cs.cs525.finalproject.framework.domain.Customer;
import edu.miu.cs.cs525.finalproject.framework.domain.Person;
import edu.miu.cs.cs525.finalproject.framework.ui.CreateAccountDialog;
import edu.miu.cs.cs525.finalproject.framework.ui.MainFrame;
import edu.miu.cs.cs525.finalproject.framework.ui.command.CreateAccountCommand;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreateCreditCardAccountDialog extends CreateAccountDialog {
    private static final String TITLE = "Add credit card account";
    private static final String[] FIELDS = {"Name", "Street", "City", "State", "Zip", "Credit Card #", "Exp. Date", "Email"};

    protected JRadioButton radioGold;
    protected JRadioButton radioSilver;
    protected JRadioButton radioBronze;
    protected JTextField txtName;
    protected JTextField txtCity;
    protected JTextField txtState;
    protected JTextField txtStreet;
    protected JTextField txtZip;
    protected JTextField txtCardNbr;
    protected JTextField txtExpiredDate;
    protected JTextField txtEmail;
    protected JButton btnOk;
    protected JButton btnCancel;

    public CreateCreditCardAccountDialog(MainFrame mainFrame) {
        super(mainFrame, TITLE, FIELDS);
    }

    @Override
    protected void populateInputFields() {
        this.radioBronze = new JRadioButton();
        this.radioGold = new JRadioButton();
        this.radioSilver = new JRadioButton();
        this.txtCardNbr = new JTextField();
        this.txtCity = new JTextField();
        this.txtEmail = new JTextField();
        this.txtExpiredDate = new JTextField();
        this.txtName = new JTextField();
        this.txtState = new JTextField();
        this.txtStreet = new JTextField();
        this.txtZip = new JTextField();
        this.btnCancel = new JButton();
        this.btnOk = new JButton();

        radioGold.setText("Gold");
        radioGold.setActionCommand("Checkings");
        getContentPane().add(radioGold);
        radioGold.setBounds(36,12,84,24);

        radioSilver.setText("Silver");
        radioSilver.setActionCommand("Savings");
        getContentPane().add(radioSilver);
        radioSilver.setBounds(36,36,84,24);

        radioBronze.setText("Bronze");
        radioBronze.setActionCommand("Savings");
        getContentPane().add(radioBronze);
        radioBronze.setBounds(36,60,84,24);

        getContentPane().add(txtName);
        txtName.setBounds(120,72,156,20);

        getContentPane().add(txtCity);
        txtCity.setBounds(120,96,156,20);

        getContentPane().add(txtState);
        txtState.setBounds(120,120,156,20);

        getContentPane().add(txtStreet);
        txtStreet.setBounds(120,144,156,20);

        getContentPane().add(txtZip);
        txtZip.setBounds(120,168,156,20);

        getContentPane().add(txtCardNbr);
        txtCardNbr.setBounds(120,192,156,20);

        getContentPane().add(txtExpiredDate);
        txtExpiredDate.setBounds(120,216,156,20);

        getContentPane().add(txtEmail);
        txtEmail.setBounds(120,240,156,20);



        btnOk.setText("OK");
        btnOk.setActionCommand("OK");
        getContentPane().add(btnOk);
        btnOk.setBounds(48,276,84,24);

        btnCancel.setText("Cancel");
        btnCancel.setActionCommand("Cancel");
        getContentPane().add(btnCancel);
        btnCancel.setBounds(156,276,84,24);



        radioGold.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                radioGold.setSelected(true);
                radioBronze.setSelected(false);
                radioSilver.setSelected(false);
            }
        });

        radioSilver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                radioSilver.setSelected(true);
                radioBronze.setSelected(false);
                radioGold.setSelected(false);
            }
        });

        radioBronze.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                radioBronze.setSelected(true);
                radioGold.setSelected(false);
                radioSilver.setSelected(false);
            }
        });

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Account account = getAccount();
                boolean isCreated = false;
                if (account != null) {
                    isCreated = executeCommand(new CreateAccountCommand(account.getCustomer(), account, mainFrame.getAccountService()));
                }

                if (isCreated) {
                    mainFrame.setNewAccount(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(btnOk, "The account is existing in the system or invalid.","Fail",JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }

    @Override
    public Account getAccount() {
        Address address = new Address(txtStreet.getText(), txtCity.getText(), txtState.getText(), txtZip.getText());
        Customer customer = new Person(txtName.getText(), txtEmail.getText(), address, null);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String expiredDateStr = txtExpiredDate.getText();
        LocalDate expiredDate = null;
        try {
            expiredDate = LocalDate.parse(expiredDateStr, formatter);
        } catch (Exception e) {

        }
        String creditCardNbr = txtCardNbr.getText();
        Account account = null;
        if (radioBronze.isSelected()) {
            account = new BronzeCreditCardAccount(creditCardNbr, "Bronze", customer, expiredDate);
        } else if (radioGold.isSelected()) {
            account = new GoldCreditCardAccount(creditCardNbr, "Gold", customer, expiredDate);
        } else if (radioSilver.isSelected()) {
            account = new SilverCreditCardAccount(creditCardNbr, "Silver", customer, expiredDate);
        }
        return account;
    }
}
