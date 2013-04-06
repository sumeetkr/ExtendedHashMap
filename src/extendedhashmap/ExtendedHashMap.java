package extendedhashmap;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class ExtendedHashMap<K, V extends IIndexableFields> extends HashMap<K, V> {

	private HashMap<String, TreeMap<Object, String>> indexedFieldsTrees;
	
	public ExtendedHashMap() {
		super();
		
		this.indexedFieldsTrees = new HashMap<String, TreeMap<Object, String>>();
	}

	@Override
	public V put(K key, V value) {
		indexFields(key, value);
		return super.put(key, value);
	}

	@Override
	public V get(Object key) {
		return super.get(key);
	}
	
	public V searchIndexablaFields(String fieldName, Object fieldValue){
		V value = null;
		if(this.indexedFieldsTrees.containsKey(fieldName)){
			TreeMap<Object, String> tree = this.indexedFieldsTrees.get(fieldName);	
			String valueKey = tree.get(fieldValue);
			
			if(valueKey != null){
			value = super.get(valueKey);	
			}
		}
		
		return value;
	}
	
	private void indexFields(K key, V value) {
		//check indexable fields in value object, for those fields get/create tree map, add fieldValue, key in tree map 
		List<Field> indexableAttributes = value.getIndexableFields();
		for(Field attribute: indexableAttributes){
			String attributeName = attribute.getName();
			TreeMap<Object, String> treeMap ;
			
			if(!this.indexedFieldsTrees.containsKey(attributeName)){
				treeMap = new TreeMap<Object, String>();
				this.indexedFieldsTrees.put(attributeName, treeMap );	
			}
			else {
				treeMap =  indexedFieldsTrees.get(attributeName);
			}
			
			try {
				treeMap.put(attribute.get(value), (String) key);
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
