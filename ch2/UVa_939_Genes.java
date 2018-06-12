package ch2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class UVa_939_Genes {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		TreeMap<String, String> person_gene = new TreeMap<String, String>();
		TreeMap<String, Pair> child_parents = new TreeMap<String, Pair>();
		int n = sc.nextInt();
		
		while(n-- > 0){
			String s1 = sc.next(), s2 = sc.next();
			if(s2.equals("dominant") || s2.equals("recessive") || s2.equals("non-existent"))
				person_gene.put(s1, s2);
			else {
				if(!child_parents.containsKey(s2))
					child_parents.put(s2, new Pair(s1, null));
				else
					child_parents.replace(s2, new Pair(child_parents.get(s2).p1, s1));
			}
		}
		
		for(Entry<String, Pair> e : child_parents.entrySet()){
			if(!person_gene.containsKey(e.getKey()))
				resolve(e.getKey(), e.getValue(), person_gene, child_parents);
		}
		
		for (Entry<String, String> e : person_gene.entrySet()) {
			out.println(e.getKey() + " " + e.getValue());
		}
		out.flush();
		out.close();
	}
	private static void resolve(String ch, Pair e, TreeMap<String, String> person_gene, TreeMap<String, Pair> child_parents) {
		String p1 = e.p1, p2 = e.p2;
		if(!person_gene.containsKey(p1))
			resolve(p1, child_parents.get(p1), person_gene, child_parents);
		if(!person_gene.containsKey(p2))
			resolve(p2, child_parents.get(p2), person_gene, child_parents);
		
		if(person_gene.get(p1).equals("dominant") || person_gene.get(p2).equals("dominant") || (!person_gene.get(p1).equals("non-existent") && !person_gene.get(p2).equals("non-existent"))){
			if((person_gene.get(p1).equals("dominant") && person_gene.get(p2).equals("dominant")) || (person_gene.get(p1).equals("dominant") && person_gene.get(p2).equals("recessive")) || (person_gene.get(p1).equals("recessive") && person_gene.get(p2).equals("dominant")))
				person_gene.put(ch, "dominant");
			else
				person_gene.put(ch, "recessive");
		} else
			person_gene.put(ch, "non-existent");
	}
	static class Pair{
		String p1, p2;
		public Pair(String p1, String p2) {
			this.p1 = p1;
			this.p2 = p2;
		}
		public String toString(){
			return p1 + " " + p2;
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