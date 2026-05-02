package com.compressor;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        //Criar um arquivo de testes

        Path input = Path.of("input.txt");
        Path compressed   = Path.of("output.huff");
        Path decompressed = Path.of("output.txt");

        java.nio.file.Files.writeString(input, "aabbbcccc");

        Compressor.compress(input, compressed);
        Decompressor.decompress(compressed, decompressed);

        String original     = java.nio.file.Files.readString(input);
        String reconstructed = java.nio.file.Files.readString(decompressed);

        System.out.println("Original:      " + original);
        System.out.println("Reconstruído:  " + reconstructed);
        System.out.println("Idênticos: " + original.equals(reconstructed));


    }

}
