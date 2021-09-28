package gui;

import controller.DataController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import static util.UtilityClass.generateUniqueId;

public class AddStudentPanel extends JPanel implements ActionListener {
    int screenWidth, screenHeight, buttonWidth;
    public JTextField  addStudent_sName;
    public JButton addStudentButton;
    public JLabel msgLable;
    JList<String> subjecttListDropdown;
    DataController DB;
    String studentID, studentFullName;
    List<String> subjectList, ListofCourse;

    public AddStudentPanel() {
        DB = new DataController();
        subjectList = DB.listOfPastCourse();
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
        constraints.gridy = 1;
        JLabel sNameLable = new JLabel("Student Name");
        sNameLable.setFont(new Font("Serif", Font.PLAIN, 14));
        sNameLable.setSize(300, 100);
        add(sNameLable, constraints);

        constraints.gridx = 1;
        addStudent_sName = new JTextField("Enter student name");
        addStudent_sName.setPreferredSize(addStudent_sName.getPreferredSize());
        addStudent_sName.setText("");
        addStudent_sName.setFont(new Font("Serif", Font.PLAIN, 14));
        addStudent_sName.setSize(300, 100);
        add(addStudent_sName, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        JLabel postSubjectLable = new JLabel("Select Past Course");
        postSubjectLable.setFont(new Font("Serif", Font.PLAIN, 14));
        postSubjectLable.setSize(300, 100);
        add(postSubjectLable, constraints);

        constraints.gridx = 1;
        subjecttListDropdown = new JList<>();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        subjectList.add(0,"None");
        for (String subject : subjectList) {
            String[] subjectStringArr = subject.split(":");
            if(subjectStringArr[0].equals("None")){
                listModel.addElement(subjectStringArr[0]);
            }else{
                listModel.addElement(subjectStringArr[1]);
            }
        }
        subjecttListDropdown.setModel(listModel);
        subjecttListDropdown.setPreferredSize(subjecttListDropdown.getPreferredSize());
        subjecttListDropdown.setFixedCellHeight(15);
        subjecttListDropdown.setFixedCellWidth(100);
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
        studentFullName = addStudent_sName.getText().trim();
        studentID = generateUniqueId();
        if (!studentID.equals("") && !studentFullName.equals("")) {
            if (studentID.matches("^[0-9]*")) {
                if (DB.checkStudent(studentID)) {
                    msgLable.setText("Student id already exist.");
                } else {
                    ListofCourse = subjecttListDropdown.getSelectedValuesList();
                    if(ListofCourse.size() == 0){
                        courseString.append("None");
                    }else{
                        for (int i = 0; i < ListofCourse.size(); i++) {
                            if (ListofCourse.size() == i + 1) {
                                courseString.append(ListofCourse.get(i));
                            } else {
                                courseString.append(ListofCourse.get(i)).append(";");
                            }
                        }
                    }
                    DB.addStudent(studentID, studentFullName, courseString.toString());
                    msgLable.setText("Student Added Successfully.");
                    msgLable.setForeground(Color.green);
                    addStudent_sName.setText("");
                }

            } else {
                msgLable.setText("Student id must be only number.");
                msgLable.setForeground(Color.red);
            }
        } else {
            msgLable.setText("All fields are mendatory.");
            msgLable.setForeground(Color.red);
        }
    }
}
