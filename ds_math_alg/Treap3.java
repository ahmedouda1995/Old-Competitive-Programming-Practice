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

public class Treap3 {
    
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
		int val, prior, sz, max;
		boolean flipped;
		Node l, r;
		
		public Node(int v, int p) {
			val = v;
			prior = p;
			sz = 1;
			max = val;
			flipped = false;
		}
		
		void flip()
		{
			flipped = !flipped;
			Node tmp = this.l;
			this.l = this.r;
			this.r = tmp;
		}
	}
	
	static int getSz(Node n) {
		return n == null?0:n.sz;
	}
	
	private static int getMax(Node n) {
		return n == null?0:n.max;
	}
	
	static void updateSz(Node n)
	{
		if(n != null)
		{
			n.sz = getSz(n.l) + 1 + getSz(n.r);
			n.max = Math.max(n.val, Math.max(getMax(n.l), getMax(n.r)));
		}
	}


	TwoNodes split(Node node, int pos, int add)
	{
		if(node == null)
			return new TwoNodes(null, null);
		int curPos = add + getSz(node.l);
		propagate(node);
		if(curPos <= pos)
		{
			TwoNodes tmp = split(node.r, pos, curPos + 1);
			node.r = tmp.left;
			updateSz(node);
			return new TwoNodes(node, tmp.right);
		}
		else
		{
			TwoNodes tmp = split(node.l, pos, add);
			node.l = tmp.right;
			updateSz(node);
			return new TwoNodes(tmp.left, node);
		}
	}
	
	private void propagate(Node node) {
		if(node.flipped)
		{
			Node left = node.l;
			Node right = node.r;
			
			if(left != null)
				left.flip();
			if(right != null)
				right.flip();
			
			node.flipped = false;
		}
	}

	Node merge(Node left, Node right)
	{
		if(left == null || right == null)
			return left == null?right:left;
		if(left.prior > right.prior)
		{
			propagate(left);
			left.r = merge(left.r, right);
			updateSz(left);
			return left;
		}
		else
		{
			propagate(right);
			right.l = merge(left, right.l);
			updateSz(right);
			return right;
		}
	}
	
	void insert(int x, int idx)
	{
		TwoNodes tmp = split(root, idx - 1, 0);
		root = merge(tmp.left, new Node(x, rand.nextInt(INF)));
		root = merge(root, tmp.right);
	}

	int delete(int idx)
	{
		int val = -1;
		TwoNodes left = split(root, idx - 1, 0);
		TwoNodes right = split(left.right, 0, 0);
		val = right.left.val;
		root = merge(left.left, right.right);
		
		return val;
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
	
	static int P;
	
	static void dfs(Node cur)
	{
		if(cur == null) return;
		dfs(cur.l);
		System.out.println(P++ + " " + cur.val);
		dfs(cur.r);
	}
	
	void swap(int L, int R)
	{
		TwoNodes rangeL = split(root, L - 1, 0);
		TwoNodes rangeR = split(rangeL.right, R - L, 0);
		rangeR.left.flip();
		root = merge(rangeR.left, rangeR.right);
		root = merge(rangeL.left, root);
	}
	
	int query(int L, int R)
	{
		int ans = -1;
		TwoNodes rangeL = split(root, L - 1, 0);
		TwoNodes rangeR = split(rangeL.right, R - L, 0);
		ans = rangeR.left.max;
		root = merge(rangeR.left, rangeR.right);
		root = merge(rangeL.left, root);
		return ans;
	}
	
    public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		Treap3 treap = new Treap3();
		
		int n = sc.nextInt(), q = sc.nextInt(), i, j, sum;
		for(int k = 0; k < n; ++k) treap.insert(sc.nextInt(), k);
		String s;
		while(q-- > 0)
		{
			s = sc.next();
			switch (s) {
			case "swap":
				i = sc.nextInt();
				sum = sc.nextInt();
				j = sum - i;
				if(j > n)
				{
					j = n;
					i = Math.max(i, sum - j);
				}
				if(i > j) { int tmp = i; i = j; j = tmp; }
				if(i <= j)
					treap.swap(i - 1, j - 1);
				break;
			default: out.println(treap.query(sc.nextInt() - 1, sc.nextInt() - 1));
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