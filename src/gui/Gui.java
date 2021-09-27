package gui;

import controller.DataController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui implements ActionListener {
    DataController DB;

    AddStudentPanel addStudentPnl;
    ViewStudentPanel viewStudentPnl;
    AddTeacherPanel addTeacherPnl;
    ViewTeacherPanel viewTeacherPnl;
    AddCoursePanel addCoursePnl;
    ViewCoursePanel viewCoursePnl;
    AssignTeacherToCoursePanel assignTeacherToCoursePnl;
    AssignStudentsToCoursePanel assignStudentsToCoursePnl;

    JFrame frame;
    JButton addStudent, viewStudent, addTeacher, viewTeacher, addCourse, viewCourse, assignTeacherToCourse,
            assignStudentToCourse;

    int screenWidth, screenHeight, buttonWidth, buttonHeight;

    public Gui() {
        DB = new DataController();
        frame = new JFrame("School Administrator System");
        addStudentPnl = new AddStudentPanel();
        viewStudentPnl = new ViewStudentPanel();
        addTeacherPnl = new AddTeacherPanel();
        viewTeacherPnl = new ViewTeacherPanel();
        addCoursePnl = new AddCoursePanel();
        viewCoursePnl = new ViewCoursePanel();
        assignTeacherToCoursePnl = new AssignTeacherToCoursePanel();
        assignStudentsToCoursePnl = new AssignStudentsToCoursePanel();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = screenSize.width;
        screenHeight = screenSize.height;
        buttonWidth = screenWidth / 10;
        buttonHeight = screenHeight / 25;

        // Menu Buttons
        buttonInit();

        frame.setSize(screenWidth, screenHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        createComponents();
        hidePanel();
        addStudentPnl.setVisible(true);
    }

    private void buttonInit() {

        System.out.println(buttonWidth);
        addStudent = new JButton("Add Student");
        int x = screenWidth / 25;
        int y = screenHeight / 25;
        addStudent.setBounds(x, y, buttonWidth, buttonHeight);
        addStudent.setFont(new Font("Serif", Font.BOLD, 14));
        addStudent.addActionListener(this);
        frame.add(addStudent);

        viewStudent = new JButton("View Student");

        viewStudent.setBounds(x + (buttonWidth) + 10, y, buttonWidth, buttonHeight);
        viewStudent.setFont(new Font("Serif", Font.BOLD, 14));
        viewStudent.addActionListener(this);
        frame.add(viewStudent);

        addTeacher = new JButton("Add Teacher");
        addTeacher.setBounds(x + (buttonWidth * 2) + 20, y, buttonWidth, buttonHeight);
        addTeacher.setFont(new Font("Serif", Font.BOLD, 14));
        addTeacher.addActionListener(this);
        frame.add(addTeacher);

        viewTeacher = new JButton("View Teacher");
        viewTeacher.setBounds(x + (buttonWidth * 3) + 30, y, buttonWidth, buttonHeight);
        viewTeacher.setFont(new Font("Serif", Font.BOLD, 14));
        viewTeacher.addActionListener(this);
        frame.add(viewTeacher);

        addCourse = new JButton("Add Course");
        addCourse.setBounds(x + (buttonWidth * 4) + 40, y, buttonWidth, buttonHeight);
        addCourse.setFont(new Font("Serif", Font.BOLD, 14));
        addCourse.addActionListener(this);
        frame.add(addCourse);

        viewCourse = new JButton("View Course");
        viewCourse.setBounds(x + (buttonWidth * 5) + 50, y, buttonWidth, buttonHeight);
        viewCourse.setFont(new Font("Serif", Font.BOLD, 14));
        viewCourse.addActionListener(this);
        frame.add(viewCourse);

        assignTeacherToCourse = new JButton("Assign Teacher");
        assignTeacherToCourse.setBounds(x + (buttonWidth * 6) + 60, y, buttonWidth, buttonHeight);
        assignTeacherToCourse.setFont(new Font("Serif", Font.BOLD, 14));
        assignTeacherToCourse.addActionListener(this);
        frame.add(assignTeacherToCourse);

        assignStudentToCourse = new JButton("Assign Student");
        assignStudentToCourse.setBounds(x + (buttonWidth * 7) + 70, y, buttonWidth, buttonHeight);
        assignStudentToCourse.setFont(new Font("Serif", Font.BOLD, 14));
        assignStudentToCourse.addActionListener(this);
        frame.add(assignStudentToCourse);

    }

    // panel init
    private void createComponents() {
        frame.add(addStudentPnl);
        frame.add(viewStudentPnl);
        frame.add(addTeacherPnl);
        frame.add(viewTeacherPnl);
        frame.add(addCoursePnl);
        frame.add(viewCoursePnl);
        frame.add(assignTeacherToCoursePnl);
        frame.add(assignStudentsToCoursePnl);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addStudent) {
            hidePanel();
            addStudentPnl.setVisible(true);
        } else if (e.getSource() == viewStudent) {
            hidePanel();
            viewStudentPnl.remove(viewStudentPnl.studentIdDropdown);
            ViewStudentPanel v = new ViewStudentPanel();
            v.studentIdList = DB.listOfStudentIds();
            DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
            for (String s : v.studentIdList) {
                comboBoxModel.addElement(s);
            }
            viewStudentPnl.studentIdDropdown.setModel(comboBoxModel);
            viewStudentPnl.add(viewStudentPnl.studentIdDropdown);
            viewStudentPnl.setVisible(true);
        } else if (e.getSource() == addTeacher) {
            hidePanel();
            addTeacherPnl.setVisible(true);
        } else if (e.getSource() == viewTeacher) {
            hidePanel();
            viewTeacherPnl.remove(viewTeacherPnl.teacherIdDropdown);
            ViewTeacherPanel v = new ViewTeacherPanel();
            v.teacherIdList = DB.listOfTeacherIds();
            DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
            for (String s : v.teacherIdList) {
                comboBoxModel.addElement(s);
            }
            viewTeacherPnl.teacherIdDropdown.setModel(comboBoxModel);
            viewTeacherPnl.add(viewTeacherPnl.teacherIdDropdown);
            viewTeacherPnl.setVisible(true);
        } else if (e.getSource() == addCourse) {
            hidePanel();
            addCoursePnl.setVisible(true);
        } else if (e.getSource() == viewCourse) {
            hidePanel();
            viewCourse.remove(viewCoursePnl.courseListDropDown);
            ViewCoursePanel v = new ViewCoursePanel();
            v.subjectList = DB.listOfCourseIds();
            DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
            for (String s : v.subjectList) {
                comboBoxModel.addElement(s);
            }
            viewCoursePnl.courseListDropDown.setModel(comboBoxModel);
            viewCoursePnl.add(viewCoursePnl.courseListDropDown);
            viewCoursePnl.setVisible(true);
        } else if (e.getSource() == assignTeacherToCourse) {
            hidePanel();
            assignTeacherToCoursePnl.setVisible(true);
        } else if (e.getSource() == assignStudentToCourse) {
            hidePanel();
            assignStudentsToCoursePnl.setVisible(true);
        }

    }

    public void hidePanel() {
        addStudentPnl.setVisible(false);
        viewStudentPnl.setVisible(false);
        addTeacherPnl.setVisible(false);
        viewTeacherPnl.setVisible(false);
        addCoursePnl.setVisible(false);
        viewCoursePnl.setVisible(false);
        assignTeacherToCoursePnl.setVisible(false);
        assignStudentsToCoursePnl.setVisible(false);
    }

}
