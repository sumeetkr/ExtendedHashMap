package test;

import static org.junit.Assert.*;
import org.junit.*;
import extendedhashmap.*;

public class ExtendedHashMapTest {
	// Problem Statement
	// As a PM/Developer, I want to build an in-memory system so that
	// I can retrieve entities (like a customer object) based on their
	// keys in a constant time operation. I addition, I would like to
	// query those entities based on some of their other attributes in
	// constant time operation or logarithmic time.

	private ExtendedHashMap<String, IndexableFields> hashMap = null;

	@Before
	public void setup() {
		this.hashMap = new ExtendedHashMap<String, IndexableFields>();
		
		String key = "key";
		ExtendedValue value = new ExtendedValue();

		this.hashMap.put(key, value);

	}

	@Test
	public void testExtendedHashMap() {
		ExtendedHashMap<String, IndexableFields> hashMap = new ExtendedHashMap<String, IndexableFields>();

		assertNotNull("construction failed", hashMap);
	}

	@Test
	public void testPutKV() {
		String key = "firstKey";
		ExtendedValue value = new ExtendedValue();

		this.hashMap.put(key, value);
		assertNotNull("value was not added", this.hashMap.get(key));
	}

	@Test
	public void testGetObject() {
		assertNotNull("value was not added", this.hashMap.get("key"));
	}

	@Test
	public void testSearchIndexedFields() {
		
		assertNotNull("value was not searched", this.hashMap.searchIndexablaFields("firstIndexableField","firstValue"));
	}

}
