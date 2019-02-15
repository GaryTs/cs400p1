import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

abstract class DataStructureADTTest<T extends DataStructureADT<String,String>> {
	
	private T dataStructureInstance;
	
	protected abstract T createInstance();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		dataStructureInstance = createInstance();
	}

	@AfterEach
	void tearDown() throws Exception {
		dataStructureInstance = null;
	}

	
	@Test
	void test00_empty_ds_size() {
		if (dataStructureInstance.size() != 0)
		fail("data structure should be empty, with size=0, but size="+dataStructureInstance.size());
	}
	
	
	// TODO: implement tests 01 - 04

	// test01_after_insert_one_size_is_one
	@Test
	void test01__after_insert_one_size_is_one() {
	    dataStructureInstance.insert("1", "HW");
	    if (dataStructureInstance.size() != 1){
	        fail("data structure should be 1, with size=1, but size="+dataStructureInstance.size());
	    }
	}
	
	// test02_after_insert_one_remove_one_size_is_0
	@Test
	void test02_after_insert_one_remove_one_size_is_0() {
	    dataStructureInstance.insert("1", "HW");
	    dataStructureInstance.remove("1");
        if (dataStructureInstance.size() != 0){
            fail("data structure size should be 0, with size=0, but size="+dataStructureInstance.size());
        }
	}
	
	// test03_duplicate_exception_is_thrown
	@Test
    void test03_duplicate_exception_is_thrown() {
        try {
            dataStructureInstance.insert("1", "HW");
            dataStructureInstance.insert("1", "Guten Tag");
            fail("data structure should throw a runtime exception but did not");
        }
	    catch(RuntimeException excpt) {
	    }     
    }
	
	// test04_remove_returns_false_when_key_not_present
	@Test
	void test04_remove_returns_false_when_key_not_present() {
	    dataStructureInstance.insert("1", "HW");
        if(dataStructureInstance.remove("2")) {
            fail("should return false, but returned true");
        }
	}

	@Test
	void test05_illegal_argument_exception_is_thrown() {
        try {
            dataStructureInstance.insert("1", "HW");
            dataStructureInstance.insert(null, "Guten Tag");
            fail("data structure should throw an illegal argument exception but did not");
        }
        catch(IllegalArgumentException excpt) {
        }     
    }
	
	@Test
	void test06_remove_null_illgeal_argument_exception_is_thrown() {
        try {
            dataStructureInstance.insert("1", "HW");
            dataStructureInstance.insert("2", "Guten Tag");
            dataStructureInstance.remove(null);
            fail("data structure should throw an illegal argument exception but did not");
        }
        catch(IllegalArgumentException excpt) {
        }     
    }
	
	@Test
	void test07_return_value_associated_with_the_key() {
        try {
            dataStructureInstance.insert("1", "HW");
            dataStructureInstance.insert("2", "Guten Tag");
            if(!dataStructureInstance.get("1").equals("HW"))
            fail("data structure should return HW, but returned" + dataStructureInstance.get("1"));
            if(dataStructureInstance.size() != 2)
            fail("data structure should have size of 2, but have" + dataStructureInstance.size());
            dataStructureInstance.get(null);
            fail("data structure should throw an illegal argument exception but did not");
        }
        catch(IllegalArgumentException excpt) {
        }     
    }
	
	@Test
    void test08_more_on_duplicates() {
        try {
            dataStructureInstance.insert("1", "HW");
            dataStructureInstance.insert("2", "Guten Tag");
            dataStructureInstance.insert("3", "Hola");
            dataStructureInstance.insert("4", "Ciao");
            dataStructureInstance.remove("2");
            dataStructureInstance.insert("2", "Guten Tag");
        }
        catch(IllegalArgumentException excpt) {
            fail("already removed should not be count as a duplicate");
        }     
    }
	// TODO: add tests to ensure that you can detect implementation that fail
	
	// Tip: consider different numbers of inserts and removes and how different combinations of insert and removes
	

}
