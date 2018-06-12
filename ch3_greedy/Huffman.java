package ch3_greedy;

import java.util.Map.Entry;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Huffman {

	public static void main(String[] args) {
		String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaabbbbbbbbbbbbbccccccccccccddddddddddddddddeeeeeeeeefffff";
		TreeMap<Character, Integer> freq = new TreeMap<Character, Integer>();
		
		for(int i = 0; i < s.length(); ++i) {
			if(!freq.containsKey(s.charAt(i)))
				freq.put(s.charAt(i), 1);
			else
				freq.replace(s.charAt(i), freq.get(s.charAt(i)) + 1);
		}
		
		Node [] arr = new Node[freq.size()];
		int i = 0;
		for(Entry<Character, Integer> e : freq.entrySet()){
			arr[i++] = new Node(e.getKey(), null, null, e.getValue());
		}
		
		Arrays.sort(arr);
		
		Node root = build(arr);
		System.out.println(root.f);
		printCodes(root, "");
	}
	
	private static Node build(Node[] arr) {
		int n = arr.length;
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		for(Node p : arr) q.offer(p);
		
		for(int i = 1; i < n; ++i) {
			Node x, y;
			q.offer(new Node('z', x = q.poll(), y = q.poll(), x.f + y.f));
		}
		return q.poll();
	}
	
	public static void printCodes(Node root, String s) {
		if(root.left == null && root.right == null) // leaf
			System.out.println(root.c + " : " + s);
		else {
			printCodes(root.left, s + "0");
			printCodes(root.right, s + "1");
		}
	}

	static class Node implements Comparable<Node>{
		char c;
		int f;
		Node left, right;
		
		
		public Node(char c, Node left, Node right, int f) {
			this.c = c;
			this.f = f;
			this.left = left;
			this.right = right;
		}

		@Override
		public int compareTo(Node p) {
			return this.f - p.f;
		}
	}
}
