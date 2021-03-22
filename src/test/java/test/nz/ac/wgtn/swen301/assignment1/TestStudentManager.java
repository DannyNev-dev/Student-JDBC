package test.nz.ac.wgtn.swen301.assignment1;

import nz.ac.wgtn.swen301.assignment1.StudentManager;
import nz.ac.wgtn.swen301.studentdb.Degree;
import nz.ac.wgtn.swen301.studentdb.Student;
import nz.ac.wgtn.swen301.studentdb.StudentDB;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


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
    
    
}
