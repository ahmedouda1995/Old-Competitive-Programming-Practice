package ch9_4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class UVa_00551 {

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		TreeSet<String> set = new TreeSet<String>();
		set.add("("); set.add("["); set.add("{"); set.add("<"); set.add("(*");
		TreeMap<String,String> map = new TreeMap<String, String>();
		map.put(")", "("); map.put("*)", "(*"); map.put(">", "<"); map.put("]", "[");
		map.put("}", "{");
		
		String s;
		Stack<String> stack = new Stack<String>();
		boolean printed;
		while((s = sc.nextLine()) != null) {
			stack.clear();
			printed = false;
			char a[] = s.toCharArray();
			int pos = 1;
			for(int i = 0; i < s.length(); ++i) {
				if(i + 1 < s.length() && a[i] == '(' && a[i + 1] == '*') {
					stack.push("(*");
					i++;
				}
				else if(i + 1 < s.length() && a[i] == '*' && a[i + 1] == ')') {
					if(stack.isEmpty()) {
						out.println("NO " + pos);
						printed = true;
						break;
					}
					if(stack.peek().equals("(*"))
						stack.pop();
					else {
						out.println("NO " + pos);
						printed = true;
						break;
					}
					i++;
				}
				else {
					if(set.contains(Character.toString(a[i])))
						stack.push(Character.toString(a[i]));
					else {
						if(map.containsKey(Character.toString(a[i]))) {
							if(stack.isEmpty()) {
								out.println("NO " + pos);
								printed = true;
								break;
							}
							if(stack.peek().equals(map.get(Character.toString(a[i]))))
								stack.pop();
							else {
								out.println("NO " + pos);
								printed = true;
								break;
							}
						}
					}
				}
				pos += 1;
			}
			
			if(!printed) {
				if(!stack.isEmpty())
					out.println("NO " + pos);
				else
					out.println("YES");
			}
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