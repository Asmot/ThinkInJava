package containers;

import java.util.Map;

public class MapEntry<K, V> implements Map.Entry<K, V> {

	private K key;
	private V value;
	
	public MapEntry(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public K getKey() {
		// TODO Auto-generated method stub
		return key;
	}

	@Override
	public V getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public V setValue(V arg0) {
		// TODO Auto-generated method stub
		value = arg0;
		return value;
	}
	
	public int hashCode() {
		return (key == null ? 0 : key.hashCode())^
				(value == null ? 0 : value.hashCode());
	}
	public boolean equals(Object o) {
		if(!(o instanceof MapEntry)) {
			return false;
		}
		MapEntry entry = (MapEntry) o;
		
		//想看懂这段需要点基本功啊...
		//保证key和value与entry的key vaule都相等才行
		return (key == null ?
				entry.getKey() == null : key.equals(entry.getKey())) &&
			   (value == null ?
			    entry.getValue() == null : value.equals(entry.getValue()));
//		if(key == entry.getKey() && value == entry.getValue())
//			return true;
//		if(key == null || value == null || entry.getKey() == null || entry.getValue() == null)
//			return false;
//		return false;
	}
	public String toString() {
		return key + " = " + value;
	}
}
