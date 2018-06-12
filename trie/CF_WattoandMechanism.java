package trie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CF_WattoandMechanism {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int n, m;
		
		n = sc.nextInt(); m = sc.nextInt();
		
		Trie t = new Trie();
		
		while(n-- > 0) { t.addWord(sc.nextLine()); }
		
		while(m-- > 0) {
			if(t.comp(sc.nextLine())) out.println("YES");
			else out.println("NO");
		}
		
		out.flush();
		out.close();
	}
	
	static class Trie {
		
		Node root;
		static String str;
		
		public Trie() {
			root = new Node();
		}
		
		void addWord(String s) {
			str = s;
			addWord(0, root);
		}
		
		private void addWord(int i, Node cur) {
			if(i == str.length()) cur.word = true;
			else {
				int pos = str.charAt(i) - 'a';
				
				if(cur.children[pos] == null)
					cur.children[pos] = new Node();
				addWord(i + 1, cur.children[pos]);
			}
		}

		boolean comp(String s) {
			str = s;
			return comp(0, root, 1);
		}
		
		private boolean comp(int i, Node cur, int diff) {
			if(i == str.length()) {
				if(cur.word && diff == 0)
					return true;
				return false;
			}
			
			char c = str.charAt(i);
			
			if(cur.children[c - 'a'] == null) {
				if(diff == 0)
					return false;
				else {
					boolean ans = false;
					for(int j = 0; j < 3 && !ans; ++j) {
						if(cur.children[j] != null)
							ans |= comp(i + 1, cur.children[j], diff - 1);
					}
					return ans;
				}
			}
			
			boolean ans = false;
			
			for(int j = 0; j < 3 && !ans; ++j) {
				if(j == (c - 'a'))
					ans |= comp(i + 1, cur.children[j], diff);
				else if(cur.children[j] != null)
					ans |= comp(i + 1, cur.children[j], diff - 1);
			}
			return ans;
		}

		static class Node {
			Node [] children;
			boolean word;
			
			public Node() {
				children = new Node[3];
				word = false;
			}
		}
		
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