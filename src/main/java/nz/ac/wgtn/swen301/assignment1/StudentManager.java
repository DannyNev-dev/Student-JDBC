package nz.ac.wgtn.swen301.assignment1;

import nz.ac.wgtn.swen301.studentdb.*;

import java.awt.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;


/**
 * A student managers providing basic CRUD operations for instances of Student, and a read operation for instances of Degree.
 * @author jens dietrich
 */
public class StudentManager {
	
	static Connection conn;
    // DO NOT REMOVE THE FOLLOWING -- THIS WILL ENSURE THAT THE DATABASE IS AVAILABLE
    // AND THE APPLICATION CAN CONNECT TO IT WITH JDBC
    static {
        StudentDB.init();
        
        //Might not be ok but makes sense to only connect once at the start of the data base creation       
        try {
			conn = DriverManager.getConnection("jdbc:derby:memory:studentdb");
		} catch (SQLException e) {
			System.out.println("Failed to connect to the data base in the static method");
			e.printStackTrace();
		}
    }
    // DO NOT REMOVE BLOCK ENDS HERE

    // THE FOLLOWING METHODS MUST IMPLEMENTED :

    /**
     * Return a student instance with values from the row with the respective id in the database.
     * If an instance with this id already exists, return the existing instance and do not create a second one.
     * @param id
     * @return
     * @throws NoSuchRecordException if no record with such an id exists in the database
     * This functionality is to be tested in test.nz.ac.wgtn.swen301.assignment1.TestStudentManager::test_readStudent (followed by optional numbers if multiple tests are used)
     */
    public static Student readStudent(String id) throws NoSuchRecordException {
    	String firstName = "null";
    	String name = "null";
    	Degree degree = null;   	
    	try {
    		String query = "SELECT FIRST_NAME,NAME,DEGREE FROM students WHERE ID=\'" + id + "\'"; //3 columns
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet results = stmt.executeQuery();   
            //get the details from the result set
            while (results.next()) {
                firstName = results.getString(1);
                name = results.getString(2);
                degree = readDegree(results.getString(3));
            }
    	}catch(SQLException s) {
    		throw new NoSuchRecordException();
    	}
    	//create the new Student instance 
    	return new Student(id,firstName,name,degree);
    }

    /**
     * Return a degree instance with values from the row with the respective id in the database.
     * If an instance with this id already exists, return the existing instance and do not create a second one.
     * @param id
     * @return
     * @throws NoSuchRecordException if no record with such an id exists in the database
     * This functionality is to be tested in test.nz.ac.wgtn.swen301.assignment1.TestStudentManager::test_readDegree (followed by optional numbers if multiple tests are used)
     */
    public static Degree readDegree(String id) throws NoSuchRecordException {
    	String name = "DefaultName";
    	try {
    		String query = "SELECT NAME FROM degrees WHERE ID=\'" + id + "\'"; //3 columns
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet results = stmt.executeQuery();            
            while (results.next()) {      //iterate through the data base (row by row)               
                 name = results.getString(1);     //get the degree name from results                              
            }
    	}catch(SQLException s) {
    		throw new NoSuchRecordException();
    	}
        return new Degree(id,name);
    }

    /**
     * Delete a student instance from the database.
     * I.e., after this, trying to read a student with this id will result in a NoSuchRecordException.
     * @param student
     * @throws NoSuchRecordException if no record corresponding to this student instance exists in the database
     * This functionality is to be tested in test.nz.ac.wgtn.swen301.assignment1.TestStudentManager::test_delete
     */
    public static void delete(Student student) throws NoSuchRecordException {}

    /**
     * Update (synchronize) a student instance with the database.
     * The id will not be changed, but the values for first names or degree in the database might be changed by this operation.
     * After executing this command, the attribute values of the object and the respective database value are consistent.
     * Note that names and first names can only be max 1o characters long.
     * There is no special handling required to enforce this, just ensure that tests only use values with < 10 characters.
     * @param student
     * @throws NoSuchRecordException if no record corresponding to this student instance exists in the database
     * This functionality is to be tested in test.nz.ac.wgtn.swen301.assignment1.TestStudentManager::test_update (followed by optional numbers if multiple tests are used)
     */
    public static void update(Student student) throws NoSuchRecordException {}


    /**
     * Create a new student with the values provided, and save it to the database.
     * The student must have a new id that is not been used by any other Student instance or STUDENTS record (row).
     * Note that names and first names can only be max 1o characters long.
     * There is no special handling required to enforce this, just ensure that tests only use values with < 10 characters.
     * @param name
     * @param firstName
     * @param degree
     * @return a freshly created student instance
     * This functionality is to be tested in test.nz.ac.wgtn.swen301.assignment1.TestStudentManager::test_createStudent (followed by optional numbers if multiple tests are used)
     */
    public static Student createStudent(String name,String firstName,Degree degree) {
        String id = Integer.toString(getAllStudentIds().size() + 1);
        final String query = "INSERT INTO students "
        		+ "VALUES (?, ?, ?, ?)";
        try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ps.setString(2, firstName);
			ps.setString(3, name);
			ps.setString(4, degree.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("ERROR WHEN TRYING TO INSERT THE NEW STUDENT INTO THE TABLE");
			e.printStackTrace();
		}
    	return new Student(id,firstName,name,degree);
    }

    /**
     * Get all student ids currently being used in the database.
     * @return
     * This functionality is to be tested in test.nz.ac.wgtn.swen301.assignment1.TestStudentManager::test_getAllStudentIds (followed by optional numbers if multiple tests are used)
     * @throws SQLException 
     */
    public static Collection<String> getAllStudentIds() {    	
    	ArrayList<String> c = new ArrayList<String>(); 
    	try {
	    	Statement stmt = conn.createStatement();
	        //query the db for all the results
	        ResultSet results = stmt.executeQuery("SELECT id FROM students");           
	        while (results.next()) {      //iterate through the data base (row by row)          	
	             c.add(results.getString(1));     //get the id and add it to the list             
	        }       
    		} catch (SQLException e) {
    		System.out.println("The database is empty or not connected!");
    		e.printStackTrace();
    	}	
        return c;      
    }

    /**
     * Get all degree ids currently being used in the database.
     * @return
     * This functionality is to be tested in test.nz.ac.wgtn.swen301.assignment1.TestStudentManager::test_getAllDegreeIds (followed by optional numbers if multiple tests are used)
     * @throws SQLException 
     */
    public static Iterable<String> getAllDegreeIds() {    
    	ArrayList<String> list = new ArrayList<String>();
    	Statement stmt;
		try {
			stmt = conn.createStatement();     
	        ResultSet results = stmt.executeQuery("SELECT id FROM degrees");           
	        while (results.next()) {      //iterate through the data base (row by row)          	
	             list.add(results.getString(1));     //get the id and add it to the list             
	        }    
		} catch (SQLException e) {
			System.out.println("The database is empty or not connected!");
			e.printStackTrace();
		}
        return list;
    }


}
