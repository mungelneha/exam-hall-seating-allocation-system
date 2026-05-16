import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class HallForm extends JFrame {

    JTextField name = new JTextField();
    JTextField cap = new JTextField();

    public HallForm() {
        setTitle("Hall Form");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = UIHelper.createPanel();

        UIHelper.addField(panel, "Hall Name:", name, 0);
        UIHelper.addField(panel, "Capacity:", cap, 1);

        JButton btn = UIHelper.button("Save");

        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 2;
        g.gridwidth = 2;
        panel.add(btn, g);

        btn.addActionListener(e -> save());

        add(panel);
        setVisible(true);
    }

    void save() {
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO hall(hall_name,capacity) VALUES(?,?)");
            ps.setString(1, name.getText());
            ps.setInt(2, Integer.parseInt(cap.getText()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Saved");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
