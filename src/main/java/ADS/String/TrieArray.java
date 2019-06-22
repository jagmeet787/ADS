class TrieArray {
    int[][] trie;
    boolean[][] finished;
    static final int CHARSET = 128;
    static final int CAPACITY = 1000_000;
    int next;
    public Trie() {
        trie = new int[CAPACITY][CHARSET];
        finished = new boolean[CAPACITY][CHARSET];
        next = 1;
    }
    public void insert(String str) {
        if (str == null) throw new IllegalArgumentException("String can't be null");
        int n = str.length();
        if (n == 0) return;
        if (next == CAPACITY) throw new IndexOutOfBoundsException("Can't create new Node");
        int prev = -1, idx = 0;
        for(int i = 0; i < n; i++) {
            int ch = str.charAt(i);
            if (trie[idx][ch] == 0) trie[idx][ch] = next++;
            prev = idx;
            idx = trie[idx][ch];
        }
        finished[prev][str.charAt(n - 1)] = true;
    }
    public boolean find(String str) {
        if (str == null) throw new IllegalArgumentException("String can't be null");
        int n = str.length();
        if (n == 0) return true;
        int prev = -1, idx = 0;
        for(int i = 0; i < n; i++) {
            int ch = str.charAt(i);
            if (trie[idx][ch] == 0) return false;
            prev = idx;
            idx = trie[idx][ch];
        }
        return finished[prev][str.charAt(n - 1)];
    }
    public void delete(String str) {
        if (str == null) throw new IllegalArgumentException("String can't be null");
        int n = str.length();
        if (n == 0) return;
        int prev = -1, idx = 0;
        for(int i = 0; i < n; i++) {
            int ch = str.charAt(i);
            if (trie[idx][ch] == 0) return;
            prev = idx;
            idx = trie[idx][ch];
        }
        finished[prev][str.charAt(n-1)] = false;
    }
}