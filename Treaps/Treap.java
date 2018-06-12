package Treaps;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;
import java.util.StringTokenizer;

public class Treap {
    
	Node root;
	static final int INF = (int) 2e9;
	static Random rand = new Random();
	
	class TwoNodes
	{
		Node left, right;
		
		public TwoNodes(Node l, Node r) {
			left = l;
			right = r;
		}
	}
	
	static class Node
	{
		int val, prior, sz;
		Node l, r;
		
		public Node(int v, int p) {
			val = v;
			prior = p;
			sz = 1;
		}
	}
	
	static int getSz(Node n) {
		return n == null?0:n.sz;
	}
	
	static void updateSz(Node n)
	{
		if(n != null)
			n.sz = getSz(n.l) + 1 + getSz(n.r);
	}
	
	TwoNodes split(Node node, int key)
	{
		if(node == null)
			return new TwoNodes(null, null);
		if(node.val <= key)
		{
			TwoNodes tmp = split(node.r, key);
			node.r = tmp.left;
			updateSz(node);
			return new TwoNodes(node, tmp.right);
		}
		else
		{
			TwoNodes tmp = split(node.l, key);
			node.l = tmp.right;
			updateSz(node);
			return new TwoNodes(tmp.left, node);
		}
	}
	
	Node merge(Node left, Node right)
	{
		if(left == null || right == null)
			return left == null?right:left;
		if(left.prior > right.prior)
		{
			left.r = merge(left.r, right);
			updateSz(left);
			return left;
		}
		else
		{
			right.l = merge(left, right.l);
			updateSz(right);
			return right;
		}
	}
	
	void insert(int key)
	{
		root = insert(root, new Node(key, rand.nextInt(INF)));
	}
	
	private Node insert(Node cur, Node newNode) {
		if(cur == null) return newNode;
		if(newNode.prior > cur.prior)
		{
			TwoNodes tmp = split(cur, newNode.val);
			newNode.l = tmp.left;
			newNode.r = tmp.right;
			updateSz(newNode);
			return newNode;
		}
		else
		{
			if(cur.val <= newNode.val)
				cur.r = insert(cur.r, newNode);
			else
				cur.l = insert(cur.l, newNode);
			updateSz(cur);
			return cur;
		}
	}

	void delete(int key)
	{
		root  = delete(root, key);
	}
	
	private Node delete(Node cur, int key) {
		if(cur == null) return null;
		if(cur.val == key)
			return merge(cur.l, cur.r);
		else if(key < cur.val)
    		cur.l = delete(cur.l, key);
    	else
    		cur.r = delete(cur.r, key);
		updateSz(cur);
		return cur;
	}

	int search(int key)
	{
		Node cur = root;
		int rank = 0;
		
		while(cur != null)
		{
			if(cur.val == key)
				return rank + getSz(cur.l) + 1;
			else if(cur.val < key)
			{
				rank += getSz(cur.l) + 1;
				cur = cur.r;
			}
			else
				cur = cur.l;
		}
		return rank;
	}
	
	static void dfs(Node cur)
	{
		if(cur == null) return;
		System.out.println(cur.val + " " + cur.sz + " " + cur.prior);
		dfs(cur.l); dfs(cur.r);
	}
	
    public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		Treap treap = new Treap();
		int total = 0;
		
		int n = sc.nextInt();
		String s;
		while(n-- > 0)
		{
			s = sc.next();
			
			switch (s) {
			case "register": treap.insert(sc.nextInt()); total++; break;
			case "unregister": treap.delete(sc.nextInt()); total--; break;
			default: out.println(total - treap.search(sc.nextInt()) + 1);
			}
		}
		
		out.flush();
	}
    
    static class Scanner 
	{
		StringTokenizer st; BufferedReader br;
		Scanner(InputStream system) {br = new BufferedReader(new InputStreamReader(system));}
		Scanner(String file) throws FileNotFoundException {br = new BufferedReader(new FileReader(file));}
		String next() throws IOException {
			while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
			return st.nextToken(); }
		String nextLine()throws IOException{return br.readLine();}
		int nextInt() throws IOException {return Integer.parseInt(next());}
		double nextDouble() throws IOException {return Double.parseDouble(next());}
		char nextChar()throws IOException{return next().charAt(0);}
		Long nextLong()throws IOException{return Long.parseLong(next());}
		boolean ready() throws IOException{return br.ready();}
	}
}