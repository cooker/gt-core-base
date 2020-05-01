package com.github.cooker;

/**
 * grant
 * 22/4/2020 11:07 下午
 * 描述：
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int nx = 0;

        if (l1 != null){
            nx += l1.val;
        }

        if (l2 != null){
            nx += l2.val;
        }

        ListNode next = new ListNode(nx % 10);

        next.next = addTwo(l1 != null ? l1.next : null, l2 != null ? l2.next : null, nx / 10);

        return next;
    }


    public ListNode addTwo(ListNode l1, ListNode l2, int nx){
        if (l1 == null && l2 == null && nx > 0){
            return new ListNode(nx);
        }else if (l1 == null && l2 == null){
            return null;
        }

        int nnx = nx;

        if (l1 != null){
            nnx += l1.val;
        }

        if (l2 != null){
            nnx += l2.val;
        }


        ListNode next = new ListNode(nnx % 10);
        if (l1 != null || l2 != null){
            next.next = addTwo(l1 != null ? l1.next : null, l2 != null ? l2.next : null, nnx / 10);
        }



        return next;
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();

        ListNode l1 = new ListNode(7);
        l1.next = new ListNode(1);
        ListNode l2 = new ListNode(5);

        ListNode l3 = solution2.addTwoNumbers(l1, l2);
        ListNode l33 = l3;
        do{
            System.out.println(l33.val);
        }while ((l33 = l33.next) != null);

    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
