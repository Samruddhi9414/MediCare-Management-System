package hospital.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class SearchRoom  extends JFrame {

     Choice choice;
     JTable table;

    SearchRoom() {

        JPanel panel=new JPanel();
        panel.setBounds(5,5,690,490);
        panel.setBackground(new Color(90,156,163));
        panel.setLayout(null);
        add(panel);


        JLabel For=new JLabel("Search For Room");
        For.setBounds(250,11,186,31);
        For.setFont(new Font("Tahoma",Font.BOLD,14));
        For.setForeground(Color.WHITE);
        panel.add(For);

        JLabel status=new JLabel("Status");
        status.setBounds(70,70,80,20);
       status.setFont(new Font("Tahoma",Font.BOLD,14));
        status.setForeground(Color.WHITE);
        panel.add(status);

        choice=new Choice();
        choice.setBounds(170,70,120,20);
        choice.add("Availabel");
        choice.add("Occupied");
        panel.add(choice);

        table=new JTable();
        table.setBounds(0,187,700,210);
        table.setBackground(new Color(90,156,163));
        table.setForeground(Color.white);
        panel.add(table);

        try{
            connection1 c=new connection1();
            String q="select * from room";
            ResultSet resultSet=c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        }catch (Exception e) {
            e.printStackTrace();
        }
        JLabel label1=new JLabel("Room Number");
        label1.setBounds(23,162,150,20);
        label1.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label1);

        JLabel label2=new JLabel("Availability");
        label2.setBounds(175,163,150,20);
        label2.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label2);

        JLabel label3=new JLabel("Price");
        label3.setBounds(458,163,150,20);
        label3.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label3);

        JLabel label4 =new JLabel("Bed Type");
        label4.setBounds(580,163,150,20);
        label4.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label4);

        JButton search =new JButton("Search");
        search.setBounds(200,420,120,25);
        search.setBackground(Color.BLACK);
        search.setForeground(Color.WHITE);
        panel.add(search);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String q="select * from Room where Availability='"+choice.getSelectedItem()+"'";

                try {
                    connection1 c=new connection1();
                    ResultSet resultSet=c.statement.executeQuery(q);
                    table.setModel(DbUtils.resultSetToTableModel(resultSet));

                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        JButton back =new JButton("BACK");
        back.setBounds(380,420,120,25);
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
        setSize(700,500);
        setLayout(null);
        setLocation(450,250);
        setVisible(true);

    }

    public static void main(String[] args) {
        new SearchRoom();
    }
}
