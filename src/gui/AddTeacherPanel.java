package gui;

import controller.DataController;
import util.UtilityClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static util.UtilityClass.generateUniqueId;

/**
 * This class is a Add teacher panel for GUI. It is having all UI component which
 * are displayed in Add teacher GUI.
 */
public class AddTeacherPanel extends JPanel implements ActionListener {
    DataController DB;
    int screenWidth, screenHeight, buttonWidth;
    public JTextField teacherNameTxtField;
    public JButton addTeacherButton;
    public JLabel msgLable;
    String teacherID, teacherFullName, ListofCourse;

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

    /**
     * It will load all the UI component to UI
     */
    private void createComponent() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        JLabel sNameLable = new JLabel("Teacher Name");
        sNameLable.setFont(new Font("Serif", Font.PLAIN, 14));
        sNameLable.setSize(300, 100);
        add(sNameLable, constraints);

        constraints.gridx = 1;
        teacherNameTxtField = new JTextField("Enter teacher name");
        teacherNameTxtField.setPreferredSize(teacherNameTxtField.getPreferredSize());
        teacherNameTxtField.setText("");
        teacherNameTxtField.setFont(new Font("Serif", Font.PLAIN, 14));
        teacherNameTxtField.setSize(300, 100);
        add(teacherNameTxtField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        addTeacherButton = new JButton("Add Teacher");
        addTeacherButton.setFont(new Font("Serif", Font.PLAIN, 14));
        addTeacherButton.setSize(300, 30);
        addTeacherButton.setLocation(500, 30);
        add(addTeacherButton, constraints);
        addTeacherButton.addActionListener(this);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        msgLable = new JLabel("");
        msgLable.setFont(new Font("Serif", Font.PLAIN, 14));
        msgLable.setSize(500, 100);
        add(msgLable, constraints);

        // set border for the panel
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Add Teacher"));
    }

    /**
     * This is the implementation of {@link ActionListener#actionPerformed(ActionEvent)}
     * It will be called when add teacher button will be clicked to submit the data to file.
     * @param actionEvent the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == addTeacherButton) {
            addTeacherSubmit();
        }
    }

    /**
     * It will add the teacher to the file.
     * Logic:
     *  First we check that all fields are filled, otherwise we will show error message. <br>
     *  After That we generate unique id for teacher using {@link UtilityClass#generateUniqueId()} <br>
     *  and check if it is already created with same id if so we give error message. <br>
     *  We find uniqueness using  {@link DataController#checkTeacherExist(String)}} <br>
     *  Once all the conditions are satisfied we add record to file using {@link DataController#addTeacher(String, String, String)} <br>
     *  Record Format for teacher: Teacher@teacherID:FullName:CourseName1;CourseName2;......
     */
    private void addTeacherSubmit() {
        teacherFullName = teacherNameTxtField.getText().trim();
        ListofCourse = "";
        String teacherID = generateUniqueId();

        if (!teacherID.equals("") && !teacherFullName.equals("")) {
            if (DB.checkTeacherExist(teacherID)) {
                msgLable.setText("Teacher id already exist.");
                msgLable.setForeground(Color.red);
            } else {
                DB = new DataController();
                DB.addTeacher(teacherID, teacherFullName, "None");
                msgLable.setText("Teacher Added Successfully.");
                msgLable.setForeground(Color.green);
            }


        } else {
            msgLable.setText("All fields are mendetory.");
            msgLable.setForeground(Color.red);
        }
    }

}
