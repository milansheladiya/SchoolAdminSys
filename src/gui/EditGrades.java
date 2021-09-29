package gui;

import controller.DataController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static util.UtilityClass.isContainDigit;
import static util.UtilityClass.printConsole;

/**
 * This class is a Edit grades panel for GUI. It is having all UI component which
 * are displayed in Assign course GUI.
 */
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
    /**
     * It will load all the UI component to UI
     */
    private void createComponent() {
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        gradeTxtField = new JTextField("");

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
        studentListDropdown.addActionListener(this::loadStudentGradeDataHandler);

        constraints.gridx = 0;
        constraints.gridy = 2;
        JLabel gradeLabel = new JLabel("Student Grades");
        gradeLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        gradeLabel.setSize(300, 30);
        gradeLabel.setLocation(300, 30);
        add(gradeLabel, constraints);

        constraints.gridx = 1;

        gradeTxtField.setColumns(12);
        gradeTxtField.setFont(new Font("Serif", Font.PLAIN, 14));
        gradeTxtField.setSize(300, 100);
        Font font1 = new Font("SansSerif", Font.BOLD, 15);
        gradeTxtField.setFont(font1);
        gradeTxtField.setHorizontalAlignment(JTextField.CENTER);
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
    /**
     * This is the implementation of {@link ActionListener#actionPerformed(ActionEvent)}<br>
     * It will be called when select course value change to load the student related to selected
     * course only.
     * @param actionEvent the event to be processed
     */
    private void loadStudentDataHandler(ActionEvent actionEvent) {
        loadStudentData();
    }
    private void loadStudentGradeDataHandler(ActionEvent actionEvent) {
        loadStudentGrade();
    }

    /**
     *  This is the implementation of {@link ActionListener#actionPerformed(ActionEvent)}<br>
     *  It will be called when edit grade button will be clicked to submit the data to file.
     * @param actionEvent the event to be processed
     */
    private void editGradeHandler(ActionEvent actionEvent) {
        editStudentGrade();
    }

    /**
     * This method will be use for loading a student records from file based on selected course in course dropdown.<br>
     *     <b>Logic:</b> <br>
     *         We check if course has any enrolled student or not otherwise it will gives an error.<br>
     *         We fetch the students record using ${@link DataController#fetchCourseStudentsById(String)}<br>
     *         Once we get students record we fetch the student details for each student we got previously using
     *         ${@link DataController#fetchStudentDetailsById(String)}
     */
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
            String cId = (courseListDropdown.getSelectedItem().toString().split("-")[0].trim());
            String sId = (studentListDropdown.getSelectedItem().toString().split("-")[0].trim());
            gradeTxtField.setText(DB.fetchGradeOfStudent(cId,sId));
        }else{
            studentListDropdown.setVisible(false);
            gradeTxtField.setText("");
            msgLabel.setText("Course has no enrolled students");
            msgLabel.setForeground(Color.red);
        }
    }

    public void loadStudentGrade()
    {
        String cId = (courseListDropdown.getSelectedItem().toString().split("-")[0].trim());
        String sId = (studentListDropdown.getSelectedItem().toString().split("-")[0].trim());
        gradeTxtField.setText(DB.fetchGradeOfStudent(cId,sId));
    }

    private void editStudentGrade() {
        if(!courseListDropdown.getSelectedItem().equals(null) && courseListDropdown.isVisible())
        {
            if(!studentListDropdown.getSelectedItem().equals(null) && studentListDropdown.isVisible())
            {
                if(isContainDigit(gradeTxtField.getText()) && (Integer.parseInt(gradeTxtField.getText()) > 0 && Integer.parseInt(gradeTxtField.getText()) <= 100))
                {
                    String courseId = (courseListDropdown.getSelectedItem().toString().split("-")[0].trim());
                    String studentId = (studentListDropdown.getSelectedItem().toString().split("-")[0].trim());

                    DB.editStudentGrade(courseId,studentId,gradeTxtField.getText());

                    msgLabel.setText("Edit successfully");
                    msgLabel.setForeground(Color.green);
                }
                else
                {
                    msgLabel.setText("Grade must be between 1 to 100 and only numbers");
                    msgLabel.setForeground(Color.red);
                }
            }
            else
            {
                msgLabel.setText("please select the student!");
                msgLabel.setForeground(Color.red);
            }
        }
        else
        {
            msgLabel.setText("Course need to be selected!");
            msgLabel.setForeground(Color.red);
        }
    }

}
