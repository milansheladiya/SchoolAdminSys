package gui;

import controller.DataController;
import util.UtilityClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static util.UtilityClass.*;

public class AddCoursePanel extends JPanel implements ActionListener {
    int screenWidth, screenHeight, buttonWidth;
    public JTextField courseNameTxtField,courseStartDateTxtField, courseEndDateTxtField;
    public JList<String> prerequisiteCourseBox;
    public JLabel msgLable;
    public JButton addCourseButton;
    DataController DB;

    String subjectList[] = { "None","Maths", "English", "Science", "Politics" };
    public AddCoursePanel() {
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
        constraints.insets = new Insets(10, 10, 10, 10);

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        JLabel courseNameLabel = new JLabel("Course Name");
        courseNameLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        courseNameLabel.setSize(300, 30);
        courseNameLabel.setLocation(300, 30);
        add(courseNameLabel, constraints);

        constraints.gridx = 1;
        courseNameTxtField = new JTextField("");
        courseNameTxtField.setColumns(12);
        courseNameTxtField.setFont(new Font("Serif", Font.PLAIN, 14));
        courseNameTxtField.setSize(300, 100);
        add(courseNameTxtField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        JLabel courseStartDateLabel = new JLabel("Start Date");
        courseStartDateLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        courseStartDateLabel.setSize(300, 30);
        courseStartDateLabel.setLocation(300, 30);
        add(courseStartDateLabel, constraints);

        constraints.gridx = 1;
        courseStartDateTxtField = new JTextField("");
        courseStartDateTxtField.setColumns(12);
        courseStartDateTxtField.setFont(new Font("Serif", Font.PLAIN, 14));
        courseStartDateTxtField.setSize(300, 100);
        add(courseStartDateTxtField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        JLabel courseEndDateLabel = new JLabel("End Date");
        courseEndDateLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        courseEndDateLabel.setSize(300, 30);
        courseEndDateLabel.setLocation(300, 30);
        add(courseEndDateLabel, constraints);

        constraints.gridx = 1;
        courseEndDateTxtField = new JTextField("");
        courseEndDateTxtField.setColumns(12);
        courseEndDateTxtField.setFont(new Font("Serif", Font.PLAIN, 14));
        courseEndDateTxtField.setSize(300, 100);
        add(courseEndDateTxtField, constraints);


        constraints.gridx = 0;
        constraints.gridy = 3;
        JLabel prerequisiteCourses = new JLabel("Select Prerequisite Course(s)");
        prerequisiteCourses.setFont(new Font("Serif", Font.PLAIN, 14));
        prerequisiteCourses.setSize(300, 100);
        add(prerequisiteCourses, constraints);

        constraints.gridx = 1;
        prerequisiteCourseBox = new JList<>(subjectList);
        prerequisiteCourseBox.setPreferredSize(prerequisiteCourseBox.getPreferredSize());
        prerequisiteCourseBox.setFixedCellHeight(15);
        prerequisiteCourseBox.setFixedCellWidth(100);
        prerequisiteCourseBox.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        prerequisiteCourseBox.setVisibleRowCount(4);
        prerequisiteCourseBox.setSelectedIndex(0);
        add(new JScrollPane(prerequisiteCourseBox), constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        addCourseButton = new JButton("Add Course");
        add(addCourseButton, constraints);
        addCourseButton.addActionListener(this);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        msgLable = new JLabel("");
        msgLable.setFont(new Font("Serif", Font.PLAIN, 14));
        msgLable.setSize(500, 100);
        add(msgLable, constraints);

        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Add Course"));
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        addCourseSubmit();
    }
    private void addCourseSubmit() {

        if(!courseNameTxtField.getText().equals("") && !courseStartDateTxtField.getText().equals("") && !courseEndDateTxtField.getText().equals(""))
        {
            if(dateFormatCheck(courseStartDateTxtField.getText()) && dateFormatCheck(courseEndDateTxtField.getText()))
            {
                String response = dateRangeCheck(courseStartDateTxtField.getText(),courseEndDateTxtField.getText());
                if(response.equals("Confirm"))
                {
                    List<String> items = prerequisiteCourseBox.getSelectedValuesList();
                    String PreCourse = items.get(0);
                    if(items.size() > 1)
                        for(int i=1;i < items.size();i++)
                        {
                            PreCourse = PreCourse + ";" + items.get(i);
                        }


                    //Course@courseId:courseName:startDate:endDate:PreRequisite
                    DB.addCourse(generateUniqueId(),courseNameTxtField.getText(),courseStartDateTxtField.getText(),courseEndDateTxtField.getText(),PreCourse);
                    //System.out.println("\nAns : " + DB.listOfPastCourse().toString());
                    msgLable.setText("Student Added Successfully.");
                    msgLable.setForeground(Color.green);

                }
                else
                {
                    msgLable.setText(response);
                    msgLable.setForeground(Color.red);
                }
            }
            else
            {
                msgLable.setText("Date format must be like DD-MM-YYYY and Valid dates");
                msgLable.setForeground(Color.red);
            }
        }
        else
        {
            msgLable.setText("All filed must be filled!");
            msgLable.setForeground(Color.red);
        }

    }
}
