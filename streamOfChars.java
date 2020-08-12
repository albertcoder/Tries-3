//time complexity O(1) since buiding the trie is a constant ie one time thing
//space complexity O(1) again constant space occupied by Trie
class StreamChecker {
    class TrieNode {
        boolean isEnd;
        TrieNode [] children;
        public TrieNode(){
            this.children = new TrieNode[26];
        }
    }
    //we will initiate this globalized root in constructor
    TrieNode root;
    //inserting the word in oppposite order from root
    public void insert(String word){
        TrieNode curr = root;
        for(int i = word.length() - 1; i >= 0; i--){
            char c = word.charAt(i);
            if(curr.children[c - 'a'] == null){
                curr.children[c - 'a'] = new TrieNode();
            }
            curr = curr.children[c - 'a'];
        }
        curr.isEnd = true;
    }
    StringBuilder sb;
    //constructor
    public StreamChecker(String[] words) {
        root = new TrieNode();
        sb = new StringBuilder();        
        for(String word: words){
            insert(word);
        }
    }
    
    public boolean query(char letter) {
        //this query function is called multiple times 
        //so we keep appending letters to sb
        sb.append(letter);
        TrieNode curr = root;
        //return true is any word is found in dict
        //returns true if and only if for some k >= 1, the last k characters queried  
        for(int i = sb.length() - 1; i >= 0; i--){
            char c = sb.charAt(i);
            if(curr.children[c - 'a'] == null) return false;
            if(curr.children[c - 'a'].isEnd) return true;
            curr = curr.children[c - 'a'];
        }
        return false;
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */
