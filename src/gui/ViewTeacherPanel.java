package gui;

import controller.DataController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 * This class is a View teacher panel for GUI. It is having all UI component which
 * are displayed in Assign course GUI.
 */
public class ViewTeacherPanel extends JPanel implements ActionListener {
    DataController DB;
    int screenWidth, screenHeight, buttonWidth;
    public JComboBox<String> teacherListDropdown;
    public JButton viewTeacherButton;
    static List<String> teacherList;
    public TextArea TeacherDataViewArea;

    public ViewTeacherPanel() {
        DB = new DataController();
        teacherList = DB.listOfTeachers();
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
        JLabel teacherIdLabel = new JLabel("Select Teacher");
        teacherIdLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        teacherIdLabel.setSize(300, 30);
        teacherIdLabel.setLocation(300, 30);
        add(teacherIdLabel, constraints);

        constraints.gridx = 1;
        teacherListDropdown = new JComboBox<>();
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        for (String teacher : teacherList) {
            String[] teacherStringArr = teacher.split(":");
            comboBoxModel.addElement(teacherStringArr[0] + " - " + teacherStringArr[1]);
        }
        teacherListDropdown.setModel(comboBoxModel);
        add(teacherListDropdown, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        TeacherDataViewArea = new TextArea("Teacher Data");
        TeacherDataViewArea.setBounds(10, 30, 300, 300);
        TeacherDataViewArea.setEditable(false);
        add(TeacherDataViewArea, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        viewTeacherButton = new JButton("View Teacher");

        add(viewTeacherButton, constraints);

        viewTeacherButton.addActionListener(this);
        // set border for the panel
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "View Teacher"));

    }
    /**
     * This is the implementation of {@link ActionListener#actionPerformed(ActionEvent)} <br>
     * It will be called when view teacher button will be clicked to view teacher details from file. <br>
     * @param actionEvent the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        viewTeacher();
    }

    /**
     *  It will show the selected teacher details from file.<br>
     *  <b>Logic:</b> <br>
     *  We take selected item from dropdown then pass it to {@link DataController#fetchTeacherById(String)}}, which will
     *  return the teacher details from file and we display it to textarea. <br>
     */
    private void viewTeacher() {
        String teacherId = String.valueOf(teacherListDropdown.getSelectedItem()).split(" - ")[0];
        String teacherDetails = DB.fetchTeacherById(teacherId);
        TeacherDataViewArea.setText(teacherDetails);

    }
}
