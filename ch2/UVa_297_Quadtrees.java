package ch2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_297_Quadtrees {

	static int i;
	static String s;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt();
		
		while(t-- > 0) {
			s = sc.nextLine();
			i = 0; QuadNode qTree1 = build(1024);
			s = sc.nextLine();
			i = 0; QuadNode qTree2 = build(1024);
			out.printf("There are %d black pixels.\n", comp(qTree1, qTree2));
		}

		out.flush();
		out.close();
	}
	
	private static int comp(QuadNode qTree1, QuadNode qTree2) {
		if(qTree1 == null) {
			if(qTree2.val > 0)
				return qTree2.val;
			if(qTree2.val == 0)
				return 0;
			int res = 0;
			for(int i = 0; i < 4; ++i) {
				res += comp(null, qTree2.children[i]);
			}
			return res;
		}
		if(qTree2 == null) {
			if(qTree1.val > 0)
				return qTree1.val;
			if(qTree1.val == 0)
				return 0;
			int res = 0;
			for(int i = 0; i < 4; ++i) {
				res += comp(qTree1.children[i], null);
			}
			return res;
		}
		
		if(qTree1.val > 0) return qTree1.val;
		if(qTree2.val > 0) return qTree2.val;
		if(qTree1.val == 0 && qTree2.val == 0) return 0;
		
		if(qTree1.val == -1 && qTree2.val == -1) {
			int res = 0;
			for(int i = 0; i < 4; ++i) {
				res += comp(qTree1.children[i], qTree2.children[i]);
			}
			return res;
		}
		
		if(qTree1.val == -1) {
			int res = 0;
			for(int i = 0; i < 4; ++i) {
				res += comp(qTree1.children[i], null);
			}
			return res;
		}
		else {
			int res = 0;
			for(int i = 0; i < 4; ++i) {
				res += comp(null, qTree2.children[i]);
			}
			return res;
		}
	}

	private static QuadNode build(int wt) {
		if(s.charAt(i) == 'f') {
			i++;
			return new QuadNode(wt);
		}
		else if(s.charAt(i) == 'e') {
			i++;
			return new QuadNode(0);
		}
		else {
			i++;
			QuadNode cur = new QuadNode(-1);
			for(int j = 0; j < 4; ++j) {
				cur.children[j] = build(wt >> 2);
			}
			return cur;
		}
	}

	static class QuadNode {
		int val;
		QuadNode children[];
		
		public QuadNode(int wt) {
			val = wt;
			children = new QuadNode[4];
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