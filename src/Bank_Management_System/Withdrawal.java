package Bank_Management_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Withdrawal extends JFrame implements ActionListener {
    JButton withdraw,back;
    JTextField amount;
    String pinNumber;
    Withdrawal(String pinNumber){
        this.pinNumber = pinNumber;
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,740,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,740);
        add(image);

        JLabel text1 = new JLabel("MAXIMUM WITHDRAWAL IS RS. 10,000");
        text1.setBounds(200,225,400,35);
        text1.setForeground(Color.WHITE);
        text1.setFont(new Font("System",Font.BOLD,14));
        image.add(text1);

        JLabel text2 = new JLabel("please enter your amount");
        text2.setBounds(200,260,400,35);
        text2.setForeground(Color.WHITE);
        text2.setFont(new Font("System",Font.BOLD,14));
        image.add(text2);

        amount = new JTextField();
        amount.setBounds(195,300,283,25);
        amount.setFont(new Font("Rale way",Font.BOLD,14));
        image.add(amount);

        withdraw = new JButton("WITHDRAW");
        withdraw.setBounds(355,396,150,25);
        withdraw.addActionListener(this);
        image.add(withdraw);

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
        if(ae.getSource() == withdraw){
            String number = amount.getText();
            Date date = new Date();
            if(number.equals("")){
                JOptionPane.showMessageDialog(null,"Please enter the amount you want to withdraw");
            }
            else{
                try {
                    Conn conn = new Conn();
                    String query = "insert into bank values ('"+pinNumber+"','"+date+"','withdrawal','"+number+"')";
                    conn.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null,"Rs " + number+ " Withdraw successfully");
                    setVisible(false);
                    new Transactions(pinNumber).setVisible(true);
                }
                catch (Exception e){
                    System.out.println(e);
                }
            }
        }
        else if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(pinNumber).setVisible(true);
        }

    }
    public static void main(String[] args) {
        new Withdrawal("");
    }

}
