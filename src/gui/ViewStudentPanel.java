package gui;

import controller.DataController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * This class is a View student panel for GUI. It is having all UI component which
 * are displayed in Assign course GUI.
 */
public class ViewStudentPanel extends JPanel implements ActionListener {
    DataController DB;
    int screenWidth, screenHeight, buttonWidth;
    public JComboBox<String> studentIdDropdown;
    public JButton viewStudentButton;
    static List<String> studentIdList;
    public TextArea studentDataViewArea;

    public ViewStudentPanel() {
        DB = new DataController();
        studentIdList = DB.listOfStudentIds();
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
        JLabel sIdLabel = new JLabel("Student ID");
        sIdLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        sIdLabel.setSize(300, 30);
        sIdLabel.setLocation(300, 30);
        add(sIdLabel, constraints);

        constraints.gridx = 1;
        studentIdDropdown = new JComboBox<>();
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        for (String s : studentIdList) {
            comboBoxModel.addElement(s);
        }
        studentIdDropdown.setModel(comboBoxModel);
        studentIdDropdown.setPrototypeDisplayValue("Select student");
        add(studentIdDropdown, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        // viewStudentButton = new JButton("View Student");
        studentDataViewArea = new TextArea("Student Data");
        studentDataViewArea.setBounds(10, 30, 300, 300);
        studentDataViewArea.setEditable(false);
        add(studentDataViewArea, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        viewStudentButton = new JButton("View Student");

        add(viewStudentButton, constraints);

        viewStudentButton.addActionListener(this);
        // set border for the panel
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "View Student"));

    }

    /**
     * This is the implementation of {@link ActionListener#actionPerformed(ActionEvent)} <br>
     * It will be called when view student button will be clicked to view student details from file. <br>
     * @param actionEvent the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        viewStudent();
    }

    /**
     *  It will show the selected student details from file.<br>
     *  <b>Logic:</b> <br>
     *  We take selected item from dropdown then pass it to {@link DataController#fetchStudentById(String)}}, which will
     *  return the student details from file and we display it to textarea. <br>
     */
    private void viewStudent() {
        String studentDetails = DB.fetchStudentById(String.valueOf(studentIdDropdown.getSelectedItem()));
        System.out.println("View Student" + studentDetails);
        studentDataViewArea.setText(studentDetails);

    }
}
