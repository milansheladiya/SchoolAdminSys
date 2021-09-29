package gui;

import controller.DataController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *      @author Milan Sheladiya - 2092040
 *              Dishant Desai - 2094440
 *              Namra Patel - 2093971
 *
 *      @version 1.0.0 <br>
 *
 *      This class is School Administrator GUI which have all the functionality as below.
 *      Admin can:
 *      1. Add student
 *      2. Add teacher
 *      3. View student
 *      4. View teacher
 *      5. Add course
 *      6. View course
 *      7. Assign/remove teacher from course
 *      8. Assign/remove student from course
 *
 *      We have used the GridBagLayout for all the panels except Parent frame.
 */
public class Gui implements ActionListener {
    /**
     * This is the object of class {@link DataController} to access the file operations from controller.
     */
    DataController DB;

    AddStudentPanel addStudentPnl;
    ViewStudentPanel viewStudentPnl;
    AddTeacherPanel addTeacherPnl;
    ViewTeacherPanel viewTeacherPnl;
    AddCoursePanel addCoursePnl;
    ViewCoursePanel viewCoursePnl;
    AssignTeacherToCoursePanel assignTeacherToCoursePnl;
    AssignStudentsToCoursePanel assignStudentsToCoursePnl;
    RemoveStudentsFromCoursePanel removeStudentsToCoursePnl;
    RemoveTeacherToCoursePanel removeTeacherToCoursePnl;
    EditGrades editGradesPnl;

    JFrame frame;
    JButton addStudent, viewStudent, addTeacher, viewTeacher, addCourse, viewCourse, assignTeacherToCourse,
            assignStudentToCourse, removerTecherCourse, removeStudentCourse, editGrade;

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
        removeStudentsToCoursePnl = new RemoveStudentsFromCoursePanel();
        removeTeacherToCoursePnl = new RemoveTeacherToCoursePanel();
        editGradesPnl = new EditGrades();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = screenSize.width;
        screenHeight = screenSize.height;
        buttonWidth = screenWidth / 12;
        buttonHeight = 40;

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

        addStudent = new JButton("Add Student");
        int x = screenWidth / 85;
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

        editGrade = new JButton("Edit Grade");
        editGrade.setBounds(x + (buttonWidth * 2) + 20, y, 120, buttonHeight);
        editGrade.setFont(new Font("Serif", Font.BOLD, 14));
        editGrade.addActionListener(this);
        frame.add(editGrade);

        addTeacher = new JButton("Add Teacher");
        addTeacher.setBounds(x + (buttonWidth * 3) + 30, y, buttonWidth, buttonHeight);
        addTeacher.setFont(new Font("Serif", Font.BOLD, 14));
        addTeacher.addActionListener(this);
        frame.add(addTeacher);

        viewTeacher = new JButton("View Teacher");
        viewTeacher.setBounds(x + (buttonWidth * 4) + 40, y, buttonWidth, buttonHeight);
        viewTeacher.setFont(new Font("Serif", Font.BOLD, 14));
        viewTeacher.addActionListener(this);
        frame.add(viewTeacher);

        addCourse = new JButton("Add Course");
        addCourse.setBounds(x + (buttonWidth * 5) + 50, y, buttonWidth, buttonHeight);
        addCourse.setFont(new Font("Serif", Font.BOLD, 14));
        addCourse.addActionListener(this);
        frame.add(addCourse);

        viewCourse = new JButton("View Course");
        viewCourse.setBounds(x + (buttonWidth * 6) + 60, y, buttonWidth, buttonHeight);
        viewCourse.setFont(new Font("Serif", Font.BOLD, 14));
        viewCourse.addActionListener(this);
        frame.add(viewCourse);

        assignTeacherToCourse = new JButton("Assign Teacher");
        assignTeacherToCourse.setBounds(x + (buttonWidth * 7) + 70, y, buttonWidth, buttonHeight);
        assignTeacherToCourse.setFont(new Font("Serif", Font.BOLD, 14));
        assignTeacherToCourse.addActionListener(this);
        frame.add(assignTeacherToCourse);

        assignStudentToCourse = new JButton("Assign Student");
        assignStudentToCourse.setBounds(x + (buttonWidth * 8) + 80, y, buttonWidth, buttonHeight);
        assignStudentToCourse.setFont(new Font("Serif", Font.BOLD, 14));
        assignStudentToCourse.addActionListener(this);
        frame.add(assignStudentToCourse);

        removerTecherCourse = new JButton("Remove Teacher");
        removerTecherCourse.setBounds(x + (buttonWidth * 9) + 90, y, buttonWidth, buttonHeight);
        removerTecherCourse.setFont(new Font("Serif", Font.BOLD, 14));
        removerTecherCourse.addActionListener(this);
        frame.add(removerTecherCourse);

