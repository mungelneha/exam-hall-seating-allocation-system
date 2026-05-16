import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginForm extends JFrame {

    JTextField user = new JTextField();
    JPasswordField pass = new JPasswordField();

    public LoginForm() {

        setTitle("Login");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 247, 250));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(10, 10, 10, 10);

        Font f = new Font("Segoe UI", Font.PLAIN, 14);

        JLabel title = new JLabel("Exam System Login");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));

        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 2;
        panel.add(title, g);

        g.gridwidth = 1;

        addField(panel, g, "Username:", user, 1, f);
        addField(panel, g, "Password:", pass, 2, f);

        JButton btn = new JButton("Login");
        styleButton(btn);

        g.gridx = 0;
        g.gridy = 3;
        g.gridwidth = 2;
        panel.add(btn, g);

        btn.addActionListener(e -> login());

        add(panel);
        setVisible(true);
    }

    void addField(JPanel p, GridBagConstraints g, String t, JComponent f, int y, Font font) {
        JLabel l = new JLabel(t);
        l.setFont(font);
        f.setFont(font);
        f.setPreferredSize(new Dimension(200, 30));

        g.gridx = 0;
        g.gridy = y;
        p.add(l, g);

        g.gridx = 1;
        p.add(f, g);
    }

    void styleButton(JButton b) {
        b.setBackground(new Color(0, 123, 255));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
    }

    void login() {
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM admin WHERE username=? AND password=?");
            ps.setString(1, user.getText());
            ps.setString(2, new String(pass.getPassword()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                new MenuForm();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Login");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}
