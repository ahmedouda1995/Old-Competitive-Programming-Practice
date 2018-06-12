package ch2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class UVA_394_Mapmaker {

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int n = sc.nextInt(), r = sc.nextInt();
		Hashtable<String, Array> h = new Hashtable<String, Array>();
		String name;
		int B, CD, D;
		ArrayList<Integer> lowerUpper;
		
		while(n-- > 0){
			name = sc.next();
			B = sc.nextInt();
			CD = sc.nextInt();
			D = sc.nextInt();
			int tmp = D;
			lowerUpper = new ArrayList<Integer>();
			while(tmp-- > 0){
				lowerUpper.add(sc.nextInt());
				lowerUpper.add(sc.nextInt());
			}
			h.put(name, new Array(name, B, CD, D, lowerUpper));
		}
		String arrName;
		ArrayList<Integer> indices, Cs;
		int result = 0, c0;
		while(r-- > 0){
			indices = new ArrayList<Integer>();
			Cs = new ArrayList<Integer>();
			arrName = sc.next();
			int tmp = h.get(arrName).D;
			while(tmp-- > 0){
				indices.add(sc.nextInt());
			}
			
			tmp = h.get(arrName).D;
			tmp--;
			Cs.add(h.get(arrName).CD);
			Array current = h.get(arrName);
			while(tmp-- > 0){
				Cs.add(0, (Cs.get(0) * (current.lowerUpper.get(((tmp + 1) * 2 + 1)) - current.lowerUpper.get(((tmp + 1) * 2)) + 1)));
			}
			c0 = current.B;
			for(int i=0;i<Cs.size();i++)
				c0 -= (Cs.get(i) * (current.lowerUpper.get(i * 2)));
			result = c0;
			for(int i=0;i<current.D;i++){
				result += (indices.get(i) * Cs.get(i));
			}
			
			out.println(arrName + indices + " = " + result);
		}
		
		out.flush();
		out.close();
	}
	
	static class Array{
		String name;
		int B, CD, D;
		ArrayList<Integer> lowerUpper;
		
		public Array(String name, int B, int CD, int D, ArrayList<Integer> lowerUpper) {
			this.name = name;
			this.B = B;
			this.CD = CD;
			this.D = D;
			this.lowerUpper = lowerUpper;
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
