package gui;

import controller.DataController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class AddStudentPanel extends JPanel implements ActionListener {
    int screenWidth, screenHeight, buttonWidth;
    public JTextField addStudent_sId, addStudent_sName;
    public JButton addStudentButton;
    public JLabel msgLable;
    JList<String> subjecttListDropdown;
    DataController DB;
    String studentID, studentFullName;
    List<String> subjectList, ListofCourse;

    public AddStudentPanel() {
        DB = new DataController();
        subjectList = DB.listOfCourse();
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
        JLabel sIdLable = new JLabel("Student ID");
        sIdLable.setFont(new Font("Serif", Font.PLAIN, 14));
        sIdLable.setSize(300, 30);
        sIdLable.setLocation(300, 30);
        add(sIdLable, constraints);

        constraints.gridx = 1;
        addStudent_sId = new JTextField("Please enter student");
        addStudent_sId.setPreferredSize(addStudent_sId.getPreferredSize());
        addStudent_sId.setText("");
        addStudent_sId.setFont(new Font("Serif", Font.PLAIN, 14));
        addStudent_sId.setSize(300, 100);
        add(addStudent_sId, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        JLabel sNameLable = new JLabel("Student Name");
        sNameLable.setFont(new Font("Serif", Font.PLAIN, 14));
        sNameLable.setSize(300, 100);
        add(sNameLable, constraints);

        constraints.gridx = 1;
        addStudent_sName = new JTextField("Enter student name");
        addStudent_sName.setPreferredSize(addStudent_sId.getPreferredSize());
        addStudent_sName.setText("");
        addStudent_sName.setFont(new Font("Serif", Font.PLAIN, 14));
        addStudent_sName.setSize(300, 100);
        add(addStudent_sName, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        JLabel postSubjectLable = new JLabel("Select Subject");
        postSubjectLable.setFont(new Font("Serif", Font.PLAIN, 14));
        postSubjectLable.setSize(300, 100);
        add(postSubjectLable, constraints);

        constraints.gridx = 1;

        subjecttListDropdown = new JList<>();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String s : subjectList) {
            listModel.addElement(s);
        }
        subjecttListDropdown.setModel(listModel);
        subjecttListDropdown.setPreferredSize(subjecttListDropdown.getPreferredSize());
        subjecttListDropdown.setFixedCellHeight(15);
        subjecttListDropdown.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        subjecttListDropdown.setVisibleRowCount(4);
        add(new JScrollPane(subjecttListDropdown), constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        addStudentButton = new JButton("Add Student");
        add(addStudentButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        msgLable = new JLabel("");
        msgLable.setFont(new Font("Serif", Font.PLAIN, 14));
        msgLable.setSize(500, 100);
        add(msgLable, constraints);

        addStudentButton.addActionListener(this);

        // set border for the panel
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Add Student"));

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == addStudentButton) {
            addStudentSubmit();
        }
    }

    private void addStudentSubmit() {
        StringBuilder courseString = new StringBuilder();
        studentID = addStudent_sId.getText().trim();
        studentFullName = addStudent_sName.getText().trim();
        if (!studentID.equals("") && !studentFullName.equals("")) {
            if (studentID.matches("^[0-9]*")) {

                if (DB.checkStudent(studentID)) {
                    msgLable.setText("Student id already exist.");
                } else {
                    ListofCourse = subjecttListDropdown.getSelectedValuesList();
                    for (int i = 0; i < ListofCourse.size(); i++) {
                        if (ListofCourse.size() == i + 1) {
                            courseString.append(ListofCourse.get(i));
                        } else {
                            courseString.append(ListofCourse.get(i)).append(";");
                        }
                    }
                    DB.addStudent(studentID, studentFullName, courseString);
                    msgLable.setText("Student Added Successfully.");
                    addStudent_sId.setText("");
                    addStudent_sName.setText("");
                }

            } else {
                msgLable.setText("Student id must be only number.");
            }
        } else {
            msgLable.setText("All fields are mendatory.");
        }
    }
}
