import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class B408 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		int k = sc.nextInt();
		
		TreeSet<Integer> set = new TreeSet<Integer>();
		for(int i = 0; i < m; ++i) set.add(sc.nextInt());
		
		if(set.contains(1)) {
			out.println(1);
			out.flush();
			return;
		}
		
		int u, v;
		
		int arr[] = new int[n + 1];
		for(int i = 1; i <= n; ++i) arr[i] = i;
		
		while(k-- > 0) {
			u = sc.nextInt();
			v = sc.nextInt();
			
			if(arr[u] == 1 && set.contains(v)) {
				out.println(v);
				out.flush();
				return;
			}
			
			if(arr[v] == 1 && set.contains(u)) {
				out.println(u);
				out.flush();
				return;
			}
			
			int tmp = arr[u];
			arr[u] = arr[v];
			arr[v] = tmp;
		}
		
		for(int i = 1; i <= n; ++i) {
			if(arr[i] == 1) {
				out.println(i);
				break;
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