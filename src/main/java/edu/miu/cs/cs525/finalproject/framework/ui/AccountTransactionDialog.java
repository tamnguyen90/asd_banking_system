package edu.miu.cs.cs525.finalproject.framework.ui;

import edu.miu.cs.cs525.finalproject.framework.ui.command.Command;
import edu.miu.cs.cs525.finalproject.framework.ui.command.DepositCommand;
import edu.miu.cs.cs525.finalproject.framework.ui.command.WithdrawCommand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountTransactionDialog extends JDialog {
    private MainFrame mainFrame;
    private String accountNbr;
    private double amount;
    private JTextField txtAccountNbr;
    private JTextField txtAmount;
    private JButton btnOk;
    private JButton btnCancel;
    private String action;


    public AccountTransactionDialog(MainFrame mainFrame, String title, String accountNbr) {
        super(mainFrame);
        this.mainFrame = mainFrame;
        this.accountNbr = accountNbr;
        this.action = title;
        this.txtAccountNbr = new JTextField();
        this.txtAmount = new JTextField();
        this.btnOk = new JButton();
        this.btnCancel = new JButton();
        //Populate fields and buttons
        display(title);
    }

    private void display(String title) {
        setTitle(title);
        setModal(true);
        getContentPane().setLayout(null);
        setSize(268,126);
        setVisible(false);

        JLabel lblAccount = new JLabel();
        lblAccount.setText("Account #");
        getContentPane().add(lblAccount);
        lblAccount.setForeground(Color.black);
        lblAccount.setBounds(12,12,100,24);

        JLabel lblAmount = new JLabel();
        lblAmount.setText("Amount");
        getContentPane().add(lblAmount);
        lblAmount.setForeground(Color.black);
        lblAmount.setBounds(12,48,100,24);

        txtAccountNbr.setEditable(false);
        getContentPane().add(txtAccountNbr);
        txtAccountNbr.setBounds(84,12,144,24);
        txtAccountNbr.setText(accountNbr);

        getContentPane().add(txtAmount);
        txtAmount.setBounds(84,48,144,24);

        btnOk.setText("OK");
        btnOk.setActionCommand("OK");
        getContentPane().add(btnOk);
        btnOk.setBounds(36,84,84,24);

        btnCancel.setText("Cancel");
        btnCancel.setActionCommand("Cancel");
        getContentPane().add(btnCancel);
        btnCancel.setBounds(156,84,84,24);

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Command command = null;
                amount = Double.parseDouble(txtAmount.getText());
                if ("Withdraw".equals(action)) {
                    command = new WithdrawCommand(mainFrame.getAccountService(), accountNbr, amount);
                } else if ("Deposit".equals(action)) {
                    command = new DepositCommand(mainFrame.getAccountService(), accountNbr, amount);
                }
                if (command != null) {
                    executeCommand(command);
                }
                dispose();
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public double getAmount() {
        return amount;
    }

    public String getAccountNbr() {
        return accountNbr;
    }

    private void executeCommand(Command command) {
        if (command.execute()) {
            //TODO refresh the UI
        }
    }
}
