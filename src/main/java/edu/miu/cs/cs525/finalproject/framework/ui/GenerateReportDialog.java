package edu.miu.cs.cs525.finalproject.framework.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateReportDialog extends JDialog {
    protected MainFrame mainFrame;
    private String report;
    private JScrollPane jScrollPane;
    private JTextArea txtContent;
    private JButton btnOk;

    public GenerateReportDialog(MainFrame mainFrame, String title, String report) {
        super(mainFrame);
        this.report = report;
        this.jScrollPane = new JScrollPane();
        this.txtContent = new JTextArea();
        this.btnOk = new JButton();

        getContentPane().setLayout(null);
        setSize(700,400);
        setVisible(false);
        setResizable(false);
        setTitle(title);
        getContentPane().add(jScrollPane);
        jScrollPane.setBounds(50,24,600,240);
        jScrollPane.getViewport().add(txtContent);
        txtContent.setBounds(0,0,600,237);
        btnOk.setText("OK");
        btnOk.setActionCommand("OK");
        getContentPane().add(btnOk);
        btnOk.setBounds(338,276,96,24);
        txtContent.setText(report);
        txtContent.setLineWrap(true);
        txtContent.setWrapStyleWord(true);

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
