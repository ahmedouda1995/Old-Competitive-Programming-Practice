package trie;

public class CyclicWords {

	
	public static int differentCW(String[] words) {
		
		Trie t = new Trie();
		StringBuilder sb;
		int res = 0;
		
		for(int i = 0; i < words.length; ++i) {
			sb = new StringBuilder(words[i]);
			boolean found = false;
			for(int j = 0; j < words[i].length(); ++j) {
				if(t.contains(sb.toString())) {
					found = true;
					break;
				}
				sb.insert(0, sb.charAt(sb.length() - 1));
				sb.deleteCharAt(sb.length() - 1);
			}
			if(!found) {
				t.addWord(sb.toString());
				res++;
			}
		}
		return res;
	}
	
	static class Trie {
		Node root;
		static final int MAX = 26;
		
		public Trie() {
			root = new Node();
		}
		
		void addWord(String s) {
			addWordUtil(0, s, root);
		}
		
		private void addWordUtil(int i, String s, Node cur) {
			if(i == s.length()) cur.word = true;
			else {
				int pos = s.charAt(i) - 'a';
				if(cur.children[pos] == null)
					cur.children[pos] = new Node();
				addWordUtil(i + 1, s, cur.children[pos]);
			}
		}
		
		boolean contains(String s) {
			return containsUtil(0, s, root);
		}

		private boolean containsUtil(int i, String s, Node cur) {
			if(i == s.length()) return cur.word;
			int pos = s.charAt(i) - 'a';
			
			if(cur.children[pos] == null) return false;
			return containsUtil(i + 1, s, cur.children[pos]);
		}

		static class Node {
			boolean word;
			Node children[];
			
			public Node() {
				children = new Node[MAX];
				word = false;
			}
		}
	}
}