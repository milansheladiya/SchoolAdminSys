# School Administrator System
Explaination Video : https://drive.google.com/file/d/121rbR4z3HAtRypEJjZWITFdpoeSaslDU/view?usp=sharing

# About Project

        We have created School Application. This school application will manage courses, 
    students, teachers, and grades. This application is directed towards Administration of the school. 
    Meaning, Our user is the administrator.

    There are various features which mention below.

    The administrator can
        Create/View teachers and students.
            o Can not edit the teacher or student after creation.
            o Can Add to class or remove from class.
            o Can edit grades of a course for specific student.

        Create/Edit/View courses.
            o Start Date / End Date
                 Not changeable after creation.
            o Pre-requisite courses. 
            o Teacher
                 Only current courses. (See end date)
            o Students
                 Only current courses. (See end date)
            o Average Grade
            o Attendance.

        Assign/remove teacher to a course. (Only 1 teacher per course)
            o Teachers only allowed to teach 2 courses at once.

        Assign/remove students to a course. (Max of 5 then course is full) 
            o Students are only allowed to take 3 courses at once.

        View Student
            o Full Name
            o Student ID
            o Current Courses
            o Past Courses
            o Grades

        For past courses

        For current course if no grade, show somehow that it is in session.

        View Teacher
            o Full Name
            o Teacher ID
            o Current Courses
            o Past Courses

# Task Division

      **Dishant Desai(2094440)**
        
        Base Plane
            Discuss and create base plan for project based on given definition

        View Teacher
            Add TeacherId - TeacherName in teacher dropdown select

        Assign Student
            Created UI for assign student
            Created functionality to assign student to specific subject 

        Assign Teacher
            Created UI for assign teacher
            Created functionality to assign student to specific teacher 

        Add Course
            Created UI for adding course

        Edit Grade
            Created Ui  
            fetch all course data from file and set in UI
            fetch student data based on selected course

        JavaDoc
            Added comments in all project
            Created java documents for project

        GUI 
            Added various components in panel
            Solved issue related refreshing UI after various operation 
        
        Clean Code
            make separation of code based on various panel and components 
            created different files of different class

        Error Solving
            Solved error related UI after testing
            Solved error related functionality after testing
        
    --------------------------------------------------------------------------------------------------

     **Namra Patel (2093971)**

        Base Plane
            Discuss and create base plan for project based on given definition

        GUI
            Created Dynamic Buttons
            Created Panels with dynamic height and width

        Add Student
            Created UI for add student

        View Student
            Created UI for view student
            Created functionality to Read all data of specific student based on Student Id
            Display student data in UI

        Add Teacher
            Created UI for add teacher

        View Teacher
            Created UI for view teacher
            Created functionality to Read all data of specific teacher based on Teacher Id
            Display teacher data in UI

        View Course
            Created UI for view course
            Created functionality to Read all data of specific course based on Course Id
            Display course data in UI

        Remove Teacher
            Created UI for view list of teacher with various courses
            Created functionality to remove teacher of specific courses

        Remove Student
            Created UI for view list of teacher with various courses
            Created functionality to remove teacher of specific courses

        Error Solving
            Solved error related UI after testing
            Solved error related functionality after testing


    --------------------------------------------------------------------------------------------------


     **Milan Sheladiya(2092040)**

        Base Plane
            Discuss and create base plan for project based on given definition

        GUI
            Created Base Code for UI
        
        Controller
            Created base file for backend functionality

        Utility
            Create Utility class to remove the code duplication

        Add Student
            Created functionality for adding student data in file

        View Student
            changes in attendence in grade

        View Teacher
            Past course and current course

        View Course
            Changes in average grade , average attendence

        Edit Grade
            Ui changes 
            fetch student grade based on selected student and course
            Created edit grade functionality

        Add Teacher
            make changes related UI
            Created functionality to add teacher data in file

        Add Course
            Created Ui for add course
            Created functionality for adding data in file

        Failing Students should be notified to the Administrator somehow
        
        Error Solving
            Solved error related UI after testing
            Solved error related functionality after testing
            



        

# Project Site Link

[School Administrator System](https://github.com/milansheladiya/SchoolAdminSys)


# Project Demo Video link

[School Administrator System](https://drive.google.com/file/d/121rbR4z3HAtRypEJjZWITFdpoeSaslDU/view?usp=sharing)
