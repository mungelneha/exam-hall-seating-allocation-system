import javax.swing.*;
import java.awt.*;

public class UIHelper {
    public static JPanel createPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 247, 250));
        return panel;
    }

    public static void addField(JPanel panel, String labelText, JTextField field, int y) {
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(10, 10, 10, 10);
        Font font = new Font("segoe UI", Font.PLAIN, 14);
        JLabel label = new JLabel(labelText);
        label.setFont(font);
        field.setFont(font);
        field.setPreferredSize(new Dimension(200, 30));
        g.gridx = 0;
        g.gridy = y;
        panel.add(label, g);
        g.gridx = 1;
        panel.add(field, g);
    }

    public static JButton button(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("segoe UI", Font.BOLD, 14));
        btn.setBackground(new Color(0, 123, 255));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        return btn;
    }
}
