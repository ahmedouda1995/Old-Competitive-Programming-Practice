package ch9_4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class UVa_11111_Generalized_Matrioshkas {

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		String s; boolean printed;
		ArrayList<Integer> a = new ArrayList<Integer>();
		ArrayList<Integer> check = new ArrayList<Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		
		while((s = sc.nextLine()) != null) {
			printed = false;
			a.clear(); stack.clear(); check.clear();
			StringTokenizer st = new StringTokenizer(s);
			
			while(st.hasMoreTokens()) a.add(Integer.parseInt(st.nextToken()));
			
			for(int i = 0; i < a.size(); ++i) {
				if(a.get(i) < 0)
					stack.push(-a.get(i));
				else {
					if(stack.isEmpty()) {
						out.println(":-( Try again.");
						printed = true;
						break;
					}
					
					if(stack.peek().intValue() == a.get(i).intValue())
						stack.pop();
					else {
						out.println(":-( Try again.");
						printed = true;
						break;
					}
				}
			}
			
			if(!printed) {
				if(!stack.isEmpty()) {
					out.println(":-( Try again.");
					printed = true;
				}
				else {
					for(int i = 0; i < a.size(); ++i) {
						if(a.get(i) < 0) {
							check.add(-a.get(i));
						}
						else {
							if(check.get(check.size() - 1) > 0) {
								check.remove(check.size() - 1);
								if(check.size() > 0)
									check.set(check.size() - 1, check.get(check.size() - 1) - a.get(i));
							}
							else {
								out.println(":-( Try again.");
								printed = true;
								break;
							}
						}
					}
					
					if(!printed)
						out.println(":-) Matrioshka!");
				}
					
			}
		}
		 
		out.flush();
		out.close();
	}
	
	static class Pair {
		int a;
		int b;
		
		public Pair(int a, int b) {
			this.a = a;
			this.b = b;
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