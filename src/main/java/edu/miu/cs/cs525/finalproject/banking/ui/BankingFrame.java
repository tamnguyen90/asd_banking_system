package edu.miu.cs.cs525.finalproject.banking.ui;

import edu.miu.cs.cs525.finalproject.banking.service.BankingSystemService;
import edu.miu.cs.cs525.finalproject.framework.domain.Account;
import edu.miu.cs.cs525.finalproject.framework.domain.Person;
import edu.miu.cs.cs525.finalproject.framework.ui.AccountTransactionDialog;
import edu.miu.cs.cs525.finalproject.framework.ui.CreateAccountDialog;
import edu.miu.cs.cs525.finalproject.framework.ui.GenerateReportDialog;
import edu.miu.cs.cs525.finalproject.framework.ui.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class BankingFrame extends MainFrame {
    private static  final String TITLE = "Banking Application";
    private static final String[] TABLE_HEADERS = {"Account #", "Name", "City", "Customer", "Type", "Amount"};
    private JButton btnCreatePersonalAcct;
    private JButton btnCreateCompanyAcct;
    private JButton btnDeposit;
    private JButton btnWithdraw;
    private JButton btnReport;
    private JButton btnAddInterest;
    private JButton btnExit;
    private MainFrame mainFrame;

    public BankingFrame() {
        super(TITLE, TABLE_HEADERS, new BankingSystemService());
        this.mainFrame = this;
    }

    @Override
    public void populateButtons(JPanel jPanel) {
        this.btnCreatePersonalAcct = new JButton();
        this.btnCreateCompanyAcct = new JButton();
        this.btnDeposit = new JButton();
        this.btnWithdraw = new JButton();
        this.btnAddInterest = new JButton();
        this.btnExit = new JButton();
        this.btnReport = new JButton();

        btnCreatePersonalAcct.setText("Add personal account");
        btnCreatePersonalAcct.setActionCommand("jbutton");
        jPanel.add(btnCreatePersonalAcct);
        btnCreatePersonalAcct.setBounds(24,20,192,33);


        btnCreateCompanyAcct.setText("Add company account");
        btnCreateCompanyAcct.setActionCommand("jbutton");
        jPanel.add(btnCreateCompanyAcct);
        btnCreateCompanyAcct.setBounds(240,20,192,33);


        btnDeposit.setText("Deposit");
        jPanel.add(btnDeposit);
        btnDeposit.setBounds(468,104,96,33);


        btnWithdraw.setText("Withdraw");
        jPanel.add(btnWithdraw);
        btnWithdraw.setBounds(468,148,96,33);

        btnReport.setText("Report");
        jPanel.add(btnReport);
        btnReport.setBounds(468,200,96,33);


        btnAddInterest.setText("Add interest");
        jPanel.add(btnAddInterest);
        btnAddInterest.setBounds(448,20,106,33);


        btnExit.setText("Exit");
        jPanel.add(btnExit);
        btnExit.setBounds(468,248,96,31);



        //Handle click action for Exit button
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitApplication();
            }
        });

        //Handle click action for Create Personal Account button
        btnCreatePersonalAcct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateAccountDialog personalAccountDialog = new CreatePersonalAccountDialog(mainFrame);
                personalAccountDialog.setBounds(450, 20, 300, 330);
                personalAccountDialog.setVisible(true);

                if (isNewAccount()) {
                    Object[] data = new Object[8];
                    Account newAccount = personalAccountDialog.getAccount();
                    data[0] = newAccount.getAccountNumber();
                    data[1] = newAccount.getCustomer().getName();
                    data[2] = newAccount.getCustomer().getAddress().getCity();
                    data[3] = "Personal";
                    data[4] = newAccount.getType();
                    data[5] = "0.0";
                    getModel().addRow(data);
                    getJTable().getSelectionModel().setAnchorSelectionIndex(-1);
                    setNewAccount(false);
                }
            }
        });

        //Handle click action for Create Company Account button
        btnCreateCompanyAcct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateAccountDialog personalAccountDialog = new CreateCompanyAccountDialog(mainFrame);
                personalAccountDialog.setBounds(450, 20, 300, 330);
                personalAccountDialog.setVisible(true);
                if (isNewAccount()) {
                    Object[] data = new Object[8];
                    Account newAccount = personalAccountDialog.getAccount();
                    data[0] = newAccount.getAccountNumber();
                    data[1] = newAccount.getCustomer().getName();
                    data[2] = newAccount.getCustomer().getAddress().getCity();
                    data[3] = "Company";
                    data[4] = newAccount.getType();
                    data[5] = "0.0";
                    getModel().addRow(data);
                    getJTable().getSelectionModel().setAnchorSelectionIndex(-1);
                    setNewAccount(false);
                }
            }
        });

        //Handle click action for Deposit button
        btnDeposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selection = getJTable().getSelectionModel().getMinSelectionIndex();
                if (selection >= 0) {
                    String accountNbr = (String)getModel().getValueAt(selection, 0);
                    AccountTransactionDialog dialog = new AccountTransactionDialog(mainFrame, "Deposit", accountNbr);
                    dialog.setBounds(430, 15, 275, 140);
                    dialog.setVisible(true);

                    // compute new amount
                    double deposit = dialog.getAmount();
                    String currentAmountStr = (String)getModel().getValueAt(selection, 5);
                    double currentAmount = Double.parseDouble(currentAmountStr);
                    double updatedAmount = currentAmount + deposit;
                    getModel().setValueAt(String.valueOf(updatedAmount),selection, 5);
                }
            }
        });

        //Handle click action for Withdraw button
        btnWithdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selection = getJTable().getSelectionModel().getMinSelectionIndex();
                if (selection >= 0) {
                    String accountNbr = (String)getModel().getValueAt(selection, 0);
                    AccountTransactionDialog dialog = new AccountTransactionDialog(mainFrame, "Withdraw", accountNbr);
                    dialog.setBounds(430, 15, 275, 140);
                    dialog.setVisible(true);

                    // compute new amount
                    double withdraw = dialog.getAmount();
                    String currentAmountStr = (String)getModel().getValueAt(selection, 5);
                    double currentAmount = Double.parseDouble(currentAmountStr);
                    double updatedAmount = currentAmount - withdraw;
                    getModel().setValueAt(String.valueOf(updatedAmount ),selection, 5);
                    if (updatedAmount < 0){
                        JOptionPane.showMessageDialog(btnWithdraw, " Account " + dialog.getAccountNbr() + " : balance is negative: $"+String.valueOf(updatedAmount )+" !","Warning: negative balance",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        //Handle click action for Add Interest button
        btnAddInterest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accountService.addInterests();
                JOptionPane.showMessageDialog(btnAddInterest, "Add interest to all accounts successfully","Success", JOptionPane.INFORMATION_MESSAGE);
                Collection<Account> accountList = accountService.getAllAccounts();
                if (accountList != null && accountList.size() > 0) {
                    DefaultTableModel model = getModel();
                    model.getDataVector().removeAllElements();
                    model.fireTableDataChanged();
                    for (Account account : accountList) {
                        Object[] data = new Object[8];
                        data[0] = account.getAccountNumber();
                        data[1] = account.getCustomer().getName();
                        data[2] = account.getCustomer().getAddress().getCity();
                        data[3] = account.getCustomer() instanceof Person ? "Personal" : "Company";
                        data[4] = account.getType();
                        data[5] = account.getBalance();
                        getModel().addRow(data);
                        getJTable().getSelectionModel().setAnchorSelectionIndex(-1);
                    }
                }

            }
        });

        btnReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String report = mainFrame.getAccountService().generateReport();
                GenerateReportDialog dialog = new GenerateReportDialog(mainFrame, "Banking account report", report);
                dialog.setBounds(450, 20, 700, 350);
                dialog.setVisible(true);
            }
        });
    }

    public static void main(String args[]) {
        try {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            //Create a new instance of our application's frame, and make it visible.
            (new BankingFrame()).setVisible(true);
        } catch (Throwable t) {
            t.printStackTrace();
            //Ensure the application exits with an error condition.
            System.exit(1);
        }
    }
}
