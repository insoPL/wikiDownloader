package InsoPL;

import javax.swing.*;
import java.awt.*;


public class Start extends JDialog {
    private TextArea terminal;
    private JPanel contentPane;
    private JButton startButton;
    private JTextField UrltextField;
    private JComboBox<String> lang;

    private Base base;

    public Start() {
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
        setContentPane(contentPane);
        setModal(true);
        startButton.addActionListener(actionEvent -> base.Start());
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
        lang = new JComboBox<>(new String[]{"Polski", "Angielski", "Niemiecki"});
        base = new Base(terminal, UrltextField, lang);
    }
}
