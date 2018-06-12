package ch2;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_12086_Potentiometer {

	public static void main(String[] args) throws IOException {
		Reader in = new Reader("input.txt");
		PrintWriter out = new PrintWriter(System.out);

		int N, arr[], cases = 1;
		boolean first = true;
		
		while((N = Integer.parseInt(in.readLine().trim())) != 0) {
			if(first) first = false; else out.println();
			arr = new int[N];
			for(int i = 0; i < N; ++i) arr[i] = Integer.parseInt(in.readLine().trim());
			
			SegmentTree segTree = new SegmentTree(arr, N);
			
			String s;
			StringTokenizer st;
			
			out.printf("Case %d:\n", cases++);
			while(!(s = in.readLine()).trim().equals("END")) {
				st = new StringTokenizer(s.trim());
				if(st.nextToken().charAt(0) == 'M') {
					int left = Integer.parseInt(st.nextToken()) - 1;
					int right = Integer.parseInt(st.nextToken()) - 1;
					out.println(segTree.rsq(left, right));
				}
				else {
					int index = Integer.parseInt(st.nextToken()) - 1;
					int val = Integer.parseInt(st.nextToken());
					segTree.update(index, val);
				}
			}
		}

		out.flush();
		out.close();
	}
	
	static class Reader {
	    final private int BUFFER_SIZE = 1 << 16;private DataInputStream din;private byte[] buffer;private int bufferPointer, bytesRead;
	    public Reader(){din=new DataInputStream(System.in);buffer=new byte[BUFFER_SIZE];bufferPointer=bytesRead=0;
	    }public Reader(String file_name) throws IOException{din=new DataInputStream(new FileInputStream(file_name));buffer=new byte[BUFFER_SIZE];bufferPointer=bytesRead=0;
	    }public String readLine() throws IOException{byte[] buf=new byte[64];int cnt=0,c;while((c=read())!=-1){if(c==10)break;buf[cnt++]=(byte)c;}return new String(buf,0,cnt);
	    }public int nextInt() throws IOException{int ret=0;byte c=read();while(c<=' ')c=read();boolean neg=(c=='-');if(neg)c=read();do{ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');if(neg)return -ret;return ret;
	    }public long nextLong() throws IOException{long ret=0;byte c=read();while(c<=' ')c=read();boolean neg=(c=='-');if(neg)c=read();do{ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');if(neg)return -ret;return ret;
	    }public double nextDouble() throws IOException{double ret=0,div=1;byte c=read();while(c<=' ')c=read();boolean neg=(c=='-');if(neg)c = read();do {ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');if(c=='.')while((c=read())>='0'&&c<='9')ret+=(c-'0')/(div*=10);if(neg)return -ret;return ret;
	    }private void fillBuffer() throws IOException{bytesRead=din.read(buffer,bufferPointer=0,BUFFER_SIZE);if(bytesRead==-1)buffer[0]=-1;
	    }private byte read() throws IOException{if(bufferPointer==bytesRead)fillBuffer();return buffer[bufferPointer++];
	    }public void close() throws IOException{if(din==null) return;din.close();}
	}
	
	static class SegmentTree {
		int nodes[];
		int N;
		
		public SegmentTree(int arr[], int N) {
			this.N = N;
			nodes = new int[getSTSize(N)];
			build(arr, 1, 0, N - 1);
		}
		
		int getSTSize(int N) {
			int sz = 1;
			while(sz < N) sz <<= 1;
			return sz << 1;
		}
		
		void build(int arr[], int stIdx, int lo, int hi) {
			if(lo == hi) {
				nodes[stIdx] = arr[lo];
			}
			else {
				int mid = lo + (hi - lo) / 2;
				int left = (stIdx << 1), right = left + 1;
				build(arr, left, lo, mid);
				build(arr, right, mid + 1, hi);
				nodes[stIdx] = nodes[left] + nodes[right];
			}
		}
		
		int rsq(int lo, int hi) {
			return rsq(1, 0, N - 1, lo, hi);
		}
		
		private int rsq(int stIdx, int left, int right, int lo, int hi) {
			if(left == lo && right == hi) return nodes[stIdx];
			
			int mid = left + (right - left) / 2;
			
			if(lo > mid)
				return rsq((stIdx << 1) + 1, mid + 1, right, lo, hi);
			if(hi <= mid)
				return rsq((stIdx << 1), left, mid, lo, hi);
			
			int leftRes = rsq((stIdx << 1), left, mid, lo, mid);
			int rightRes = rsq((stIdx << 1) + 1, mid + 1, right, mid + 1, hi);
			return leftRes + rightRes;
		}

		void update(int index, int val) {
			update(1, 0, N - 1, index, val);
		}
		
		private void update(int stIdx, int left, int right, int index, int val) {
			if(left == right) nodes[stIdx] = val;
			else {
				int mid = left + (right - left) / 2;
				
				if(index > mid)
					update((stIdx << 1) + 1, mid + 1, right, index, val);
				else if(index <= mid)
					update((stIdx << 1), left, mid, index, val);
				
				nodes[stIdx] = nodes[(stIdx << 1)] +  nodes[(stIdx << 1) + 1];
			}
		}
	}
}