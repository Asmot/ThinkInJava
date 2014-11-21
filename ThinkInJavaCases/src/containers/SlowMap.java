package containers;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

//HashMapҲ�Ǽ̳е������AbstractMap
public class SlowMap<K, V> extends AbstractMap<K, V> {

	private List<K> keys = new ArrayList<K>();
	private List<V> values = new ArrayList<V>();
	
	public V put(K key, V value) {
		V oldValue = get(key);
		if(keys.contains(key)) {
			values.set(keys.indexOf(key), value);
		} else {
			keys.add(key);
			values.add(value);
		}
		return oldValue;
	}
	
	//public V get(K key)//���ڸ��� Ҫ��AbstractMap��get����һ��
	public V get(Object key) {
		if(keys.contains(key)) {
			//�����±�ȥvalues��ȥ��Ӧ��value
			return values.get(keys.indexOf(key));
		}
		return null;
	}
	
	//�̳��˾ͱ���Ҫʵ�ֵķ���
	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		Set<java.util.Map.Entry<K, V>> set = new HashSet<java.util.Map.Entry<K, V>>();
		Iterator<K> ki = keys.iterator();
		Iterator<V> vi = values.iterator();
		
		while(ki.hasNext()) {
			//������ҪMap.Entry<K,V> ������������Ǹ��ӿ�
			//������Ҫ�Լ�ʵ��һ��
			set.add(new MapEntry<K, V>(ki.next(), vi.next()));
		}
		return set;
	}
	
	public static void main(String[] args) {
		SlowMap<String, String> map = new SlowMap<String, String>();
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
