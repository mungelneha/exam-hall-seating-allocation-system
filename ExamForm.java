import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ExamForm extends JFrame {

    JTextField name = new JTextField();
    JTextField date = new JTextField();

    public ExamForm() {
        setTitle("Exam Form");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = UIHelper.createPanel();

        UIHelper.addField(panel, "Exam Name:", name, 0);
        UIHelper.addField(panel, "Date (YYYY-MM-DD):", date, 1);

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
                    "INSERT INTO exam(exam_name,exam_date) VALUES(?,?)");
            ps.setString(1, name.getText());
            ps.setDate(2, java.sql.Date.valueOf(date.getText()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Saved");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
