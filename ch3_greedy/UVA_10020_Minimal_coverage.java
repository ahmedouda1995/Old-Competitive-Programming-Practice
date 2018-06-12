package ch3_greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class UVA_10020_Minimal_coverage {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		String s = br.readLine();
		StringTokenizer st;
		int t = Integer.parseInt(s), m, l, r;
		ArrayList<Pair> a;
		while(t-- > 0){
			br.readLine();
			m = Integer.parseInt(br.readLine());
			a = new ArrayList<Pair>();
			while(true){
				s = br.readLine();
				st = new StringTokenizer(s);
				l = Integer.parseInt(st.nextToken());
				r = Integer.parseInt(st.nextToken());
				if(l == 0 && r == 0)
					break;
				a.add(new Pair(l, r));
			}
			
			Collections.sort(a);
			int currentEnd = 0, maxRight = 0;
			Pair wanted = null;
			ArrayList<Pair> result = new ArrayList<Pair>();
			int i = 0;
			while(currentEnd < m){
				while(i < a.size()){
					if(a.get(i).left > currentEnd)
						break;
					if(a.get(i).left <= currentEnd && a.get(i).right > maxRight){
						maxRight = a.get(i).right;
						wanted = a.get(i);
					}
					i++;
				}
				if(maxRight > currentEnd){
					currentEnd = maxRight;
					result.add(wanted);
				}
				else break;
			}
			if(currentEnd >= m){
				out.println(result.size());
				for(Pair p : result)
					out.println(p.left + " " + p.right);
			}
			else
				out.println(0);
			
			out.println();
		}
		
		out.flush();
		out.close();
	}
	static class Pair implements Comparable<Pair>{
		int left, right;
		
		public Pair(int left, int right) {
			this.left = left;
			this.right = right;
		}
		
		@Override
		public String toString() {
			return "(" + left + "," + right + ")";
		}

		@Override
		public int compareTo(Pair o) {
			if(this.left != o.left)
				return Integer.compare(this.left, o.left);
			else
				return Integer.compare(o.right, this.right);
		}

	}
}