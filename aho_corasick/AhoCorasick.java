package aho_corasick;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class AhoCorasick
{
	int numNodes;
	Node tree[];
	
	public AhoCorasick(int n)
	{
		tree = new Node[n + 1];
		tree[0] = new Node();
		numNodes = 1;
	}
	
	void insert(char s[])
	{
		int curNode = 0;
		for(int i = 0; i < s.length; ++i)
		{
			if(tree[curNode].child[s[i] - 'a'] == -1)
			{
				tree[curNode].child[s[i] - 'a'] = numNodes;
				tree[numNodes++] = new Node();
			}
			curNode = tree[curNode].child[s[i] - 'a'];
		}
		tree[curNode].matches = 1;
	}
	
	void constructSuffixLinks()
	{
		Queue<Integer> q = new LinkedList<Integer>();
		
		while(!q.isEmpty())
		{
			
		}
	}
	
	static class Node
	{
		static final int MAX = 26;
		int child[];
		int nxt, matches;

		public Node()
		{
			child = new int[26];
			Arrays.fill(child, -1);
			matches = 0;
		}
	}
}