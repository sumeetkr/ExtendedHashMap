package extendedhashmap;

import java.util.List;

public interface Indexable {
	public List<Object> searchFields(String fieldName, Object fieldValue);
}
