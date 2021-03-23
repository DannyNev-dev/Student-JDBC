package test.nz.ac.wgtn.swen301.assignment1;

import nz.ac.wgtn.swen301.assignment1.StudentManager;
import nz.ac.wgtn.swen301.studentdb.Degree;
import nz.ac.wgtn.swen301.studentdb.NoSuchRecordException;
import nz.ac.wgtn.swen301.studentdb.Student;
import nz.ac.wgtn.swen301.studentdb.StudentDB;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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
    	assertEquals(StudentManager.readStudent("id0").getName(), "James");
    }
    
    /**
     * tests if we can read a degree from the degree db.
     * @throws Exception
     */
    @Test
    public void test_readDegree() throws Exception{
    	Degree degree = StudentManager.readDegree("deg0");
    	assertEquals(degree.getName(), "BSc Computer Science");
    }
    
    /**
     * tests if we can get all of the id's from the student table
     */
    @Test
    public void test_getAllStudentIds(){      
    	ArrayList<String> list = (ArrayList<String>) StudentManager.getAllStudentIds();
    	assertEquals(list.get(0), "id0");
    }
    /**
     * tests if we can get all of the id's from the degree table
     */
    @Test
    public void test_getAllDegreeIds(){  
    	
    	ArrayList<String> list = (ArrayList<String>) StudentManager.getAllDegreeIds();
    	assertEquals(list.get(5), "deg5");
    }
    /**
     * tests if we can create a student
     * @throws NoSuchRecordException 
     */
    @Test
    public void test_createStudent() throws NoSuchRecordException{  
    	StudentManager sm = new StudentManager();
    	Student s = sm.createStudent("mcneil", "pauly", sm.readDegree("deg1"));
    	assertNotNull(s);
    }
    
    /**
     * tests if we can delete a student (We expect to receive no such record exception)
     * @throws NoSuchRecordException 
     */
    @Test
    public void test_delete() throws NoSuchRecordException{  
    	Student s = StudentManager.readStudent("id5");
    	StudentManager.delete(s);
    	try {
    		StudentManager.readStudent("id5");
    	}catch(NoSuchRecordException e) {
    		assert(true);
    		return;
    	}    
    	assert(false);
    }
    /**
     * tests the performance of the read student method, reading a total of 1000 random students
     * @throws NoSuchRecordException
     */
    @Test
    public void testPerformance() throws NoSuchRecordException{  
    	final long start = System.currentTimeMillis();       
    	for (int i = 0; i < 1000; i++) {                
    		StudentManager.readStudent("id" +  (int)(Math.random()*1000));            
        }
        final long end = System.currentTimeMillis();
        final long speed = end-start;
        System.out.print("Performance speed: " + speed + "ms");
        assertTrue(speed <= 1000);
    }
    
    
    
}
