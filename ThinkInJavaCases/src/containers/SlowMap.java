package containers;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

//HashMap也是继承的这个类AbstractMap
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
	
	//public V get(K key)//属于覆盖 要和AbstractMap中get保持一致
	public V get(Object key) {
		if(keys.contains(key)) {
			//根据下标去values中去对应的value
			return values.get(keys.indexOf(key));
		}
		return null;
	}
	
	//继承了就必须要实现的方法
	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		Set<java.util.Map.Entry<K, V>> set = new HashSet<java.util.Map.Entry<K, V>>();
		Iterator<K> ki = keys.iterator();
		Iterator<V> vi = values.iterator();
		
		while(ki.hasNext()) {
			//这里需要Map.Entry<K,V> 但是这个东西是个接口
			//所以需要自己实现一个
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
		
		//在没有实现entrySet的时候会报错java.lang.NullPointerException
		//应该是toString里面调用了这个方法，然后再处理了一下格式
		System.out.println(map);
		
		//这个打印的格式是MapEntry里面的
		System.out.println(map.entrySet());
	}
}
