package hospital.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Room extends JFrame {

    JTable table;


    Room() {

        JPanel panel=new JPanel();
        panel.setLayout(null);
        panel.setBounds(5,5,890,590);
        panel.setBackground(new Color(9,156,163));
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/roomm.png"));
        Image image = imageIcon.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        JLabel imgLabel = new JLabel(new ImageIcon(image));
        imgLabel.setBounds(580, 220, 250, 250);   // right side
        panel.add(imgLabel);

        table = new JTable();
        table.setBackground(new Color(90,156,163));
        table.setRowHeight(25);
        table.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel.add(table);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 40, 520, 300);   // top-left table
        panel.add(scrollPane);


        try {
            connection1 c = new connection1();
            String q = "select * from room";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel title = new JLabel("Room Details");
        title.setBounds(320, 5, 250, 30);
        title.setFont(new Font("Tahoma", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        panel.add(title);

        JButton back = new JButton("Back");
        back.setBounds(360, 500, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        panel.add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setUndecorated(true);
        setSize(900,600);
        setLayout(null);
        setLocation(300,230);
        setVisible(true);
    }
    public static void main(String[] args) {
  new Room();
    }
}
