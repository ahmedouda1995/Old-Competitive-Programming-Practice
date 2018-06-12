package math;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class UVA_11155 {

	public static void main(String[] args) throws IOException {
		//Scanner sc = new Scanner(new FileReader("input.txt"));
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt();
		int A, B, C, N, M;
		
		int arr[];
		int cases = 1;
		
		while(t-- > 0)
		{
			A = sc.nextInt();
			B = sc.nextInt();
			C = sc.nextInt();
			M = sc.nextInt();
			N = sc.nextInt();
			
			arr = new int[N];
			if(N > 0)
				arr[0] = A;
			for(int i = 1; i < N; ++i) arr[i] = ((arr[i - 1] * B) + C) % M + 1;
			
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			
			for(int i = 1; i < N; ++i) arr[i] += arr[i - 1];
			
			int res = 0;
			
			map.put(0, 1);
			
			for(int i = 0; i < N; ++i)
			{

				if(map.containsKey(arr[i] % M))
					res += map.get(arr[i] % M);
				
				if(!map.containsKey(arr[i] % M))
					map.put(arr[i] % M, 0);
				map.put(arr[i] % M, map.get(arr[i] % M) + 1);
			}
			
			System.out.printf("Case %d: %d\n", cases++, res);
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
