package two_pointers;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CF_252C {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int l = 0, r = 0;
		
		int n = sc.nextInt();
		int diff = sc.nextInt();
		int arr[] = new int[n];
		for(int i = 0; i < n; ++i) arr[i] = sc.nextInt();
		long res = 0;
		
		while(l < n) {
			while(r < n && arr[r] - arr[l] <= diff) r++;
			if(r - l >= 3)
				res += comb(r - l - 1);
			l++;
		}
		
		out.println(res);
		
		out.flush();
		out.close();
	}
	
	static long comb(int n) {
		return (n * 1L * (n - 1) >> 1);
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