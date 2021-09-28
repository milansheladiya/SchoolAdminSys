package gui;

import controller.DataController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 * This class is a Remove student from course panel for GUI. It is having all UI component which
 * are displayed in Assign course GUI.
 */
public class RemoveStudentsFromCoursePanel extends JPanel implements ActionListener {

    int screenWidth, screenHeight, buttonWidth;
    public JComboBox<String> courseListDropdown;
    public JLabel msgLable;
    public JButton removeStudentsButton;
    public DataController DB;
    static List<String> studentCourseList;

    public RemoveStudentsFromCoursePanel() {
        DB = new DataController();
        studentCourseList = DB.listOfStudentCourse();

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

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        JLabel selectCourseLabel = new JLabel("Select Student and Course");
        selectCourseLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        selectCourseLabel.setSize(300, 100);
        add(selectCourseLabel, constraints);

        constraints.gridx = 1;
        courseListDropdown = new JComboBox<>();
        DefaultComboBoxModel<String> comboBoxModelModel = new DefaultComboBoxModel<>();
        for (String course : studentCourseList) {
            comboBoxModelModel.addElement(course);
        }
        courseListDropdown.setModel(comboBoxModelModel);
        add(courseListDropdown, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        removeStudentsButton = new JButton("Remove Student");
        add(removeStudentsButton, constraints);
        removeStudentsButton.addActionListener(this);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        msgLable = new JLabel("");
        msgLable.setFont(new Font("Serif", Font.PLAIN, 14));
        msgLable.setSize(500, 100);
        add(msgLable, constraints);
        // set border for the panel
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Remove Students from course"));

    }

    /**
     * This is the implementation of {@link ActionListener#actionPerformed(ActionEvent)}
     * It will be called when remove student button will be clicked to remove student from course.
     * @param actionEvent the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        removeStudentCourse();
    }

    /**
     *  It will remove the selected student from course and will delete a record from file.<br>
     *  <b>Logic:</b> <br>
     *  We take selected item from dropdown then split it in required format and remove the
     *  selected student from the course. <br>
     *  we perform this action using  {@link DataController#removeFromFile(String)}} <br>
     *  File Record Format which will be removed from file : StudentCourse:courseId:studentId
     */
    private void removeStudentCourse() {
        String student = String.valueOf(courseListDropdown.getSelectedItem());
        String[] studentCourse = student.split(":");
        String removeString = "StudentCourse:" + studentCourse[1].trim() + ":" + studentCourse[3].trim();
        DB.removeFromFile(removeString);

        remove(courseListDropdown);
        studentCourseList = DB.listOfStudentCourse();
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        for (String s : studentCourseList) {
            comboBoxModel.addElement(s);
        }
        courseListDropdown.setModel(comboBoxModel);
        add(courseListDropdown);
    }
}
