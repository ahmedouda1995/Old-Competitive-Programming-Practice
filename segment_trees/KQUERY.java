package segment_trees;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class KQUERY {

	static int arr[];
	
	public static void main(String[] args) throws IOException {
		//Scanner sc = new Scanner(new FileReader("input.txt"));
		Reader sc = new Reader();
		PrintWriter out = new PrintWriter(System.out);

		int n = sc.nextInt();
		arr = new int[n];
		for(int i = 0; i < n; ++i) arr[i] = sc.nextInt();
		
		int q = sc.nextInt();
		
		int l, r, k;
		SegmentTree st = new SegmentTree(n);
		
		while(q-- > 0)
		{
			l = sc.nextInt() - 1;
			r = sc.nextInt() - 1;
			k = sc.nextInt();
			
			out.println(st.query(l ,r, k));
		}
		
		out.flush();
		out.close();
	}
	
	static class SegmentTree
	{
		static int tree[][];
		static int N;
		
		public SegmentTree(int n) {
			N = n;
			tree = new int[getSz(n)][];
			build(1, 0, N - 1);
		}
		
		public int query(int l, int r, int k) {
			return query(1, 0, N - 1, l, r, k);
		}

		private int query(int stIdx, int L, int R, int lo, int hi, int k) {
			if(L > hi || R < lo) return 0;
			if(L >= lo && R <= hi)
			{
				int LO = 0, HI = tree[stIdx].length - 1;
				int ans = -1;
				
				while(LO <= HI)
				{
					int mid = (LO + HI >> 1);
					if(tree[stIdx][mid] > k)
					{
						ans = mid;
						HI = mid - 1;
					}
					else
						LO = mid + 1;
				}
				
				return ans == -1?0:tree[stIdx].length - ans;
			}
			else
			{
				int mid = ((L + R) >> 1);
				int left = (stIdx << 1);
				int right = left | 1;
				
				return query(left, L, mid, lo, hi, k) + query(right, mid + 1, R, lo, hi, k);
			}
		}

		private void build(int stIdx, int lo, int hi) {
			if(lo == hi) tree[stIdx] = new int[]{arr[lo]};
			else
			{
				int mid = (lo + hi >> 1);
				int left = stIdx << 1;
				int right = left | 1;
				build(left, lo, mid);
				build(right, mid + 1, hi);
				
				tree[stIdx] = new int[tree[left].length + tree[right].length];
				
				int i, j, k = 0;
				
				for(i = j = 0; i < tree[left].length || j < tree[right].length;)
				{
					if(i == tree[left].length) tree[stIdx][k++] = tree[right][j++];
					else if(j == tree[right].length) tree[stIdx][k++] = tree[left][i++];
					else if(tree[left][i] < tree[right][j])
						tree[stIdx][k++] = tree[left][i++];
					else
						tree[stIdx][k++] = tree[right][j++];
				}
			}
		}

		static int getSz(int n)
		{
			int sz = 1;
			
			while(sz < n) sz <<= 1;
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
