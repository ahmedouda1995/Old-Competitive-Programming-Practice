package ch9_4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class UVa_673_Parentheses_Balance {

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		String s;
		Stack<Character> st = new Stack<Character>();
		
		while(t-- > 0) {
			st.clear();
			s = sc.nextLine();
			
			for(int i = 0; i < s.length(); ++i) {
				if(s.charAt(i) == '(' || s.charAt(i) == '[') {
					st.push(s.charAt(i));
				}
				else if(s.charAt(i) == ')' || s.charAt(i) == ']') {
					if(st.isEmpty()) { st.push('a'); break;}
					
					if(st.peek() == '(' && s.charAt(i) == ')' || st.peek() == '[' && s.charAt(i) == ']')
						st.pop();
					else
						break;
				}
			}
			
			if(st.isEmpty())
				out.println("Yes");
			else
				out.println("No");
		}
		
		out.flush();
		out.close();
	}
	
	static class Pair {
		char c;
		int n;
		
		public Pair(char c, int n) {
			this.c = c;
			this.n = n;
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