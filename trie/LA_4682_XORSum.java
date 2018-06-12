package trie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class LA_4682_XORSum {

	static int amount;
	static int len = 31;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt(), n;
		
		while(t-- > 0) {
			n = sc.nextInt();
			Trie trie = new Trie();
			
			int xor = 0;
			trie.addWord(xor);
			int max = 0;
			
			for(int i = 0; i < n; ++i) {
				xor ^= sc.nextInt();
				trie.addWord(xor);
				
				max = Math.max(max, trie.best(xor));
			}
			
			out.println(max);
		}
		
		out.flush();
		out.close();
	}
	
	static int log2(int n) { return (int) (Math.log(n) / Math.log(2)); }
	 
	static class Trie {
		
		Node root;
		
		public Trie() {
			root = new Node();
		}
		
		int best(int n) {
			return best(31, n, root, 0);
		}
		
		private int best(int i, int bits, Node cur, int res) {
			if(i == -1) return res;
			int c = ((1 << i) & bits);
			
			if(c == 0) {
				if(cur.one != null)
					return best(i - 1, bits, cur.one, res | (1 << i));
				else
					return best(i - 1, bits, cur.zero, res);
			}
			else {
				if(cur.zero != null)
					return best(i - 1, bits, cur.zero, res | (1 << i));
				else
					return best(i - 1, bits, cur.one, res);
			}
		}

		void addWord(int n) {
			addWord(31, n, root);
		}
		
		private void addWord(int i, int bits, Node cur) {
			if(i == -1) return;
			else {
				if(((1 << i) & bits) == 0) {
					if(cur.zero == null)
						cur.zero = new Node();
					addWord(i - 1, bits, cur.zero);
				}
				else {
					if(cur.one == null)
						cur.one = new Node();
					addWord(i - 1, bits, cur.one);
				}
			}
		}

		static class Node { Node zero, one; }
	}
	
	static class Scanner{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){	br = new BufferedReader(r);}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}
	}
}