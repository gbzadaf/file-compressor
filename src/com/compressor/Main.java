package com.compressor;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        //Criar um arquivo de testes

        Path input = Path.of("input.txt");
        Path output = Path.of("output.huff");

        java.nio.file.Files.writeString(input, "aabbbcccc");

        Compressor.compress(input, output);


    }

}
