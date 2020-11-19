package edu.miu.cs.cs525.finalproject.framework.ui;

import edu.miu.cs.cs525.finalproject.framework.domain.Account;
import edu.miu.cs.cs525.finalproject.framework.ui.command.Command;

import javax.swing.*;
import java.awt.*;

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
}
