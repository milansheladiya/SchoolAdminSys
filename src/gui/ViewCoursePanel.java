package gui;

import controller.DataController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewCoursePanel extends JPanel implements ActionListener {
    int screenWidth, screenHeight, buttonWidth;
    public JComboBox<String> courseListDropDown;
    DataController DB;
    // String[] subjectList = { "Maths", "Java", "Science", "English" };
    static List<String> subjectList;
    public TextArea CourseDataViewArea;

    public ViewCoursePanel() {
        DB = new DataController();
        subjectList = DB.listOfCourseIds();
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

        constraints.gridx = 0;
        constraints.gridy = 0;
        JLabel selectCourseLabel = new JLabel("Select Course");
        selectCourseLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        selectCourseLabel.setSize(300, 100);
        add(selectCourseLabel, constraints);

        constraints.gridx = 1;
        courseListDropDown = new JComboBox<>();
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        for (String s : subjectList) {
            comboBoxModel.addElement(s);
        }
        courseListDropDown.setPrototypeDisplayValue("Select teacher");
        courseListDropDown.setModel(comboBoxModel);
        add(courseListDropDown, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        CourseDataViewArea = new TextArea("Course Data");
        CourseDataViewArea.setBounds(10, 30, 300, 300);
        CourseDataViewArea.setEditable(false);
        add(CourseDataViewArea, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        JButton viewCourseButton = new JButton("View Course");
        add(viewCourseButton, constraints);
        viewCourseButton.addActionListener(this);

        // set border for the panel
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "View course details"));

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        viewCourse();
    }

    private void viewCourse() {
        String response = DB.fetchCourseById(String.valueOf(courseListDropDown.getSelectedItem()));
        String[] Variable = response.split(":");
        int i = 0;
        StringBuilder courseDetails = new StringBuilder();
        for (String value : Variable) {
            if (i == 0) {
                courseDetails.append("Course Id = " + value + "\n");
            } else if (i == 1) {
                courseDetails.append("Course Name = " + value + "\n");
            } else if (i == 2) {
                courseDetails.append("Course Start Date = " + value + "\n");
            } else if (i == 3) {
                courseDetails.append("Course End Date = " + value + "\n");
            } else if (i == 4) {
                courseDetails.append("PreRequisite = " + value + "\n");
            }
            i++;
        }
        System.out.println("View Course" + courseDetails);
        CourseDataViewArea.setText(courseDetails.toString());
    }
}
