package edu.miu.cs.cs525.finalproject.creditcard.ui;

import edu.miu.cs.cs525.finalproject.creditcard.service.CreditCardSystemService;
import edu.miu.cs.cs525.finalproject.creditcard.domain.CreditCardAccount;
import edu.miu.cs.cs525.finalproject.framework.ui.AccountTransactionDialog;
import edu.miu.cs.cs525.finalproject.framework.ui.GenerateReportDialog;
import edu.miu.cs.cs525.finalproject.framework.ui.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

public class CreditCardFrame extends MainFrame {
    private static  final String TITLE = "Credit-card processing Application";
    private static final String[] TABLE_HEADERS = {"Credit Card #", "Exp date", "Name", "City", "Type", "Balance"};
    protected JButton btnCreateAccount;
    protected JButton btnGenerateReport;
    protected JButton btnDeposit;
    protected JButton btnCharge;
    protected JButton btnExit;
    protected MainFrame mainFrame;

    public CreditCardFrame() {
        super(TITLE, TABLE_HEADERS, new CreditCardSystemService());
        this.mainFrame = this;
    }

    @Override
    public void populateButtons(JPanel jPanel) {
        this.btnCreateAccount = new JButton();
        this.btnGenerateReport = new JButton();
        this.btnDeposit = new JButton();
        this.btnCharge = new JButton();
        this.btnExit = new JButton();

        btnCreateAccount.setText("Add Credit-card account");
        jPanel.add(btnCreateAccount);
        btnCreateAccount.setBounds(24,20,192,33);

        btnGenerateReport.setText("Generate Monthly bills");
        btnGenerateReport.setActionCommand("jbutton");
        jPanel.add(btnGenerateReport);
        btnGenerateReport.setBounds(240,20,192,33);

        btnDeposit.setText("Deposit");
        jPanel.add(btnDeposit);
        btnDeposit.setBounds(468,104,96,33);

        btnCharge.setText("Charge");
        jPanel.add(btnCharge);
        btnCharge.setBounds(468,164,96,33);

        btnExit.setText("Exit");
        jPanel.add(btnExit);
        btnExit.setBounds(468,248,96,31);

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitApplication();
            }
        });
        btnCreateAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateCreditCardAccountDialog dialog = new CreateCreditCardAccountDialog(mainFrame);
                dialog.setBounds(450, 20, 300, 330);
                dialog.setVisible(true);
                if (isNewAccount()) {
                    Object[] data = new Object[8];
                    CreditCardAccount newAccount = (CreditCardAccount) dialog.getAccount();
                    String expiredDate = "";
                    if (newAccount.getExpiredDate() != null) {
                        try {
                            expiredDate = newAccount.getExpiredDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        } catch (Exception exception) {

                        }
                    }

                    data[0] = newAccount.getAccountNumber();
                    data[1] = expiredDate;
                    data[2] = newAccount.getCustomer().getName();
                    data[3] = newAccount.getCustomer().getAddress().getCity();
                    data[4] = newAccount.getType();
                    data[5] = "0.0";
                    getModel().addRow(data);
                    getJTable().getSelectionModel().setAnchorSelectionIndex(-1);
                    setNewAccount(false);
                }
            }
        });
        btnGenerateReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String report = mainFrame.getAccountService().generateReport();
                GenerateReportDialog dialog = new GenerateReportDialog(mainFrame, "Credit card billing report", report);
                dialog.setBounds(450, 20, 700, 350);
                dialog.setVisible(true);
            }
        });
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
        btnCharge.addActionListener(new ActionListener() {
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
                        JOptionPane.showMessageDialog(btnCharge, " Credit Card Account " + dialog.getAccountNbr() + " : balance is negative: $"+String.valueOf(updatedAmount )+" !","Warning: negative balance",JOptionPane.WARNING_MESSAGE);
                    }
                }
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
            (new CreditCardFrame()).setVisible(true);
        } catch (Throwable t) {
            t.printStackTrace();
            //Ensure the application exits with an error condition.
            System.exit(1);
        }
    }
}
