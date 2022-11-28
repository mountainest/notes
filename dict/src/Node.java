import java.util.HashMap;
import java.util.Map;

public class Node {
    /**
     * 如果是尾节点，key为null
     */
    private Map<String, Node> nodes = new HashMap<>();

    public boolean containes(String ch) {
        return this.nodes.containsKey(ch);
    }

    public Node getNext(String ch) {
        return this.nodes.get(ch);
    }

    public void addNext(String ch, Node node) {
        this.nodes.put(ch, node);
    }
}
