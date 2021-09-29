package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static util.UtilityClass.*;

/**
 * This class is for business logic of School Admin Application. <br>
 *     In this class we perform all the operations which are directly related to file. <br>
 *     All the operations of fetching and removing from file or data formation for GUI class to use are contains in this class.
 */
public class DataController {

    FileReader reader;
    File file;
    String fileData = "";

    public DataController() {
        file = new File("./src/masterFile.txt");
    }

    /**
     * This is used to remove data from file. <br>
     * It will take the record string StudentCourse:courseId:studentId in this format and find in file and remove that
     * line from file.
     * <b>Logic:</b> <br>
     *     We loop through each line of file and add the each line to one by one to new temporary variable except the removeData we
     *     get from method params. After completion of loop we replace file with temporary variable data.
     * @param removeData This will be the string record which Admin wants to delte from file.
     */
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
                fileData = fileData.replaceAll("\n+", "\n");
                FileWriter writer = new FileWriter(file, false);
                BufferedWriter buffer = new BufferedWriter(writer);
                buffer.write(fileData);
                buffer.close();
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

    /**
     * This is used to check if student is already exist in file. <br>
     * <b>Logic:</b> <br>
     *   We go through each line of file and when we find keyword Student we compare that line
     *   studentId with params studentId if they are same we return true.
     * @param studentID It will be studentId  which is  uniquely generated at creation time
     * @return method will return true if already exist in file with same id otherwise false
     */
    public boolean checkStudentExist(String studentID) {
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

    /**
     * This is used to check if teacher is already exist in file. <br>
     * <b>Logic:</b> <br>
     *        We go through each line of file and when we find keyword Teacher we compare that line <br>
     *         teacherId with params teacherId if they are same we return true. <br>
     * @param teacherId It will be teacherId which is uniquely generated at creation time
     * @return method will return true if already exist in file with same id otherwise false
     */
    public boolean checkTeacherExist(String teacherId) {
        boolean isteacherAvailable = false;
        try {

            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] teacher = line.split("@");
                if (teacher[0].equals("Teacher")) {
                    String[] teacherdata = teacher[1].split(":");
                    if (teacherId.equals(teacherdata[0])) {
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

    /**
     * This method will use to check if student is already enrolled in given course.<br>
     *     <b>Logic:</b> <br>
     *     We go through each line of file and when we find keyword StudentCourse we compare that line <br>
     *     courseId and studentId with param's courseId and studentId if they are same we return true. <br>
     * @param courseId It is the id of course for which we have to check
     * @param studentId It is the id of student for whom we have to check
     * @return method will return true if already enrolled in given course
     */
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

    /**
     * This method will use to check if teacher is already assigned in given course. <br>
     *     <b>Logic:</b> <br>
     *     We go through each line of file and when we find keyword TeacherCourse we compare that line <br>
     *     teacherId and courseId with param's teacherId and courseId if they are same we return true. <br>
     * @param courseId It is the id of course for which we have to check
     * @param teacherId It is the id of teacher for whom we have to check
     * @return method will return true if already assigned in given course otherwise false
     */
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

    /**
     * This is used to add the new student record at the end of the file <br>
     *     Record add to file will be in this format: Student@studentID:FullName:CourseName1;CourseName2;attandence:90%;grade:90 <br>
     * @param studentID Randomly generated id at the time of calling the method
     * @param studentFullName Student full name which is inserted in text field
     * @param listOfCourse Selected previous courses
     */
    public void addStudent(String studentID, String studentFullName, StringBuilder listOfCourse) {
        try {
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write("Student@" + studentID + ":" + studentFullName + ":" + listOfCourse);
            bufferedWriter.newLine();

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        printConsole("Add student : Confirm...");
    }
    /**
     * This is used to add the new teacher record at the end of the file <br>
     *     Record add to file will be in this format: Teacher@teacherID:FullName:CourseName1;CourseName2;
     * @param teacherID Randomly generated id at the time of calling the method
     * @param teacherFullName Teacher full name which is inserted in text field
     * @param listOfCourse None
     */
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

    /**
     * This is used to add the new course record at the end of the file <br>
     *     Record add to file will be in this format: Course@courseId:courseName:startDate:endDate:PreRequisite
     * @param courseId Randomly generated id at the time of calling the method
     * @param courseName Course  name which is inserted in text field
     * @param startDate  Course start date in DD-MM-YYYY format
     * @param endDate    Course completion date in DD-MM-YYYY format
     * @param preRequisite Prerequisite courses require to enroll in this course
     */
    public void addCourse(String courseId, String courseName, String startDate, String endDate, String preRequisite) {
        try {
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write(
                    "Course@" + courseId + ":" + courseName + ":" + startDate + ":" + endDate + ":" + preRequisite);
            bufferedWriter.newLine();

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        printConsole("Add Course : Confirm...");
    }

    /**
     * This is used to assign the course to student and create record for that the end of the file <br>
     *     Record add to file will be in this format: StudentCourse:courseID:studentId
     * @param courseId  id of course to which student needs to be add
     * @param studentId  id of student who will be added to course
     */
    public void assignCourseToStudent(String courseId, String studentId) {
        try {
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write("\nStudentCourse" + ":" + courseId + ":" + studentId+":attendance=100:grade=0");
            bufferedWriter.newLine();

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This is used to assign the course to teacher and create record for that the end of the file <br>
     *     Record add to file will be in this format: TeacherCourse:courseID:teacherId
     * @param courseId id of course to which teacher needs to be add
     * @param teacherId id of teacher who will be added to course
     */
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

    /**
     * This is used to get the total number of enrolled students in the course.
     * <b>Logic:</b> <br>
     *      We go through each line of file and when we find keyword StudentCourse we compare that line courseId with param's courseId
     *      after that we check if course end date is not in past then we increase the count.
     * @param courseId for which we have to find enrolled students
     * @return will return the count of enrolled students
     */
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

    /**
     * This is used to get the total number of assigned teachers in the course.
     *     <b>Logic:</b> <br>
     *        We go through each line of file and when we find keyword TeacherCourse we compare that line courseId with param's courseId
     *          after that we check if course end date is not in past then we increase the count.
     * @param courseId  for which we have to find assigned teachers
     * @return will return the count of assigned teachers
     */
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

    /**
     * This is used to get the total number of current course teacher is teaching.
     *     <b>Logic:</b> <br>
     *        We go through each line of file and when we find keyword TeacherCourse we compare that line teacherId with param's teacherId
     *          after that we check if course end date is not in past then we increase the count.
     * @param teacherId for which we have to find total number of current course
     * @return will return the count of total course teacher is teaching
     */
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

    /**
     * This is used to get the total number of current course student is enrolled.
     *     <b>Logic:</b> <br>
     *        We go through each line of file and when we find keyword StudentCourse we compare that line studentId with param's studentId
     *          after that we check if course end date is not in past then we increase the count.
     * @param studentId for which we have to find total number of current course
     * @return will return the count of total enrolled course
     */
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

    /**
     * This is used to get the list of courses which end date is not in past.
     * @return return the list of String of current course
     */
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

    /**
     * This is used to get the list of all courses irrespective of end date.
     * @return return the list of String of current course
     */
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

    /**
     * This is used to get the list of all student ids record from the file.
     * @return return the list of String of student ids
     */
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

    /**
     * This is used to get the list of all teacher ids record from the file.
     * @return return the list of String of teacher ids
     */
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

    /**
     * This is used to get the list of all course ids record from the file.
     * @return return the list of String of course ids
     */
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

    /**
     * This is used to get the list of all students record from the file.
     * @return return the list of String of students
     */
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

    /**
     * This is used to get the list of all records for assigned teacher to all courses from the file.<br>
     *     we use TeacherCourse:courseId:teacherId for assigned teacher in file.
     * @return return the list of String of assigned teachers to all course
     */
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

    /**
     * This is used to get the list of all records for enrolled students to all courses from the file.<br>
     *     we use StudentCourse:courseId:studentId for assigned students in file.
     * @return return the list of String of enrolled students to all course
     */
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

    /**
     * This is used to get the list of all teachers record from the file.
     * @return return the list of String of teachers
     */
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

    /**
     * This is used to get the details of particular student in particular string from the file.
     * @param studentId for whom data needs to be get
     * @return will return string of student details
     */
    public String fetchStudentById(String studentId) {
        StringBuilder studentDetails = new StringBuilder();
        String currentCourse = "";
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
                                studentDetails.append("Student Past Subject = ").append(value).append("\n");
                            } else {
                                studentDetails.append(value).append("\n");
                            }
                            i++;
                        }
                    }
                }
                String[] Variable = line.split(":");
                if(Variable[0].equals("StudentCourse"))
                {
                    if(Variable[2].equals(studentId))
                    {
                        if((Variable[4].split("=")[1]).equals("0"))
                        {
                            currentCourse = currentCourse + "Current Course = " + getCourseName(Variable[1]) + " \n[" + Variable[3] + " , grade = (On going session)]\n";
                        }
                        else {
                            currentCourse = currentCourse + "Current Course = " + getCourseName(Variable[1]) + " \n[" + Variable[3] + " , " + Variable[4] + "]\n";
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
        if(currentCourse.equals(""))
        {
            currentCourse = "[ Courses not assigned for current session! ]";
        }
        studentDetails.append(currentCourse);
        return studentDetails.toString();
    }

    public String getCourseName(String CourseID)
    {
        String cName = "";
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] course = line.split("@");
                if (course[0].equals("Course")) {
                    String[] Variable = course[1].split(":");
                    if (Variable[0].equals(CourseID)) {
                        cName = Variable[1];
                    }
                }
            }
        } catch (Exception ex) {
            printConsole(ex.getMessage());
        }
        return cName;
    }

    /**
     * This is used to get the details of particular teacher from the file.
     * @param teacherId for whom data needs to be get
     * @return will return string of teacher details
     */
    public String fetchTeacherById(String teacherId) {
        StringBuilder teacherDetails = new StringBuilder();
        String teachCourse = "";

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
                                //teacherDetails.append("Teacher Subject = " + value + "\n");
                            }
                            i++;
                        }
                    }
                }

                String[] tCourse = line.split(":");
                if(tCourse[0].equals("TeacherCourse"))
                {
                    if(tCourse[2].equals(teacherId))
                    {
                        teachCourse = teachCourse + getCourseDetails(tCourse[1]);
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

        if(teachCourse.equals(""))
        {
            teachCourse = " [ Courses not assigned to this Teacher! ]";
        }
        teacherDetails.append(teachCourse);
        return teacherDetails.toString();
    }

    public String getCourseDetails(String CourseID)
    {
        String cName = "";
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] course = line.split("@");
                if (course[0].equals("Course")) {
                    String[] Variable = course[1].split(":");
                    if (Variable[0].equals(CourseID)) {

                        cName += "Teacher Subject : "+Variable[1] + " (subID: "+ Variable[0] +" ) [ " + (isPastCourse(course[1]) == true ? "Past Course" : "Current Course") + " ]\n";
                    }
                }
            }
        } catch (Exception ex) {
            printConsole(ex.getMessage());
        }
        return cName;
    }


    /**
     * This is used to get the name of particular teacher from the file.
     * @param teacherId for whom data needs to be get
     * @return will return teacher name
     */
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

    /**
     * This is used to get the details of particular student from the file.<br>
     *     It will return the whole line of student studentID:FullName:CourseName1;CourseName2;attandence:90%;grade:90 like this.
     * @param studentId for whom data needs to be get
     * @return will return string of student details
     */
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

    /**
     * This is used to get the details of particular course from the file.
     * @param courseId for which data needs to be get
     * @return will return string of course details
     */
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
            printConsole(ex.getMessage());
        }


        return courseDetails;
    }

    public String getViewCourseFurther(String CourseID)
    {
        String sName = "";
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] course = line.split(":");
                if (course[0].equals("StudentCourse")) {
                    if (course[1].equals(CourseID)) {
                        if(Integer.parseInt(course[4].split("=")[1]) < 60)
                        {
                            if(course[4].split("=")[1].equals("0"))
                            {
                                sName = sName + getStudentNameById(course[2]) + " ,  grade=( grade not assigned )\n";
                            }
                            else
                            {
                                sName = sName + getStudentNameById(course[2]) + "  ,  " +course[4] + "\n";
                            }

                        }
                    }
                }
            }
        } catch (Exception ex) {
            printConsole(ex.getMessage());
        }
        return sName;
    }

    public String getFailingStudentList(String CourseID)
    {
        String sName = "";
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] course = line.split(":");
                if (course[0].equals("StudentCourse")) {
                    if (course[1].equals(CourseID)) {
                        if(Integer.parseInt(course[4].split("=")[1]) < 60)
                        {
                            if(course[4].split("=")[1].equals("0"))
                            {
                                sName = sName + getStudentNameById(course[2]) + " ,  grade=( grade not assigned )\n";
                            }
                            else
                            {
                                sName = sName + getStudentNameById(course[2]) + "  ,  " +course[4] + "\n";
                            }

                        }
                    }
                }
            }
        } catch (Exception ex) {
            printConsole(ex.getMessage());
        }
        return sName;
    }


    public String getAvgAttendance(String CourseID)
    {
        int avgAttande = 0;
        String str = "";
        int cnt = 0;
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] course = line.split(":");
                if (course[0].equals("StudentCourse")) {
                    if (course[1].equals(CourseID)) {
                        avgAttande = avgAttande + Integer.parseInt(course[3].split("=")[1]);
                        cnt++;
                    }
                }
            }
            if(cnt > 0)
            {
                str = String.valueOf(avgAttande/cnt);
            }
            else
            {
                str = " [ No student assigned ]";
            }

        } catch (Exception ex) {
            printConsole(ex.getMessage());
        }
        return str;
    }

    public String getAvgGrade(String CourseID)
    {
        int avgGrade = 0;
        String str = "";
        int cnt = 0;
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] course = line.split(":");
                if (course[0].equals("StudentCourse")) {
                    if (course[1].equals(CourseID)) {
                        avgGrade = avgGrade + Integer.parseInt(course[4].split("=")[1]);
                        cnt++;
                    }
                }
            }
            if(cnt > 0)
            {
                str = String.valueOf(avgGrade/cnt);
            }
            else
            {
                str = " [ No student assigned ]";
            }

        } catch (Exception ex) {
            printConsole(ex.getMessage());
        }
        return str;
    }


    public String getStudentNameById(String studentID)
    {
        String sName = "";
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] course = line.split("@");
                if (course[0].equals("Student")) {
                    String[] Variable = course[1].split(":");
                    if (Variable[0].equals(studentID)) {

                        sName = Variable[1];
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            printConsole(ex.getMessage());
        }
        return sName;
    }

    /**
     * This is used to get the list of all enrolled students for particular course.
     * @param courseId for which data needs to be get
     * @return return the list of String of enrolled students
     */
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

    /**
     * It is used to print the  message to console
     * @param msg message that needs to be displayed in console
     */
    // common message print gateway
    public static void printConsole(String msg) {
        System.out.println(msg);
    }

    /**
     * This is used to get the list of all enrolled students for particular course.
     * @param courseId for which data needs to be get
     * @param studentId for which data needs to be get
     * @return return the grade of specific students in specific course
     */
    public String fetchGradeOfStudent(String courseId,String studentId)
    {
        String grade="0";
        try {
            FileReader reader = new FileReader(file);
            BufferedReader fetchCourseBufferedReader = new BufferedReader(reader);
            String line;
            while ((line = fetchCourseBufferedReader.readLine()) != null) {
                String[] courseStudent = line.split(":");
                if (courseStudent[0].equals("StudentCourse") && courseId.equals(courseStudent[1]) && studentId.equals(courseStudent[2])) {
                   grade = courseStudent[4].split("=")[1];
                    break;
                }
            }
            reader.close();
        } catch (Exception ex) {
            printConsole(ex.getMessage());
        }
        return grade;
    }


    public void editStudentGrade(String courseId, String studentId, String grade) {
        ArrayList<String> tempArray = new ArrayList<>();

        try {
            FileReader reader = new FileReader(file);
            BufferedReader fetchCourseBufferedReader = new BufferedReader(reader);
            String line;
            while ((line = fetchCourseBufferedReader.readLine()) != null) {
                String[] courseStudent = line.split(":");
                if (courseStudent[0].equals("StudentCourse") && courseId.equals(courseStudent[1]) && studentId.equals(courseStudent[2])) {
                    tempArray.add("StudentCourse:"+courseStudent[1]+":"+courseStudent[2]+":"+courseStudent[3]+":grade="+grade);
                }
                else
                {
                    tempArray.add(line);
                }
            }
            reader.close();
        } catch (Exception ex) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printConsole(ex.getMessage());
        }


            try(PrintWriter pr = new PrintWriter(file)){
                for(String str : tempArray){
                    pr.println(str);
                }
                pr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


    }
}
