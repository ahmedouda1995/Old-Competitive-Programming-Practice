package trie;

public class Trie {

	static final int MAX = 26;
	Node root;
	
	public Trie() {
		root = new Node();
	}
	
	void addWord(String s) {
		addWordUtil(0, s, root);
	}
	
	void addWordUtil(int i, String s, Node cur) {
		if(i == s.length())
			cur.words += 1;
		else {
			cur.pref += 1;
			int pos = s.charAt(i) - 'a';
			if(cur.children[pos] == null)
				cur.children[pos] = new Node();
			addWordUtil(i + 1, s, cur.children[pos]);
		}
	}
	
	int countWords(String s) {
		return countWordsUtil(0, s, root);
	}
	
	private int countWordsUtil(int i, String s, Node cur) {
		if(i == s.length()) return cur.words;
		int pos = s.charAt(i) - 'a';
		if(cur.children[pos] == null) return 0;
		return countWordsUtil(i + 1, s, cur.children[pos]);
	}

	int countPrefixes(String s) {
		return countPrefixesUtil(0, s, root);
	}
	
	private int countPrefixesUtil(int i, String s, Node cur) {
		if(i == s.length()) return cur.pref;
		int pos = s.charAt(i) - 'a';
		if(cur.children[pos] == null) return 0;
		return countPrefixesUtil(i + 1, s, cur.children[pos]);
	}

	static class Node {
		int words, pref;
		Node children[];
		
		public Node() {
			words = pref = 0;
			children = new Node[26];
		}
	}
	
	public static void main(String[] args) {
		Trie t = new Trie();
		t.addWord("ahmed");
		t.addWord("ahmer");
		System.out.println(t.countWords("ahmer"));
	}
}