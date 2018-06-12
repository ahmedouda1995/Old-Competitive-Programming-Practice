package geometry;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SPOJ_ALIEN {

	public static void main(String[] args) throws IOException {
		Reader sc = new Reader();
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		int stations, maxPeople;
		int arr[];
		
		while(t-- > 0)
		{
			stations = sc.nextInt();
			maxPeople = sc.nextInt();
			
			arr = new int[stations];
			
			for(int i = 0; i < stations; ++i) arr[i] = sc.nextInt();
			
			int i = 0, j = 0;
			
			int sum = 0;
			int maxStations = 0;
			int minPeople = 100_000_000;
			
			while(j < stations)
			{
				if(sum + arr[j] <= maxPeople)
				{
					sum += arr[j];
					
					if(j - i + 1 == maxStations && sum <= maxPeople && sum < minPeople)
						minPeople = sum;
					else if(j - i + 1 > maxStations && sum <= maxPeople)
					{
						maxStations = j - i + 1;
						minPeople = sum;
					}
					
					j++;
				}
				else if(j == i)
				{
					sum = 0;
					i++;
					j++;
				}
				else
				{
					sum -= arr[i];
					i++;
				}
			}
			if(minPeople == 100_000_000)
				out.println("0 0");
			else
				out.println(minPeople + " " + maxStations);
		}
		
		
		out.flush();
		out.close();
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
