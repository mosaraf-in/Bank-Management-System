package Bank_Management_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PinChange extends JFrame implements ActionListener {
    JButton change,back;
    JPasswordField pin,repin;
    String pinNumber;
    PinChange(String pinNumber){
        this.pinNumber = pinNumber;
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,740,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,740);
        add(image);

        JLabel text1 = new JLabel("CHANGE YOUR PIN");
        text1.setBounds(255,230,200,35);
        text1.setForeground(Color.WHITE);
        text1.setFont(new Font("System",Font.BOLD,16));
        image.add(text1);

        JLabel newPin1 = new JLabel("New PIN:");
        newPin1.setBounds(165,270,100,35);
        newPin1.setForeground(Color.WHITE);
        newPin1.setFont(new Font("System",Font.BOLD,14));
        image.add(newPin1);

        JLabel newPin2 = new JLabel("Re-Enter New PIN:");
        newPin2.setBounds(165,300,150,35);
        newPin2.setForeground(Color.WHITE);
        newPin2.setFont(new Font("System",Font.BOLD,14));
        image.add(newPin2);

        pin = new JPasswordField();
        pin.setBounds(320,270,180,22);
        pin.setFont(new Font("Arial",Font.BOLD,18));
        image.add(pin);

        repin= new JPasswordField();
        repin.setBounds(320,300,180,22);
        repin.setFont(new Font("Arial",Font.BOLD,18));
        image.add(repin);

        change = new JButton("CHANGE");
        change.setBounds(355,396,150,25);
        change.addActionListener(this);
        image.add(change);

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
        if (ae.getSource() == change){
            try{
                String nPin = pin.getText();
                String rPin = repin.getText();

                if (nPin.equals("")){
                    JOptionPane.showMessageDialog(null,"Please enter New PIN");
                    return;
                }

                if (rPin.equals("")){
                    JOptionPane.showMessageDialog(null,"Please enter Re-Enter PIN");
                    return;
                }

                if (!nPin.equals(rPin)){
                    JOptionPane.showMessageDialog(null,"Entered PIN does not match");
                    return;
                }




                Conn conn = new Conn();
                String query1 = "update bank set pin = '"+rPin+"' where pin = '"+pinNumber+"'";
                String query2 = "update login set pinNo = '"+rPin+"' where pinNo = '"+pinNumber+"'";
                String query3 = "update signupthree set pinNo = '"+rPin+"' where pinNo = '"+pinNumber+"'";

                conn.s.executeUpdate(query1);
                conn.s.executeUpdate(query2);
                conn.s.executeUpdate(query3);

                JOptionPane.showMessageDialog(null,"PIN changed successfully");
                setVisible(false);
                new Transactions(rPin).setVisible(true);

            }
            catch (Exception e){
                System.out.println(e);
            }
        }
        else {
            setVisible(false);
            new Transactions(pinNumber).setVisible(true);
        }

    }
    public static void main(String[] args) {
        new PinChange("");
    }
}
