package gui;

import controller.DataController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTeacherPanel extends JPanel implements ActionListener {
    DataController DB;
    int screenWidth, screenHeight, buttonWidth;
    public JTextField addTeacher_teacherId,addTeacher_teacherName;
    public JButton addTeacherButton;

    public AddTeacherPanel() {
        DB = new DataController();
        setLayout(new GridBagLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = screenSize.width;
        screenHeight = screenSize.height;
        buttonWidth = screenWidth / 10;
        int x = screenWidth - (screenWidth / 8);
        int y = screenHeight - (screenHeight / 4);

        setBounds(100, 100, x, y);
        setBackground(Color.lightGray);
        createComponent();
    }

    private void createComponent() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10,10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        JLabel teacherIdLable = new JLabel("Teacher ID");
        teacherIdLable.setFont(new Font("Serif", Font.PLAIN, 14));
        teacherIdLable.setSize(300, 30);
        teacherIdLable.setLocation(300, 30);
        add(teacherIdLable, constraints);

        constraints.gridx = 1;
        addTeacher_teacherId = new JTextField("Please enter teacher");
        addTeacher_teacherId.setPreferredSize(addTeacher_teacherId.getPreferredSize());
        addTeacher_teacherId.setText("");
        addTeacher_teacherId.setFont(new Font("Serif", Font.PLAIN, 14));
        addTeacher_teacherId.setSize(300, 100);
        add(addTeacher_teacherId, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        JLabel sNameLable = new JLabel("Teacher Name");
        sNameLable.setFont(new Font("Serif", Font.PLAIN, 14));
        sNameLable.setSize(300, 100);
        add(sNameLable, constraints);

        constraints.gridx = 1;
        addTeacher_teacherName = new JTextField("Enter teacher name");
        addTeacher_teacherName.setText("");
        addTeacher_teacherName.setFont(new Font("Serif", Font.PLAIN, 14));
        addTeacher_teacherName.setSize(300, 100);
        add(addTeacher_teacherName, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        addTeacherButton = new JButton("Add Teacher");
        addTeacherButton.setFont(new Font("Serif", Font.PLAIN, 14));
        addTeacherButton.setSize(300, 30);
        addTeacherButton.setLocation(500, 30);
        add(addTeacherButton, constraints);

        // set border for the panel
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Add Teacher"));
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        
    }
}
