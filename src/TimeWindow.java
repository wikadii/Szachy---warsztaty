import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class TimeWindow extends JDialog {
    private int selectedTime = -1;

    public TimeWindow(JFrame parent) {
        super(parent, "Wybierz czas gry", true);
        parent.setBackground(Color.decode("#FF69B4"));
        setSize(300, 250);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(5, 1, 10, 10));

        add(createButton("5 minut", 5 * 60));
        add(createButton("10 minut", 10 * 60));
        add(createButton("30 minut", 30 * 60));
        add(createButton("Bez limitu", -1));
        add(createCancelButton());

        setVisible(true);
    }

    private JButton createButton(String label, int timeInSeconds) {
        JButton button = new JButton(label);
        button.setBorder(new LineBorder(Color.BLACK, 2));
        button.setBackground(Color.decode("#FF69B4"));
        button.setFocusPainted(false);
        button.addActionListener(e -> {
            selectedTime = timeInSeconds;
            dispose();
        });
        return button;
    }

    private JButton createCancelButton() {
        JButton button = new JButton("Anuluj");
        button.setBorder(new LineBorder(Color.BLACK, 2));
        button.setBackground(Color.decode("#CF0068"));
        button.setFocusPainted(false);
        button.addActionListener(e -> {
            selectedTime = -2;
            dispose();
        });
        return button;
    }

    public int getSelectedTime() {
        return selectedTime;
    }
}