        removeStudentCourse = new JButton("Remove Student");
        removeStudentCourse.setBounds(x + (buttonWidth * 10) + 100, y, buttonWidth, buttonHeight);
        removeStudentCourse.setFont(new Font("Serif", Font.BOLD, 14));
        removeStudentCourse.addActionListener(this);
        frame.add(removeStudentCourse);

    }

    // panel init
    private void createComponents() {
        frame.add(addStudentPnl);
        frame.add(viewStudentPnl);
        frame.add(editGradesPnl);
        frame.add(addTeacherPnl);
        frame.add(viewTeacherPnl);
        frame.add(addCoursePnl);
        frame.add(viewCoursePnl);
        frame.add(assignTeacherToCoursePnl);
        frame.add(assignStudentsToCoursePnl);
        frame.add(removeTeacherToCoursePnl);
        frame.add(removeStudentsToCoursePnl);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addStudent) {
            hidePanel();
            addStudentPnl.msgLable.setText("");
            addStudentPnl.setVisible(true);
        } else if (e.getSource() == viewStudent) {
            hidePanel();
            viewStudentPnl.studentDataViewArea.setText("");
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
            addTeacherPnl.msgLable.setText("");
            addTeacherPnl.setVisible(true);
        } else if (e.getSource() == viewTeacher) {
            hidePanel();
            viewTeacherPnl.TeacherDataViewArea.setText("");
            viewTeacherPnl.remove(viewTeacherPnl.teacherListDropdown);
            ViewTeacherPanel v = new ViewTeacherPanel();
            v.teacherList = DB.listOfTeachers();
            DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
            for (String teacher : v.teacherList) {
                String[] teacherStringArr = teacher.split(":");
                comboBoxModel.addElement(teacherStringArr[0] + " - " + teacherStringArr[1]);
            }
            viewTeacherPnl.teacherListDropdown.setModel(comboBoxModel);
            viewTeacherPnl.add(viewTeacherPnl.teacherListDropdown);
            viewTeacherPnl.setVisible(true);
        } else if (e.getSource() == addCourse) {
            hidePanel();
            addCoursePnl.msgLable.setText("");
            addCoursePnl.setVisible(true);
        } else if (e.getSource() == viewCourse) {
            hidePanel();
            viewCoursePnl.CourseDataViewArea.setText("");
            viewCourse.remove(viewCoursePnl.courseListDropDown);
            ViewCoursePanel v = new ViewCoursePanel();
            v.courseList = DB.listOfCourse();
            DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
            for (String course : v.courseList) {
                String[] courseStringArr = course.split(":");
                comboBoxModel.addElement(courseStringArr[0] + " - " + courseStringArr[1]);
            }
            viewCoursePnl.courseListDropDown.setModel(comboBoxModel);
            viewCoursePnl.add(viewCoursePnl.courseListDropDown);
            viewCoursePnl.setVisible(true);
        } else if (e.getSource() == assignTeacherToCourse) {
            hidePanel();
            assignTeacherToCoursePnl.msgLable.setText("");
            assignTeacherToCoursePnl.setVisible(true);
        } else if (e.getSource() == assignStudentToCourse) {
            hidePanel();
            assignStudentsToCoursePnl.msgLable.setText("");
            assignStudentsToCoursePnl.setVisible(true);
        } else if (e.getSource() == removerTecherCourse) {
            hidePanel();
            removerTecherCourse.remove(viewCoursePnl.courseListDropDown);
            RemoveTeacherToCoursePanel v = new RemoveTeacherToCoursePanel();
            v.teacherCourseList = DB.listOfTeacherCourse();
            DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
            for (String s : v.teacherCourseList) {
                comboBoxModel.addElement(s);
            }
            removeTeacherToCoursePnl.courseListDropdown.setModel(comboBoxModel);
            removeTeacherToCoursePnl.add(removeTeacherToCoursePnl.courseListDropdown);
            removeTeacherToCoursePnl.setVisible(true);
        } else if (e.getSource() == removeStudentCourse) {
            hidePanel();
            removeStudentCourse.remove(viewCoursePnl.courseListDropDown);
            RemoveStudentsFromCoursePanel v = new RemoveStudentsFromCoursePanel();
            v.studentCourseList = DB.listOfStudentCourse();
            DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
            for (String s : v.studentCourseList) {
                comboBoxModel.addElement(s);
            }
            removeStudentsToCoursePnl.courseListDropdown.setModel(comboBoxModel);
            removeStudentsToCoursePnl.add(removeStudentsToCoursePnl.courseListDropdown);
            removeStudentsToCoursePnl.setVisible(true);
        }else if(e.getSource() == editGrade){
            hidePanel();
            editGradesPnl.msgLabel.setText("");
            editGrade.remove(editGradesPnl.courseListDropdown);
            EditGrades.courseList = DB.listOfCourseIds();
            DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
            for (String s : EditGrades.courseList) {
                comboBoxModel.addElement(s);
            }
            editGradesPnl.courseListDropdown.setModel(comboBoxModel);
            editGradesPnl.add(editGradesPnl.courseListDropdown);
            editGradesPnl.setVisible(true);
        }
    }

    /**
     * It will hide the all panels from GUI.
     */
    public void hidePanel() {
        addStudentPnl.setVisible(false);
        viewStudentPnl.setVisible(false);
        addTeacherPnl.setVisible(false);
        viewTeacherPnl.setVisible(false);
        addCoursePnl.setVisible(false);
        viewCoursePnl.setVisible(false);
        assignTeacherToCoursePnl.setVisible(false);
        assignStudentsToCoursePnl.setVisible(false);
        removeTeacherToCoursePnl.setVisible(false);
        removeStudentsToCoursePnl.setVisible(false);
        editGradesPnl.setVisible(false);
    }

}
