package test.nz.ac.wgtn.swen301.assignment1;

import nz.ac.wgtn.swen301.assignment1.StudentManager;
import nz.ac.wgtn.swen301.studentdb.Degree;
import nz.ac.wgtn.swen301.studentdb.Student;
import nz.ac.wgtn.swen301.studentdb.StudentDB;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;


/**
 * @author danma
 *
 */
public class TestStudentManager {

    // DO NOT REMOVE THE FOLLOWING -- THIS WILL ENSURE THAT THE DATABASE IS AVAILABLE
    // AND IN ITS INITIAL STATE BEFORE EACH TEST RUNS
    @Before
    public  void init () {
        StudentDB.init();
    }
    // DO NOT REMOVE BLOCK ENDS HERE
    
    /**
     * tests if we can read the student james smith from the db.
     * @throws Exception
     */
    @Test
    public void test_readStudent() throws Exception{
    	Student student = new StudentManager().readStudent("id0");
    	assertEquals(student.getName(), "James");
    }
    
    /**
     * tests if we can read a degree from the degree db.
     * @throws Exception
     */
    @Test
    public void test_readDegree() throws Exception{
    	Degree degree = new StudentManager().readDegree("deg0");
    	assertEquals(degree.getName(), "BSc Computer Science");
    }
    
    /**
     * tests if we can get all of the id's from the student table
     */
    @Test
    public void test_getAllStudentIds(){  
    	StudentManager sm = new StudentManager();
    	ArrayList<String> list = (ArrayList<String>) sm.getAllStudentIds();
    	assertEquals(list.get(0), "id0");
    }
    /**
     * tests if we can get all of the id's from the degree table
     */
    @Test
    public void test_getAllDegreeIds(){  
    	StudentManager sm = new StudentManager();
    	ArrayList<String> list = (ArrayList<String>) sm.getAllDegreeIds();
    	assertEquals(list.get(5), "deg5");
    }
    
    
    
}
