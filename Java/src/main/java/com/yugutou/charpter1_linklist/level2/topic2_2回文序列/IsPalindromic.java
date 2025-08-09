package com.yugutou.charpter1_linklist.level2.topic2_2回文序列;

import com.yugutou.charpter1_linklist.level1.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/***
 * 判断链表是否回文
 */
public class IsPalindromic {
    static ListNode temp;

    public static void main(String[] args) {
        int[] a = {1, 0, 1};
        ListNode node = initLinkedList(a);
        int testMethod = 5;
        boolean result = false;
        switch (testMethod) {
            case 1://方法1：通过双指针的方式来判断
                result = isPalindromeByTwoPoints(node);
                break;
            case 2: //方法2：全部压栈
                result = isPalindromeByAllStack(node);
                break;
            case 3://方法3：只将一半的数据压栈
                result = isPalindromeByHalfStack(node);
                break;
            case 4://方法4：通过递归来实现
                result = isPalindromeByRe(node);
                break;
            case 5://方法4：通过递归来实现
                result = isPalindromeByAllStackOpti3(node);
                break;

        }
        System.out.println("result:" + result);
    }


    /**
     * 方法1：将链表元素都赋值到数组中，然后可以从数组两端向中间对比。这种方法会被视为逃避链表，面试不能这么干。
     *
     * @param head
     * @return
     */
    public static boolean isPalindromeByTwoPoints(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int i = 0;
        while (i <= list.size() / 2) {
            if (list.get(i) != list.get(list.size() - 1 - i)) {
                return false;
            }
            i += 1;
        }
        return true;
    }

    /**
     * 方法2：将链表元素全部压栈，然后一边出栈，一边重新遍历链表，一边比较两者元素值，只要有一个不相等，那就不是。
     *
     * @param head
     * @return
     */
    public static boolean isPalindromeByAllStack(ListNode head) {
        ListNode temp = head;
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        while (temp != null) {
            if (temp.val != stack.pop().val) {
                return false;
            }
            temp = temp.next;
        }
        return true;
    }

    /**
     * 方法2优化，先遍历第一遍，得到总长度。之后一边遍历链表，一边压栈。到达链表长度一半后就不再压栈，而是一边出栈，一边遍历，一边比较，只要有一个不相等，就不是回文链表。这样可以节省一半的空间。
     *
     * @param head
     * @return
     */
    public static boolean isPalindromeByAllStackOpti1(ListNode head) {
        ListNode temp = head;
        Stack<ListNode> stack = new Stack<>();
        int count = 0;
        while (head != null) {
            count += 1;
            head = head.next;
        }
        int pCount = 0;
        while (temp != null) {
            if (pCount < count / 2) {
                stack.push(temp);
                pCount += 1;
            } else {
                break;
            }
            temp = temp.next;
        }
        if (count % 2 != 0) {
            temp = temp.next;
        }
        while (stack.size() > 0) {
            if (stack.pop().val != temp.val) {
                return false;
            }
            temp = temp.next;
        }
        return true;
    }

    /**
     * 方法2优化2，先遍历第一遍，得到总长度。之后一边遍历链表，一边压栈。到达链表长度一半后就不再压栈，而是一边出栈，一边遍历，一边比较，只要有一个不相等，就不是回文链表。这样可以节省一半的空间。
     *
     * @param head
     * @return
     */
    public static boolean isPalindromeByAllStackOpti2(ListNode head) {
        ListNode temp = head;
        Stack<ListNode> stack = new Stack<>();
        int count = 0;
        while (head != null) {
            count += 1;
            head = head.next;
        }
        int pCount = 0;
        while (temp != null) {
            if (pCount < count / 2) {
                stack.push(temp);
                pCount += 1;
            } else {
                break;
            }
            temp = temp.next;
        }
        if (count % 2 != 0) {
            temp = temp.next;
        }
        while (stack.size() > 0) {
            if (stack.pop().val != temp.val) {
                return false;
            }
            temp = temp.next;
        }
        return true;
    }


    /**
     * 方法2优化3，优化方法3：既然要得到长度，那还是要遍历一次链表才可以，那是不是可以一边遍历一边全部压栈，
     * 然后第二遍比较的时候，只比较一半的元素呢？也就是只有一半的元素出栈， 链表也只遍历一半，当然可以。
     *
     * @param head
     * @return
     */
    public static boolean isPalindromeByAllStackOpti3(ListNode head) {
        ListNode temp = head;
        Stack<ListNode> stack = new Stack<>();
        int count = 0;
        while (head != null) {
            stack.push(head);
            count += 1;
            head = head.next;
        }
        int pCount = 0;
        while (temp != null && pCount < count / 2) {
            if (temp.val != stack.pop().val) {
                return false;
            }
            temp = temp.next;
        }
        return true;
    }

    /**
     * 方法3：只将一半的数据压栈
     *
     * @param head
     * @return
     */
    public static boolean isPalindromeByHalfStack(ListNode head) {
        if (head == null)
            return true;
        ListNode temp = head;
        Stack<Integer> stack = new Stack();
        //链表的长度
        int len = 0;
        //把链表节点的值存放到栈中
        while (temp != null) {
            stack.push(temp.val);
            temp = temp.next;
            len++;
        }
        //len长度除以2
        len >>= 1;
        //然后再出栈
        while (len-- >= 0) {
            if (head.val != stack.pop())
                return false;
            head = head.next;
        }
        return true;
    }

    /**
     * 方法4：通过递归来实现
     */

    public static boolean isPalindromeByRe(ListNode head) {
        temp = head;
        return check(head);
    }


    private static ListNode initLinkedList(int[] array) {
        ListNode head = null, cur = null;
        for (int i = 0; i < array.length; i++) {
            ListNode newNode = new ListNode(array[i]);
            newNode.next = null;
            if (i == 0) {
                head = newNode;
                cur = head;
            } else {
                cur.next = newNode;
                cur = newNode;
            }
        }
        return head;
    }

    private static boolean check(ListNode head) {
        if (head == null)
            return true;
        boolean res = check(head.next) && (temp.val == head.val);
        temp = temp.next;
        return res;
    }

    static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
