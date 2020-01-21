package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Start extends JDialog {
    private TextArea terminal;
    private JPanel contentPane;
    private JButton startButton;
    private JTextField UrltextField;
    private JComboBox lang;



    private Base base;

    private void $$$setupUI$$$() {
        createUIComponents();
        contentPane = new JPanel();
        BoxLayout boxLayout = new BoxLayout(contentPane, BoxLayout.Y_AXIS);
        contentPane.setLayout(boxLayout);

        final JPanel panel4 = new JPanel();
        contentPane.add(panel4);
        panel4.setLayout(new BorderLayout(0, 0));
        panel4.add(terminal, BorderLayout.CENTER);

        final JPanel panel2 = new JPanel();
        contentPane.add(panel2,BorderLayout.CENTER);

        startButton = new JButton();
        startButton.setText("Start");
        panel2.add(startButton);
        UrltextField.setText("Kraków");
        panel2.add(UrltextField);
        panel2.add(lang);
    }

    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }


    public Start() {
        $$$setupUI$$$();
        setContentPane(contentPane);
        setModal(true);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                base.Start();
            }
        });
    }

    public static void main(String[] args) {
        Start dialog = new Start();
        dialog.setMinimumSize(new Dimension(450, 450));
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void createUIComponents() {
        UrltextField = new JTextField();
        UrltextField.setPreferredSize(new Dimension(250,25));
        terminal = new TextArea();
        lang = new JComboBox(new String[]{"Polski", "Angielski", "Niemiecki"});
        base = new Base(terminal, UrltextField, lang);
    }
}
