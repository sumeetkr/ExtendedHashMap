package test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import extendedhashmap.IIndexableFields;

public class ExtendedValueTest {

	private IIndexableFields extendedValue;

	@Before
	public void setUp() throws Exception {
		class Customer extends ExtendedValue{

			private String name;
			private int age;

			public Customer(){
				this.name = "test name";
				this.age = 10;
			}
			
		}

		this.extendedValue = new Customer();
	}

	@Test
	public void testGetIndexableFieldsShoulNotBeNull() {
	 assertNotNull("should not be null", this.extendedValue.getIndexableFields());
	}
	
	
	@Test
	public void testGetIndexableAttributes() {
		List<Field> fields = this.extendedValue.getIndexableFields();
		assertTrue(fields.size() == 2);
	}
}
