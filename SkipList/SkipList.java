package SkipList;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;
import java.util.StringTokenizer;

public class SkipList {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner();
		PrintWriter out = new PrintWriter(System.out);

		int n, v;
		String s[];
		n = Integer.parseInt(sc.readLine());
		
		SkipList list = new SkipList();
		while(n-- > 0)
		{
			s = sc.readLine().split(" ");
			switch (s[0].charAt(0)) {
			case 'i':
				v = Integer.parseInt(s[1]);
				if(!list.find(v))
					list.insert(v);
				break;
			case 'd':
				list.delete(Integer.parseInt(s[1]));
				break;
			case 's':
				out.println(list.find(Integer.parseInt(s[1]))?"YES":"NO");
				break;
			}
			//list.print();
		}
		out.flush();
	}
	
	static final Random rand = new Random();
	Node head;
	int levels;
	
	public SkipList() {
		levels = 0;
		head = new Node(null, 0, null);
		head.down = head;
	}
	
	class Node
	{
		Integer val, level;
		Node next, down;
		
		public Node(Integer val, Integer level, Node down)
		{
			this.val = val;
			this.level = level;
			this.down = down;
			this.next = null;
		}
	}
	
	void print()
	{
		Node cur = head;
		int curLevel = cur.level;
		Node start = cur;
		
		while(curLevel-- >= 0)
		{
			System.out.println("======================");
			while(cur != null)
			{
				System.out.print("(" + cur.val + "," + cur.level + ") ");
				cur = cur.next;
			}
			System.out.println();
			System.out.println("======================");
			start = cur = start.down;
		}
	}
	
	void insert(Integer X)
	{
		int putInLevels = getLevels();
		
		Node cur = head;
		for(int i = cur.level + 1; i <= putInLevels; ++i)
			cur = new Node(null, i, cur);
		
		Node prevUp = null;
		head = cur;
		
		int curLevel = cur.level;
		
		while(curLevel >= 0)
		{
			if(curLevel > putInLevels) { cur = cur.down; curLevel--; continue; };
			while(cur.next != null && (int)cur.next.val < (int)X)
				cur = cur.next;
			
			Node newNode = new Node(X, curLevel, null);
			newNode.down = newNode;
			if(prevUp != null) prevUp.down = newNode;
			newNode.next = cur.next;
			prevUp = cur.next = newNode;
			
			cur = cur.down;
			curLevel--;
		}
	}
	
	void delete(Integer X)
	{
		Node cur = head;
		int curLevel = cur.level;
		
		while(curLevel-- >= 0)
		{
			while(cur.next != null && (int)cur.next.val < (int)X)
				cur = cur.next;
			if(cur.next != null && (int)cur.next.val == (int)X)
				cur.next = cur.next.next;
			cur = cur.down;
		}
	}
	
	boolean find(Integer  X)
	{
		Node cur = head;
		int curLevel = cur.level;
		
		while(curLevel-- >= 0)
		{
			while(cur.next != null && (int)cur.next.val <= (int)X)
				cur = cur.next;
			cur = cur.down;
		}
		return cur != null && cur.val != null && (int)cur.val == (int)X; 
	}
	
	int getLevels()
	{
		int lev = 0;
		while(lev <= levels && rand.nextInt(2) == 0)
			lev++;
		levels = Math.max(levels, lev);
		return lev;
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
