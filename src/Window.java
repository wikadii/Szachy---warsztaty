import javax.swing.*;
import java.util.Objects;

public class Window extends JFrame {
    public MainPanel mainPanel;

    public Window(int initialTimeInSeconds){
        this.setTitle("Szachy");
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("images/icon.jpg")));
        this.setIconImage(icon.getImage());
        this.setSize(1000, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        mainPanel = new MainPanel(initialTimeInSeconds);
        this.add(mainPanel);
        this.setVisible(true);

    }
}
