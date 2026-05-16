import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;

public class ReportForm extends JFrame {

    JTable table;

    public ReportForm() {

        setTitle("Report");
        setSize(700, 400);
        setLocationRelativeTo(null);

        table = new JTable();

        JButton btn = new JButton("Load Data");

        btn.addActionListener(e -> load());

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(btn, BorderLayout.SOUTH);

        setVisible(true);
    }

    void load() {
        try {
            Connection con = DB.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM seat_allocation");

            table.setModel(buildTableModel(rs));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DefaultTableModel buildTableModel(ResultSet rs) throws Exception {
        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();

        java.util.Vector<String> colNames = new java.util.Vector<>();
        for (int i = 1; i <= cols; i++)
            colNames.add(meta.getColumnName(i));

        java.util.Vector<java.util.Vector<Object>> data = new java.util.Vector<>();

        while (rs.next()) {
            java.util.Vector<Object> row = new java.util.Vector<>();
            for (int i = 1; i <= cols; i++)
                row.add(rs.getObject(i));
            data.add(row);
        }

        return new DefaultTableModel(data, colNames);
    }
}
