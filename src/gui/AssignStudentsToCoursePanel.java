package gui;

import controller.DataController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AssignStudentsToCoursePanel extends JPanel implements ActionListener {

    int screenWidth, screenHeight, buttonWidth;
    public JComboBox<String> courseListDropdown;
    public JLabel msgLable;
    JList<String> studentListDropdown;
    JButton assignStudentsButton;
    DataController DB;
    List<String> studentList,courseList;

    public AssignStudentsToCoursePanel() {
        DB = new DataController();
        studentList = DB.listOfStudents();
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
        DefaultComboBoxModel<String> comboBoxModelModel = new DefaultComboBoxModel<>();
        for (String course : courseList) {
            String[] courseStringArr = course.split(":");
            comboBoxModelModel.addElement(courseStringArr[0] + " - " + courseStringArr[1]);
        }
        courseListDropdown.setModel(comboBoxModelModel);
        add(courseListDropdown, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        JLabel selectStudentLabel = new JLabel("Select Student");
        selectStudentLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        add(selectStudentLabel, constraints);

        constraints.gridx = 1;
        studentListDropdown = new JList<>();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String student : studentList) {
            String[] studentStringArr = student.split(":");
            listModel.addElement(studentStringArr[0] + " - " + studentStringArr[1]);
        }
        studentListDropdown.setModel(listModel);
        studentListDropdown.setPreferredSize(studentListDropdown.getPreferredSize());
        studentListDropdown.setFixedCellHeight(15);
        studentListDropdown.setFixedCellWidth(200);
        studentListDropdown.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        studentListDropdown.setVisibleRowCount(5);
        add(new JScrollPane(studentListDropdown), constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        assignStudentsButton = new JButton("Assign Students");
        add(assignStudentsButton, constraints);
        assignStudentsButton.addActionListener(this);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        msgLable = new JLabel("");
        msgLable.setFont(new Font("Serif", Font.PLAIN, 14));
        msgLable.setSize(500, 100);
        add(msgLable, constraints);
        // set border for the panel
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Assign Students"));

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        assignStudentToCourseSubmit();
    }

    private void assignStudentToCourseSubmit() {
        if(studentListDropdown.getSelectedValue() != null){
            List<String> items = studentListDropdown.getSelectedValuesList();
            String courseId = String.valueOf(courseListDropdown.getSelectedItem()).split(" - ")[0];
                if(items.size() + DB.getCourseCurrentStudentCapacity(courseId) <= 5){
                    for(int i=0;i < items.size();i++)
                    {
                        String studentId = String.valueOf(items.get(i)).split(" - ")[0];
                        String studentName = String.valueOf(items.get(i)).split(" - ")[1];
                        if(DB.getStudentEnrolledCourseCount(studentId) < 3){
                            DB.assignCourseToStudent(courseId,studentId);
                            msgLable.setText("Students Assigned Successfully.");
                            msgLable.setForeground(Color.green);
                            courseListDropdown.setSelectedIndex(0);
                            studentListDropdown.clearSelection();
                        }else{
                            msgLable.setText("Students are only allowed to take 3 courses at once."  + studentName + "is already enrolled in 3 courses");
                            msgLable.setForeground(Color.red);
                        }
                    }

                }else{
                    msgLable.setText("Course can have maximum 5 students");
                    msgLable.setForeground(Color.red);

                }

        }else {
            msgLable.setText("Please select student");
            msgLable.setForeground(Color.red);
        }
    }
}
