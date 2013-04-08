package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.*;
import extendedhashmap.*;

public class IndexableHashMapTest {
	// Problem Statement
	// As a PM/Developer, I want to build an in-memory system so that
	// I can retrieve entities (like a customer object) based on their
	// keys in a constant time operation. I addition, I would like to
	// query those entities based on some of their other attributes in
	// constant time operation or logarithmic time.

	private IndexableHashMap<String, IndexableFields> hashMap = null;

	@Before
	public void setup() {
		this.hashMap = new IndexableHashMap<String, IndexableFields>();
	}

	@Test
	public void testExtendedHashMapConstruction() {
		IndexableHashMap<String, IndexableFields> hashMap = new IndexableHashMap<String, IndexableFields>();

		assertNotNull("construction failed", hashMap);
	}

	@Test
	public void testPutKV() {
		String key = "firstKey";
		Customer value = new Customer();

		this.hashMap.put(key, value);
		assertNotNull("value should be present after put", this.hashMap.get(key));
	}

	@Test
	public void testGetObject() {
		String key = "key";
		Customer value = new Customer();
		this.hashMap.put(key, value);
		assertNotNull("value was not present", this.hashMap.get("key"));
	}

	@Test
	public void testSearchFieldsWithOneValue() {

		Customer value = new Customer();
		value.name = "Sumeet";
		value.address = "Sunnyyvale";

		String key = "key";
		this.hashMap.put(key, value);
		
		assertTrue("value should be returned by search", this.hashMap.searchFieldsInIndex("name","Sumeet").size() == 1);
	}

	@Test
	public void testSearchFieldsWithTwoValuesWhenBothMatch() {
		
		String key = "key2";
		Customer firstCustomer = new Customer();
		firstCustomer.name = "Kumar";
		firstCustomer.address = "secondValue2";
		this.hashMap.put(key, firstCustomer);
		
		String anotherKey = "key3";
		Customer secondCustomer = new Customer();
		secondCustomer.name = "Kumar";
		secondCustomer.address = "secondValue3";
		this.hashMap.put(anotherKey, secondCustomer);
		
		
		List<IndexableFields> valuesForFirstIndexableField = this.hashMap.searchFieldsInIndex("name","Kumar");
		assertNotNull("value was not searched", valuesForFirstIndexableField);
		assertTrue("Not all fields indexed", valuesForFirstIndexableField.size() == 2);
	}
	
	@Test
	public void testSearchFieldsWithTwoValuesButOnlyOneMatch() {
		String key = "key2";
		Customer firstCustomer = new Customer();
		firstCustomer.name = "Sumeet";
		firstCustomer.address = "Sunnyvale";
		this.hashMap.put(key, firstCustomer);
		
		String anotherKey = "key3";
		Customer secondCustomer = new Customer();
		secondCustomer.name = "Sumeet";
		secondCustomer.address = "SantaClara";
		this.hashMap.put(anotherKey, secondCustomer);
	
		List<IndexableFields> valuesForSecondIndexableField = this.hashMap.searchFieldsInIndex("address","Sunnyvale");
		assertNotNull("value should be returned by serach of index fields", valuesForSecondIndexableField);
		assertTrue("Not all fields indexed", valuesForSecondIndexableField.size() == 1);
	}
	
	@Test
	public void testRemove(){
		String key = "key2";
		Customer firstCustomer = new Customer();
		firstCustomer.name = "Sumeet";
		firstCustomer.address = "Sunnyvale";
		this.hashMap.put(key, firstCustomer);
		
		assertNotNull("value should be present after put", this.hashMap.get(key));
		
		this.hashMap.remove(key);
		assertNull("value should not be present after remove", this.hashMap.get(key));
		
	}
	
	@Test
	public void testRemoveShouldCleanIndex(){
		Customer value = new Customer();
		value.name = "Sumeet";
		value.address = "Sunnyvale";

		String key = "key";
		this.hashMap.put(key, value);
		
		assertTrue("value should be returned by search", this.hashMap.searchFieldsInIndex("name","Sumeet").size() == 1);
		assertTrue("value should be returned by search", this.hashMap.searchFieldsInIndex("address","Sunnyvale").size()== 1);
		
		this.hashMap.remove(key);
		assertNull("value should not be present after remove", this.hashMap.get(key));
		
		List<IndexableFields> valuesForSecondIndexableField = this.hashMap.searchFieldsInIndex("address","Sunnyvale");
		assertTrue("Index should not be cleaned after remove", valuesForSecondIndexableField.size() == 0);
		
	}
	
}
