package ch3_greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Test {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer st;
		String s;
		int n, l, pos, radius;
		double w;
		ArrayList<Pair> a;
		
		while((s = br.readLine()) != null){
			st = new StringTokenizer(s);
			n = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());
			w = Double.parseDouble(st.nextToken());
			w /= 2.0;
			a = new ArrayList<Pair>();
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				pos = Integer.parseInt(st.nextToken());
				radius = Integer.parseInt(st.nextToken());
				if(radius > w){
					double dx = Math.sqrt((double)radius*radius - w*w);
					a.add(new Pair(((double)pos - dx), (double)pos + dx));
				}
			}
			Collections.sort(a);
			
			double rightmost = 0.0;
			int count = 0;
			int i, j;
			for (i = 0; i <a.size(); i = j)
			{
				if (a.get(i).start > rightmost) break;
				for (j = i+1; j <a.size() && a.get(j).start <= rightmost; ++j)
				{
					if (a.get(j).end > a.get(i).end)
					{
						i = j;
					}
				}
				++count;
				rightmost = a.get(i).end;
				if (rightmost >= l) break;
			}
			if (rightmost >= l)
				out.println(count);
			else 
				out.println(-1);
		}
			
		out.flush();
		out.close();
	}
	
	static class Pair implements Comparable<Pair>{
		double start, end;
		
		public Pair(double start, double end) {
			this.start = start;
			this.end = end;
		}
		
		@Override
		public String toString() {
			return "(" + start + "," + end + ")";
		}

		@Override
		public int compareTo(Pair o) {
			if(this.start - ((Pair)o).start < 0)
				return -1;
			else if(this.start - ((Pair)o).start > 0)
				return 1;
			else {
				if((((Pair)o).end - this.end) < 0)
					return -1;
				else if(((Pair)o).end - this.end > 0)
					return 1;
				else return 0;
			}
		}
	}
}
