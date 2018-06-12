import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sam3 {

	static int N, K;
	static boolean vis[];
	static int table[][];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		N = sc.nextInt();
		vis = new boolean[N];
		
		int [] arr = new int[N];
		
		for(int i = 0; i < N; ++i) arr[i] = sc.nextInt();
		
		K = sc.nextInt();
		table = new int[K][N];
		
		for(int i = 0; i < K; ++i) {
			for(int j = 0; j < N; ++j) {
				table[i][sc.nextInt() - 1] = j;
			}
		}
		
		int pos = sc.nextInt();
		
		for(int i = 0; i < N; ++i)
			if(arr[i] == pos) {
				pos = i;
				break;
			}
		
		out.println(solve(pos)?"YES":"NO");
		
		out.flush();
		out.close();
	}
	
	private static boolean solve(int pos) {
		
		if(pos == 0) return true;
		if(vis[pos]) return false;
		
		vis[pos] = true;
		
		boolean res = false;
		
		for(int i = 0; i < K; ++i) {
			res |= solve(table[i][pos]);
		}
		
		return res;
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