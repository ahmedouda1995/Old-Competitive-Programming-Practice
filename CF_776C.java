import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class CF_776C {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int n = sc.nextInt(), k = sc.nextInt();
		
		long powers[];
		
		if(k == 0) {
			powers = new long[1];
			powers[0] = 0;
		}
		else if(k == 1) {
			powers = new long[1];
			powers[0] = 1;
		}
		else if(k == -1) {
			powers = new long[2];
			powers[0] = 1;
			powers[1] = -1;
		}
		else {
			int sz = (int) (Math.log(1e17) / Math.log(Math.abs(k)));
			powers = new long[sz + 1];
			powers[0] = 1;
			long a = k;
			for(int i = 1; i < powers.length; ++i) {
				powers[i] = a;
				a *= k;
			}
		}
		
		int arr[] = new int[n];
		for(int i = 0; i < n; ++i) arr[i] = sc.nextInt();
		
		long sums[] = new long[n];
		TreeMap<Long, Long> map = new TreeMap<Long, Long>();
		
		sums[0] = arr[0];
		
		long res = 0;
		
		for(int i = 0; i < powers.length; ++i) {
			if(sums[0] == powers[i]) {
				res++;
				break;
			}
		}
		
		map.put(sums[0], 1L);
		
		for(int i = 1; i < n; ++i) {
			sums[i] = sums[i - 1] + arr[i];
			
			for(int j = 0; j < powers.length; ++j) {
				if(sums[i] == powers[j]) res++;
				
				if(map.containsKey(sums[i] - powers[j])) {
					res += map.get(sums[i] - powers[j]);
				}
			}
			
			if(map.containsKey(sums[i]))
				map.put(sums[i], map.get(sums[i]) + 1);
			else
				map.put(sums[i], 1L);
		}
		
		out.println(res);
		
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