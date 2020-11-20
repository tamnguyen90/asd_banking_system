package edu.miu.cs.cs525.finalproject.framework.ui;

import edu.miu.cs.cs525.finalproject.framework.domain.Account;
import edu.miu.cs.cs525.finalproject.framework.ui.command.Command;
import edu.miu.cs.cs525.finalproject.framework.ui.command.CreateAccountCommand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class CreateAccountDialog extends JDialog {
    protected MainFrame mainFrame;
    public CreateAccountDialog(MainFrame mainFrame, String title, String[] labels) {
        super(mainFrame);
        this.mainFrame = mainFrame;

        setTitle(title);
        setModal(true);
        getContentPane().setLayout(null);
        setSize(298,339);
        setVisible(false);
        setResizable(false);
        
        init(labels);

    }

    private void init(String[] labels) {
        if (labels != null && labels.length > 0) {
            int xLabel = 12, yLabel = 72;
            for (String label : labels) {
                JLabel jLabel = new JLabel();
                jLabel.setText(label);
                getContentPane().add(jLabel);
                jLabel.setForeground(Color.black);
                jLabel.setBounds(xLabel,yLabel,120,24);
                yLabel += 24;
            }
        }

        populateInputFields();
    }

    protected boolean executeCommand(Command command) {
        return command.execute();
    }

    public abstract Account getAccount();

    protected abstract void populateInputFields();

    public class CreateAccountAction implements ActionListener {

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
                JOptionPane.showMessageDialog((Component) e.getSource(), "The account is existing in the system or invalid.","Fail",JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public class CancelAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }
}
