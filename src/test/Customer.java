package test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import extendedhashmap.IndexableFields;

public class Customer implements IndexableFields {

	public String name;
	public String address;
	
	public Customer(){
		this.name = "Sumeet";
		this.address = "Sunnyvale";
	}
	
	@Override
	public List<Field> getIndexableFields() {
		Class<?> c = this.getClass();
		List<Field> fields = Arrays.asList(c.getFields());
		
		return fields;
	}

}
