package com.compressor;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        //Criar um arquivo de testes

        Path input = Path.of("input.txt");
        java.nio.file.Files.writeString(input, "aabbbcccc");

        Map<Byte, Integer> frequency = FrequencyCounter.counter(input);
        HuffmanNode root = HuffmanTree.build(frequency);

        System.out.println("Raiz da árvore — frequência total: " + root.frequency);
        System.out.println("Filho esquerdo: " + root.left.frequency);
        System.out.println("Filho direito: " + root.right.frequency);
        
    }
}
