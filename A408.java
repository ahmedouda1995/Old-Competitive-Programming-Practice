import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class A408 {

	static final int INF = (int) 1e9;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n = sc.nextInt(), m = sc.nextInt() - 1, k = sc.nextInt();
		
		int [] a= new int[n];
		for(int i = 0; i < n; ++i) a[i] = sc.nextInt();
		
		int dist1 = 0, dist2 = 0;
		boolean found = false;
		
		for(int i = m + 1; i < n; ++i) {
			dist1 += 10;
			if(a[i] <= k && a[i] > 0) {
				found = true;
				break;
			}
		}
		
		if(!found)
			dist1 = INF;
		
		found = false;
		for(int i = m - 1; i >= 0; --i) {
			dist2 += 10;
			if(a[i] <= k && a[i] > 0) {
				found = true;
				break;
			}
		}
		if(!found)
			dist2 = INF;
		
		out.println(Math.min(dist1, dist2));
		
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