package com.compressor;

import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTree {

    public static HuffmanNode build(Map<Byte, Integer> frequency) {

        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>();

        //Cria um nó folha para cada byte

        for (Map.Entry<Byte, Integer> entry : frequency.entrySet()) {

            queue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        //Constrói a árvore

        while (queue.size() > 1) {
            HuffmanNode left = queue.poll();  // menor frequência
            HuffmanNode right = queue.poll(); // segunda menor

            HuffmanNode parent = new HuffmanNode(left.frequency + right.frequency, left, right);

            queue.add(parent);

        }

        return queue.poll();  // raiz da árvore

    }
}
