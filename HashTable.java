import java.util.NoSuchElementException;
import java.util.Iterator;
import java.io.Serializable;

/**
 * HashTable.
 * @param <K> is type parameter.
 * @param <V> is type parameter
 */
public class HashTable<K, V> implements Serializable {

	// -------------------------------------------------------------
	// DO NOT EDIT ANYTHING FOR THIS SECTION EXCEPT TO ADD JAVADOCS
	// -------------------------------------------------------------
	/**
	 * Table Entry.
	 * @author laraibpervez
	 *
	 * @param <K> is type parameter.
	 * @param <V> is type parameter.
	 */
	private class TableEntry<K, V> implements Serializable {
		/**
		 * attribute.
		 */
		private K key;
		/**
		 * attribute.
		 */
		private V value;

		/**
		 * Constructor.
		 * 
		 * @param key   key
		 * @param value value
		 */
		public TableEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		/**
		 * get Key.
		 * 
		 * @return K
		 */
		public K getKey() {
			return key;
		}

		/**
		 * Get Value.
		 * 
		 * @return V
		 */
		public V getValue() {
			return value;
		}

		/**
		 * String.
		 * @return String
		 */
		public String toString() {
			return key.toString() + ":" + value.toString();
		}
	}

	// -------------------------------------------------------------
	// END OF PROVIDED "DO NOT EDIT" SECTION
	// -------------------------------------------------------------

	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!
	/**
	 * attribute.
	 */
	private TableEntry<K, V>[] storage;
	/**
	 * attribute.
	 */
	private int size;
	/**
	 * attribute.
	 */
	private int linearProbing;

	/**
	 * Constructor.
	 * 
	 * @param size size
	 */
	@SuppressWarnings("unchecked")
	public HashTable(int size) {
		// Create a hash table where the initial storage
		// is size and K keys can be mapped to V values.
		// You may assume size is >= 2
		storage = new TableEntry[size];
		linearProbing = 1;

	}

	/**
	 * HashCode.
	 * 
	 * @param key key.
	 * @return key
	 */
	private int getHash(K key) {
		return key.hashCode() % getCapacity();
	}

	/**
	 * Capacity.
	 * 
	 * @return Integer
	 */
	public int getCapacity() {
		// return how big the storage is
		// O(1)

		return storage.length; // default return: change or remove as needed
	}

	/**
	 * Size.
	 * 
	 * @return Integer
	 */
	public int size() {
		// return the number of elements in the table
		// O(1)

		return size; // default return: change or remove as needed
	}

	/**
	 * Put.
	 * 
	 * @param key key
	 * @param val val
	 * @return boolean
	 */
	public boolean put(K key, V val) {

		// Place value val at the location of key.
		// Use linear probing if that location is in use.

		// Return false w/o updating the table if
		// either key or val is null; otherwise return true.

		// Hint: Make a TableEntry to store in storage
		// and use the absolute value of key.hashCode() for
		// the probe start. You may also need to ensure
		// a valid index that is within bound.

		// If the key already exists in the table
		// replace the current value with val.

		// If key isn't in the table, add in the key,val.
		// If the table is >= 80% full, rehash to ensure the
		// table is expanded to twice the size.

		// Worst case: O(n), Average case: O(1)
		if(key == null || val == null) {
			return false;
		}

		int loc = this.getHash(key);

		while(true) {
			if (storage[loc] == null || (this.isTombstone(loc))) {
				storage[loc]=new TableEntry<>(key,val);
				size++;
				if(size >= getCapacity() * 0.8) {
					rehash(2 * getCapacity());
				}
				break;
			}
			if (storage[loc].getKey().equals(key)) {
				storage[loc].value = val;
				break;
			}
			loc = (loc + 1) % getCapacity();
		}

		return true;
	}

	/**
	 * Get.
	 * 
	 * @param key key
	 * @return V
	 */
	public V get(K key) {
		// Given a key, return the value from the table.

		// If the key is not in the table, return null.

		// Worst case: O(n), Average case: O(1)
		int hashCode = getHash(key);
		int location = hashCode % storage.length;

		if (storage[location] == null) {
			return null;
		}

		while (storage[location] != null) {

			if (storage[location].getKey().equals(key)) {
				return storage[location].getValue();
			}

			location = (location + 1) % getCapacity();

		}

		return null; // default return: change or remove as needed

	}

	/**
	 * Tombstone.
	 * 
	 * @param loc loc
	 * @return boolean
	 */
	public boolean isTombstone(int loc) {
		// this is a helper method needed for printing

		// return whether or not there is a tombstone at the
		// given index

		// O(1)
		if (loc >= storage.length || storage[loc] == null) {
			return false;
		}

		if (storage[loc].getValue() == null) {
			return true; // default return: change or remove as needed
		}

		return false;
	}

