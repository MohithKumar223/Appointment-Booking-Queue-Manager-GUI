package AppointmentManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainUI extends JFrame {

    JTextField nameField, phoneField, timeField;
    JTable table;
    DefaultTableModel model;
    AppointmentDAO dao = new AppointmentDAO();

    public MainUI() {
        setTitle("Appointment Queue Manager");
        setSize(750, 500);
        setLayout(new BorderLayout());

        // ===== TOP PANEL =====
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Phone:"));
        phoneField = new JTextField();
        panel.add(phoneField);

        panel.add(new JLabel("Time (YYYY-MM-DD HH:MM:SS):"));
        timeField = new JTextField();
        panel.add(timeField);

        JButton addBtn = new JButton("Add Appointment");
        panel.add(addBtn);

        add(panel, BorderLayout.NORTH);

        // ===== TABLE =====
        model = new DefaultTableModel(
                new String[]{"ID", "Name", "Phone", "Time", "Status"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ===== BOTTOM PANEL =====
        JPanel bottom = new JPanel();

        JButton doneBtn = new JButton("Mark Done");
        JButton refreshBtn = new JButton("Refresh");

        bottom.add(doneBtn);
        bottom.add(refreshBtn);

        add(bottom, BorderLayout.SOUTH);

        // ===== ACTIONS =====
        addBtn.addActionListener(e -> addAppointment());
        refreshBtn.addActionListener(e -> loadData());
        doneBtn.addActionListener(e -> markDone());

        loadData();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    // ===== ADD APPOINTMENT =====
    void addAppointment() {
        try {
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String time = timeField.getText().trim();

            // Validation
            if (name.isEmpty() || phone.isEmpty() || time.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!");
                return;
            }

            // Validate datetime format
            try {
                java.sql.Timestamp.valueOf(time); // check format
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Invalid Date Format!\nUse: YYYY-MM-DD HH:MM:SS");
                return;
            }

            Appointment a = new Appointment();
            a.setName(name);
            a.setPhone(phone);
            a.setTime(time);

            dao.addAppointment(a);

            JOptionPane.showMessageDialog(this, "Appointment Added!");

            loadData();

            // Clear fields
            nameField.setText("");
            phoneField.setText("");
            timeField.setText("");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding appointment!");
        }
    }

    // ===== LOAD DATA =====
    void loadData() {
        try {
            model.setRowCount(0);
            List<Appointment> list = dao.getAllAppointments();

            for (Appointment a : list) {
                model.addRow(new Object[]{
                        a.getId(),
                        a.getName(),
                        a.getPhone(),
                        a.getTime(),
                        a.getStatus()
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data!");
        }
    }

    // ===== MARK DONE =====
    void markDone() {
        try {
            int row = table.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a row first!");
                return;
            }

            int id = (int) model.getValueAt(row, 0);

            dao.updateStatus(id, "DONE");

            JOptionPane.showMessageDialog(this, "Marked as DONE");

            loadData();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating status!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainUI());
    }
}