import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CodeJam5 {
	
	static int n;
	static char grid[][];
	static char orig[][];
	static char fig[] = {'+', 'x', 'o'};
	static int sc[] = {1, 1, 2};
	static int [] rows, cols, diagsL, diagsR;
	static int [][] L,R;
	static int mx=0;
	static ArrayList<tri> arr=new ArrayList<tri>();
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("bath.out");

		int t = sc.nextInt(), k, cases = 1;
		char ch;
		
		while(t-- > 0) {
			n = sc.nextInt();
			k = sc.nextInt();
			int a, b;
			arr.clear();
			L=new int [n][n];
			R=new int [n][n];
			
			rows = new int[n];
			cols = new int[n];
			diagsL = new int[n << 1];
			diagsR = new int[n << 1];
			
			orig = new char[n][n];
			for(int i = 0; i < n; ++i) Arrays.fill(orig[i], '.');
			grid = new char[n][n];
			for(int i = 0; i < n; ++i) Arrays.fill(grid[i], '.');
			
			for (int i=0;i<n;i++){
				for (int j=0;j<n;j++){
					a=i;b=j;
					while (inbounds(a,b)){
						a--;b++;
					}
					a++;b--;
					if (a==0)R[i][j]=b;
					else{
						R[i][j]=n+a-1;
					}
					a=i;b=j;
					while (inbounds(a,b)){
						a--;b--;
					}
					a++;b++;
					if (a==0)L[i][j]=b;
					else{
						L[i][j]=n+a-1;
					}
				}
			}
			
			for(int i = 0; i < k; ++i) {
				ch = sc.next().charAt(0);
				a = sc.nextInt() - 1;
				b = sc.nextInt() - 1;
				orig[a][b] = ch;
				grid[a][b] = ch;
				switch (ch) {
				case '+': diagsL[getDiagIdxL(a, b)]++;
						  diagsR[getDiagIdxR(a, b)]++;
				break;
				case 'x':
				rows[a]++; cols[b]++;
				break;
				case 'o':
					diagsL[getDiagIdxL(a, b)]++;
					diagsR[getDiagIdxR(a, b)]++;
					rows[a]++; cols[b]++;	
				break;
				}
			}
			
			mx=0;
			solve(0, 0,0);
			out.printf("Case #%d: %d %d\n", cases++, mx, arr.size());
			for(tri tr : arr) {
				out.println(tr.ch + " " + tr.x + " " + tr.y);
			}
		}
		
		out.flush();
		out.close();
	}
	static class tri{
		int x;int y;char ch;
	  
		public tri(int a, int b, char c) {
			x = a; y = b; ch = c;
		}
	}
	
	private static void  solve(int x, int y,int res) {
		if(x == n && y == 0) {
			
			if (res>mx){
				mx=res;
				arr.clear();
				for (int i=0;i<n;i++){
					for (int j=0;j<n;j++){
						if (grid[i][j]!=orig[i][j]){
							arr.add(new tri(i+1,j+1,grid[i][j]));						}
					}
				}
				
			}
			return ;
		}
		
		int nextX, nextY;
		if(y == n - 1) { nextX = x + 1; nextY = 0; }
		else {
			nextX = x; nextY = y + 1;
		}
		
		if(grid[x][y] == 'o'){
			solve(nextX, nextY, res + 2);
			return ;
		}
		if (grid[x][y] != '.'){
			switch (grid[x][y]) {
			case '+': diagsL[getDiagIdxL(x, y)]--;
					  diagsR[getDiagIdxR(x, y)]--;
			break;
			case 'x':
			rows[x]--; cols[y]--;
			break;
			}
			
            if (isValid(x,y,'o')){
            	diagsL[getDiagIdxL(x, y)]++;
				diagsR[getDiagIdxR(x, y)]++;
				rows[x]++; cols[y]++;	
				grid[x][y]='o';
				 solve(nextX, nextY,res+2);
				 diagsL[getDiagIdxL(x, y)]--;
					diagsR[getDiagIdxR(x, y)]--;
					rows[x]--; cols[y]--;	
					grid[x][y]=orig[x][y];
					switch (grid[x][y]) {
					case '+': diagsL[getDiagIdxL(x, y)]++;
							  diagsR[getDiagIdxR(x, y)]++;
					break;
					case 'x':
					rows[x]++; cols[y]++;
					break;
					}
					solve(nextX, nextY,res+1);
					return;
			}
            switch (grid[x][y]) {
			case '+': diagsL[getDiagIdxL(x, y)]++;
					  diagsR[getDiagIdxR(x, y)]++;
			break;
			case 'x':
			rows[x]++; cols[y]++;
			break;
			}
			solve(nextX, nextY,res+1);
			return;
		}
		
		boolean f=true;
		for(int i = 0; i < 3; ++i) {
			if(isValid(x, y, fig[i])) {
				f=false;
				grid[x][y] = fig[i];
				switch (fig[i]) {
				case '+': diagsL[getDiagIdxL(x, y)]++;
						  diagsR[getDiagIdxR(x, y)]++;
				break;
				case 'x':
				rows[x]++; cols[y]++;
				break;
				case 'o':
					diagsL[getDiagIdxL(x, y)]++;
					diagsR[getDiagIdxR(x, y)]++;
					rows[x]++; cols[y]++;	
				break;
				}
				 solve(nextX, nextY,res+sc[i]);
				grid[x][y] = '.';
				switch (fig[i]) {
				case '+': diagsL[getDiagIdxL(x, y)]--;
						  diagsR[getDiagIdxR(x, y)]--;
				break;
				case 'x':
				rows[x]--; cols[y]--;
				break;
				case 'o':
					diagsL[getDiagIdxL(x, y)]--;
					diagsR[getDiagIdxR(x, y)]--;
					rows[x]--; cols[y]--;	
				break;
				}
			}
		}
		if(f)
			solve(nextX, nextY,res);
	}
	
	static int getDiagIdxL(int x, int y) {
		return L[x][y];
	}
	
	static int getDiagIdxR(int x, int y) {
	   return R[x][y];	
	}
	
	static boolean isValid(int x, int y, char c) {
		switch (c) {
		case '+': return diagsL[getDiagIdxL(x, y)] == 0 && diagsR[getDiagIdxR(x, y)] == 0;
		case 'x': return rows[x] == 0 && cols[y] == 0;
		case 'o': return (diagsL[getDiagIdxL(x, y)] == 0 && diagsR[getDiagIdxR(x, y)] == 0)
				&& (rows[x] == 0 && cols[y] == 0);
		default: return true;
		}
	}

	static boolean inbounds(int x, int y) {
		return x >= 0 && y >= 0 && x < n && y < n;
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