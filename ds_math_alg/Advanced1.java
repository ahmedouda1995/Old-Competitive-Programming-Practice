package ds_math_alg;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Advanced1 {
	
	static int n, m;
	static final int A = 0, B = 1, C = 2, MAX = (int) 1e5;
	static final int D = 3, E = 4, F = 5, G = 6;
	static long[][] arr;
	static long chips[], MOD;
	static long [][] constant = new long[3][3];
	static long INF = (long) 1e18;
	static long magic;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner();
		PrintWriter out = new PrintWriter(System.out);
		
		m = sc.nextInt();
		n = sc.nextInt();
		
		arr = new long[n][7];
		chips = new long[n];
		
		for(int i = 0; i < n; ++i)
			for(int j = 0; j < 7; ++j)
				arr[i][j] = sc.nextInt();
		
		int operations;
		long mat[][] = new long[2][2];
		long val[][] = new long[2][1];
		int tmp;
		
		for(int i = 0; i < n; ++i)
		{
			operations = (int) arr[i][C];
			tmp = (operations % 2 == 0)?0:1;
			val[0][0] = arr[i][E]; val[1][0] = arr[i][B];
			
			mat[0][0] = 1;
			mat[0][1] = 0;
			mat[1][0] = 1;
			mat[1][1] = arr[i][D] * arr[i][D];
			
			MOD = INF / arr[i][F] * arr[i][F];
			magic = arr[i][F];
			chips[i] = Matrix.mult(Matrix.matExp(mat, (operations - tmp) >> 1), val)[1][0] % arr[i][F];
			
			if(tmp == 1)
				chips[i] = (arr[i][D] * chips[i]) % arr[i][F];
		}
		
		int segIn[] = new int[MAX + 1];
		Arrays.fill(segIn, -1);
		SegmentTree st = new SegmentTree(segIn);
		st.updatePoint(m, 0);
		
		long q;
		for(int i = 0; i < n; ++i)
		{
			q = st.query((int) arr[i][A], MAX);
			if(q != -1)
				st.updatePoint((int) chips[i], q + arr[i][G]);
		}

		out.println(st.query(0, MAX));
		out.flush();
		out.close();
	}
	
	static class SegmentTree {

		int n, arr[];
		long sTree[];
		
		public SegmentTree(int in[]) {
			n = in.length; arr = in;
			int sz = getSz(n);
			sTree = new long[sz];
			build(1, 0, n - 1);
		}
		
		private int getSz(int n) {
			int sz = 1;
			while(sz < n) sz <<= 1;
			return sz <<= 1;
		}

		void build(int node, int b, int e) {
			if(b == e)
				sTree[node] = arr[b];
			else
			{
				int mid = (b + e >> 1);
				build(node << 1, b, mid);
				build(node << 1 | 1, mid + 1, e);
				sTree[node] = Math.max(sTree[node << 1], sTree[node << 1 | 1]);
			}
		}
		
		void updatePoint(int idx, long val)
		{
			updatePoint(1, idx, 0, n - 1, val);
		}

		void updatePoint(int node, int idx, int b, int e, long val) {
			if(b == e) sTree[node] = Math.max(sTree[node], val);
			else
			{
				int mid = (b + e >> 1);
				if(idx <= mid)
					updatePoint(node << 1, idx, b, mid, val);
				else
					updatePoint(node << 1 | 1, idx, mid + 1, e, val);
				sTree[node] = Math.max(sTree[node << 1], sTree[node << 1 | 1]);
			}
		}
		
		long query(int lo, int hi)
		{
			return query(1, 0, n - 1, lo, hi);
		}

		private long query(int node, int b, int e, int lo, int hi) {
			if(e < lo || b > hi) return -1;
			if(b >= lo && e <= hi)
				return sTree[node];
			else
			{
				int mid = (b + e >> 1);
				long left = query(node << 1, b, mid, lo, hi);
				long right = query(node << 1 | 1, mid + 1, e, lo, hi);
				return Math.max(left, right);
			}
		}
	}
	
	static class Matrix {
		
		private static long[][] matExp(long[][] mat, int p) {
			if(p == 0)
				return identity(mat.length);
			
			long [][] ret = matExp(mat, p >> 1);
			ret = mult(ret, ret);
			
			if((p & 1) == 1)
				ret = mult(ret, mat);
			
			return ret;
		}
		
		static long[][] add(long [][] a, long [][] b)
		{
			long c[][] = new long[a.length][a[0].length];
			
			for(int i = 0; i < a.length; ++i)
				for(int j = 0; j < a[0].length; ++j)
					c[i][j] = a[i][j] + b[i][j];
			
			return c;
		}

		private static long[][] identity(int n) {
			
			long ret[][] = new long[n][n];
			
			for(int i = 0; i < n; ++i) ret[i][i] = 1;
			
			return ret;
		}

		static void addIdentity(long [][] mat)
		{
			for(int i = 0; i < mat.length; ++i) mat[i][i]++;
		}
		
		static long[][] multSpec(long [][] a, long [][] b)
		{
			constant[0][0] = a[0][0] * b[0][0];
			constant[0][2] = (a[0][0] * b[0][2] + a[0][2] * b[2][2]);
			constant[1][0] = a[1][0] * b[0][0];
			constant[1][2] = a[1][0] * b[0][2];
			constant[2][2] = a[2][2] * b[2][2];
			constant[0][0] %= magic;
			constant[0][2] %= magic;
			constant[1][0] %= magic;
			constant[1][2] %= magic;
			constant[2][2] %= magic;
			return constant;
		}
		
		static long[][] mult(long [][] a, long [][] b)
		{
			int n = a.length;
			int m = b[0].length;
			long [][] c = new long[n][m];
			
			// Optimizations => switch loop order if matrices are sparse
			// could make it n ^ 2 by checking if a[i][k] = 0 or b[k][j] = 0
			
			for(int i = 0; i < n; ++i)
				for(int j = 0; j < m; ++j)
					for(int k = 0; k < b.length; ++k)
						c[i][j] = (c[i][j] + a[i][k] * b[k][j] % magic) % magic;
			
			return c;
		}
	}

	
	static class Scanner {
	    final private int BUFFER_SIZE = 1 << 16;private DataInputStream din;private byte[] buffer;private int bufferPointer, bytesRead;
	    public Scanner(){din=new DataInputStream(System.in);buffer=new byte[BUFFER_SIZE];bufferPointer=bytesRead=0;
	    }public Scanner(String file_name) throws IOException{din=new DataInputStream(new FileInputStream(file_name));buffer=new byte[BUFFER_SIZE];bufferPointer=bytesRead=0;
	    }public String readLine() throws IOException{byte[] buf=new byte[64];int cnt=0,c;while((c=read())!=-1){if(c==10)break;buf[cnt++]=(byte)c;}return new String(buf,0,cnt - 1);
	    }public int nextInt() throws IOException{int ret=0;byte c=read();while(c<=' ')c=read();boolean neg=(c=='-');if(neg)c=read();do{ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');if(neg)return -ret;return ret;
	    }public long nextLong() throws IOException{long ret=0;byte c=read();while(c<=' ')c=read();boolean neg=(c=='-');if(neg)c=read();do{ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');if(neg)return -ret;return ret;
	    }public double nextDouble() throws IOException{double ret=0,div=1;byte c=read();while(c<=' ')c=read();boolean neg=(c=='-');if(neg)c = read();do {ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');if(c=='.')while((c=read())>='0'&&c<='9')ret+=(c-'0')/(div*=10);if(neg)return -ret;return ret;
	    }private void fillBuffer() throws IOException{bytesRead=din.read(buffer,bufferPointer=0,BUFFER_SIZE);if(bytesRead==-1)buffer[0]=-1;
	    }private byte read() throws IOException{if(bufferPointer==bytesRead)fillBuffer();return buffer[bufferPointer++];
	    }public void close() throws IOException{if(din==null) return;din.close();}
	}
}