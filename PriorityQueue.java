import java.util.NoSuchElementException;
import java.util.Iterator;
/**
 * This is Priority Queue.
 * @author laraibpervez
 * @param <T> is type Parameter.
 */
public class PriorityQueue<T extends Comparable<T>> implements Iterable<T> {

	// -------------------------------------------------------------
	// DO NOT EDIT ANYTHING FOR THIS SECTION EXCEPT TO ADD JAVADOCS
	// -------------------------------------------------------------
	/**
	 * attribute.
	 */
	private Node<T> head = null;
	/**
	 * attribute.
	 */
	private int size;


	/**
	 * provided linked list node class.
	 * @author laraibpervez
	 *
	 * @param <T> is type parameter.
	 */
	private static class Node<T> {
		/**
		 * attribute.
		 */
		private T value;
		/**
		 * attribute.
		 */
		private Node<T> next;
		/**
		 * Constructor.
		 * @param value value
		 */
		public Node(T value) {
			this.value = value;
		}
	}

	/**
	 * provided toString() method using the iterator.
	 * @return String
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder("");
		for (T value : this) {
			builder.append(value);
			builder.append(" ");
		}
		return builder.toString().trim();
	}

	/**
	 *  provided iterator, if your code is working, this should work too.
	 * @return T
	 */
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Node<T> current = head;

			public T next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				T val = current.value;
				current = current.next;
				return val;
			}

			public boolean hasNext() {
				return (current != null);
			}
		};
	}

	// -------------------------------------------------------------
	// END OF PROVIDED "DO NOT EDIT" SECTION
	// -------------------------------------------------------------

	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!
	/**
	 * Priority Queue.
	 */
	public PriorityQueue() {
		// Constructor
		// initializing members if needed
		head = null;
		size = 0;

	}
	/**
	 * Size.
	 * @return Integer
	 */
	public int size() {
		// Return the number of elements in the priority queue
		// O(1)
		return size; // default return: change or remove as needed
	}
	/**
	 * Add.
	 * @param value value
	 */
	public void add(T value) {
		// Add a value into the priority queue. Use the value
		// as its priority.

		// The priority queue must be organized as a sorted singly
		// linked list. No dummy nodes.

		// Hint: you will need to decide a way to store/sort the values
		// so that the remove/element methods can also meet the required
		// behavior and big-O in time. Do check the requirements of
		// remove()/element() below before you code this method.

		// O(n) where n is the number of items in queue.

		Node<T> newNode = new Node<T>(value);
		// if first value in priority queue
		if (head == null || value.compareTo(head.value) < 0) {
			newNode.next = head;
			head = newNode;
			size++;
			return;
		} else {
			Node<T> current = head;
			Node<T> temp = head.next;
			while (temp != null && value.compareTo(temp.value) > 0) {
				current = temp;
				temp = current.next;
			}

			newNode.next = current.next;
			current.next = newNode;
			size++;
		}
	}
	/**
	 * Remove.
	 * @return T 
	 */
	public T remove() {
		// Remove and return the value with the minimal priority value.
		// If two or more items are of the same priority, keep their order
		// as FIFO, i.e. the one that was added earlier should be removed first.
		// Check main() below for examples.

		// Throw NoSuchElementException if queue is empty.
		// Use this _exact_ error message for the exception
		// (quotes are not part of the message):
		// "Priority queue empty!"

		// O(1)
		if (head == null) {
			throw new NoSuchElementException("Priority queue empty!");
		} else {
			T data = head.value;
			head = head.next;
			size--;
			return data;
		}

	}
	/**
	 * Element.
	 * @return T
	 */
	public T element() {
		// Return (but do not remove) the value with the minimal priority value.
		// If two or more items are of the same priority, keep the order
		// as FIFO, i.e. the one that was added earlier should be reported.
		// Check main() below for examples.

		// Throw NoSuchElementException if queue is empty.
		// Use this _exact_ error message for the exception
		// (quotes are not part of the message):
		// "Priority queue empty!"

		// O(1)
		if (head == null) {
			throw new NoSuchElementException("Priority queue empty!");
		} else {
			return head.value;
		}

	}
	/**
	 * Contains.
	 * @param value value
	 * @return boolean
	 */
	public boolean contains(T value) {
		// Return true if value is present in queue;
		// return false otherwise.

		// Hint: remember to use .equals() for comparison.

		// O(n) where n is the number of items in queue.
		Node<T> current = head;
		while (current != null) {
			int compareValue = current.value.compareTo(value);
			if (compareValue == 0) {
				return true;
			}
			else if (compareValue > 0) {
				break;
			} else {
				current = current.next;
			}
		}

		return false; // default return: change or remove as needed

	}

	// -------------------------------------------------------------
	// Main Method For Your Testing -- Edit all you want
	// -------------------------------------------------------------
	/**
	 * Main Method.
	 * @param args is type parameter.
	 */
	public static void main(String[] args) {
		PriorityQueue<Character> letters = new PriorityQueue<>();

		// add/size/element/contains
		String chars = "MASON";
		for (int i = 0; i < 5; i++) {
			letters.add(chars.charAt(i));
		}

		if (letters.size() == 5 && letters.element() == 'A' && letters.contains('O') && !letters.contains('B')) {
			System.out.println("Yay 1");
		}

		// remove
		if (letters.remove() == 'A' && letters.size() == 4 && letters.element() == 'M') {
			System.out.println("Yay 2");
		}

		// sequence of add/remove
		PriorityQueue<Integer> nums = new PriorityQueue<>();
		for (int i = 0; i < 10; i++) {
			int val = (i * i) % 17;
			nums.add(val);
		}
		boolean ok = nums.toString().trim().equals("0 1 2 4 8 9 13 13 15 16");
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			int val = nums.remove();
			output.append(val);
			output.append(" ");
		}
		if (ok && output.toString().trim().equals("0 1 2 4 8 9 13 13 15 16")) {
			System.out.println("Yay 3");
		}

		// values added with the same priority are kept in FIFO order
		PriorityQueue<String> msgs = new PriorityQueue<>();
		String msg1 = new String("Hello");
		String msg2 = new String("Hello");
		msgs.add(msg1);
		msgs.add(chars);
		msgs.add(msg2);
		if (msgs.toString().trim().equals("Hello Hello MASON") && msgs.contains(msg1) && msgs.contains(msg2)
				&& msgs.element() == msg1 && msgs.remove() != msg2) { // use of "==" is intentional here
			System.out.println("Yay 4");
		}

	}

}