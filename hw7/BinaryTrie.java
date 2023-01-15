import edu.princeton.cs.algs4.MinPQ;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class BinaryTrie implements Serializable {
    Node trie;
    private static class Node implements Comparable<Node>, Serializable {
        private final char ch;
        private final int freq;
        private final Node left, right;
        public Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }
        public boolean isLeaf(){
            return left == null && right == null;
        }
        @Override
        public int compareTo(Node o) {
            return this.freq - o.freq;
        }
    }

    public BinaryTrie(Map<Character, Integer> frequencyTable){
        MinPQ<Node> pq = new MinPQ<>();
        for (Character ch: frequencyTable.keySet()){
            pq.insert(new Node(ch, frequencyTable.get(ch), null, null));
        }
        while (pq.size() > 1){
            Node n1 = pq.delMin();
            Node n2 = pq.delMin();
            Node root = null;
            if (n1.compareTo(n2) > 0){
                root = new Node('\0', n1.freq + n2.freq, n2, n1);
            } else {
                root = new Node('\0', n1.freq + n2.freq, n1, n2);
            }
            pq.insert(root);
        }
        trie = pq.delMin();
    }
    public Match longestPrefixMatch(BitSequence querySequence) {
        Node node = trie;
        BitSequence bitSequence = new BitSequence();
        for (int i = 0; i < querySequence.length(); i++) {
            int bitNum = querySequence.bitAt(i);
            if (bitNum == 0) {
                node = node.left;
                bitSequence = bitSequence.appended(0);
            } else {
                node = node.right;
                bitSequence = bitSequence.appended(1);
            }
            if (node.isLeaf()){
                return new Match(bitSequence, node.ch);
            }
        }
        return null;
    }

    public void buildLookupTableHelperFunction(
            Map<Character, BitSequence> table, Node tree, BitSequence bitSequence){
        if (tree == null){
            return;
        } else if (tree.isLeaf()){
            table.put(tree.ch, bitSequence);
        }
        BitSequence leftBitSequence = bitSequence.appended(0);
        BitSequence rightBitSequence = bitSequence.appended(1);
        buildLookupTableHelperFunction(table, tree.left, leftBitSequence);
        buildLookupTableHelperFunction(table, tree.right, rightBitSequence);
    }

    public Map<Character, BitSequence> buildLookupTable(){
        Map<Character, BitSequence> table = new HashMap<>();
        BitSequence leftBitSequence = new BitSequence("0");
        BitSequence rightBitSequence = new BitSequence("1");
        buildLookupTableHelperFunction(table, trie.left, leftBitSequence);
        buildLookupTableHelperFunction(table, trie.right, rightBitSequence);
        return table;
    }
}
