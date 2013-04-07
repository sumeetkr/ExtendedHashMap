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
		
		String key = "key";
		ExtendedValue value = new ExtendedValue();
		this.hashMap.put(key, value);

	}

	@Test
	public void testExtendedHashMap() {
		IndexableHashMap<String, IndexableFields> hashMap = new IndexableHashMap<String, IndexableFields>();

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
	public void testSearchFields() {
		
		assertNotNull("value was not searched", this.hashMap.searchFields("firstIndexableField","firstValue"));
	}

	@Test
	public void testSearchFieldsWithTwoItems() {
		
		String key = "key2";
		ExtendedValue value = new ExtendedValue();
		value.firstIndexableField = "firstValue2";
		value.secondIndexableField = "secondValue2";
		this.hashMap.put(key, value);
		
		String anotherKey = "key3";
		ExtendedValue anotherValue = new ExtendedValue();
		anotherValue.firstIndexableField = "firstValue2";
		anotherValue.secondIndexableField = "secondValue3";
		this.hashMap.put(anotherKey, anotherValue);
		
		
		List<IndexableFields> valuesForFirstIndexableField = this.hashMap.searchFields("firstIndexableField","firstValue2");
		assertNotNull("value was not searched", valuesForFirstIndexableField);
		assertTrue("Not all fields indexed", valuesForFirstIndexableField.size() == 2);
		
		List<IndexableFields> valuesForSecondIndexableField = this.hashMap.searchFields("secondIndexableField","secondValue2");
		assertNotNull("value was not searched", valuesForSecondIndexableField);
		assertTrue("Not all fields indexed", valuesForSecondIndexableField.size() == 1);
	}
}
