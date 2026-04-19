import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;

public class RoomForm extends JFrame {

    private JTextField txtRoomNo, txtPrice;
    private JComboBox<RoomType> cmbType;
    private JTable table;
    private DefaultTableModel model;

    private ArrayList<Room> roomList = new ArrayList<>();

    public RoomForm() {

        setTitle("Room Form");
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5,2,5,5));
        panel.setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel("Room Information");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setForeground(Color.BLACK);

        panel.add(lblTitle);
        panel.add(new JLabel(""));

        panel.add(new JLabel("Room No:"));
        txtRoomNo = new JTextField();
        panel.add(txtRoomNo);

        panel.add(new JLabel("Room Type:"));
        cmbType = new JComboBox<>(RoomType.values());
        panel.add(cmbType);

        panel.add(new JLabel("Price:"));
        txtPrice = new JTextField();
        panel.add(txtPrice);

        JButton btnSave = new JButton("Save");
        JButton btnDelete = new JButton("Delete");

        btnSave.setBackground(Color.GRAY);
        btnSave.setForeground(Color.WHITE);

        btnDelete.setBackground(Color.GRAY);
        btnDelete.setForeground(Color.WHITE);

        panel.add(btnSave);
        panel.add(btnDelete);

        model = new DefaultTableModel(
                new String[]{"Room No","Type","Price"},0);
        table = new JTable(model);

        JScrollPane sp = new JScrollPane(table);

        add(panel,"North");
        add(sp,"Center");

        load();

        btnSave.addActionListener(e -> saveRoom());
        btnDelete.addActionListener(e -> deleteRoom());

        setVisible(true);
    }

    private void saveRoom() {
        Room r = new Room(
                Integer.parseInt(txtRoomNo.getText()),
                (RoomType)cmbType.getSelectedItem(),
                Double.parseDouble(txtPrice.getText())
        );
        roomList.add(r);
        save();
        refresh();
    }

    private void deleteRoom() {
        int row = table.getSelectedRow();
        if(row >= 0){
            roomList.remove(row);
            save();
            refresh();
        } else {
            JOptionPane.showMessageDialog(this,"Select a row first");
        }
    }

    private void refresh() {
        model.setRowCount(0);
        for(Room r : roomList){
            model.addRow(new Object[]{
                    r.getRoomNo(),
                    r.getRoomType(),
                    r.getPrice()
            });
        }
    }

    private void save() {
        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(new FileOutputStream("rooms.dat"));
            oos.writeObject(roomList);
            oos.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private void load() {
        try {
            ObjectInputStream ois =
                    new ObjectInputStream(new FileInputStream("rooms.dat"));
            roomList = (ArrayList<Room>) ois.readObject();
            ois.close();
            refresh();
        } catch(Exception e){
            roomList = new ArrayList<>();
        }
    }
}