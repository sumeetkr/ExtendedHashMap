package extendedhashmap;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class IndexableHashMap<K, V extends IndexableFields> extends
		HashMap<K, V> implements Indexable<V> {

	private HashMap<String, TreeMap<Object, List<String>>> indexedFieldsTrees;

	public IndexableHashMap() {
		super();

		this.indexedFieldsTrees = new HashMap<String, TreeMap<Object, List<String>>>();
	}

	@Override
	public V put(K key, V value) {
		createIndex(key, value);
		return super.put(key, value);
	}

	@Override
	public V get(Object key) {
		return super.get(key);
	}

	@Override
	public V remove(Object key) {
		cleanIndex(key);

		return super.remove(key);
	}

	@Override
	public List<V> searchFieldsInIndex(String fieldName, Object fieldValue) {
		List<V> values = new ArrayList<V>();

		if (this.indexedFieldsTrees.containsKey(fieldName)) {
			TreeMap<Object, List<String>> tree = this.indexedFieldsTrees
					.get(fieldName);
			List<String> valueKeys = tree.get(fieldValue);

			if (valueKeys != null && valueKeys.size() > 0) {
				for (String key : valueKeys) {
					values.add(super.get(key));
				}
			}
		}

		return values;
	}

	private void createIndex(K key, V value) {
		// check indexable fields in value object, for those fields get/create
		// tree map, add fieldValue, key in tree map
		List<Field> indexableAttributes = value.getIndexableFields();
		for (Field field : indexableAttributes) {
			String fieldName = field.getName();
			TreeMap<Object, List<String>> treeMap;

			if (!this.indexedFieldsTrees.containsKey(fieldName)) {
				treeMap = new TreeMap<Object, List<String>>();
				this.indexedFieldsTrees.put(fieldName, treeMap);
			} else {
				treeMap = indexedFieldsTrees.get(fieldName);
			}

			insertKeyInFieldsTreeMap(key, value, field, treeMap);
		}
	}

	private void insertKeyInFieldsTreeMap(K key, V value, Field field,
			TreeMap<Object, List<String>> treeMap) {
		try {

			Object fieldValue = field.get(value);
			if (treeMap.containsKey(fieldValue)) {
				List<String> keys = treeMap.get(fieldValue);
				keys.add((String) key);
			} else {
				List<String> keys = new ArrayList<String>();
				keys.add((String) key);
				treeMap.put(field.get(value), keys);
			}

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void cleanIndex(Object key) {
		// clean the index
		// check indexable fields in value object, for those fields get/create
		// tree map, remove key in tree map
		V value = super.get(key);
		if (value != null) {
			List<Field> indexableAttributes = value.getIndexableFields();
			for (Field field : indexableAttributes) {
				String attributeName = field.getName();
				TreeMap<Object, List<String>> treeMap= this.indexedFieldsTrees.get(attributeName);
				
				try {
					Object fieldValue = field.get(value);
					List<String> keys = treeMap.get(fieldValue);
					keys.remove(key);
					
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}
}
