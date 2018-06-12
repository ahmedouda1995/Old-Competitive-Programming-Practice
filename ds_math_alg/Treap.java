package ds_math_alg;

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

    static final int INF = (int)1e9;
    static final Random rand = new Random();

    Node root;

    TwoNode split(Node t, int X) {

        if(t == null)
            return new TwoNode(null, null);

        if(t.key <= X) {

            TwoNode nxt = split(t.right, X);
            t.right = nxt.left;
            return new TwoNode(t, nxt.right);
        }
        else {
            TwoNode nxt = split(t.left, X);
            t.left = nxt.right;
            return new TwoNode(nxt.left, t);
        }
    }

    Node merge(Node L, Node R) {
        if(L == null || R == null)
            return L == null ? R : L;
        if(L.priority < R.priority) {
            R.left = merge(L, R.left);
            return R;
        }
        else {
            L.right = merge(L.right, R);
            return L;
        }
    }

    void insert(int key) { root = insert(root, new Node(key, rand.nextInt(INF))); }

    Node insert(Node cur, Node newNode) {
    	if(cur == null) return newNode;
        if(newNode.priority > cur.priority)
        {
        	TwoNode tmp = split(cur, newNode.key);
        	newNode.left = tmp.left;
        	newNode.right = tmp.right;
        	return newNode;
        }
        else
        {
        	if(newNode.key < cur.key)
        		cur.left = insert(cur.left, newNode);
        	else
        		cur.right = insert(cur.right, newNode);
        	return cur;
        }
        
    }

    void delete(int key) {
        root = delete(root, key);
    }

    private Node delete(Node cur, int key) {
		if(cur == null) return cur;

		if(cur.key == key)
    		return merge(cur.left, cur.right);
    	else if(key < cur.key)
    		cur.left = delete(cur.left, key);
    	else
    		cur.right = delete(cur.right, key);
    	return cur;
	}

	boolean search(int key) {
        Node cur = root;
        while(cur != null)
        {
        	if(cur.key == key)
        		return true;
        	else if(key < cur.key)
        		cur = cur.left;
        	else
        		cur = cur.right;
        }
        return false;
    }


	static void dfs(Node cur)
	{
		if(cur == null) return;
		System.out.println(cur.key);
		dfs(cur.left); dfs(cur.right);
	}
	
    class Node {
        Node left, right;
        int key, priority;

        Node(int k, int p) { key = k; priority = p; }
    }

    class TwoNode {
        Node left, right;

        TwoNode(Node l, Node r) { left = l; right = r; }
    }
    
    public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		Treap treap = new Treap();
		
		int n = sc.nextInt();
		char c;
		while(n-- > 0)
		{
			c = sc.next().charAt(0);
			
			switch (c) {
			case 'i': treap.insert(sc.nextInt()); break;
			case 'r': treap.delete(sc.nextInt()); break;
			default: out.println(treap.search(sc.nextInt())?"YES":"NO");
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