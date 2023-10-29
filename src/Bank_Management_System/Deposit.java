package Bank_Management_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {
    JButton deposit,back;
    JTextField amount;
    String pinNumber;
    Deposit(String pinNumber){
        this.pinNumber = pinNumber;
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,740,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,740);
        add(image);

        JLabel text = new JLabel("Enter the amount you want to deposit");
        text.setBounds(195,235,400,35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System",Font.BOLD,16));
        image.add(text);

        amount = new JTextField();
        amount.setBounds(195,275,283,25);
        amount.setFont(new Font("Rale way",Font.BOLD,14));
        image.add(amount);

        deposit = new JButton("DEPOSIT");
        deposit.setBounds(355,396,150,25);
        deposit.addActionListener(this);
        image.add(deposit);

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
        if(ae.getSource() == deposit){
            String number = amount.getText();
            Date date = new Date();
            if(number.equals("")){
                JOptionPane.showMessageDialog(null,"Please enter the amount you want to deposit");
            }
            else{
                try {
                    Conn conn = new Conn();
                    String query = "insert into bank values ('"+pinNumber+"','"+date+"','deposit','"+number+"')";
                    conn.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null,"Rs " + number+ " Deposited successfully");
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
        new Deposit("");
    }

}
