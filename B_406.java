import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_406 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int n = sc.nextInt(), m = sc.nextInt(), k;
		
		int arr[] = new int[n + 1];
		
		while(m-- > 0) {
			k = sc.nextInt();
			Arrays.fill(arr, 0);
			
			int i;
			
			boolean ans = true;
			
			for(i = 0; i < k; ++i) {
				int tmp = sc.nextInt();
				if(arr[Math.abs(tmp)] != 0) {
					if(arr[Math.abs(tmp)] < 0 && tmp > 0)
						ans = false;
					else if(arr[Math.abs(tmp)] > 0 && tmp < 0)
						ans = false;
				}
				else
					arr[Math.abs(tmp)] = tmp;
			}
			
			if(ans) {
				System.out.println("YES");
				return;
			}
		}
		
		System.out.println("NO");
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