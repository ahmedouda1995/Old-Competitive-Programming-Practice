package segment_trees;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class MULTQ3 {
	
	public static void main(String[] args) throws IOException {
		//Scanner sc = new Scanner(new FileReader("input.txt"));
		Reader sc = new Reader();
		PrintWriter out = new PrintWriter(System.out);

		int q;
		
		SegmentTree st = new SegmentTree(sc.nextInt());
		q = sc.nextInt();
		
		int op, l, r;
		
		while(q-- > 0)
		{
			op = sc.nextInt();
			l = sc.nextInt();
			r = sc.nextInt();
			
			if(op == 0)
				st.update(l, r);
			else
				out.println(st.query(l, r));
		}
		
		out.flush();
		out.close();
	}
	
	static class SegmentTree
	{
		static int tree[][];
		static int lazy[];
		static int N;
		
//		static class Node
//		{
//			int rem0, rem1, rem2;
//			
//			public Node(int a, int b, int c) {
//				rem0 = a; rem1 = b; rem2 = c;
//			}
//		}
		
		public SegmentTree(int n) {
			N = n;
			int sz = getSZ(n);
			tree = new int[sz][3];
			lazy = new int[sz];
			build(1, 0, N - 1);
		}

		void build(int stIdx, int lo, int hi) {
			if(lo == hi) tree[stIdx][0] = 1;
			else
			{
				int mid = ((lo + hi) >> 1);
				int left = (stIdx << 1);
				int right = (left | 1);
				build(left, lo, mid);
				build(right, mid + 1, hi);
				
				tree[stIdx][0] = tree[left][0] + tree[right][0];
				tree[stIdx][1] = tree[left][1] + tree[right][1];
				tree[stIdx][2] = tree[left][2] + tree[right][2];
			}
		}

		void propagate(int stIdx, int b, int mid, int e)		
		{
			lazy[stIdx<<1] = (lazy[stIdx<<1] + lazy[stIdx]);
			if(lazy[stIdx<<1] >= 3) lazy[stIdx<<1] -= 3;
			lazy[stIdx<<1|1] = (lazy[stIdx<<1|1] + lazy[stIdx]);
			if(lazy[stIdx<<1|1] >= 3) lazy[stIdx<<1|1] -= 3;
			
			int left = stIdx<<1;
			int right = left | 1;
			if(lazy[stIdx] == 1)
			{
				int tmp = tree[left][2];
				tree[left][2] = tree[left][1];
				tree[left][1] = tree[left][0];
				tree[left][0] = tmp;
			}
			else if(lazy[stIdx] == 2)
			{
				int tmp = tree[left][0];
				tree[left][0] = tree[left][1];
				tree[left][1] = tree[left][2];
				tree[left][2] = tmp;
			}
			
			if(lazy[stIdx] == 1)
			{
				int tmp = tree[right][2];
				tree[right][2] = tree[right][1];
				tree[right][1] = tree[right][0];
				tree[right][0] = tmp;
			}
			else if(lazy[stIdx] == 2)
			{
				int tmp = tree[right][0];
				tree[right][0] = tree[right][1];
				tree[right][1] = tree[right][2];
				tree[right][2] = tmp;
			}
			lazy[stIdx] = 0;
		}
		
		void update(int lo, int hi)
		{
			update(1, 0, N - 1, lo, hi);
		}
		
		void update(int stIdx, int L, int R, int lo, int hi) {
			if(L > hi || R < lo) return;
			if(L >= lo && R <= hi)
			{
				int tmp = tree[stIdx][2];
				tree[stIdx][2] = tree[stIdx][1];
				tree[stIdx][1] = tree[stIdx][0];
				tree[stIdx][0] = tmp;
				lazy[stIdx] = (lazy[stIdx] + 1);
				if(lazy[stIdx] >= 3)
					lazy[stIdx] -= 3;
			}
			else
			{
				int mid = L + R >> 1;
				propagate(stIdx, L, mid, R);
				int left = stIdx << 1;
				int right = left | 1;
				update(left, L, mid, lo, hi);
				update(right, mid + 1, R, lo, hi);
				
				tree[stIdx][0] = tree[left][0] + tree[right][0];
				tree[stIdx][1] = tree[left][1] + tree[right][1];
				tree[stIdx][2] = tree[left][2] + tree[right][2];
			}
		}

		int query(int lo, int hi)
		{
			return query(1, 0, N - 1, lo, hi);
		}
		
		int query(int stIdx, int L, int R, int lo, int hi) {
			if(L > hi || R < lo) return 0;
			if(L >= lo && R <= hi) return tree[stIdx][0];
			int mid = ((L + R) >> 1);
			propagate(stIdx, L, mid, R);
			int left = (stIdx << 1);
			int right = (left | 1);
			
			int a = query(left, L, mid, lo, hi);
			int b = query(right, mid + 1, R, lo, hi);
			
			return a + b;
		}

		int getSZ(int n) {
			int sz = 1;
			
			while(sz < n)
				sz <<= 1;
			return (sz << 1);
		}
	}
	
	static class Reader {
	    final private int BUFFER_SIZE = 1 << 16;private DataInputStream din;private byte[] buffer;private int bufferPointer, bytesRead;
	    public Reader(){din=new DataInputStream(System.in);buffer=new byte[BUFFER_SIZE];bufferPointer=bytesRead=0;
	    }public Reader(String file_name) throws IOException{din=new DataInputStream(new FileInputStream(file_name));buffer=new byte[BUFFER_SIZE];bufferPointer=bytesRead=0;
	    }public String readLine() throws IOException{byte[] buf=new byte[64];int cnt=0,c;while((c=read())!=-1){if(c==10)break;buf[cnt++]=(byte)c;}return new String(buf,0,cnt - 1);
	    }public int nextInt() throws IOException{int ret=0;byte c=read();while(c<=' ')c=read();boolean neg=(c=='-');if(neg)c=read();do{ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');if(neg)return -ret;return ret;
	    }public long nextLong() throws IOException{long ret=0;byte c=read();while(c<=' ')c=read();boolean neg=(c=='-');if(neg)c=read();do{ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');if(neg)return -ret;return ret;
	    }public double nextDouble() throws IOException{double ret=0,div=1;byte c=read();while(c<=' ')c=read();boolean neg=(c=='-');if(neg)c = read();do {ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');if(c=='.')while((c=read())>='0'&&c<='9')ret+=(c-'0')/(div*=10);if(neg)return -ret;return ret;
	    }private void fillBuffer() throws IOException{bytesRead=din.read(buffer,bufferPointer=0,BUFFER_SIZE);if(bytesRead==-1)buffer[0]=-1;
	    }private byte read() throws IOException{if(bufferPointer==bytesRead)fillBuffer();return buffer[bufferPointer++];
	    }public void close() throws IOException{if(din==null) return;din.close();}
	}
}
