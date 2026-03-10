package fieldworks.DSA.linkedList;

class NodeDLL {
	int data;
	NodeDLL next;
	NodeDLL back;

	NodeDLL(int data1, NodeDLL next1, NodeDLL back1) {
		this.data = data1;
		this.next = next1;
		this.back = back1;
	}

	NodeDLL(int data1) {
		this.data = data1;
		this.next = null;
		this.back = null;
	}
}

public class doublyLinkedList {
	public static void main(String args[]) {
		int[] arr = { 3, 4, 5, 1, 2 };
		NodeDLL head = convertArr2DLL(arr);
		// 1.Deleting
		// // 1.1 => head
		// head = removeHead(head);
		// // 1.2=> last
		// head = removeLast(head);
		// // 1.3=> K position
		// head = removeK(head, 3);
		// // 1.4=> node
		// removeEl(head.next.next);

		// 2. Inserting
		// // 2.1=> before the head
		// head = insertBeforeHead(head, 10);
		// 2.2=> before the tail
		// head = insertBeforeTail(head, 100);
		// 2.3=>before the K position
		// head = insertBeforeK(head, 3, 10);
		// 2.4=>before given node
		// insertEleBeforeNode(head.next.next, 50);

		// 3.Reverse a DLL
		// 3.1==> Broute force (using Stack)
		// 3.2==>
		head = reverseDLL(head);

		printDoublyLinkedList(head);

	}

	private static NodeDLL reverseDLL(NodeDLL head) {
		if (head == null || head.next == null) {
			return head;
		}
		NodeDLL prev = null;
		NodeDLL current = head;
		while (current != null) {
			prev = current.back;
			current.back = current.next;
			current.next = prev;

			current = current.back;
		}
		return prev.back;
	}

	private static void insertEleBeforeNode(NodeDLL temp, int val) {
		NodeDLL prev = temp.back;
		NodeDLL newNode = new NodeDLL(val, temp, prev);
		prev.next = newNode;
		temp.back = newNode;

	}

	private static NodeDLL insertBeforeK(NodeDLL head, int k, int val) {
		if (k == 1) {
			return insertBeforeHead(head, val);
		}
		int cnt = 0;
		NodeDLL temp = head;
		while (temp != null) {
			cnt++;
			if (cnt == k)
				break;
			temp = temp.next;
		}
		NodeDLL prev = temp.back;
		NodeDLL newNode = new NodeDLL(val, temp, prev);
		prev.next = newNode;
		temp.back = newNode;
		return head;
	}

	private static NodeDLL insertBeforeTail(NodeDLL head, int val) {
		if (head.next == null) {
			return insertBeforeHead(head, val);
		}
		NodeDLL tail = head;
		while ((tail.next != null)) {
			tail = tail.next;
		}
		NodeDLL prev = tail.back;
		NodeDLL newNode = new NodeDLL(val, tail, prev);
		prev.next = newNode;
		tail.back = newNode;
		return head;
	}

	private static NodeDLL insertBeforeHead(NodeDLL head, int val) {
		NodeDLL newHead = new NodeDLL(val, head, null);
		head.back = newHead;
		return newHead;
	}

	private static void removeEl(NodeDLL temp) {
		NodeDLL prev = temp.back;
		NodeDLL front = temp.next;
		if (front == null) {
			prev.next = null;
			temp.back = null;
			return;
		}
		prev.next = front;
		front.back = prev;
		temp.back = null;
		temp.next = null;
		return;
	}

	private static NodeDLL removeK(NodeDLL head, int k) {
		if (head == null)
			return null;
		NodeDLL temp = head;
		int cnt = 0;
		while (temp != null) {
			cnt++;
			if (cnt == k) {
				break;
			}
			temp = temp.next;
		}
		NodeDLL prev = temp.back;
		NodeDLL front = temp.next;
		if (prev == null && front == null) {
			return null;
		} else if (prev == null) {
			return removeHead(head);
		} else if (front == null) {
			return removeLast(head);
		}

		prev.next = front;
		front.back = prev;
		temp.back = null;
		temp.next = null;

		return head;
	}

	private static NodeDLL removeLast(NodeDLL head) {
		if (head == null || head.next == null)
			return null;
		NodeDLL tail = head;
		while (tail.next != null) {
			tail = tail.next;
		}
		NodeDLL newTail = tail.back;
		newTail.next = null;
		tail.back = null;
		return head;
	}

	private static NodeDLL removeHead(NodeDLL head) {
		if (head == null || head.next == null)
			return null;

		NodeDLL prev = head;
		head = head.next;
		head.back = null;
		prev.next = null;
		return head;
	}

	private static void printDoublyLinkedList(NodeDLL head) {
		NodeDLL temp = head;
		while (temp != null) {
			System.out.print(temp.data + "->");
			temp = temp.next;
		}
		System.out.print("null");
	}

	private static NodeDLL convertArr2DLL(int[] arr) {
		NodeDLL head = new NodeDLL(arr[0]);
		NodeDLL prev = head;
		for (int i = 1; i < arr.length; i++) {
			NodeDLL temp = new NodeDLL(arr[i], null, prev);
			prev.next = temp;
			prev = temp;
		}
		return head;
	}
}
