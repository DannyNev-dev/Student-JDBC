package nz.ac.wgtn.swen301.assignment1.cli;
import java.sql.SQLException;

import nz.ac.wgtn.swen301.assignment1.StudentManager;
import nz.ac.wgtn.swen301.studentdb.NoSuchRecordException;
import nz.ac.wgtn.swen301.studentdb.Student;



public class FindStudentDetails {

    // THE FOLLOWING METHOD MUST IMPLEMENTED
    /**
     * Executable: the user will provide a student id as single argument, and if the details are found,
     * the respective details are printed to the console.
     * E.g. a user could invoke this by running "java -cp <someclasspath> nz.ac.wgtn.swen301.assignment1.cli.FindStudentDetails id42"
     * @param arg
     * @throws SQLException 
     */
    public static void main (String[] arg){   
    	if(arg.length!=1) {throw new IllegalArgumentException();}
    	System.out.println("Retrieving student details!");
    	Student s;
    	try {
			s = StudentManager.readStudent(arg[0]);
			System.out.println(s.getFirstName() + ", " + s.getName() + ", " + s.getDegree().getName() + ".\n");
		} catch (NoSuchRecordException e) {
			System.out.println("Failed to retrieve the student details!");
			e.printStackTrace();
		}
    }
}
