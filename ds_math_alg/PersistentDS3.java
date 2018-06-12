package ds_math_alg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class PersistentDS3 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int n = sc.nextInt(), noFiles;
		TreeMap<String, String> parent = new TreeMap<>();
		TreeMap<String, Node> map = new TreeMap<>();
		String ch, pa, rootDir;
		Node root;
		ch = sc.next(); rootDir = pa = sc.next();
		noFiles = sc.nextInt();
		parent.put(ch, pa);
		if(!map.containsKey(pa))
			map.put(pa, new Node(0));
		if(!map.containsKey(ch))
			map.put(ch, new Node(noFiles));
		map.get(pa).children.put(ch, map.get(ch));
		root = map.get(pa);
		n--;
		while(n-- > 0)
		{
			ch = sc.next(); pa = sc.next();
			noFiles = sc.nextInt();
			parent.put(ch, pa);
			if(!map.containsKey(ch))
				map.put(ch, new Node(noFiles));
			map.get(pa).children.put(ch, map.get(ch));
		}
		PersistentTree ps = new PersistentTree(rootDir, root, parent);
		n = sc.nextInt();
		while(n-- > 0)
		{
			switch (sc.next().charAt(0)) {
			case 'q': out.println(ps.query(sc.next())); break;
			case 'd': ps.update(sc.next(), -1); break;
			case 'c': ps.checkout(sc.nextInt()); break;
			case 'a': ps.update(sc.next(), 1); break;
			}
		}
		
		out.flush();
	}
	
	static class PersistentTree
	{
		String rootDir;
		ArrayList<Node> versions;
		TreeMap<String, String> parent;
		int versionCount, curVersion;
		
		public PersistentTree(String rD, Node root, TreeMap<String, String> pa) {
			versions = new ArrayList<>();
			versions.add(root);
			parent = pa;
			rootDir = rD;
			prepare(versions.get(0));
			versionCount = 1;
			curVersion = 0;
		}
		
		public void update(String file, int diff) {
			LinkedList<String> path = getFilePath(file);
//			Node cur = versions.get(curVersion);
//			for(String s : path)
//				cur = cur.children.get(s);
			versions.add(update(path.toArray(new String[path.size()]), 0, versions.get(curVersion), diff));
			curVersion = versionCount++;
		}

		private Node update(String path[], int idx, Node cur, int diff) {
			if(idx == path.length)
			{
				Node newNode = new Node(cur.sz + diff);
				newNode.subtreeSz = cur.subtreeSz + diff;
				newNode.children = new TreeMap<>(cur.children);
				return newNode;
			}
			Node child = update(path, idx + 1, cur.children.get(path[idx]), diff);
			Node newNode = new Node(cur.sz + diff);
			newNode.subtreeSz = cur.subtreeSz + diff;
			newNode.children = new TreeMap<>(cur.children);
			newNode.children.put(path[idx], child);
			return newNode;
		}

		int prepare(Node node) {
			int res = node.sz;
			for(Entry<String, Node> ch : node.children.entrySet())
				res += prepare(ch.getValue());
			return node.subtreeSz = res;
		}
		
		void checkout(int v)
		{
			curVersion = v;
		}
		
		int query(String file)
		{
			LinkedList<String> path = getFilePath(file);
			Node cur = versions.get(curVersion);
			for(String s : path)
				cur = cur.children.get(s);
			return cur.subtreeSz;
		}

		LinkedList<String> getFilePath(String file)
		{
			LinkedList<String> path = new LinkedList<>();
			while(!file.equals(rootDir))
			{
				path.addFirst(file);
				file = parent.get(file);
			}
			return path;
		}
	}
	
	static class Node
	{
		int sz, subtreeSz;
		TreeMap<String, Node> children;
		
		public Node(int n) {
			children = new TreeMap<>();
			sz = n;
		}
	}
	
	static class Scanner {
		BufferedReader br;
		StringTokenizer st;

		public Scanner(InputStream in) {
			br = new BufferedReader(new InputStreamReader(in));
		}

		public Scanner(String file) throws FileNotFoundException {
			br = new BufferedReader(new FileReader(file));
		}

		String next() throws IOException {
			while (st == null || !st.hasMoreTokens()) {
				st = new StringTokenizer(br.readLine());
			}
			return st.nextToken();
		}

		String nextLine() throws IOException {
			return br.readLine();
		}

		int nextInt() throws NumberFormatException, IOException {
			return Integer.parseInt(next());
		}

		long nextLong() throws NumberFormatException, IOException {
			return Long.parseLong(next());
		}

		double nextDouble() throws NumberFormatException, IOException {
			return Double.parseDouble(next());
		}

		boolean ready() throws IOException {
			return br.ready();
		}
	}
}