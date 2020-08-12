class Node implements Comparable { 
  int value; 
  Node next; 
  Node(int v) { 
    value = v; 
  } 
  public int compareTo(Node that) {
    return this.value > that.value;
  }
  
} 

L1: 1 -> 1 -> 4 -> 6 -> 9 
L2: 2 -> 3 -> 7 -> 10 
L3: 1 -> 5 -> 11 
Output: 1 -> 1 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 9 -> 10 -> 11 



/**
 * Given list of sorted lists, merge them into a single sorted list.
 */
public Node mergeLists(List<Node> lists) { 

//K = total number of elements in all the lists (i.e. 12 in this example)
//N = number of lists

    if (lists == null || lists.isEmpty()) {
        return null;
    }
    if (lists.size() == 1) {
        return lists.get(0);
    }
    
    int N = lists.size();
    Node helper = new Node(-1);
    Node pre = helper;
    
    PriorityQueue<Node> minHeap = new PriorityQueue<Node>(N);
    
    for (int i = 0; i< N; i++) {
        minHeap.add(lists.get(i));
    }
    
    while (minHeap.hasNext()) {
        Node min = minHeap.poll();
        Node n = new Node(min.value);
        pre.next = n;
        pre = pre.next;
        if (min.next != null) {
            minHeap.add(min.next);
        }
    }
    
    return helper.next;
}
