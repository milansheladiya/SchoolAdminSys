package gui;

import controller.DataController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AssignTeacherToCoursePanel extends JPanel implements ActionListener {
    int screenWidth, screenHeight, buttonWidth;
    public JComboBox<String> courseListDropdown, teacherListDropdown;
    public JLabel msgLable;
    JButton assignTeacherButton;
    DataController DB;
    List<String> teacherList,courseList;

    public AssignTeacherToCoursePanel() {
        DB = new DataController();
        teacherList = DB.listOfTeachers();
        courseList = DB.listOfCourse();
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
        constraints.insets = new Insets(10, 10, 10, 10);

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        JLabel selectCourseLabel = new JLabel("Select Course");
        selectCourseLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        selectCourseLabel.setSize(300, 100);
        add(selectCourseLabel, constraints);

        constraints.gridx = 1;
        courseListDropdown = new JComboBox<>();
        DefaultComboBoxModel<String> courseComboBoxModelModel = new DefaultComboBoxModel<>();
        for (String course : courseList) {
            String[] courseStringArr = course.split(":");
            courseComboBoxModelModel.addElement(courseStringArr[0] + " - " + courseStringArr[1]);
        }
        courseListDropdown.setModel(courseComboBoxModelModel);
        add(courseListDropdown, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        JLabel selectTeacherLabel = new JLabel("Select Teacher");
        selectTeacherLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        selectTeacherLabel.setSize(300, 100);
        add(selectTeacherLabel, constraints);

        constraints.gridx = 1;
        teacherListDropdown = new JComboBox<>();
        DefaultComboBoxModel<String> teacherComboBoxModelModel = new DefaultComboBoxModel<>();
        for (String course : teacherList) {
            String[] teacherStringArr = course.split(":");
            teacherComboBoxModelModel.addElement(teacherStringArr[0] + " - " + teacherStringArr[1]);
        }
        teacherListDropdown.setModel(teacherComboBoxModelModel);
        add(teacherListDropdown, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        assignTeacherButton = new JButton("Assign Teacher");
        add(assignTeacherButton, constraints);
        assignTeacherButton.addActionListener(this);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        msgLable = new JLabel("");
        msgLable.setFont(new Font("Serif", Font.PLAIN, 14));
        msgLable.setSize(500, 100);
        add(msgLable, constraints);

        // set border for the panel
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Assign Teacher"));

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        assignTeacherToCourseSubmit();
    }

    private void assignTeacherToCourseSubmit() {
        if(teacherListDropdown.getSelectedItem() != null){
            String teacherId = String.valueOf(teacherListDropdown.getSelectedItem()).split(" - ")[0];
            String courseId = String.valueOf(courseListDropdown.getSelectedItem()).split(" - ")[0];
            if(DB.getCourseCurrentTeacherCapacity(courseId) < 1 ){
                if(DB.getTeacherTeachingCourseCount(teacherId) < 2){
                    DB.assignCourseToTeacher(courseId,teacherId);
                    msgLable.setText("Teacher Assigned Successfully.");
                    msgLable.setForeground(Color.green);
                    courseListDropdown.setSelectedIndex(0);
                    teacherListDropdown.setSelectedIndex(0);
                }else{
                    msgLable.setText("Teachers only allowed to teach 2 courses at once.");
                    msgLable.setForeground(Color.red);
                }
            }else{
                msgLable.setText("Course can have maximum 1 teacher");
                msgLable.setForeground(Color.red);

            }

        }else {
            msgLable.setText("Please select teacher");
            msgLable.setForeground(Color.red);
        }
    }
}
