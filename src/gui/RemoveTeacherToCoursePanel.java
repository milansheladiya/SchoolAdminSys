package gui;

import controller.DataController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RemoveTeacherToCoursePanel extends JPanel implements ActionListener {

    int screenWidth, screenHeight, buttonWidth;
    public JComboBox<String> courseListDropdown;
    public JLabel msgLable;
    JButton removeTeacherButton;
    DataController DB;
    static List<String> teacherCourseList;

    public RemoveTeacherToCoursePanel() {
        DB = new DataController();
        teacherCourseList = DB.listOfTeacherCourse();

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
        JLabel selectCourseLabel = new JLabel("Select Teacher and Course");
        selectCourseLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        selectCourseLabel.setSize(300, 100);
        add(selectCourseLabel, constraints);

        constraints.gridx = 1;
        courseListDropdown = new JComboBox<>();
        DefaultComboBoxModel<String> comboBoxModelModel = new DefaultComboBoxModel<>();
        for (String course : teacherCourseList) {
            comboBoxModelModel.addElement(course);
        }
        courseListDropdown.setModel(comboBoxModelModel);
        add(courseListDropdown, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        removeTeacherButton = new JButton("Remove Teacher");
        add(removeTeacherButton, constraints);
        removeTeacherButton.addActionListener(this);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        msgLable = new JLabel("");
        msgLable.setFont(new Font("Serif", Font.PLAIN, 14));
        msgLable.setSize(500, 100);
        add(msgLable, constraints);
        // set border for the panel
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Remove Teacher From Course"));

    }

    /**
     * This is the implementation of {@link ActionListener#actionPerformed(ActionEvent)} <br>
     * It will be called when remove teacher button will be clicked to remove teacher from course. <br>
     * @param actionEvent the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        removeTeacherCourse();
    }

    /**
     *  It will remove the selected teacher from course and will delete a record from file.<br>
     *  <b>Logic:</b> <br>
     *  We take selected item from dropdown then split it in required format and remove the
     *  selected teacher from the course. <br>
     *  we perform this action using  {@link DataController#removeFromFile(String)}} <br>
     *  File Record Format which will be removed from file: TeacherCourse:courseId:teacherId
     */
    private void removeTeacherCourse() {
        String teacher = String.valueOf(courseListDropdown.getSelectedItem());
        String[] teacherCourse = teacher.split(":");
        String removeString = "TeacherCourse:" + teacherCourse[1].trim() + ":" + teacherCourse[3].trim();
        DB.removeFromFile(removeString);

        remove(courseListDropdown);
        teacherCourseList = DB.listOfTeacherCourse();
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        for (String s : teacherCourseList) {
            comboBoxModel.addElement(s);
        }
        courseListDropdown.setModel(comboBoxModel);
        add(courseListDropdown);
    }
}
