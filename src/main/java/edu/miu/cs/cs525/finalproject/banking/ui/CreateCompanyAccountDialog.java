package edu.miu.cs.cs525.finalproject.banking.ui;

import edu.miu.cs.cs525.finalproject.banking.domain.CompanyCheckingsAccount;
import edu.miu.cs.cs525.finalproject.banking.domain.CompanySavingsAccount;
import edu.miu.cs.cs525.finalproject.framework.domain.Account;
import edu.miu.cs.cs525.finalproject.framework.domain.Address;
import edu.miu.cs.cs525.finalproject.framework.domain.Company;
import edu.miu.cs.cs525.finalproject.framework.domain.Customer;
import edu.miu.cs.cs525.finalproject.framework.ui.MainFrame;

import javax.swing.*;

public class CreateCompanyAccountDialog extends CreateBankingAccountDialog {
    private static final String TITLE = "Add Company Account";
    private static final String[] FIELDS = {"Account #", "Name", "Street", "City", "State", "Zip", "Email", "No of Employees"};

    private JTextField txtNoOfEmp;

    public CreateCompanyAccountDialog(MainFrame mainFrame) {
        super(mainFrame, TITLE, FIELDS);
    }

    @Override
    protected void populateAdditionalFields() {
        this.txtNoOfEmp = new JTextField();
        getContentPane().add(txtNoOfEmp);
        txtNoOfEmp.setBounds(120,240,156,20);
    }

    @Override
    protected Customer getCustomer() {
        Address address = new Address(txtStreet.getText(), txtCity.getText(), txtState.getText(), txtZip.getText());
        Customer customer = new Company(txtName.getText(), address, txtEmail.getText(), Integer.parseInt(txtNoOfEmp.getText()));
        return customer;
    }

    @Override
    public Account getAccount() {
        Customer customer = getCustomer();
        String accountNbr = txtAccountNbr.getText();
        Account account = null;
        String type = radioCheckings.isSelected() ? "Checkings" : "Savings";
        if ("Checkings".equals(type)) {
            account = new CompanyCheckingsAccount(accountNbr, type, customer);
        } else if ("Savings".equals(type)) {
            account = new CompanySavingsAccount(accountNbr, type, customer);
        }
        return account;
    }
}
