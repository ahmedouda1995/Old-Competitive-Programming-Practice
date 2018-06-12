package ds_math_alg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class NumberTheory4 {
	
	static int MOD = 10;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		StringTokenizer st;
		Stack<Integer> stack = new Stack<Integer>();
		String s;
		
		while(t-- > 0)
		{
			stack.clear();
			MOD = sc.nextInt();
			st = new StringTokenizer(sc.nextLine());
			while(st.hasMoreTokens())
			{
				s = st.nextToken();
				switch (s) {
				case "+": stack.push((int) ((1L * stack.pop() + stack.pop()) % MOD)); break;
				case "*": stack.push((int) ((1L * stack.pop() * stack.pop()) % MOD)); break;
				case "^": stack.push((int) modExp(stack.pop(), stack.pop())); break;
				default: stack.push(Integer.parseInt(s)); break;
				}
			}
			out.println(stack.pop() % MOD);
		}
		
		out.flush();
		out.close();
	}
	
	private static int modExp(int p, int b) {
		if(p == 0) return 1;
		int ans = modExp(p >> 1, b);
		
		ans = (int) (1L * ans * ans % MOD);
		if((p & 1) == 1)
			ans = (int) (1L * ans * b % MOD);
		return ans;
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