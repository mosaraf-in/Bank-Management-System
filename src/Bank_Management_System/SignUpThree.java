package Bank_Management_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class SignUpThree extends JFrame implements ActionListener {
    JRadioButton saving,current;
    JCheckBox atm,mobile,internet,email,statement,cheque,declare;
    JButton submit,cancel;
    String formno;
    SignUpThree(String formno){
        this.formno = formno;
        setLayout(null);
        setTitle("NEW ACCOUNT APPLICATION FORM -- PAGE 3");

        JLabel accountDetails = new JLabel("Page 3 : Account Details ");
        accountDetails.setFont(new Font("Rale way",Font.BOLD,22));
        accountDetails.setBounds(280,40,400,40);
        add(accountDetails);

        JLabel type = new JLabel("Account Type :");
        type.setFont(new Font("Rale way",Font.BOLD,22));
        type.setBounds(100,140,400,40);
        add(type);

        saving = new JRadioButton("Saving Account");
        saving.setFont(new Font("Rale way",Font.BOLD,16));
        saving.setBackground(Color.WHITE);
        saving.setBounds(110,180,150,20);
        add(saving);

        current = new JRadioButton("Current Account");
        current.setFont(new Font("Rale way",Font.BOLD,16));
        current.setBackground(Color.WHITE);
        current.setBounds(350,180,170,20);
        add(current);

        ButtonGroup accountType = new ButtonGroup();
        accountType.add(saving);
        accountType.add(current);

        JLabel card = new JLabel("Card Number :");
        card.setFont(new Font("Rale way",Font.BOLD,22));
        card.setBounds(100,240,400,30);
        add(card);

        JLabel cardInfo = new JLabel("(Your 16- digit card number)");
        cardInfo.setFont(new Font("Rale way",Font.BOLD,12));
        cardInfo.setBounds(100,270,400,20);
        add(cardInfo);

        JLabel cardNo = new JLabel("XXXX-XXXX-XXXX-4184");
        cardNo.setFont(new Font("Rale way",Font.BOLD,18));
        cardNo.setBounds(330,240,300,30);
        add(cardNo);

        JLabel pin = new JLabel("PIN :");
        pin.setFont(new Font("Rale way",Font.BOLD,22));
        pin.setBounds(100,310,400,30);
        add(pin);

        JLabel pinInfo = new JLabel("(4- digit Password)");
        pinInfo.setFont(new Font("Rale way",Font.BOLD,12));
        pinInfo.setBounds(100,340,300,20);
        add(pinInfo);

        JLabel pinNo = new JLabel("XXXX");
        pinNo.setFont(new Font("Rale way",Font.BOLD,18));
        pinNo.setBounds(330,310,300,30);
        add(pinNo);

        JLabel services = new JLabel("Services Required:");
        services.setFont(new Font("Rale way",Font.BOLD,22));
        services.setBounds(100,390,400,20);
        add(services);

        atm = new JCheckBox("ATM CARD");
        atm.setFont(new Font("Rale way",Font.BOLD,16));
        atm.setBackground(Color.WHITE);
        atm.setBounds(110,430,200,20);
        add(atm);

        internet = new JCheckBox("Internet Banking");
        internet.setFont(new Font("Rale way",Font.BOLD,16));
        internet.setBackground(Color.WHITE);
        internet.setBounds(350,430,200,20);
        add(internet);

        mobile = new JCheckBox("Mobile Banking");
        mobile.setFont(new Font("Rale way",Font.BOLD,16));
        mobile.setBackground(Color.WHITE);
        mobile.setBounds(110,470,200,20);
        add(mobile);

        email = new JCheckBox("Email Alerts");
        email.setFont(new Font("Rale way",Font.BOLD,16));
        email.setBackground(Color.WHITE);
        email.setBounds(350,470,200,20);
        add(email);

        cheque = new JCheckBox("Cheque Book");
        cheque.setFont(new Font("Rale way",Font.BOLD,16));
        cheque.setBackground(Color.WHITE);
        cheque.setBounds(110,510,200,20);
        add(cheque);

        statement = new JCheckBox("E-Statement");
        statement.setFont(new Font("Rale way",Font.BOLD,16));
        statement.setBackground(Color.WHITE);
        statement.setBounds(350,510,200,20);
        add(statement);

        declare = new JCheckBox("I hereby declare that the above entered details are correct.");
        declare.setFont(new Font("Rale way",Font.BOLD,14));
        declare.setBackground(Color.WHITE);
        declare.setBounds(100,600,500,20);
        add(declare);

        submit = new JButton("SUBMIT");
        submit.setBounds(300,650,100,30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("CANCEL");
        cancel.setBounds(500,650,100,30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        getContentPane().setBackground(Color.WHITE);
        setSize(850,820);
        setLocation(350,0);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit){
            String accountType = null;
            if(saving.isSelected()){
                accountType = "Saving Account";
            }
            else if (current.isSelected()) {
                accountType = "Current Account";
            }
            Random random = new Random();
            String cardNo = "" + (Math.abs(random.nextLong() % 9000_000L) + 5040_9360_0000_0000L);
            String pinNo = "" + (Math.abs(random.nextInt() % 9000) + 1000);
            String facility = "";
            if(atm.isSelected()){
                facility = facility + " ATM CARD";
            }
            else if (mobile.isSelected()) {
                facility = facility + " Internet Banking";
            }
            else if (email.isSelected()) {
                facility = facility + " Mobile Banking";
            }
            else if (internet.isSelected()) {
                facility = facility + " Email Alerts";
            }
            else if (cheque.isSelected()) {
                facility = facility + " Cheque Book";
            }
            else if (statement.isSelected()) {
                facility = facility + " E-Statement";
            }

            try{
                if(accountType.equals("")){
                    JOptionPane.showMessageDialog(null,"Account Type is Required");
                }
                else{
                    Conn c = new Conn();
                    String query1 = "insert into signupThree values ('"+formno+"','"+accountType+"','"+cardNo+"','"+pinNo+"','"+facility+"')";
                    String query2 = "insert into login values ('"+formno+"','"+cardNo+"','"+pinNo+"')";
                    c.s.executeUpdate(query1);
                    c.s.executeUpdate(query2);

                    JOptionPane.showMessageDialog(null,"Card Number: " +cardNo+ "\nPin: " +pinNo);

                    setVisible(false);
                    new Deposit(pinNo).setVisible(false);
                }

            }
            catch (Exception e){
                System.out.println(e);
            }
        }
        else if (ae.getSource() == cancel) {
            setVisible(false);
            new Login().setVisible(true);
        }

    }

    public static void main(String[] args) {
        new SignUpThree("");
    }


}
