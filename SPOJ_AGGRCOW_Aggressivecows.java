import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SPOJ_AGGRCOW_Aggressivecows {

	static int N, C;
	static int arr[];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt();
		
		while(t-- > 0) {
			N = sc.nextInt(); C = sc.nextInt();
			arr = new int[N];
			for(int i = 0; i < N; ++i) arr[i] = sc.nextInt();
			
			Arrays.sort(arr);
			
			int lo = 0, hi = arr[N - 1], mid, ans = 1;
			
			while(lo <= hi) {
				mid = ((lo + hi) >> 1);
				
				if(check(mid)) {
					ans = mid;
					lo = mid + 1;
				}
				else
					hi = mid - 1;
			}
			
			out.println(ans);
		}
		
		out.flush();
		out.close();
	}
	
	static boolean check(int dist) {
		int k = C - 1;
		
		int prev = 0;
		
		for(int i = 1; i < N && k > 0; ++i) {
			if(arr[i] - arr[prev] >= dist) {
				k--;
				prev = i;
			}
		}
		
		if(k == 0) return true;
		return false;
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