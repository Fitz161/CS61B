import java.util.ArrayList;
import java.util.List;

public class HuffmanDecoder {
    public static void main(String[] args) {
        List<Character> content = new ArrayList<>();
        ObjectReader reader = new ObjectReader(args[0]);
        int symbolNum = (int) reader.readObject();
        BinaryTrie trie = (BinaryTrie) reader.readObject();
        BitSequence bitSequence = (BitSequence) reader.readObject();
        Match m = trie.longestPrefixMatch(bitSequence);
        while (trie.longestPrefixMatch(bitSequence) != null) {
            content.add(m.getSymbol());
            bitSequence = bitSequence.allButFirstNBits(m.getSequence().length());
            m = trie.longestPrefixMatch(bitSequence);
        }
        BitSequence remainedBitSequence = bitSequence;
        char[] chars = new char[content.size()];
        for (int i = 0; i < content.size(); i++) {
            chars[i] = content.get(i);
        }
        FileUtils.writeCharArray(args[1], chars);
    }
}