package Bank_Management_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {
    JButton cash_1, cash_2, cash_3, cash_4, cash_5, cash_6, back;
    String pinNumber;

    FastCash(String pinNumber) {
        this.pinNumber = pinNumber;
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 770, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 770);
        add(image);

        JLabel text = new JLabel("Select withdrawal amount");
        text.setBounds(225, 250, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        cash_1 = new JButton("Rs 100");
        cash_1.setBounds(170, 358, 150, 25);
        cash_1.addActionListener(this);
        image.add(cash_1);

        cash_2 = new JButton("Rs 500");
        cash_2.setBounds(355, 358, 150, 25);
        cash_2.addActionListener(this);
        image.add(cash_2);

        cash_3 = new JButton("Rs 1000");
        cash_3.setBounds(170, 387, 150, 25);
        cash_3.addActionListener(this);
        image.add(cash_3);

        cash_4 = new JButton("Rs 2000");
        cash_4.setBounds(355, 387, 150, 25);
        cash_4.addActionListener(this);
        image.add(cash_4);

        cash_5 = new JButton("Rs 5000");
        cash_5.setBounds(170, 416, 150, 25);
        cash_5.addActionListener(this);
        image.add(cash_5);

        cash_6 = new JButton("Rs 10000");
        cash_6.setBounds(355, 416, 150, 25);
        cash_6.addActionListener(this);
        image.add(cash_6);

        back = new JButton("BACK");
        back.setBounds(355, 445, 150, 25);
        back.addActionListener(this);
        image.add(back);


        setSize(900, 900);
        setLocation(300, 0);
       // setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(pinNumber).setVisible(true);
        }
        else {
            String amount = ((JButton)ae.getSource()).getText().substring(3); //Rs 500
            Conn C = new Conn();
            try {
                ResultSet rs = C.s.executeQuery("select * from bank where pin = '"+pinNumber+"'");

                int balance = 0;
                while (rs.next()){
                    if (rs.getString("type").equals("deposit")){
                        balance += Integer.parseInt(rs.getString("amount"));
                    }
                    else{
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                }

                if(ae.getSource() != back && balance < Integer.parseInt(amount)){
                    JOptionPane.showMessageDialog(null,"Insufficient Balance");
                    return;
                }
                Date date = new Date();
                String query = "insert into bank values ('"+pinNumber+"','"+date+"','withdrawal','"+amount+"')";
                C.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Rs "+amount+" Debited Successfully");

                setVisible(false);
                new Transactions(pinNumber).setVisible(true);
            }

            catch(Exception e){
                System.out.println(e);
            }
        }


    }

    public static void main(String[] args) {
        new FastCash("");
    }
}
