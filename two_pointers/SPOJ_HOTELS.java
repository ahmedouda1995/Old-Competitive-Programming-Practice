package two_pointers;
import java.util.*;
import java.io.*;

public class SPOJ_HOTELS {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int n = sc.nextInt();
		long m = sc.nextLong();
		
		int arr[] = new int[n];
		for(int i = 0; i < n; ++i) arr[i] = sc.nextInt();
		
		int l = 0, r = 0;
		long sum = 0, max = 0;
		
		while(l < n) {
			while(r < n && sum + arr[r] <= m) {
				sum += arr[r];
				r++;
			}
			
			max = Math.max(max, sum);
			sum -= arr[l];
			l++;
		}
		
		out.println(max);
		
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