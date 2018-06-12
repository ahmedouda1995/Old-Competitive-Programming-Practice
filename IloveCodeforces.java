import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class IloveCodeforces {

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		int n = Integer.parseInt(in.readLine());
		
		TreeMap<Integer, Pair> map = new TreeMap<Integer, Pair>();
		for (int i = 1; i <= n; i++) {
			map.put(i, new Pair(in.readLine(), 0));
		}
		
		int m = Integer.parseInt(in.readLine());
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			Pair p = map.get(b);
			
			map.put(a, new Pair(p.s, p.n + 1));
		}
		
		Pair p = map.get(1);
		
		for (int i = 0; i < p.n; i++) {
			out.print("I_love_");
		}
		
		out.println(p.s);
		
		out.flush();
		out.close();
	}
	
	static class Pair {
		String s;
		int n;
		
		public Pair(String a, int k) {
			s = a;
			n = k;
		}
	}
}
