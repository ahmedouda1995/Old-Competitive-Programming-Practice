package segment_trees;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Math.max;

public class GSS3_Canyouanswerthesequeries3 {

	static int arr[];
	
	public static void main(String[] args) throws IOException {
		Reader in = new Reader("input.txt");
		PrintWriter out = new PrintWriter(System.out, true);
		int n = in.nextInt(), l, r;
		arr = new int[n];
		for(int i=0;i<n;++i)arr[i]=in.nextInt();
		SegmentTree st = new SegmentTree(arr.length);
		n = in.nextInt();
		while(n-- > 0) {
			int op = in.nextInt();
			l = in.nextInt()-1;r=in.nextInt()-1;
			if(op==1)
				out.println(st.query(l,r));
			else
				st.update(l,r+1);
		}
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
		int N;
		int [] sum, max, maxPref, maxSuf;
		
		SegmentTree(int N) {
			this.N=N;
			int sz=getSTSize(N);
			sum=new int[sz];
			max=new int[sz];
			maxPref=new int[sz];
			maxSuf=new int[sz];
			build(1,0,N-1);
		}
		private void build(int stIdx, int lo, int hi) {
			if(lo == hi) {
				sum[stIdx]=max[stIdx]=maxPref[stIdx]=maxSuf[stIdx]=arr[lo];
			}
			else {
				int mid = (lo + hi) >> 1;
				int left = (stIdx << 1), right = left + 1;
				build(left, lo, mid);
				build(right, mid + 1, hi);
				sum[stIdx] = sum[left] + sum[right];
				maxPref[stIdx] = max(maxPref[left], sum[left] + maxPref[right]);
				maxSuf[stIdx] = max(maxSuf[right], maxSuf[left] + sum[right]);
				max[stIdx] = max(max[right], max(max[left], max(maxSuf[left] + maxPref[right], max(maxPref[stIdx], maxSuf[stIdx]))));
			}
		}
		private int getSTSize(int n) {
			int sz = 1;
			while(sz<N) sz <<= 1;
			return sz << 1;
		}
		int query(int lo, int hi) {
			return query(1,0,N-1,lo,hi)[0];
		}

		private int[] query(int stIdx, int left, int right, int lo, int hi) {
			if(left == lo && right == hi)
				return new int[]{max[stIdx], maxPref[stIdx], maxSuf[stIdx], sum[stIdx]};
			int mid = (left + right) >> 1;
			if(lo > mid)
				return query((stIdx << 1) + 1, mid + 1, right, lo, hi);
			if(hi <= mid)
				return query((stIdx << 1), left, mid, lo, hi);
			int[] first = query((stIdx << 1), left, mid, lo, mid);
			int[] sec = query((stIdx << 1) + 1, mid + 1, right, mid + 1, hi);
			int total, pre, suf, maximum;
			total = first[3] + sec[3];
			pre = max(first[1], first[3] + sec[1]);
			suf = max(sec[2], first[2] + sec[3]);
			maximum = max(first[0], max(sec[0], max(first[2] + sec[1], max(pre, suf))));
			return new int[]{maximum, pre, suf, total};
		}
		void update(int index, int val) {
			update(1, 0, N - 1, index, val);
		}
		private void update(int stIdx, int lo, int hi, int index, int val) {
			if(lo == hi) {
				sum[stIdx] = max[stIdx] = maxPref[stIdx] = maxSuf[stIdx] = val;
			}
			else {
				int mid = (lo + hi) >> 1;
				int left = (stIdx << 1), right = left + 1;
				if(index > mid)
					update(right, mid + 1, hi, index, val);
				else
					update(left, lo, mid, index, val);
				sum[stIdx] = sum[left] + sum[right];
				maxPref[stIdx] = max(maxPref[left], sum[left] + maxPref[right]);
				maxSuf[stIdx] = max(maxSuf[right], maxSuf[left] + sum[right]);
				max[stIdx] = max(max[right], max(max[left], max(maxSuf[left] + maxPref[right], max(maxPref[stIdx], maxSuf[stIdx]))));
			}
		}
	}
}