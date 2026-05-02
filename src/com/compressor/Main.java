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

        java.nio.file.Files.writeString(input,
                "Java é uma linguagem de programação orientada a objetos. " +
                        "Foi desenvolvida por James Gosling na Sun Microsystems. " +
                        "É amplamente usada no desenvolvimento de aplicações web, " +
                        "mobile, desktop e sistemas embarcados. " +
                        "O principal lema do Java é: escreva uma vez, rode em qualquer lugar."
        );

        Compressor.compress(input, compressed);
        Decompressor.decompress(compressed, decompressed);

        String original     = java.nio.file.Files.readString(input);
        String reconstructed = java.nio.file.Files.readString(decompressed);

        System.out.println("Idênticos: " + original.equals(reconstructed));

    }

}

/*Nesse caso o arquivo compactado ficou maior que o original, isso acontece porque o cabeçalho com a tabela de frequências
 tem um custo fixo alto para textos pequenos. O cenário muda para textos de LIVROS, LOGS DE SISTEMA, valendo a pena a
 compactação.*/
