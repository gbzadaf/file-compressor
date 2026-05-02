package com.compressor;

import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Decompressor {

    public static void decompress(Path inputPath, Path outputPath) throws IOException {
        try (DataInputStream in = new DataInputStream(
                new BufferedInputStream(new FileInputStream(inputPath.toFile())))) {

            // 1. Lê a tabela de frequências
            int tableSize = in.readInt();
            Map<Byte, Integer> frequency = new HashMap<>();
            for (int i = 0; i < tableSize; i++) {
                byte b = in.readByte();
                int freq = in.readInt();
                frequency.put(b, freq);
            }

            // 2. Reconstrói a árvore
            HuffmanNode root = HuffmanTree.build(frequency);

            // 3. Lê o total de bits
            int totalBits = in.readInt();

            // 4. Lê os bytes e converte em string de bits
            StringBuilder bitString = new StringBuilder();
            byte[] compressedBytes = in.readAllBytes();
            for (byte b : compressedBytes) {
                String bits = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
                bitString.append(bits);
            }

            // 5. Percorre a árvore usando os bits e reconstrói os bytes originais
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            HuffmanNode current = root;
            for (int i = 0; i < totalBits; i++) {
                current = bitString.charAt(i) == '0' ? current.left : current.right;

                if (current.isLeaf()) {
                    result.write(current.value);
                    current = root;
                }
            }

            // 6. Salva o arquivo descompactado
            java.nio.file.Files.write(outputPath, result.toByteArray());
        }

        System.out.println("Descompactado com sucesso!");
    }
}
