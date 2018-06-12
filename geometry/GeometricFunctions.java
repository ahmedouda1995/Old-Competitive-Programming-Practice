package geometry;

import static java.lang.Math.PI;
import static java.lang.Math.min;
import static java.lang.Math.max;
import static java.lang.Math.cos;
import static java.lang.Math.acos;
import static java.lang.Math.sin;
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import geometry.QCJ4.Circle;
import geometry.QCJ4.Point;

import java.util.ArrayList;
import java.util.Random;

public class GeometricFunctions {

	static final double EPS = 1e-9;
	static int n;
	static Point pnts[];
	static Point boundary[] = new Point[3];
	static int pntr = 0;
	
	public static void main(String[] args) {
		new Thread(null, new Runnable() {
			
			@Override
			public void run() {
				solve();
			}
		}, "ahmed", 100_000_000).start();
	}
	
	public static void solve() {
		Point p0 = new Point(0, 5);
		Point p1 = new Point(2, 5);
		Point p2 = new Point(0, 0);
		
		n = 1000_000;
		pnts = new Point[n];
		
		Random rand = new Random();
		
		for(int i = 0; i < n; ++i)
			pnts[i] = new Point(rand.nextInt(100000), rand.nextInt(100000));
		
		System.out.println(MEC().r);
	}
	
	// Heron's Formula (given three points)
	
	static double triangleArea(Point p0, Point p1, Point p2)
	{
		double a = len(p0, p1), b = len(p0, p2), c = len(p1, p2);
		double s = (a + b + c) / 2;
		return Math.sqrt(s * (s - a) * (s - b) * (s - c));
		
		// if three points on circle boundry (Triangle inside circle)
		// double radius1 = (a * b * c) / (4 * traingle area)
		// if circle inside triangle
		// double radius2 = sqrt((s - a) * (s - b) * (s - c)/s)
	}
	
	// given three medians
	
	static double triangleArea2(double m1, double m2, double m3)
	{
		// Area of triangle using medians as sides = 3/4 * (area of original triangle)
		if(m1 <= 0 || m2 <= 0 || m3 <= 0) return -1; // impossible
		double s = 0.5 * (m1 + m2 + m3);
		double mediansArea = (s * (s - m1) * (s - m2) * (s - m3));
		double area = 4.0 / 3.0 * Math.sqrt(mediansArea);
		if(mediansArea <= 0 || area <= 0) return -1; // impossible
		return area;
	}
	
	// return true if point p is in either convex/concave polygon P
	// winding number algorithm
	
