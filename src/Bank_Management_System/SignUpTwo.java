package Bank_Management_System;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpTwo extends JFrame implements ActionListener {
    JComboBox religionComboBox,categoryComboBox,incomeComboBox,qualificationComboBox,occupationComboBox;
    JTextField panTextField,adharTextField;
    JButton next;
    JRadioButton syes,sno,eyes,eno;
    String formno;
    SignUpTwo(String formno){
        this.formno = formno;
        setLayout(null);
        setTitle("NEW ACCOUNT APPLICATION FORM -- PAGE 2");

        JLabel additionalDetails = new JLabel("Page 2 : Additional Details ");
        additionalDetails.setFont(new Font("Rale way",Font.BOLD,22));
        additionalDetails.setBounds(290,80,400,30);
        add(additionalDetails);

        JLabel religion = new JLabel("Religion :");
        religion.setFont(new Font("Rale way",Font.BOLD,20));
        religion.setBounds(100,140,100,30);
        add(religion);

        String valReligion[] = {"Hindu","Muslim","Christian","Sikh","Other"};
        religionComboBox = new JComboBox(valReligion);
        religionComboBox.setBounds(300,140,400,30);
        religionComboBox.setBackground(Color.WHITE);
        add(religionComboBox);

        JLabel category = new JLabel("Category :");
        category.setFont(new Font("Rale way",Font.BOLD,20));
        category.setBounds(100,190,200,30);
        add(category);

        String valCategory[] = {"General","OBC","SC","ST","Other"};
        categoryComboBox = new JComboBox(valCategory);
        categoryComboBox.setBounds(300,190,400,30);
        categoryComboBox.setBackground(Color.WHITE);
        add(categoryComboBox);

        JLabel income = new JLabel("Income :");
        income.setFont(new Font("Rale way",Font.BOLD,20));
        income.setBounds(100,240,200,30);
        add(income);

        String valIncome[] = {"Null","< 1 Lakh","< 2 Lakh","< 5 Lakh"};
        incomeComboBox = new JComboBox(valIncome);
        incomeComboBox.setBounds(300,240,400,30);
        incomeComboBox.setBackground(Color.WHITE);
        add(incomeComboBox);

        JLabel qualification = new JLabel("Qualification :");
        qualification.setFont(new Font("Rale way",Font.BOLD,20));
        qualification.setBounds(100,290,200,30);
        add(qualification);

        String valQualification[] = {"Non Graduate","Graduate","Post Graduate","Doctorate","Other"};
        qualificationComboBox = new JComboBox(valQualification);
        qualificationComboBox.setBounds(300,290,400,30);
        qualificationComboBox.setBackground(Color.WHITE);
        add(qualificationComboBox);


        JLabel occupation = new JLabel("Occupation :");
        occupation.setFont(new Font("Rale way",Font.BOLD,20));
        occupation.setBounds(100,340,200,30);
        add(occupation);

        String valOccupation[] = {"Salaried","Self-Employed","Business","Student","Retired","Other"};
        occupationComboBox = new JComboBox(valOccupation);
        occupationComboBox.setBounds(300,340,400,30);
        occupationComboBox.setBackground(Color.WHITE);
        add(occupationComboBox);

        JLabel pan = new JLabel("PAN Number :");
        pan.setFont(new Font("Rale way",Font.BOLD,20));
        pan.setBounds(100,390,200,30);
        add(pan);

        panTextField = new JTextField();
        panTextField.setFont(new Font("Arial",Font.BOLD,14));
        panTextField.setBounds(300,390,400,30);
        add(panTextField);

        JLabel adhar = new JLabel("Adhar Number :");
        adhar.setFont(new Font("Rale way",Font.BOLD,20));
        adhar.setBounds(100,440,200,30);
        add(adhar);

        adharTextField = new JTextField();
        adharTextField.setFont(new Font("Arial",Font.BOLD,14));
        adharTextField.setBounds(300,440,400,30);
        add(adharTextField);

        JLabel citizen = new JLabel("Senior Citizen :");
        citizen.setFont(new Font("Rale way",Font.BOLD,20));
        citizen.setBounds(100,490,200,30);
        add(citizen);

        syes = new JRadioButton("Yes");
        syes.setBackground(Color.WHITE);
        syes.setBounds(300,490,100,30);
        add(syes);

        sno = new JRadioButton("No");
        sno.setBackground(Color.WHITE);
        sno.setBounds(450,490,100,30);
        add(sno);

        ButtonGroup citizengroup = new ButtonGroup();
        citizengroup.add(syes);
        citizengroup.add(sno);

        JLabel existAccount = new JLabel("Existing Account :");
        existAccount.setFont(new Font("Rale way",Font.BOLD,20));
        existAccount.setBounds(100,540,200,30);
        add(existAccount);

        eyes = new JRadioButton("Yes");
        eyes.setBackground(Color.WHITE);
        eyes.setBounds(300,540,100,30);
        add(eyes);

        eno = new JRadioButton("No");
        eno.setBackground(Color.WHITE);
        eno.setBounds(450,540,100,30);
        add(eno);

        ButtonGroup existingAccgroup = new ButtonGroup();
        existingAccgroup.add(eyes);
        existingAccgroup.add(eno);

        JButton next = new JButton("Next");

        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("rale way",Font.BOLD,14));
        next.setBounds(620,660,80,30);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(Color.WHITE);
        setSize(850,800);
        setLocation(350,5);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){

        String religion = (String) religionComboBox.getSelectedItem();
        String category = (String) categoryComboBox.getSelectedItem();
        String income = (String) incomeComboBox.getSelectedItem();
        String qualification = (String) qualificationComboBox.getSelectedItem();
        String occupation = (String) occupationComboBox.getSelectedItem();
        String pan = panTextField.getText();
        String adhar = adharTextField.getText();


        String citizen = null;
        if (syes.isSelected()){
            citizen = "Yes";
        }
        else if (sno.isSelected()) {
            citizen = "No";
        }

        String existAccount = null;
        if(eyes.isSelected()){
            existAccount = "Yes";
        }
        else if (eno.isSelected()) {
            existAccount = "No";
        }

        try{
            Conn c = new Conn();
            String query = "insert into signupTwo values ('"+formno+"','"+religion+"','"+category+"','"+income+"','"+qualification+"','"+occupation+"','"+pan+"','"+adhar+"','"+citizen+"','"+existAccount+"')";
            c.s.executeUpdate(query);

            setVisible(false);
            new SignUpThree(formno).setVisible(true);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        new SignUpTwo("");
    }
}
