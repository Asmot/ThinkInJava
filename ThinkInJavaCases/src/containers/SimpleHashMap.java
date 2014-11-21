package containers;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;

public class SimpleHashMap<K, V> extends AbstractMap<K, V> {

	private final static int SIZE = 997;
	@SuppressWarnings("unchecked")
	LinkedList<MapEntry<K, V>>[] buckets = new LinkedList[SIZE];
	
	
	public V put(K key, V value) {
		V oldValue = get(key);
		//计算下标，保证不超过数组的大小
		int index = Math.abs(key.hashCode() % SIZE);
		
		if(buckets[index] == null) {
			//数组中每个元素都是list
			buckets[index] = new LinkedList<MapEntry<K, V>>();
		}
		
		LinkedList<MapEntry<K, V>> bucket = buckets[index];
		MapEntry<K, V> pair = new MapEntry<K, V>(key, value);
		boolean found = false;
		ListIterator<MapEntry<K, V>> iterator = bucket.listIterator();
		while (iterator.hasNext()) {
			MapEntry<K, V> type = iterator.next();
			if(type.getKey().equals(key)){ 
				oldValue = type.getValue();
				iterator.set(pair);
				found = true;
				break;
			}
		}
		if(!found) {
			buckets[index].add(pair);
		}
		return oldValue;
	}
	
	//public V get(K key)//属于覆盖 要和AbstractMap中get保持一致
	public V get(Object key) {
		int index = Math.abs(key.hashCode() % SIZE);
		if(buckets[index] == null) {
			return null;
		}
		for (MapEntry<K, V> pair : buckets[index]) {
			if(pair.getKey().equals(key)) {
				return pair.getValue();
			}
		}
		return null;
	}
	
	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		Set<java.util.Map.Entry<K, V>> set = new HashSet<java.util.Map.Entry<K, V>>();
		for(LinkedList<MapEntry<K, V>> bucket : buckets) {
			if(bucket == null) {
				continue;
			} else {
				for(MapEntry<K, V> pair : bucket) {
					set.add(pair);
				}
			}
		}
		return set;
	}
	
	public static void main(String[] args) {
		SimpleHashMap<String, String> map = new SimpleHashMap<String, String>();
		map.put("1", "test1");
		map.put("2", "test2");
		map.put("3", "test3");
		map.put("4", "test4");
		map.put("5", "test5");
		
		System.out.println(map.get("4"));
		
		//在没有实现entrySet的时候会报错java.lang.NullPointerException
		//应该是toString里面调用了这个方法，然后再处理了一下格式
		System.out.println(map);
		
		//这个打印的格式是MapEntry里面的
		System.out.println(map.entrySet());
	}

}
