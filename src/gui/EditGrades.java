package gui;

import controller.DataController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class EditGrades extends JPanel {

    public int screenWidth, screenHeight, buttonWidth;
    GridBagConstraints constraints;
    public JComboBox<String> courseListDropdown;
    public JComboBox<String> studentListDropdown = new JComboBox<>();
    public JTextField gradeTxtField;
    public JButton editGradeButton;
    public JLabel msgLabel = new JLabel("");;
    public DataController DB;
    public static List<String> courseList;
    public EditGrades() {
        DB = new DataController();
        courseList = DB.listOfCurrentCourse() ;
        constraints = new GridBagConstraints();

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
        DefaultComboBoxModel<String> courseComboBoxModel = new DefaultComboBoxModel<>();
        for (String course : courseList) {
            String[] courseStringArr = course.split(":");
            courseComboBoxModel.addElement(courseStringArr[0] + " - " + courseStringArr[1]);
        }
        courseListDropdown.setModel(courseComboBoxModel);
        add(courseListDropdown, constraints);
        courseListDropdown.addActionListener(this::loadStudentDataHandler);

        constraints.gridx = 0;
        constraints.gridy = 1;
        JLabel selectStudentLabel = new JLabel("Select Student");
        selectStudentLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        selectStudentLabel.setSize(300, 100);
        add(selectStudentLabel, constraints);

        loadStudentData();
        add(studentListDropdown, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        JLabel gradeLabel = new JLabel("Student Grades");
        gradeLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        gradeLabel.setSize(300, 30);
        gradeLabel.setLocation(300, 30);
        add(gradeLabel, constraints);

        constraints.gridx = 1;
        gradeTxtField = new JTextField("");
        gradeTxtField.setColumns(12);
        gradeTxtField.setFont(new Font("Serif", Font.PLAIN, 14));
        gradeTxtField.setSize(300, 100);
        add(gradeTxtField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        editGradeButton = new JButton("Edit Student Grades");
        add(editGradeButton, constraints);
        editGradeButton.addActionListener(this::editGradeHandler);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;

        msgLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        msgLabel.setSize(500, 100);
        add(msgLabel, constraints);
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Edit Student Grade"));
    }
    private void loadStudentDataHandler(ActionEvent actionEvent) {
        loadStudentData();
    }
    private void editGradeHandler(ActionEvent actionEvent) {
        System.out.println("Edit");
        editStudentGrade();
    }

    private void loadStudentData() {
        msgLabel.setText("");
        constraints.gridx = 1;

        DefaultComboBoxModel<String> studentComboBoxModelModel = new DefaultComboBoxModel<>();
        String courseId = String.valueOf(courseListDropdown.getSelectedItem()).split(" - ")[0];
        List<String> studentIds = DB.fetchCourseStudentsById(courseId);
        if(studentIds.size() > 0){
            for (String studentId : studentIds) {
                String student = DB.fetchStudentDetailsById(studentId);
                String[] studentStringArr = student.split(":");
                studentComboBoxModelModel.addElement(studentStringArr[0] + " - " + studentStringArr[1]);
            }
            studentListDropdown.setVisible(true);
            studentListDropdown.setModel(studentComboBoxModelModel);
        }else{
            studentListDropdown.setVisible(false);
            msgLabel.setText("Course has no enrolled students");
            msgLabel.setForeground(Color.red);
        }

    }
    private void editStudentGrade() {
    }

}
