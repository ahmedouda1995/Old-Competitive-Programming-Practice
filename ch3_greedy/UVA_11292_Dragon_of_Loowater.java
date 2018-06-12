package ch3_greedy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVA_11292_Dragon_of_Loowater {

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int heads, knights;
		int [] hs, ks;
		while(true){
			heads = sc.nextInt();
			knights = sc.nextInt();
			
			if(heads == 0 && knights == 0)
				break;
			
			hs = new int[heads];
			ks = new int[knights];
			
			for(int i=0;i<heads;i++){
				hs[i] = sc.nextInt();
			}
			for(int i=0;i<knights;i++){
				ks[i] = sc.nextInt();
			}
			
			Arrays.sort(hs);
			Arrays.sort(ks);
			
			if(hs.length > ks.length){
				out.println("Loowater is doomed!");
			}
			else {
				int i = 0, j = 0;
				
				int killedHeads = 0, money = 0;
				
				while(i < hs.length && j < ks.length && killedHeads < hs.length){
					
					if(ks[j] >= hs[i]){
						killedHeads++;
						money += ks[j];
						i++;
						j++;
					}
					else 
						j++;
				}
				
				if(killedHeads == hs.length)
					out.println(money);
				else 
					out.println("Loowater is doomed!");
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
