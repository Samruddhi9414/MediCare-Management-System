package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Patient_Discharge extends JFrame {
    Patient_Discharge() {

        JPanel panel=new JPanel();
        panel.setBounds(5,5,790,390);
        panel.setBackground(new Color(90,156,163));
        panel.setLayout(null);
        add(panel);

        JLabel label1=new JLabel("CHECK-OUT");
        label1.setBounds(100,20,150,20);
        label1.setFont(new Font("Tahoma",Font.BOLD,14));
        label1.setForeground(Color.WHITE);
        panel.add(label1);

        JLabel label2=new JLabel("Customer ID");
        label2.setBounds(30,80,150,20);
        label2.setFont(new Font("Tahoma",Font.BOLD,14));
        label2.setForeground(Color.WHITE);
        panel.add(label2);

        Choice choice=new Choice();
        choice.setBounds(200,80,150,25);
        panel.add(choice);

        try{

            connection1 c=new connection1();
            ResultSet resultSet  =c.statement.executeQuery("select * from patient_info");
            while(resultSet.next()) {
                choice.add(resultSet.getString("number"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        JLabel label3=new JLabel("Room Number");
        label3.setBounds(30,130,150,20);
        label3.setFont(new Font("Tahoma",Font.BOLD,14));
        label3.setForeground(Color.WHITE);
        panel.add(label3);


        JLabel RNo=new JLabel();
        RNo.setBounds(200,130,150,20);
       RNo.setFont(new Font("Tahoma",Font.BOLD,14));
        RNo.setForeground(Color.WHITE);
        panel.add(RNo);

        JLabel label4=new JLabel("In Time");
        label4.setBounds(30,180,150,20);
        label4.setFont(new Font("Tahoma",Font.BOLD,14));
        label4.setForeground(Color.WHITE);
        panel.add(label4);

        JLabel Intime=new JLabel();
        Intime.setBounds(200,180,250,20);
        Intime.setFont(new Font("Tahoma",Font.BOLD,14));
        Intime.setForeground(Color.WHITE);
        panel.add(Intime);

        JLabel label5=new JLabel("Out Time");
        label5.setBounds(30,230,150,20);
        label5.setFont(new Font("Tahoma",Font.BOLD,14));
        label5.setForeground(Color.WHITE);
        panel.add(label5);

        Date date=new Date();

        JLabel Outtime=new JLabel(""+date);
        Outtime.setBounds(200,230,250,20);
        Outtime.setFont(new Font("Tahoma",Font.BOLD,14));
        Outtime.setForeground(Color.WHITE);
        panel.add(Outtime);

        JButton discharge=new JButton("Discharge");
        discharge.setBounds(30,300,120,30);
        discharge.setBackground(Color.BLACK);
        discharge.setForeground(Color.WHITE);
        panel.add(discharge);
        discharge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                connection1 c = new connection1();

                try {

                    // STEP 1: Get room number BEFORE deleting
                    String roomNo = RNo.getText();

                    if(roomNo.equals("")) {
                        JOptionPane.showMessageDialog(null, "Please click CHECK first");
                        return;
                    }

                    // STEP 2: Delete patient
                    c.statement.executeUpdate(
                            "delete from patient_info where number='" + choice.getSelectedItem() + "'"
                    );

                    // STEP 3: Update room availability
                    c.statement.executeUpdate(
                            "update room set Availability='Available' where room_no='" +RNo.getText()+ "'"
                    );

                    JOptionPane.showMessageDialog(null, "Patient Discharged Successfully");

                    setVisible(false);

                } catch (SQLException sq) {
                    sq.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error while discharging");
                }
            }
        });


        JButton Check=new JButton("Check");
        Check.setBounds(170,300,120,30);
        Check.setBackground(Color.BLACK);
        Check.setForeground(Color.WHITE);
        panel.add(Check);
        Check.addActionListener(e -> {
            try {
                connection1 c = new connection1();
                ResultSet rs = c.statement.executeQuery(
                        "select * from patient_info where number='" + choice.getSelectedItem() + "'"
                );

                while (rs.next()) {
                    RNo.setText(rs.getString("Room_No"));
                    Intime.setText(rs.getString("Time"));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        JButton BACK=new JButton("BACK");
            BACK.setBounds(300,300,120,30);
            BACK.setBackground(Color.BLACK);
            BACK.setForeground(Color.WHITE);
            panel.add(BACK);

            BACK.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                  setVisible(false);
                }
            });




            setUndecorated(true);
            setSize(800,400);
            setLayout(null);
            setLocation(400,250);
            setVisible(true);
        }
        public static void main(String[] args) {
            new Patient_Discharge();
        }
    }
