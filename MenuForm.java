import javax.swing.*;
import java.awt.*;

public class MenuForm extends JFrame {

    public MenuForm() {

        setTitle("Dashboard");
        setSize(500, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 1, 15, 15));
        panel.setBackground(new Color(245, 247, 250));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        Font f = new Font("Segoe UI", Font.BOLD, 16);

        panel.add(createBtn("Student Form", f, () -> new StudentForm()));
        panel.add(createBtn("Exam Form", f, () -> new ExamForm()));
        panel.add(createBtn("Hall Form", f, () -> new HallForm()));
        panel.add(createBtn("Seat Allocation", f, () -> new SeatAllocationForm()));
        panel.add(createBtn("Report", f, () -> new ReportForm()));

        add(panel);
        setVisible(true);
    }

    JButton createBtn(String text, Font f, Runnable action) {
        JButton b = new JButton(text);
        b.setFont(f);
        b.setBackground(new Color(0, 123, 255));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.addActionListener(e -> action.run());
        return b;
    }
}
