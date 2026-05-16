import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class StudentForm extends JFrame {

    JTextField name = new JTextField();
    JTextField course = new JTextField();

    public StudentForm() {
        setTitle("Student Form");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = UIHelper.createPanel();

        UIHelper.addField(panel, "Name:", name, 0);
        UIHelper.addField(panel, "Course:", course, 1);

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
                    "INSERT INTO student(name,course) VALUES(?,?)");
            ps.setString(1, name.getText());
            ps.setString(2, course.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Saved");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