	/**
	 * Rehash.
	 * 
	 * @param size size
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	public boolean rehash(int size) {
		// Increase or decrease the size of the storage,
		// rehashing all values.

		// If the new size won't fit all the elements,
		// return false and do not rehash. Return true
		// if you were able to rehash.
		int currentSize = this.size;
		int currentCapacity = this.getCapacity();
		if (size < currentSize) {

			return false;
		}

		HashTable<K, V> temp = new HashTable<K, V>(size);

		for (int i = 0; i < currentCapacity; i++) {

			if (storage[i] != null && isTombstone(i) == false) {

				temp.put(this.storage[i].getKey(), this.storage[i].getValue());
			}
		}

		this.storage = temp.storage;
		linearProbing = temp.linearProbing;
		currentCapacity = temp.getCapacity();
		return true;
	}

	/**
	 * Remove.
	 * 
	 * @param key key
	 * @return V
	 */
	public V remove(K key) {
		// Remove the given key (and associated value)
		// from the table. Return the value removed.
		// If the key is not in the table, return null.

		// Hint 1: Remember to leave a tombstone!
		// Hint 2: Does it matter what a tombstone is?
		// Yes and no... You need to be able to tell
		// the difference between an empty spot and
		// a tombstone and you also need to be able
		// to tell the difference between a "real"
		// element and a tombstone.

		// Worst case: O(n), Average case: O(1)
		int location = getHash(key);
		if (location < 0) {
			location += storage.length;
		}
		int index = location;

		do {

			if (storage[index] == null) {
				return null;
			}
			if (storage[index].getKey().equals(key)) {
				V remove = storage[index].getValue();
				storage[index].value = null;
				size--;
				return remove;
			}

			index = (index + 1) % getCapacity();

		} while (index != location);

		return null; // default return: change or remove as needed

	}

	// -------------------------------------------------------------
	// PROVIDED METHODS BELOW
	// DO NOT EDIT ANYTHING FOR THIS SECTION EXCEPT TO ADD JAVADOCS
	// -------------------------------------------------------------
	/**
	 * ToString.
	 * @return String
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < storage.length; i++) {
			if (storage[i] != null && !isTombstone(i)) {
				s.append(storage[i] + "\n");
			}
		}
		return s.toString().trim();
	}

	/**
	 * To String Debug.
	 * @return String
	 */
	public String toStringDebug() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < storage.length; i++) {
			if (!isTombstone(i)) {
				s.append("[" + i + "]: " + storage[i] + "\n");
			} else {
				s.append("[" + i + "]: tombstone\n");
			}

		}
		return s.toString().trim();
	}

	// -------------------------------------------------------------
	// END OF PROVIDED METHODS SECTION
	// -------------------------------------------------------------

	// -------------------------------------------------------------
	// TESTING CODE
	// -------------------------------------------------------------
	/**
	 * Main Method.
	 * @param args args
	 */
	public static void main(String[] args) {
		// main method for testing, edit as much as you want
		HashTable<String, Integer> ht1 = new HashTable<>(10);
		HashTable<Integer, Character> ht2 = new HashTable<>(5);

		// initialize
		if (ht1.getCapacity() == 10 && ht2.getCapacity() == 5 && ht1.size() == 0 && ht2.size() == 0) {
			System.out.println("Yay 1");
		}

		// put
		ht1.put("a", 1); // "a".hashCode = 97
		ht1.put("b", 1); // "b".hashCode = 98
		ht1.put("b", 2); // update
		ht1.put("b", 3);

		// System.out.println(ht1);
		// System.out.println(ht1.toStringDebug());

		if (ht1.toString().equals("a:1\nb:3") && ht1.toStringDebug().equals(
				"[0]: null\n[1]: null\n[2]: null\n[3]: null\n[4]: null\n[5]: null\n[6]: null\n[7]: a:1\n[8]: b:3\n[9]: null")) {
			System.out.println("Yay 2");
		}

		if (!ht1.put(null, 0) && ht1.getCapacity() == 10 && ht1.size() == 2 && ht1.get("a") == 1 && ht1.get("b") == 3) {
			System.out.println("Yay 3");
		}

		// put with collision
		ht2.put(12, 'A');
		ht2.put(22, 'B');
		ht2.put(37, 'C');
		// System.out.println(ht2.toStringDebug());
		ht2.put(47, 'D');

		if (ht2.getCapacity() == 10 && ht2.size() == 4 && ht2.get(1) == null && ht2.get(12) == 'A' && ht2.get(22) == 'B'
				&& ht2.get(37) == 'C' && ht2.get(47) == 'D') {
			System.out.println("Yay 4");
		}

		if (ht2.toString().equals("12:A\n22:B\n47:D\n37:C") && ht2.toStringDebug().equals(
				"[0]: null\n[1]: null\n[2]: 12:A\n[3]: 22:B\n[4]: null\n[5]: null\n[6]: null\n[7]: 47:D\n[8]: 37:C\n[9]: null")) {
			System.out.println("Yay 5");
		}

		// remove
		HashTable<String, Integer> ht3 = new HashTable<>(2);
		ht3.put("apple", 1); // hashCode: 93029210

		if (ht3.remove("apple") == 1 && ht3.remove("banana") == null && ht3.toString().equals("")
				&& ht3.toStringDebug().equals("[0]: tombstone\n[1]: null")) {
			ht3.put("B", 1);
			if (ht3.toString().equals("B:1") && ht3.toStringDebug().equals("[0]: B:1\n[1]: null")) {
				System.out.println("Yay 6");
			}
		}

		// rehash
		if (!ht2.rehash(2) && ht2.size() == 4 && ht2.getCapacity() == 10) {
			System.out.println("Yay 7");
		}

		if (ht2.rehash(7) && ht2.size() == 4 && ht2.getCapacity() == 7) {
			System.out.println("Yay 8");
		}
		// System.out.println(ht2);
		// System.out.println(ht2.toStringDebug());

		if (ht2.toString().equals("22:B\n37:C\n12:A\n47:D") && ht2.toStringDebug()
				.equals("[0]: null\n[1]: 22:B\n[2]: 37:C\n[3]: null\n[4]: null\n[5]: 12:A\n[6]: 47:D")) {
			System.out.println("Yay 9");
		}

	}

}