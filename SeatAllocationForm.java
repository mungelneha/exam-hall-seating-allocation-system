import javax.swing.*;
import java.sql.*;
import java.awt.*;

public class SeatAllocationForm extends JFrame {

    JTextField student = new JTextField();
    JTextField exam = new JTextField();
    JTextField hall = new JTextField();
    JTextField seat = new JTextField();

    public SeatAllocationForm() {

        setTitle("Seat Allocation");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Main panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(245, 247, 250)); // light background

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);

        // Title
        JLabel title = new JLabel("Seat Allocation Form");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(33, 37, 41));

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(title, gbc);

        gbc.gridwidth = 1;

        // Labels & Fields
        addField(panel, gbc, "Student Name:", student, 1, labelFont, fieldFont);
        addField(panel, gbc, "Exam Name:", exam, 2, labelFont, fieldFont);
        addField(panel, gbc, "Hall Name:", hall, 3, labelFont, fieldFont);
        addField(panel, gbc, "Seat Number:", seat, 4, labelFont, fieldFont);

        // Button
        JButton btn = new JButton("Allocate Seat");
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(new Color(0, 123, 255));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(btn, gbc);

        btn.addActionListener(e -> saveData());

        add(panel);
        setVisible(true);
    }

    void addField(JPanel panel, GridBagConstraints gbc, String text, JTextField field, int y, Font labelFont,
            Font fieldFont) {

        JLabel label = new JLabel(text);
        label.setFont(labelFont);

        field.setFont(fieldFont);
        field.setPreferredSize(new Dimension(200, 30));

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(label, gbc);

        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    void saveData() {
        try {
            Connection con = DB.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO seat_allocation(student_name, exam_name, hall_name, seat_no) VALUES(?,?,?,?)");

            ps.setString(1, student.getText());
            ps.setString(2, exam.getText());
            ps.setString(3, hall.getText());
            ps.setInt(4, Integer.parseInt(seat.getText()));

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Seat Allocated Successfully ✅");

            student.setText("");
            exam.setText("");
            hall.setText("");
            seat.setText("");

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error! Check inputs ❌");
        }
    }
}