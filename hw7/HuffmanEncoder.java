import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanEncoder {
    public static Map<Character, Integer> buildFrequencyTable(char[] inputSymbols){
        Map<Character, Integer> frequencyTable = new HashMap<>();
        for (char ch : inputSymbols){
            int freq = frequencyTable.getOrDefault(ch, 0);
            frequencyTable.put(ch, freq + 1);
        }
        return frequencyTable;
    }
    public static void main(String[] args){
        char[] fileContent = FileUtils.readFile(args[0]);
        Map<Character, Integer> frequencyTable = buildFrequencyTable(fileContent);
        BinaryTrie trie = new BinaryTrie(frequencyTable);
        ObjectWriter writer = new ObjectWriter(args[0] + ".huf");
        writer.writeObject(frequencyTable.size());
        writer.writeObject(trie);
        Map<Character, BitSequence> lookupTable = trie.buildLookupTable();
        List<BitSequence> bitSequences = new ArrayList<>();
        for (char ch : fileContent){
            bitSequences.add(lookupTable.get(ch));
        }
        BitSequence concatedBitSequence = BitSequence.assemble(bitSequences);
        writer.writeObject(concatedBitSequence);
        try {
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}