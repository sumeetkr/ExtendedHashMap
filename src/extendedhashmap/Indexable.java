package extendedhashmap;

import java.util.List;

public interface Indexable<V extends IndexableFields> {
	public List<V> searchFieldsInIndex(String fieldName, Object fieldValue);
}
