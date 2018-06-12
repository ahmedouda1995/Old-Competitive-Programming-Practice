import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class CodeJam4 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("bath.out");

		int t = sc.nextInt(), cases = 1;
		long n, k;
		TreeMap<Long, Long> map = new TreeMap<Long, Long>(Collections.reverseOrder());
		
		while(t-- > 0) {
			n = sc.nextLong();
			k = sc.nextLong();
			map.clear();
			
			map.put(n, 1L);
			Entry<Long, Long> e;
			
			while(true) {
				e = map.firstEntry();
				
				if(k <= e.getValue()) break;
				map.remove(e.getKey());
				
				if(e.getKey() % 2 == 1) {
					if(map.containsKey(e.getKey() >> 1))
						map.put(e.getKey() >> 1, map.get(e.getKey() >> 1) + (e.getValue() << 1));
					else
						map.put(e.getKey() >> 1, e.getValue() << 1);
				}
				else {
					if(map.containsKey((e.getKey() >> 1) - 1)) {
						map.put((e.getKey() >> 1) - 1, map.get((e.getKey() >> 1) - 1) + e.getValue());
					}
					else
						map.put((e.getKey() >> 1) - 1, e.getValue());
					if(map.containsKey((e.getKey() >> 1))) {
						map.put((e.getKey() >> 1), map.get((e.getKey() >> 1)) + e.getValue());
					}
					else
						map.put((e.getKey() >> 1), e.getValue());
				}
				
				k -= e.getValue();
			}
			
			e = map.firstEntry();
			
			long a, b;
			if(e.getKey() % 2 == 1) {
				a = b = (e.getKey() >> 1);
			}
			else {
				a = (e.getKey() >> 1);
				b = (e.getKey() >> 1) - 1;
			}
			out.printf("Case #%d: ", cases++);
			out.println(a + " " + b);
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