import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class FreqTable {
	static Map dict = new HashMap();

	public static void main(String[] args) {

/*		freqtable.create();
		freqtable.add("DB2");
		freqtable.add("Oracle");
		freqtable.add("MySQL");
		freqtable.add("Oracle");
		freqtable.add("MySQL");
		freqtable.add("MySQL");
		freqtable.add("Oracle");
		freqtable.add("MySQL");

		getmaxvalue();

		freqtable.print();
		System.out.println(" max " + freqtable.getmaxcount());
		System.out.println(" max " + freqtable.getmaxvalue());

*/	}

	public boolean isContains(Object obj) {
		return dict.containsKey(obj);
	}

	public void create() {
		dict = new HashMap();
	}

	public void add(String key) {

		if (!dict.containsKey(key))
			dict.put(key, "" + 1); // / first Discovery

		else {
			Integer value = Integer.parseInt("" + dict.get(key));
			dict.remove(key);
			dict.put(key, "" + (value + 1)); // Not First
		}
	}

	public int getmaxcount() {
		Object[] values = dict.values().toArray();
		Object[] key = dict.keySet().toArray();

		int max = 0, index = 0;
		String value = "";
		while (values.length > index) {
			if (max <= Integer.parseInt("" + values[index])) {
				max = Integer.parseInt("" + values[index]);
				value = (String) key[index];
			}
			index++;
		}
		return max;
	}

	public String getmaxvalue() {
		Object[] values = dict.values().toArray();
		Object[] key = dict.keySet().toArray();

		int max = 0, index = 0;
		String value = "";
		while (values.length > index) {
			if (max <= Integer.parseInt("" + values[index])) {
				max = Integer.parseInt("" + values[index]);
				value = (String) key[index];
			}
			index++;
		}

		return value;
	}

	public void put(String key, String value) {
		dict.put(key, value);
	}

	public void delete(Object key) {
		dict.remove(key);
	}

	public void print() {
		Iterator iter = dict.entrySet().iterator();

		while (iter.hasNext()) {
			Map.Entry mEntry = (Map.Entry) iter.next();
			System.out.println(mEntry.getKey() + " : " + mEntry.getValue());
		}
	}

}