	static boolean inPolygon(Point pt, Point P[])
	{
		if(P.length == 0) return false;
		double sum = 0.0; // assume the first vertex is equal to the last vertex
		
		for(int i = 0; i < P.length - 1; ++i)
		{
			if (ccw(pt, P[i], P[i+1]))
				sum += angle(P[i], pt, P[i+1]); // left turn/ccw
			else
				sum -= angle(P[i], pt, P[i+1]);
		}
		return abs(abs(sum) - 2*PI) < EPS;
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
	
	static double len(Point p0, Point p1) {
		return Math.sqrt((p0.x - p1.x) * (p0.x - p1.x) + (p0.y - p1.y) * (p0.y - p1.y));
	}
	
	static double angle(Point a, Point o, Point b)
	{ // returns angle aob in rad
		Vect oa = new Vect(o, a), ob = new Vect(o, b);
		return acos(fixAngle(oa.dot(ob) / (sqrt(oa.norm_sq()) * sqrt(ob.norm_sq()))));
	}
	
	static double fixAngle(double A)
	{
		return A > 1?1:(A < -1?-1:A);
	}
	
	static int insideCircle(Point p, Point c, double r) {
		double dx = p.x - c.x, dy = p.y - c.y;
		double Euc = dx * dx + dy * dy, rSq = r * r;
		return Euc < rSq - EPS ? 0 : Math.abs(Euc - rSq) < EPS ? 1 : 2; //inside/border/outside
	}
	
	// returns the distance from p to the line defined by
	// two points a and b (a and b must be different)
	// the closest point is stored in the 4th parameter (byref)
	
	static double distToLine(Point p, Point a, Point b)
	{
		// formula: c = a + u * ab
		Vect ap = new Vect(a, p);
		Vect ab = new Vect(a, b);
		
		double u = ap.dot(ab) / ab.norm_sq();
		ab.scale(u);							// translate a to c
		Point c = Vect.translate(a, ab);
		return len(p, c);						// Euclidean distance between p and c
	}
	
	static double distToLine2(Point p, Point a, Point b)
	{
		Vect ab = new Vect(a, b);
		Vect ap = new Vect(a, p);
		return abs(ab.cross(ap) / len(a, b));
	}
	
	// returns the distance from p to the line segment ab defined by
	// two points a and b (still OK if a == b)
	// the closest point is stored in the 4th parameter (byref)
	
	static double distToLineSegment(Point p, Point a, Point b)
	{
		Vect ap = new Vect(a, p);
		Vect ab = new Vect(a, b);
		
		double u = ap.dot(ab) / ab.norm_sq();
		
		Point c;
		
		if (u < 0.0)
		{
			c = new Point(a.x, a.y); // closer to a
			return len(p, a);		// Euclidean distance between p and a
		}
		if (u > 1.0) // Euclidean distance between p and b
		{
			c = new Point(b.x, b.y); // closer to b
			return len(p, b);
		}
		return distToLine(p, a, b);
	}
	
	// note: to accept collinear points, we have to change the ‘> 0’
	// returns true if point r is on the left side of line pq
	
	static boolean ccw(Point p, Point q, Point r)
	{
		return (new Vect(p, q)).cross(new Vect(p, r)) > 0;
	}
	
	// returns true if point r is on the same line as the line pq
	
	static boolean areCollinear(Point p, Point q, Point r)
	{
		return abs((new Vect(p, q)).cross(new Vect(p, r))) < EPS;
	}
	
	static boolean isPointOnRay(Point a, Point b, Point p)
	{
		if(!areCollinear(a, b, p))
			return false;
		Vect ab = new Vect(a, b);
		Vect ap = new Vect(a, p);
		
		return ab.dot(ap) > EPS;
	}
	
	static boolean isPointOnSegment(Point a, Point b, Point p)
	{
		return isPointOnRay(a, b, p) && isPointOnRay(b, a, p);
	}
	
	static boolean isPointOnSegment2(Point a, Point b, Point p)
	{
		return abs(len(a, b) - len(a, p) - len(b, p)) < EPS;
	}
	
	// returns the reflection of point on a line
	static Point reflectionPoint(Line l, Point p)
	{
		Point b;
		b = closestPoint(l, p); // similar to distToLine
		Vect v = new Vect(p, b); // create a vector
		Point ans = Vect.translate(Vect.translate(p, v), v); // translate p twice
		return ans;
	}

	
	private static Point closestPoint(Line l, Point p) {
		// use shortest distance code above
		return null;
	}

	static Point findCircle(Point a, Point b, Point c)
	{
		
		if(areCollinear(a, b, c)) return null;
		Point m1 = new Point((a.x + b.x) / 2.0, (a.y + b.y) / 2.0);
		Point m2 = new Point((b.x + c.x) / 2.0, (b.y + c.y) / 2.0);
		
		Vect v1, v2, pv1, pv2;
		v1 = new Vect(a, b); v2 = new Vect(c, b);
		pv1 = new Vect(v1.y, -v1.x); pv2 = new Vect(v2.y, -v2.x);
		
		Point end1 = Vect.translate(m1, pv1);
		Point end2 = Vect.translate(m2, pv2);
		
		return areIntersect(new Line(m1, end1), new Line(m2, end2));
	}
	
	// Emo welzl recursive randomize algorithm to compute minimum enclosing circle
	
	static Circle MEC() {
		shuffle();
		return minidisk(0);
	}
	
	static Circle minidisk(int i) {
		if (i == n || pntr == 3) {
			return baseCase();
		}
		else
		{
			Circle D = minidisk(i+1);
			if (len(pnts[i], new Point(D.x, D.y)) > D.r) {
	            boundary[pntr++] = pnts[i];
	            D = minidisk(i+1);
	            pntr--;
	        }
	        return D;
		}
	}
	
	static void shuffle()
	{
		Random rand = new Random();
		Point tmp;
		int idx;
		
		for(int i = 0; i < n - 1; ++i)
		{
			idx = i + rand.nextInt(n - i);
			
			tmp = pnts[idx];
			pnts[idx] = pnts[i];
			pnts[i] = tmp;
		}
	}

	static Circle baseCase() {
		if(pntr == 0)
			return new Circle(0, 0, -1);
		else if(pntr == 1)
			return new Circle(boundary[0].x, boundary[0].y, 0.0);
		else if(pntr == 2)
			return new Circle((boundary[0].x + boundary[1].x) / 2.0, (boundary[0].y + boundary[1].y) / 2.0, len(boundary[0], boundary[1]) / 2);
		else
		{
			Point p = findCircle(boundary[0], boundary[1], boundary[2]);
			return new Circle(p.x, p.y, len(p, boundary[0]));
		}
	}
	
	// return 0, 1, or 2 points
	
	static ArrayList<Point> intersectLineCircle(Point p0, Point p1, Point C, double r)
	{
		Vect p0p1 = new Vect(p0, p1);
		double a = p0p1.dot(p0p1);
		
		Vect Cp0 = new Vect(C, p0);
		double b = 2 * p0p1.dot(Cp0);
		
		double c = Cp0.dot(Cp0) - r * r;
		
		double f = b * b - 4 * a * c;
		
		ArrayList<Point> ret = new ArrayList<Point>();
		
		if(Math.abs(f - 0) < EPS) f = 0.0;
		
		if(f >= 0.0)
		{
			double t1 = (-b + sqrt(f) / (2 * a));
			double t2 = (-b - sqrt(f) / (2 * a));
			
			double x = p0.x + t1 * (p1.x - p0.x);
			double y = p0.y + t1 * (p1.y - p0.y);
			ret.add(new Point(x, y));

			if(f != 0.0)
			{
				x = p0.x + t2 * (p1.x - p0.x);
				y = p0.y + t2 * (p1.y - p0.y);
				ret.add(new Point(x, y));
			}
			
		}
		return ret;
	}
	
	static 	Point areIntersect(Line l1, Line l2)
	{
		if(l1.areParallel(l2)) return null;
		// solve system of 2 linear algebraic equations with 2 unknowns
		
		Point p = new Point(0.0, 0.0);
		
		p.x = (l2.b * l1.c - l1.b * l2.c) / (l2.a * l1.b - l1.a * l2.b);
		
		// special case: test for vertical line to avoid division by zero
		
		if (abs(l1.b) > EPS) p.y = -(l1.a * p.x + l1.c);
		else p.y = -(l2.a * p.x + l2.c);
		
		return p;
	}
	
	// ---------------------- Line class ----------------------------

	static class Line
	{
		// this linear equation has b = 1 for non vertical lines and b = 0
		// for vertical lines unless otherwise stated
		
		double a, b, c;
		
		public Line(int x, int y, int z) {
			a = x; b = y; c = z;
		}
		
		boolean areParallel(Line l) { // check coefficients a & b
			return (abs(a - l.a) < EPS) && (abs(b - l.b) < EPS); }
		
		boolean areSame(Line l) { // also check coefficient c
			return this.areParallel(l) && (abs(c - l.c) < EPS); }
		
		public Line(Point p1, Point p2)
		{
			if(abs(p1.x - p2.x) < EPS)
			{
				a = 1.0; b = 0.0; c = -p1.x; // vertical line
			}
			else
			{
				a = -(p1.y - p2.y) / (p1.x - p2.x);
				b = 1.0;				// IMPORTANT: we fix the value of b to 1.0
				c = -(a * p1.x) - p1.y;
			}
		}
	}
	
	// ---------------------- End of Line class ----------------------------
	
	// ----------------------  Point class  -------------------------
	
	static class Point implements Comparable<Point>
	{
		double x, y;
		
		public Point(double a, double b) {
			x = a;
			y = b;
		}

		boolean equals(Point p)
		{
			return abs(x - p.x) < EPS && abs(y - p.y) < EPS;
		}
		
		double dist(Point p)
		{
			return sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y));
		}
		
