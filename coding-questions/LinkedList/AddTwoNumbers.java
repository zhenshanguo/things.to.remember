/*
实现中注意维护进位，陷阱的话记住最后还要判一下有没有进位，如果有再生成一位。 
这道题还是有一些扩展的，比如这个其实是BigInteger的相加，数据结果不一定要用链表，也可以是数组，面试中可能两种都会问而且实现。
然后接下来可以考一些OO设计的东西，比如说如果这是一个类应该怎么实现，也就是把数组或者链表作为成为成员变量，再把这些操作作为成员函数，
进一步的问题可能是如何设计constructor，这个问题除了基本的还得对内置类型比如int, long的constructor, 类似于BigInteger(int num), 
BigInteger(int long). 总体来说问题还是比较简单，但是这种问题不能出错，所以还是要谨慎对待
*/

public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    int carry = 0;
    int digit = 0;
    ListNode head = null;
    ListNode pre = null;
    while(l1!=null && l2!=null)
    {
        digit = (l1.val+l2.val+carry)%10;
        carry = (l1.val+l2.val+carry)/10;
        ListNode newNode = new ListNode(digit);
        if(head==null)
            head = newNode;
        else
            pre.next = newNode;
        pre = newNode;
        l1 = l1.next;
        l2 = l2.next;
    }
    while(l1!=null)
    {
        digit = (l1.val+carry)%10;
        carry = (l1.val+carry)/10;
        ListNode newNode = new ListNode(digit);
        if(head==null)
            head = newNode;
        else
            pre.next = newNode;
        pre = newNode;
        l1 = l1.next;            
    }
    while(l2!=null)
    {
        digit = (l2.val+carry)%10;
        carry = (l2.val+carry)/10;
        ListNode newNode = new ListNode(digit);
        if(head==null)
            head = newNode;
        else
            pre.next = newNode;
        pre = newNode;
        l2 = l2.next;            
    }        
    if(carry>0)
    {
        ListNode newNode = new ListNode(carry);
        pre.next = newNode;
    }
    return head;
}

	/* another way, more concised */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1); // 头节点
        int carry = 0;
        ListNode prev = dummy;
        for (ListNode pa = l1, pb = l2;
             pa != null || pb != null;
             pa = pa == null ? null : pa.next,
             pb = pb == null ? null : pb.next,
             prev = prev.next) {
            final int ai = pa == null ? 0 : pa.val;
            final int bi = pb == null ? 0 : pb.val;
            final int value = (ai + bi + carry) % 10;
            carry = (ai + bi + carry) / 10;
            prev.next = new ListNode(value); // 尾插法
        }
        if (carry > 0)
            prev.next = new ListNode(carry);
        return dummy.next;
    }
