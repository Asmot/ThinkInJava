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
		//�����±꣬��֤����������Ĵ�С
		int index = Math.abs(key.hashCode() % SIZE);
		
		if(buckets[index] == null) {
			//������ÿ��Ԫ�ض���list
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
	
	//public V get(K key)//���ڸ��� Ҫ��AbstractMap��get����һ��
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
		
		//��û��ʵ��entrySet��ʱ��ᱨ��java.lang.NullPointerException
		//Ӧ����toString������������������Ȼ���ٴ�����һ�¸�ʽ
		System.out.println(map);
		
		//�����ӡ�ĸ�ʽ��MapEntry�����
		System.out.println(map.entrySet());
	}

}