		// theta counter-clockwise
		
		Point rotate(double theta)
		{
			double rad = theta * PI / 180.0;
			
			return new Point(x * cos(rad) - y * sin(rad),
							 x * sin(rad) + y * cos(rad));
		}
		
		@Override
		public int compareTo(Point p) {
			if(abs(x - p.x) > EPS)
				return Double.compare(x, p.x);
			return Double.compare(y, p.y);
		}
		
		@Override
		public String toString() {
			return "(" + x + ", " + y + ")";
		}
	}
	
	// ------------------------- End of Point class -------------------------
	
	// ------------------------- Vector class -------------------------------
	
	static class Vect
	{
		double x, y;
		
		public Vect(double a, double b) {
			x = a;
			y = b;
		}
		
		public Vect(Point a, Point b) {
			x = b.x - a.x;
			y = b.y - a.y;
		}
		
		public void scale(double s)
		{
			x *= s;
			y *= s;
		}
		
		public static Point translate(Point p, Vect v)
		{
			return new Point(p.x + v.x, p.y + v.y);
		}
		
		public double dot(Vect v)
		{
			return x * v.x + y * v.y;
		}
		
		public double cross(Vect v) { return x * v.y - y * v.x; }
		
		public double norm_sq()
		{
			return x * x + y * y;
		}
	}
	
	// ------------------------- End ofVector class --------------------------
	
}
