package ch3_greedy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_11100TheTrip2007 {
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n, a[];
		boolean start = true;
		while((n = sc.nextInt()) != 0){
			a = new int[n];
			for(int i = 0; i < n; ++i) a[i] = sc.nextInt();
			
			if(!start) out.println();
			if(start) start = false;
			
			
			Arrays.sort(a);
			
			int bags = 1;
			int dupl = 1;
			for(int i = 1; i < n; ++i) {
				if(a[i] == a[i - 1]) 
					dupl++;
				else {
					bags = Math.max(bags, dupl);
					dupl = 1;
				}
			}
			
			bags = Math.max(bags, dupl);
			
			ArrayList<Integer> arr[] = new ArrayList[bags];
			for(int i = 0; i < bags; ++i) arr[i] = new ArrayList<Integer>();
			
			
			for(int i = 0; i < n; ++i)
				arr[i % bags].add(a[i]);
			
			out.println(bags);
			
			for (int i = 0; i < arr.length; i++) {
				out.print(arr[i].get(0));
				for (int j = 1; j < arr[i].size(); j++) {
					out.print(" " + arr[i].get(j));
				}
				out.println();
			}
		}
		
		out.flush();
		out.close();
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