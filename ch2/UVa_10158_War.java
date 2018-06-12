package ch2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_10158_War {
	
	// VERY IMPORTANT --> FAILED USING TREESETS (RTE) I THINK STACK OVERFLOWED
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n = sc.nextInt();
		UFDS ds = new UFDS(n);
		int op, a, b;
		while((op = sc.nextInt()) != 0){
			a = sc.nextInt(); b = sc.nextInt();
			switch (op) {
			case 1:if(!ds.setFriends(a, b)) out.println(-1);break;
			case 2: if(!ds.setEnemies(a, b)) out.println(-1); break;
			case 3:out.println((ds.areFriends(a, b))?1:0);break;
			case 4:out.println((ds.areEnemies(a, b))?1:0);break;
			}
		}
		out.flush();
		out.close();
	}

	static public class UFDS {

		private int p[], rank[], N;
		public UFDS(int n) {
			this.N = n;
			this.p = new int[2 * N];
			this.rank = new int[2 * N];
			for(int i = 0;i < (2 * N);++i){ p[i] = i; rank[i] = 0;}
		}
		public int findSet(int i){ return (p[i] == i)?i:(p[i] = findSet(p[i])); }
		public boolean areFriends(int i, int j) {return findSet(i) == findSet(j);}
		public boolean setFriends(int i, int j){
			int a = findSet(i), b = findSet(j);
			int aEnemy = findSet(i + N), bEnemy = findSet(j + N);
			if(a == bEnemy || b == aEnemy) return false;
			if(rank[a] > rank[b]){
				p[b] = a; p[bEnemy] = aEnemy;
			}
			else {
				p[a] = b; p[aEnemy] = bEnemy;
				if(rank[a] == rank[b]) rank[b]++;
			}
			return true;
		}
		public boolean setEnemies(int i, int j){
			int a = findSet(i), b = findSet(j);
			int aEnemy = findSet(i + N), bEnemy = findSet(j + N);
			if(a == b) return false;
			p[aEnemy] = b;
			p[bEnemy] = a;
			return true;
		}
		public boolean areEnemies(int i, int j) {
			int a = findSet(i), b = findSet(j);
			int aEnemy = findSet(i + N), bEnemy = findSet(j + N);
			if(a == bEnemy || b == aEnemy) return true;
			return false;
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