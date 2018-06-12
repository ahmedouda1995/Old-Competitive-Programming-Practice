package ch3_greedy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_11269_SettingProblems {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n;
		Pair arr[];
		
		while(sc.ready()) {
			n = sc.nextInt();
			arr = new Pair[n];
			int a[] = new int[n];
			int b[] = new int[n];
			
			for(int i = 0; i < n; ++i) a[i] = sc.nextInt();
			for(int i = 0; i < n; ++i) b[i] = sc.nextInt();
			
			for(int i = 0; i < n; ++i) arr[i] = new Pair(a[i], b[i]);
			Arrays.sort(arr);
			System.out.println(Arrays.toString(arr));
			for(int i = 1; i < n; ++i)
				arr[i].a += arr[i - 1].a;
			
			int time = 0;
			for(int i = 0; i < n; ++i) {
				if(time < arr[i].a)
					time = arr[i].a;
				time += arr[i].b;
			}
			System.out.println(Arrays.toString(arr));
			out.println(time);
		}
		
		out.flush();
		out.close();
	}
	
	static class Pair implements Comparable<Pair>{
		int a, b;
		
		public Pair(int x, int y) {
			a = x;
			b = y;
		}
		
		@Override
		public int compareTo(Pair p) {
//			if(Integer.compare(a + b, p.a + p.b) == 0)
//				return Integer.compare(a, p.a);
			return Integer.compare(a + b, p.a + p.b);
		}
		
		@Override
		public String toString() {
			return a + " " + b;
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