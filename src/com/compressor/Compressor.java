package com.compressor;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class Compressor {

    public static void compress(Path inputPath, Path outputPath) throws IOException {
        // 1. Lê os bytes do arquivo original
        byte[] inputBytes = java.nio.file.Files.readAllBytes(inputPath);

        // 2. Conta frequências e gera códigos
        Map<Byte, Integer> frequency = FrequencyCounter.counter(inputPath);
        HuffmanNode root = HuffmanTree.build(frequency);
        Map<Byte, String> codes = CodeGenerator.generate(root);

        // 3. Monta a string de bits
        StringBuilder bitString = new StringBuilder();
        for (byte b : inputBytes) {
            bitString.append(codes.get(b));
        }

        // 4. Escreve o arquivo compactado
        try (DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(outputPath.toFile())))) {

            // Salva a tabela de frequências (para descompactar depois)
            out.writeInt(frequency.size());
            for (Map.Entry<Byte, Integer> entry : frequency.entrySet()) {
                out.writeByte(entry.getKey());
                out.writeInt(entry.getValue());
            }

            // Salva o total de bits (para ignorar padding no final)
            out.writeInt(bitString.length());

            // Converte bits em bytes e salva
            int i = 0;
            while (i < bitString.length()) {
                int end = Math.min(i + 8, bitString.length());
                String byteStr = bitString.substring(i, end);

                // Padding: completa com zeros se necessário
                while (byteStr.length() < 8) byteStr += "0";

                out.writeByte((byte) Integer.parseInt(byteStr, 2));
                i += 8;
            }
        }

        System.out.println("Compactado com sucesso!");
        System.out.println("Original:   " + inputBytes.length + " bytes");
        System.out.println("Compactado: " + outputPath.toFile().length() + " bytes");
    }
}
