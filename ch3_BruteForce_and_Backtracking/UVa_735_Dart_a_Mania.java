package ch3_BruteForce_and_Backtracking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class UVa_735_Dart_a_Mania {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int [] a = new int[62];
		for(int i = 1; i <= 20; ++i) {
			a[i] = i; a[i + 20] = (i << 1); a[i + 40] = i * 3;
		}
		a[61] = 50;
		int [] tmp = new int[3];
		TreeMap<Integer, TreeSet<Triple>> mapPerm = new TreeMap<Integer, TreeSet<Triple>>();
		TreeMap<Integer, TreeSet<Triple>> mapComb = new TreeMap<Integer, TreeSet<Triple>>();
		
		for (int i = 0; i < 62; i++) {
			for (int j = 0; j < 62; j++) {
				for (int k = 0; k < 62; k++) {
					int total = a[i] + a[j] + a[k];
					tmp[0] = a[i]; tmp[1] = a[j]; tmp[2] = a[k];
					if(!mapPerm.containsKey(total))
						mapPerm.put(total, new TreeSet<Triple>());
					mapPerm.get(total).add(new Triple(a[i], a[j], a[k]));
					Arrays.sort(tmp);
					if(!mapComb.containsKey(total))
						mapComb.put(total, new TreeSet<Triple>());
					mapComb.get(total).add(new Triple(tmp[0], tmp[1], tmp[2]));
				}
			}
		}
		int n;
		while((n = sc.nextInt()) > 0){
			if(!mapPerm.containsKey(n))
				out.println("THE SCORE OF " + n + " CANNOT BE MADE WITH THREE DARTS.");
			else {
				int b = mapComb.get(n).size(), c = mapPerm.get(n).size();
				out.println("NUMBER OF COMBINATIONS THAT SCORES " + n + " IS " + b + ".");
				out.println("NUMBER OF PERMUTATIONS THAT SCORES " + n + " IS " + c + ".");
			}
			out.println("**********************************************************************");
		}
		out.println("END OF OUTPUT");
		out.flush();
		out.close();
	}
	
	static class Triple implements Comparable<Triple>{
		int x, y, z;
		
		public Triple(int x, int y, int z) {
			this.x = x; this.y= y; this.z = z;
		}

		@Override
		public int compareTo(Triple t) {
			if(Integer.compare(this.x, t.x) == 0)
				if(Integer.compare(this.y, t.y) == 0)
					return Integer.compare(this.z, t.z);
				else
					return Integer.compare(this.y, t.y);
			else
				return Integer.compare(this.x, t.x);
		}
		
		@Override
		public String toString() {
			return "(" + this.x + " " + this.y + " " + this.z + ")";
		}
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