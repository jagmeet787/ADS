package ADS.String;

class Trie {

	class Node {
		java.util.HashMap<Character, Node> children;
		char ch;
		boolean isWord;
		public Node(char ch) {
			this.ch = ch;
			children = new java.util.HashMap<Character, Node>();
			isWord = false;
		}

		public void addChild(char c, Node n) {
			children.put(c, n);
		}

		public boolean hasChild(char ch) {
			return children.containsKey(ch);
		}

		public Node getChild(char ch) {
			return children.get(ch);
		}

		public void removeChild(char ch) {
			children.remove(ch);
		}

		public int numChildren() {
			return children.size();
		}

		@Override
		public String toString() {
			return "[" + ch + " : " + isWord + " : " + children + "]";
		}
	}

	Node root;

	public Trie() {
		this.root = new Node('\0');
	}

	public boolean insert(String str) {
		if (str == null) throw new IllegalArgumentException("Key can't be null.");
		Node node = root;
		for(int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if(!node.hasChild(ch))
				node.addChild(ch, new Node(ch));
			node = node.getChild(ch);
			if(i == str.length() - 1) node.isWord = true;
		}
		return true;
	}

	public boolean isPrefix(String prefix) {
		if (prefix == null) throw new IllegalArgumentException("prefix can't be null.");
		Node node = root;
		for(int i = 0; i < prefix.length(); i++) {
			char ch = prefix.charAt(i);
			if(!node.hasChild(ch)) return false;
			node = node.getChild(ch);
		}
		return true;
	}

	public boolean contains(String str) {
		if (str == null) return false;
		Node node = root;
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if(!node.hasChild(ch)) return false;
			node = node.getChild(ch);
			if(i == str.length() - 1) if(node.isWord) return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return this.allStrings().toString();
	}

	public java.util.ArrayList<String> allStrings() {
		java.util.ArrayList<String> list = new java.util.ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		_allStrings(root, list, sb);
		return list;
	}

	private void _allStrings(Node node, java.util.ArrayList<String> list, StringBuilder sb) {
		for (Character ch : node.children.keySet()) {
			sb.append(ch);
			_allStrings(node.children.get(ch), list, sb);
			if(sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
		}
		if(node.isWord) {
			list.add(sb.toString());
		}
	}

	public void delete(String key) {
		if (key == null) throw new IllegalArgumentException("Key can't be null");
		if (key.length() == 0) return;
		if(!contains(key)) return;
		_delete(root , key, 0);
	}

	private Node _delete(Node node, String key, int depth) {
		if(key.length() == depth) {
			if(node.numChildren() == 0) {
				return null;
			} else {
				node.isWord = false;
				return node;
			}
		}
		char ch = key.charAt(depth);
		if (_delete(node.getChild(ch), key, depth+1) == null && node.numChildren() == 1) {
			node.removeChild(ch);
			if(!node.isWord) return null;
		}
		return node;
	}

	public static void main(String[] args) {
		Trie tr = new Trie();
		tr.insert("hello");
		tr.insert("aired");
		tr.insert("zip");
		tr.insert("ghiler");
		tr.insert("hell");
		// tr.insert("abc");
		// tr.insert("aaa");
		// System.out.println("isPrefix: " + tr.isPrefix("shil"));
		System.out.println(tr.allStrings());
		tr.delete("hell");
		// System.out.println("contains: " + tr.contains("zip"));
		System.out.println("contains: " + tr.contains("hell"));
		System.out.println(tr);
		// System.out.println(tr.root);
	}
}