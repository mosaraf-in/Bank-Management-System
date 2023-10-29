package Bank_Management_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class BalanceEnquiry extends JFrame implements ActionListener {
    JButton back;
    String pinNumber;
    BalanceEnquiry(String pinNumber)  {
        this.pinNumber = pinNumber;
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,740,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,740);
        add(image);

        Conn C = new Conn();
        int balance = 0;
        try {
            ResultSet rs = C.s.executeQuery("select * from bank where pin = '" + pinNumber + "'");

            while (rs.next()) {
                if (rs.getString("type").equals("deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
        }
         catch(Exception e){
            System.out.println(e);
        }
        JLabel text = new JLabel("Your Current Account balance is Rs  " + balance);
        text.setBounds(190,235,400,35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System",Font.BOLD,13));
        image.add(text);



        back = new JButton("BACK");
        back.setBounds(355,425,150,25);
        back.addActionListener(this);
        image.add(back);

        setSize(900,900);
        setLocation(300,0);
        //setUndecorated(true);
        setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Transactions(pinNumber).setVisible(true);
    }
    public static void main(String[] args) {
        new BalanceEnquiry("");
    }


}
