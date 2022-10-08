//Vishaun Jones
//April 24, 2022
//Assignment 6 - School
//Silei Sol
//CGS3416

import java.io.BufferedReader; //Import the BufferReader class to read the input file line by line
import java.io.File;  // Import the File class to create a new file
import java.io.FileReader; // Import the FileReader class to read the input file
import java.io.FileWriter;   // Import the FileWriter class to write into the new file
import java.io.IOException;  // Import the IOException class to handle errors

abstract class Student {
    public String name;
    public char letterGrade;

    public String getName() {
        return name;
    }

    public char getLetterGrade() {
        return letterGrade;
    }

    public abstract void setLetterGrade();
}

class Undergrad extends Student {
    private double gpa;

    public Undergrad(String name, double a, double b, double c) {
        super.name = name;
        gpa = (a+b+c)/3;
    }

    public void setLetterGrade() {

        //The student's letter grade will be based on where the gpa falls in a range from 100 to 60.
        if (this.gpa >= 90) {
            super.letterGrade = 'a';
        } else if (this.gpa >= 80) {
            super.letterGrade = 'b';
        } else if (this.gpa >= 70) {
            super.letterGrade = 'c';
        } else if (this.gpa >= 60) {
            super.letterGrade = 'd';
        } else {
            super.letterGrade = 'f';
        }
    }
}

class Graduate extends Student {
    private boolean pass1 = false;
    private boolean pass2 = false;
    private boolean pass3 = false;

    public Graduate(String name, char a, char b, char c) {
        super.name = name;

        //If any of the input characters are P the boolean value will change to pass variables to true
        if (a == 'P') {
            pass1 = true;
        }
        if (b == 'P') {
            pass2 = true;
        }
        if (c == 'P') {
            pass3 = true;
        }
    }

    public void setLetterGrade() {
        //If all the pass variables are true the student's letter grade will set to P, if not then the student's letter grade will be set to N
        if (pass1 && pass2 && pass3) {
            letterGrade = 'P';
        } else {
            letterGrade = 'N';
        }
    }
}

public class School {
    public static void main(String[] args) {
        String[] students = new String[10]; //Array to hold extracted information from the input.txt file

        try {
            //Create file reader
            FileReader file = new FileReader("input.txt");

            //Create a buffer reader
            BufferedReader input = new BufferedReader(file);

            //Read file into array using a loop
            for (int i = 0; i < 10; ++i) {
                students[i] = input.readLine();
                input.mark(1000);
            }

            //Close reader
            input.close();
        }

        //Catches any errors
        catch (Exception e) {
            e.getStackTrace();
        }

        try {

            //Create new file
            File newFile = new File("output.txt");
            newFile.createNewFile();

            //Reference variable to write into new file the grades of the students
            FileWriter myWriter = new FileWriter("output.txt");

            //Loop for the Undergrad Students, the first half of the students array, used to extract information and write out to a new file
            for (int i = 0; i < 5; ++i) {
                //Temporary array used to store the student information individually, without the white space
                String[] information = students[i].split(" ");

                //Variables used to call to the information stored in the temporary array
                String studentName = information[0];
                Double grade1 = Double.parseDouble(information[1]);
                Double grade2 = Double.parseDouble(information[2]);
                Double grade3 = Double.parseDouble(information[3]);

                //Object for Undergrad class that passes in the variables previously declared above
                Undergrad studentInfo = new Undergrad(studentName, grade1, grade2, grade3);

                //Call to the setLetterGrade() method of the Undergrad class to calculate the final grade of the students
                studentInfo.setLetterGrade();

                //Write information of the student after calling to the getName() method to return the students name and calling to the getLetterGrade() method to return the students grade calculated in setLetterGrade()
                myWriter.write(studentInfo.getName() + " " + studentInfo.getLetterGrade() + "\n");
            }

            //Loop for the Graduate Students, the second half of the students array, used to extract information and write out to a new file
            for (int i = 5; i < 10; ++i) {
                //Temporary array used to store the student information individually, without the white space
                String[] information = students[i].split(" ");

                //Variables used to call to the information stored in the temporary array
                String studentName = information[0];
                char grade1 = information[1].charAt(0);
                char grade2 = information[2].charAt(0);
                char grade3 = information[3].charAt(0);

                //Object for Undergrad class that passes in the variables previously declared above
                Graduate studentInfo = new Graduate(studentName, grade1, grade2, grade3);

                //Call to the setLetterGrade() method of the Graduate class to calculate the final grade of the students
                studentInfo.setLetterGrade();

                //Write information of the student after calling to the getName() method to return the students name and calling to the getLetterGrade() method to return the students grade calculated in setLetterGrade()
                myWriter.write(studentInfo.getName() + " " + studentInfo.getLetterGrade() + "\n");
            }

            //Close myWriter
            myWriter.close();

        }
        //Catches any errors
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
 
 