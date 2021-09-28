package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static util.UtilityClass.*;

public class DataController {

    FileReader reader;
    File file;
    String fileData = "";

    public DataController() {
        file = new File("./src/masterFile.txt");

    }

    public void readFile() {

        try {
            FileReader reader = new FileReader(file);

            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while (true) {
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (Exception ex) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printConsole(ex.getMessage());
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeFromFile(String removeData) {
        boolean shouldAdd = false;
        try {
            fileData = "";
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.contains(removeData)) {
                    fileData = fileData + "\n" + line;
                } else {
                    shouldAdd = true;
                }
            }
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printConsole(ex.getMessage());
        }

        if (shouldAdd) {
            try {

                FileWriter writer = new FileWriter(file, false);
                BufferedWriter buffer = new BufferedWriter(writer);
                buffer.write(fileData);
                buffer.close();
                System.out.println("Success");
            } catch (Exception ex) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                printConsole(ex.getMessage());
            }
        }
    }

    public boolean checkStudent(String studentID) {
        boolean isStudentAvailable = false;
        try {

            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] student = line.split("@");
                if (student[0].equals("Student")) {
                    String[] studentdata = student[1].split(":");
                    if (studentID.equals(studentdata[0])) {
                        isStudentAvailable = true;
                        break;
                    }
                }
                if (isStudentAvailable)
                    break;
            }
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printConsole(ex.getMessage());
        }
        return isStudentAvailable;
    }

    public boolean checkTeacher(String studentID) {
        boolean isteacherAvailable = false;
        try {

            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] teacher = line.split("@");
                if (teacher[0].equals("Teacher")) {
                    String[] studentdata = teacher[1].split(":");
                    if (studentID.equals(studentdata[0])) {
                        isteacherAvailable = true;
                        break;
                    }
                }
            }
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printConsole(ex.getMessage());
        }
        return isteacherAvailable;
    }

    public boolean checkStudentAlreadyEnrolledInCourse(String courseId, String studentId){
        try{
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] studentCourse = line.split(":");
                if (studentCourse[0].equals("StudentCourse") && courseId.equals(studentCourse[1]) && studentId.equals(studentCourse[2])) {
                    return true;
                }
            }
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (Exception ex) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printConsole(ex.getMessage());
        }
        return false;
    }
    public boolean checkTeacherAlreadyAssignedInCourse(String courseId, String teacherId){
        try{
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] teacherCourse = line.split(":");
                if (teacherCourse[0].equals("TeacherCourse") && courseId.equals(teacherCourse[1]) && teacherId.equals(teacherCourse[2])) {
                    return true;
                }
            }
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (Exception ex) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printConsole(ex.getMessage());
        }
        return false;
    }
    public void addStudent(String studentID, String studentFullName, StringBuilder listOfCourse) {
        try {
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write("Student@" + studentID + ":" + studentFullName + ":" + listOfCourse
                    + ":attendance = 90%:grade = A++");
            bufferedWriter.newLine();

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        printConsole("Add student : Confirm...");
    }

    public void addTeacher(String teacherID, String teacherFullName, String listOfCourse) {
        try {
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write("Teacher@" + teacherID + ":" + teacherFullName + ":" + listOfCourse);
            bufferedWriter.newLine();

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        printConsole("Add teacher : Confirm...");
    }

    public void addCourse(String CourseID, String courseName, String startDate, String endDate, String preRequisite) {
        try {
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write(
                    "Course@" + CourseID + ":" + courseName + ":" + startDate + ":" + endDate + ":" + preRequisite);
            bufferedWriter.newLine();

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        printConsole("Add Course : Confirm...");
    }

    public void assignCourseToStudent(String courseId, String studentId) {
        try {
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write("\nStudentCourse" + ":" + courseId + ":" + studentId);
            bufferedWriter.newLine();

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void assignCourseToTeacher(String courseId, String teacherId) {
        try {
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write("\nTeacherCourse" + ":" + courseId + ":" + teacherId);
            bufferedWriter.newLine();

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getCourseCurrentStudentCapacity(String courseId) {
        int count = 0;
        try {

            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] studentCourse = line.split(":");
                if (studentCourse[0].equals("StudentCourse") && courseId.equals(studentCourse[1])) {
                    String course = fetchCourseById(studentCourse[1]);
                    if (!isPastCourse(course)) {
                        count++;
                    }
                }
            }
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printConsole(ex.getMessage());
        }
        return count;
    }

    public int getCourseCurrentTeacherCapacity(String courseId) {
        int count = 0;
        try {

            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] teacherCourse = line.split(":");
                if (teacherCourse[0].equals("TeacherCourse") && courseId.equals(teacherCourse[1])) {
                    String course = fetchCourseById(teacherCourse[1]);
                    if (!isPastCourse(course)) {
                        count++;
                    }
                }
            }
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printConsole(ex.getMessage());
        }
        return count;
    }

    public int getTeacherTeachingCourseCount(String teacherId) {
        int count = 0;
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader1 = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader1.readLine()) != null) {
                String[] teacherCourse = line.split(":");
                System.out.println("line" + line);
                if (teacherCourse[0].equals("TeacherCourse") && teacherId.equals(teacherCourse[2])) {
                    String course = fetchCourseById(teacherCourse[1]);
                    if (!isPastCourse(course)) {
                        count++;
                    }
                }
            }
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printConsole(ex.getMessage());
        }
        return count;
    }

    public int getStudentEnrolledCourseCount(String studentId) {
        int count = 0;
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] studentCourse = line.split(":");
                if (studentCourse[0].equals("StudentCourse") && studentId.equals(studentCourse[2])) {
                    String course = fetchCourseById(studentCourse[1]);
                    if (!isPastCourse(course)) {
                        count++;
                    }
                }
            }
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printConsole(ex.getMessage());
        }
        return count;
    }

    public List<String> listOfCurrentCourse() {
        List<String> list = new ArrayList<>();
        try {

            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Course = line.split("@");
                if (Course[0].equals("Course")) {
                    String[] Variable = Course[1].split(":");
                    // printConsole(Variable[3]);
                    String[] EDate = Variable[3].split("-"); // date format : DD-MM-YYYY
                    if (Integer.parseInt(EDate[0]) >= CurrentDay() && Integer.parseInt(EDate[1]) >= CurrentMonth()
                            && Integer.parseInt(EDate[2]) >= CurrentYear())
                        ;
                    {
                        list.add(Variable[0] + ":" + Variable[1]);
                        // printConsole("Filtered Past course : "+line);
                    }
                }
            }
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printConsole(ex.getMessage());
        }
        return list;
    }

    public List<String> listOfCourse() {
        List<String> list = new ArrayList<>();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Course = line.split("@");
                if (Course[0].equals("Course")) {
                    String[] Variable = Course[1].split(":");
                    // printConsole(Variable[3]);
                    list.add(Variable[0] + ":" + Variable[1]);
                }
            }
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printConsole(ex.getMessage());
        }
        return list;
    }

    public List<String> listOfStudentIds() {
        List<String> list = new ArrayList<>();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Student = line.split("@");
                if (Student[0].equals("Student")) {
                    String[] Variable = Student[1].split(":");
                    // printConsole(Variable[3]);
                    list.add(Variable[0]);
                }
            }
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printConsole(ex.getMessage());
        }
        return list;
    }

    public List<String> listOfTeacherIds() {
        List<String> list = new ArrayList<>();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Student = line.split("@");
                if (Student[0].equals("Teacher")) {
                    String[] Variable = Student[1].split(":");
                    // printConsole(Variable[3]);
                    list.add(Variable[0]);
                }
            }
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printConsole(ex.getMessage());
        }
        return list;
    }

    public List<String> listOfCourseIds() {
        List<String> list = new ArrayList<>();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Student = line.split("@");
                if (Student[0].equals("Course")) {
                    String[] Variable = Student[1].split(":");
                    // printConsole(Variable[3]);
                    list.add(Variable[0]);
                }
            }
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printConsole(ex.getMessage());
        }
        return list;
    }

    public List<String> listOfStudents() {
        List<String> list = new ArrayList<>();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Student = line.split("@");
                if (Student[0].equals("Student")) {
                    String[] Variable = Student[1].split(":");
                    // printConsole(Variable[3]);
                    list.add(Variable[0] + ":" + Variable[1]);
                }
            }
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printConsole(ex.getMessage());
        }
        return list;
    }

    public List<String> listOfTeacherCourse() {
        List<String> list = new ArrayList<>();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] courseTeacher = line.split(":");
                if (courseTeacher[0].equals("TeacherCourse")) {

                    String course = fetchCourseById(courseTeacher[1]);
                    String[] courseList = course.split(":");

                    String teacher = fetchTeacherNameById(courseTeacher[2]);
                    // String[] teacherList = teacher.split(":");

                    list.add(courseList[1] + " : " + courseTeacher[1] + " : " + teacher + " : " + courseTeacher[2]);
                }
            }
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printConsole(ex.getMessage());
        }
        return list;
    }

    public List<String> listOfStudentCourse() {
        List<String> list = new ArrayList<>();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] courseStudent = line.split(":");
                if (courseStudent[0].equals("StudentCourse")) {

                    String course = fetchCourseById(courseStudent[1]);
                    String[] courseList = course.split(":");

                    String student = fetchStudentDetailsById(courseStudent[2]);
                    String[] studentStringArr =  student.split(":");
                    // String[] teacherList = teacher.split(":");

                    list.add(courseList[1] + " : " + courseStudent[1] + " : " + studentStringArr[1] + " : " + courseStudent[2]);
                }
            }
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printConsole(ex.getMessage());
        }
        return list;
    }

    public List<String> listOfTeachers() {
        List<String> list = new ArrayList<>();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] teacher = line.split("@");
                if (teacher[0].equals("Teacher")) {
                    String[] Variable = teacher[1].split(":");
                    // printConsole(Variable[3]);
                    list.add(Variable[0] + ":" + Variable[1]);
                }
            }
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printConsole(ex.getMessage());
        }
        return list;
    }

    public String fetchStudentById(String studentId) {
        StringBuilder studentDetails = new StringBuilder();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Student = line.split("@");
                if (Student[0].equals("Student")) {
                    String[] Variable = Student[1].split(":");
                    if (Variable[0].equals(studentId)) {
                        int i = 0;
                        for (String value : Variable) {
                            if (i == 0) {
                                studentDetails.append("Student Id = ").append(value).append("\n");
                            } else if (i == 1) {
                                studentDetails.append("Student Name = ").append(value).append("\n");
                            } else if (i == 2) {
                                studentDetails.append("Student Subject = ").append(value).append("\n");
                            } else {
                                studentDetails.append(value).append("\n");
                            }
                            i++;
                        }
                    }
                }
            }
        } catch (Exception ex) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printConsole(ex.getMessage());
        }
        return studentDetails.toString();
    }

    public String fetchTeacherById(String teacherId) {
        StringBuilder teacherDetails = new StringBuilder();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Student = line.split("@");
                if (Student[0].equals("Teacher")) {
                    String[] Variable = Student[1].split(":");
                    if (Variable[0].equals(teacherId)) {
                        int i = 0;
                        for (String value : Variable) {
                            if (i == 0) {
                                teacherDetails.append("Teacher Id = " + value + "\n");
                            } else if (i == 1) {
                                teacherDetails.append("Teacher Name = " + value + "\n");
                            } else if (i == 2) {
                                teacherDetails.append("Teacher Subject = " + value + "\n");
                            }
                            i++;
                        }
                    }
                }
            }
        } catch (Exception ex) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printConsole(ex.getMessage());
        }
        return teacherDetails.toString();
    }

    public String fetchTeacherNameById(String teacherId) {
        StringBuilder teacherDetails = new StringBuilder();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Student = line.split("@");
                if (Student[0].equals("Teacher")) {
                    String[] Variable = Student[1].split(":");
                    if (Variable[0].equals(teacherId)) {
                        int i = 0;
                        for (String value : Variable) {
                            if (i == 1) {
                                teacherDetails.append(value);
                            }
                            i++;
                        }
                    }
                }
            }
        } catch (Exception ex) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printConsole(ex.getMessage());
        }
        return teacherDetails.toString();
    }

    public String fetchStudentDetailsById(String studentId) {
        StringBuilder studentDetails = new StringBuilder();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Student = line.split("@");
                if (Student[0].equals("Student")) {
                    String[] Variable = Student[1].split(":");
                    if (Variable[0].equals(studentId)) {
                        studentDetails.append(Student[1]);
                    }
                }
            }
        } catch (Exception ex) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printConsole(ex.getMessage());
        }
        return studentDetails.toString();
    }

    public String fetchCourseById(String courseId) {
        String courseDetails = "";
        try {
            FileReader reader = new FileReader(file);
            BufferedReader fetchCourseBufferedReader = new BufferedReader(reader);
            String line;
            while ((line = fetchCourseBufferedReader.readLine()) != null) {
                String[] course = line.split("@");
                if (course[0].equals("Course")) {
                    String[] Variable = course[1].split(":");
                    if (Variable[0].equals(courseId)) {
                        courseDetails = course[1];
                    }
                }
            }
        } catch (Exception ex) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printConsole(ex.getMessage());
        }
        return courseDetails;
    }

    public List<String> fetchCourseStudentsById(String courseId) {
        List<String> list = new ArrayList<>();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader fetchCourseBufferedReader = new BufferedReader(reader);
            String line;
            while ((line = fetchCourseBufferedReader.readLine()) != null) {
                String[] courseStudent = line.split(":");
                if (courseStudent[0].equals("StudentCourse") && courseId.equals(courseStudent[1])) {
                    list.add(courseStudent[2]);
                }
            }
        } catch (Exception ex) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printConsole(ex.getMessage());
        }
        return list;
    }

    // common message print gateway
    public static void printConsole(String msg) {
        System.out.println(msg);
    }

}
