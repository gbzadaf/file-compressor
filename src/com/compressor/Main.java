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
        Map<Byte, String> codes = CodeGenerator.generate(root);

        System.out.println("Códigos gerados:");
        codes.forEach((b, code) ->
                System.out.println("'" + (char)(byte)b + "' → " + code)
        );

        // Mostra a compactação da string
        String original = "aabbbcccc";
        System.out.println("\nOriginal:   " + original.length() * 8 + " bits");

        int compressed = 0;
        for (char c : original.toCharArray()) {
            compressed += codes.get((byte) c).length();
        }
        System.out.println("Compactado: " + compressed + " bits");

    }
}
