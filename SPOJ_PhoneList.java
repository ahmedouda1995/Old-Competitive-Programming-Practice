import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SPOJ_PhoneList {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt(), n;
		
		while(t-- > 0) {
			n = sc.nextInt();
			String s;
			
			boolean ans = true;
			Trie trie = new Trie();
			
			for(int i = 0; i < n; ++i) {
				s = sc.nextLine();
				if(ans) {
					if(trie.hasPref(s))
						ans = false;
				}
			}
			
			out.println(ans?"YES":"NO");
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
		
		static class Node {
			Node [] children;
			boolean isWord;
			
			public Node() {
				children = new Node[10];
				isWord = false;
			}
		}
		
		void addWord(String s) {
			str = s;
			addWord(0, root);
		}

		private void addWord(int i, Node cur) {
			if(i == str.length()) { cur.isWord = true; return; }
			int pos = str.charAt(i) - '0';
			
			if(cur.children[pos] == null)
				cur.children[pos] = new Node();
			addWord(i + 1, cur.children[pos]);
		}
		
		boolean hasPref(String s) {
			str = s;
			return hasPref(0, root);
		}

		private boolean hasPref(int i, Node cur) {
			if(i == str.length()) return true;
			if(cur.isWord == true) return true;
			int pos = str.charAt(i) - '0';
			if(cur.children[pos] == null) {
				addWord(i, cur);
				return false;
			}
			return hasPref(i + 1, cur.children[pos]);
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