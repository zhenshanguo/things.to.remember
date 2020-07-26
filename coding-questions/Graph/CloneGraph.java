/*
Given a reference of a node in a connected undirected graph.

Return a deep copy (clone) of the graph.

Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.

class Node {
    public int val;
    public List<Node> neighbors;
}
 

Test case format:

For simplicity sake, each node's value is the same as the node's index (1-indexed). For example, the first node with val = 1, the second node with val = 2, and so on. The graph is represented in the test case using an adjacency list.

Adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.

The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.
*/

/*
这道题是LeetCode中为数不多的关于图的题目，不过这道题还是比较基础，就是考察图非常经典的方法：深度优先搜索和广度优先搜索。
这道题用两种方法都可以解决，因为只是一个图的复制，用哪种遍历方式都可以。具体细节就不多说了，因为两种方法太常见了。
这里恰好可以用旧结点和新结点的HashMap来做visited的记录
*/

import java.util.*;
import java.lang.*;

// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

public class Main
{
    // use BFS to traverse the graph and deep copy each node
    public Node cloneGraph(Node node) {
        
        // check edge case
        if(node==null)
            return null;
        //define the queue for BFS
        LinkedList<Node> queue = new LinkedList<Node>();
        //define the map to hold each node and its copy
        HashMap<Node, Node> map = new HashMap<Node, Node>();
        
        // copy current node
        Node copy = new Node(node.val);
        
        // initialize the queue and the map
        map.put(node,copy);
        queue.offer(node);
        while(!queue.isEmpty())
        {
            Node cur = queue.poll();
            // go through each neighbor of current node
            for(int i=0;i<cur.neighbors.size();i++)
            {
                // if the neighbor is not cloned, clone it and put it to the queue and the map
                if(!map.containsKey(cur.neighbors.get(i)))
                {
                    copy = new Node(cur.neighbors.get(i).val);
                    map.put(cur.neighbors.get(i),copy);
                    queue.offer(cur.neighbors.get(i));
                }
                // add the neighbor copy to current node's copy's neighbor list
                map.get(cur).neighbors.add(map.get(cur.neighbors.get(i)));
            }
        }
        return map.get(node);
    }
    
    // use DFS to traverse the graph and deep copy each node
    public Node cloneGraph(Node node) {
        
        // check edge case
        if(node == null)
            return null;
        
        // define a stack for DFS traverse
        LinkedList<Node> stack = new LinkedList<Node>();
        
        // define a map to hold each node and its copy
        HashMap<Node, Node> map = new HashMap<Node, Node>();
        // initialize the queue and the map
        stack.push(node);
        Node copy = new Node(node.val);
        map.put(node,copy);
        
        // go through the stack till it's empty
        while(!stack.isEmpty())
        {
            Node cur = stack.pop();
            
            // for each neighbor, if it's not copied, deep copy it and put it to the queue and map
            for(int i=0;i<cur.neighbors.size();i++)
            {
                if(!map.containsKey(cur.neighbors.get(i)))
                {
                    copy = new Node(cur.neighbors.get(i).val);
                    map.put(cur.neighbors.get(i),copy);
                    stack.push(cur.neighbors.get(i));
                }
                // add the neighbor copy to current node's copy's neighbor list
                map.get(cur).neighbors.add(map.get(cur.neighbors.get(i)));
            }
        }
        return map.get(node);
    }
    
    // use recursive way to traverse the graph and copy each node
    public Node cloneGraph(Node node) {
        
        // edge case checking
        if(node == null)
            return null;
        // define the map to hold each node and its copy
        HashMap<Node, Node> map = new HashMap<Node, Node>();
        Node copy = new Node(node.val);
        map.put(node,copy);
        // call the helper function to recursively copy the entire graph
        helper(node,map);
        return copy;
    }
    
    // recursive function to copy neighbors of the passed-in node
    private void helper(Node node, HashMap<Node, Node> map)
    {
        // loop through each neighbor, if it's not copied, copy it, put it in the map and 
        // then copy its neighbors
        for(int i=0;i<node.neighbors.size();i++)
        { 
            Node cur = node.neighbors.get(i);
            if(!map.containsKey(cur))
            {
                Node copy = new Node(cur.label);
                map.put(cur,copy);
                // recursively copy the neighbor's neighbors
                helper(cur,map);
            }
            // add the neighbor's copy to current node's copy's neighbor list
            map.get(node).neighbors.add(map.get(cur));
        }
    }

	public static void main(String[] args) {
	    Main test = new Main();
	    Node res = test.cloneGraph(new Node(1));
	}
}
