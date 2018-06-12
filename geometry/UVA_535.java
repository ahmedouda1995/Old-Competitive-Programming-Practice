package geometry;

import static java.lang.Math.PI;
import static java.lang.Math.acos;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.TreeMap;

public class UVA_535 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		TreeMap<String, Pair> map = new TreeMap<String, Pair>();
		
		String s;
		String in[];
		
		while(!(s = br.readLine()).equals("#"))
		{
			in = s.split(" ");
			map.put(in[0], new Pair(Double.parseDouble(in[1]), Double.parseDouble(in[2])));
		}
		
		while(!(s = br.readLine()).equals("# #"))
		{
			in = s.split(" ");
			out.println(in[0] + " - " + in[1]);
			Pair a, b;
			a = map.get(in[0]);
			b = map.get(in[1]);
			if(a == null || b == null)
				out.println("Unknown");
			else
				out.printf("%d km\n",
						Math.round(greatCircleDistance(a.a, a.b, b.a, b.b, 6378)));
		}
		
		out.flush();
		out.close();
	}
	
	static double greatCircleDistance(double pLat, double pLong, double qLat, double qLong, double radius)
	{
		pLat *= PI / 180;
		pLong *= PI / 180;
		qLat *= PI / 180;
		qLong *= PI / 180;
		
		return radius * acos(cos(pLat)*cos(pLong)*cos(qLat)*cos(qLong) +
				cos(pLat)*sin(pLong)*cos(qLat)*sin(qLong) +
				sin(pLat)*sin(qLat));
	}
	
	static class Pair
	{
		double a, b;
		
		public Pair(double x, double y) {
			a = x;
			b = y;
		}
	}
}
