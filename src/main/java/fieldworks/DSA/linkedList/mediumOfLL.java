package fieldworks.DSA.linkedList;

import java.util.HashMap;
import java.util.Stack;

class Node {
    int data;
    Node next;

    Node(int data1, Node next1) {
        this.data = data1;
        this.next = next1;
    }

    Node(int data1) {
        this.data = data1;
        this.next = null;
    }
}

public class mediumOfLL {

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5 };
        Node head = convertArrToLL(arr);

        // 1.Middle of a LinkedList [TortoiseHare Method]
        // findmiddle(head);

        // 2.Reverse a LinkedList [Iterative]
        // 2.1 => Broute Force (using stack)
        // head = reverseLLUsingStack(head);
        // 2.2 => Optimal (Iterative)
        // head = reverseLLUsingIterative(head);
        // 2.3 => optimal (Recursive)
        // head = reverseLLUsingRecursive(head);

        // // 3.Detect a Cycle in a Linked List
        // Node head1 = new Node(1);
        // Node second = new Node(2);
        // Node third = new Node(3);
        // Node fourth = new Node(4);
        // Node fifth = new Node(5);

        // head1.next = second;
        // second.next = third;
        // third.next = fourth;
        // fourth.next = fifth;
        // // Create a loop
        // fifth.next = third;

        // boolean flag = detectLoop(head1);
        // System.out.println(flag);

        // // 4.Find the starting point in LL
        // Node head1 = new Node(1);
        // Node second = new Node(2);
        // Node third = new Node(3);
        // Node fourth = new Node(4);
        // Node fifth = new Node(5);

        // head1.next = second;
        // second.next = third;
        // third.next = fourth;
        // fourth.next = fifth;
        // // Create a loop
        // fifth.next = third;
        // Node result = findStartingPoint(head1);
        // if (result != null) {
        // System.out.println(result.data);
        // } else {
        // System.out.println("null");
        // }

        // 5.Length of Loop in LL
        Node head1 = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);
        Node fourth = new Node(4);
        Node fifth = new Node(5);

        head1.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        // Create a loop
        fifth.next = third;
        int length = lengthOfLoop(head1);
        System.out.println(length);

        // printLL(head);
    }

    private static int lengthOfLoop(Node head1) {
        // // 1.Broute Force
        // Node temp = head1;
        // HashMap<Node, Integer> map = new HashMap<>();
        // int timer = 0;
        // while ((temp != null)) {
        // if (map.containsKey(temp)) {
        // int value = map.get(temp);
        // return timer - value;
        // } else {
        // map.put(temp, timer);
        // }
        // temp = temp.next;
        // timer++;
        // }
        // return 0;

        // 2.Optimal (TortoiseHare)
        Node slow = head1;
        Node fast = head1;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                fast = fast.next;
                int cnt = 1;
                while (slow != fast) {
                    cnt++;
                    fast = fast.next;
                }
                return cnt;
            }

        }
        return 0;
    }

    private static Node findStartingPoint(Node head1) {
        // // 1.Broute Force
        // Node temp = head1;
        // HashMap<Node, Integer> map = new HashMap<>();
        // while ((temp != null)) {
        // if (map.containsKey(temp)) {
        // return temp;
        // } else {
        // map.put(temp, 1);
        // }
        // temp = temp.next;
        // }
        // return null;

        // 2.Optimal (TortoiseHare)
        Node slow = head1;
        Node fast = head1;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                slow = head1;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }

        }
        return null;
    }

    private static boolean detectLoop(Node head1) {
        // // 1.Broute Force
        // Node temp = head1;
        // HashMap<Node, Integer> map = new HashMap<>();
        // while ((temp != null)) {
        // if (map.containsKey(temp)) {
        // return true;
        // }else{
        // map.put(temp,1);
        // }
        // temp = temp.next;
        // }
        // return false;

        // 2.Optimal (TortoiseHare)
        Node slow = head1;
        Node fast = head1;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }
        return false;
    }

    private static Node reverseLLUsingRecursive(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node newHead = reverseLLUsingRecursive(head.next);
        Node front = head.next;
        front.next = head;
        head.next = null;
        return newHead;
    }

    private static Node reverseLLUsingIterative(Node head) {
        Node prev = null;
        Node temp = head;
        while (temp != null) {
            Node front = temp.next;
            temp.next = prev;
            prev = temp;
            temp = front;
        }
        return prev;
    }

    private static Node reverseLLUsingStack(Node head) {
        Node temp = head;
        Stack<Integer> stack = new Stack<>();
        while (temp != null) {
            stack.push(temp.data);
            temp = temp.next;
        }

        temp = head;
        while (temp != null) {
            temp.data = stack.pop();
            temp = temp.next;
        }

        return head;
    }

    private static void findmiddle(Node head) {
        // // 1.1 ==> Broute Force
        // Node temp = head;
        // int len = 0;
        // while (temp != null) {
        // len++;
        // temp = temp.next;
        // }

        // int middle = (len / 2) + 1;
        // temp = head;
        // while (temp != null) {
        // middle = middle - 1;
        // if (middle == 0)
        // break;
        // temp = temp.next;
        // }
        // System.out.println(temp.data);

        // 1.2==> Optimal (TortoiseHare)
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        System.out.println(slow.data);
    }

    private static Node convertArrToLL(int[] arr) {
        Node head = new Node(arr[0]);
        Node current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new Node(arr[i]);
            current = current.next;
        }
        return head;
    }

    private static void printLL(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
    }

}
