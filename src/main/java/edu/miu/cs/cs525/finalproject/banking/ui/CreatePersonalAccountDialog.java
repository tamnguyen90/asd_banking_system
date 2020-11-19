package edu.miu.cs.cs525.finalproject.banking.ui;

import edu.miu.cs.cs525.finalproject.banking.domain.CompanyCheckingsAccount;
import edu.miu.cs.cs525.finalproject.banking.domain.CompanySavingsAccount;
import edu.miu.cs.cs525.finalproject.banking.domain.PersonalCheckingsAccount;
import edu.miu.cs.cs525.finalproject.banking.domain.PersonalSavingsAccount;
import edu.miu.cs.cs525.finalproject.framework.domain.*;
import edu.miu.cs.cs525.finalproject.framework.ui.MainFrame;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreatePersonalAccountDialog extends CreateBankingAccountDialog {
    private static final String TITLE = "Add Personal Account";
    private static final String[] FIELDS = {"Account #", "Name", "Street", "City", "State", "Zip", "Email", "Birthday"};

    private JTextField txtBirthdate;
    public CreatePersonalAccountDialog(MainFrame mainFrame) {
        super(mainFrame, TITLE, FIELDS);
    }

    @Override
    protected void populateAdditionalFields() {
        this.txtBirthdate = new JTextField();
        getContentPane().add(txtBirthdate);
        txtBirthdate.setBounds(120,240,156,20);
    }

    @Override
    protected Customer getCustomer() {
        Address address = new Address(txtStreet.getText(), txtCity.getText(), txtState.getText(), txtZip.getText());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = txtBirthdate.getText();
        LocalDate birthday = null;
        if (date != null) {
            try {
                birthday = LocalDate.parse(date, formatter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Customer customer = new Person(txtName.getText(), txtEmail.getText(), address, birthday);
        return customer;
    }

    @Override
    public Account getAccount() {
        Customer customer = getCustomer();
        String accountNbr = txtAccountNbr.getText();
        Account account = null;
        String type = radioCheckings.isSelected() ? "Checkings" : "Savings";
        if ("Checkings".equals(type)) {
            account = new PersonalCheckingsAccount(accountNbr, type, customer);
        } else if ("Savings".equals(type)) {
            account = new PersonalSavingsAccount(accountNbr, type, customer);
        }
        return account;
    }
}
