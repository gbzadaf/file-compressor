package com.compressor;

import java.util.HashMap;
import java.util.Map;

public class CodeGenerator {

    public static Map<Byte, String> generate(HuffmanNode root) {

        Map<Byte, String> codes = new HashMap<>();
        traverse(root, "", codes);
        return codes;
    }

    public static void traverse(HuffmanNode node, String code, Map<Byte, String> codes) {
        if (codes == null) return;

        if (node.isLeaf()) {
            // Se a árvore tem só um nó (arquivo com um único byte)
            codes.put(node.value, code.isEmpty() ? "0" : code);
            return;
        }

        traverse(node.left, code + "0", codes);
        traverse(node.right, code + "1", codes);
    }

}
