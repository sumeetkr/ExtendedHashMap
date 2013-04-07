package test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import extendedhashmap.IndexableFields;

public class ExtendedValue implements IndexableFields {

	public String firstIndexableField;
	public String secondIndexableField;
	
	public ExtendedValue(){
		this.firstIndexableField = "firstValue";
		this.secondIndexableField = "secondvalue";
	}
	
	@Override
	public List<Field> getIndexableFields() {
		Class<?> c = this.getClass();
		List<Field> fields = Arrays.asList(c.getFields());
		
		return fields;
	}

}
