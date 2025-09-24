import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Objects;

public class MainMenu extends JFrame {

    public MainMenu() {
        this.setTitle("Menu Główne");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("images/icon.jpg")));
        this.setIconImage(icon.getImage());
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.decode("#FFCFE7"));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        JButton startButton = new JButton("Nowa gra");
        JButton exitButton = new JButton("Wyjdź");

        startButton.setBackground(Color.decode("#FF69B4"));
        exitButton.setBackground(Color.decode("#FF69B4"));

        startButton.setBorder(new LineBorder(Color.BLACK, 2));
        exitButton.setBorder(new LineBorder(Color.BLACK, 2));


        startButton.setFocusPainted(false);
        exitButton.setFocusPainted(false);

        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        startButton.setMaximumSize(new Dimension(200, 40));
        exitButton.setMaximumSize(new Dimension(200, 40));

        startButton.addActionListener(e -> {
            TimeWindow dialog = new TimeWindow(this);
            int selectedTime = dialog.getSelectedTime();

            if (selectedTime == -2) {
                return;
            }

            new Window(selectedTime);
            this.dispose();
        });


        exitButton.addActionListener(e -> System.exit(0));

        panel.add(startButton);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(exitButton);

        this.add(panel);
        this.setVisible(true);
    }
}
