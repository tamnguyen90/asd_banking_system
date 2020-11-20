package edu.miu.cs.cs525.finalproject.framework.ui;

import edu.miu.cs.cs525.finalproject.framework.service.AccountService;
import edu.miu.cs.cs525.finalproject.framework.ui.command.Command;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public abstract class MainFrame extends JFrame {
    private DefaultTableModel model;
    private JTable jTable;
    private JScrollPane jScrollPane;
    private JPanel jPanel;
    protected AccountService accountService;
    private boolean newAccount;

    public MainFrame(String title, String[] tableHeaders, AccountService accountService) {

        this.accountService = accountService;
        this.jPanel = new JPanel();
        this.model = new DefaultTableModel();
        this.jScrollPane = new JScrollPane();

        setTitle(title);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0,0));
        setSize(575,310);
        setVisible(false);
        setResizable(false);

        jPanel.setLayout(null);
        getContentPane().add(BorderLayout.CENTER, jPanel);
        jPanel.setBounds(0,0,575,310);

        //Design the table header
        for (String col : tableHeaders) {
            model.addColumn(col);
        }
        jTable = new JTable(model);

        jPanel.add(jScrollPane);
        jScrollPane.setBounds(12,92,444,160);
        jScrollPane.getViewport().add(jTable);
        jTable.setBounds(0, 0, 420, 0);


        populateButtons(jPanel);
    }

    public void exitApplication() {
        try {
            this.setVisible(false);
            this.dispose();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JTable getJTable() {
        return this.jTable;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public JScrollPane getJScrollPane() {
        return jScrollPane;
    }

    public JPanel getJPanel() {
        return jPanel;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public boolean executeCommand(Command command) {
        return command.execute();
    }

    public boolean isNewAccount() {
        return newAccount;
    }

    public void setNewAccount(boolean newAccount) {
        this.newAccount = newAccount;
    }

    public abstract void populateButtons(JPanel jPanel);
}
