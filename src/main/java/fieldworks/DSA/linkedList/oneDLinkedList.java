//package fieldworks.DSA.linkedList;
//
//class Node {
//    int data; // the data value
//    Node next; // the reference to the next Node in the linked list
//    // Constructors
//
//    Node(int data1, Node next1) {
//        this.data = data1; // Initialize data with the provided value
//        this.next = next1; // Initialize next with the provided reference
//    }
//
//    Node(int data1) {
//        this.data = data1; // Initialize data with the provided value
//        this.next = null; // Initialize next as null since it's the end of the list
//    }
//}
//
//public class oneDLinkedList {
//
//    public static void main(String[] args) {
//        // // 1.Introduction to LinkedList, learn about struct, and how is node
//        // represented
//        // // 1.1
//        // int[] arr = { 2, 5, 8, 7 };
//        // Node y = new Node(arr[0]);
//        // System.out.println(y.data);
//
//        // 1.2
//        // int[] arr = { 2, 5, 8, 7 };
//        // Node x = new Node(arr[0]);
//        // Node y = x;
//        // System.out.println(y);
//
//        // 2 & 3
//        // int[] arr = { 2, 5, 8, 7 };
//        // ==> create a linkedlist
//        // Node head = convertArrToLL(arr);
//        // 2. Deleting
//        // // 2.1==> Head
//        // head = removeHead(head);
//        // // 2.2==> last
//        // head = removeTail(head);
//        // // 2.3==> K position
//        // head = removeK(head, 3);
//        // // 2.4=> delete by Element(value)-(Node)
//        // int val = 3;
//        // head = removeEl(head, val);
//
//        // 3. Inserting
//        // // 3.1=> head
//        // head = insertHead(head, 5);
//        // 3.2=> last
//        // head = insertLast(head, 100);
//        // 3.3=>k position
//        // head = insertK(head, 3, 8);
//        // 3.4=>insert element before the value x
//        // head = insertEleBeforeX(head, 10, 5);
//        // ==> print LinkedList
//        // printLL(head);
//
//    }
//
//    // private static Node insertEleBeforeX(Node head, int val, int x) {
//    // // case-1
//    // if (head == null) {
//    // return null;
//    // }
//    // // case-2
//    // if (head.data == x) {
//    // Node newNode = new Node(val, head);
//    // return newNode;
//    // }
//
//    // // case-3
//
//    // Node temp = head;
//    // while (temp.next != null) {
//    // if (temp.next.data == x) {
//    // Node newNode = new Node(val);
//    // newNode.next = temp.next;
//    // temp.next = newNode;
//    // break;
//    // }
//    // temp = temp.next;
//    // }
//    // return head;
//
//    // // Node prev = null;
//    // // Node temp = head;
//    // // while (temp != null) {
//    // // if (temp.data == x) {
//    // // Node newNode = new Node(val);
//    // // prev.next = newNode;
//    // // newNode.next = temp;
//    // // break;
//    // // }
//    // // prev = temp;
//    // // temp = temp.next;
//    // // }
//    // // return head;
//    // }
//
//    // private static Node insertK(Node head, int k, int val) {
//    // // case-1
//    // if (head == null) {
//    // if (k == 1) {
//    // return new Node(val);
//    // } else {
//    // return null;
//    // }
//    // }
//    // // case-2
//    // if (k == 1) {
//    // Node newNode = new Node(val, head);
//    // return newNode;
//    // }
//    // // case-3
//    // int cnt = 0;
//    // Node temp = head;
//    // while (temp != null) {
//    // cnt++;
//    // if (cnt == k - 1) {
//    // Node newNode = new Node(val);
//    // newNode.next = temp.next;
//    // temp.next = newNode;
//    // break;
//    // }
//    // temp = temp.next;
//    // }
//    // return head;
//    // }
//
//    // private static Node insertLast(Node head, int val) {
//    // if (head == null)
//    // return new Node(val);
//
//    // Node temp = head;
//    // while (temp.next != null) {
//    // temp = temp.next;
//    // }
//    // Node newNode = new Node(val);
//    // temp.next = newNode;
//    // return head;
//    // }
//
//    // private static Node insertHead(Node head, int val) {
//    // Node temp = new Node(val, head);
//    // return temp;
//    // }
//
//    // private static Node removeEl(Node head, int val) {
//    // if (head == null)
//    // return null;
//
//    // if (head.data == val) {
//    // head = head.next;
//    // return head;
//    // }
//    // Node prev = null;
//    // Node temp = head;
//    // while (temp != null) {
//    // if (temp.data == val) {
//    // prev.next = prev.next.next;
//    // break;
//    // }
//    // prev = temp;
//    // temp = temp.next;
//    // }
//    // return head;
//    // }
//
//    // private static Node removeK(Node head, int k) {
//    // if (head == null)
//    // return null;
//    // if (k == 1) {
//    // head = head.next;
//    // return head;
//    // }
//    // int cnt = 0;
//    // Node prev = null;
//    // Node temp = head;
//
//    // while (temp != null) {
//    // cnt++;
//    // if (cnt == k) {
//    // prev.next = prev.next.next;
//    // break;
//    // }
//    // prev = temp;
//    // temp = temp.next;
//    // }
//
//    // return head;
//    // }
//
//    // private static Node removeTail(Node head) {
//    // if (head == null || head.next == null)
//    // return null;
//    // Node temp = head;
//    // while (temp.next.next != null) {
//    // temp = temp.next;
//    // }
//    // temp.next = null;
//    // return head;
//    // }
//
//    // private static Node removeHead(Node head) {
//    // if (head == null)
//    // return head;
//    // head = head.next;
//    // return head;
//    // }
//
//    // private static Node convertArrToLL(int[] arr) {
//    // Node head = new Node(arr[0]);
//    // Node current = head;
//    // for (int i = 1; i < arr.length; i++) {
//    // current.next = new Node(arr[i]);
//    // current = current.next;
//    // }
//    // return head;
//    // }
//
//    // private static void printLL(Node head) {
//    // Node temp = head;
//    // while (temp != null) {
//    // System.out.print(temp.data + "->");
//    // temp = temp.next;
//    // }
//    // }
//
//}
