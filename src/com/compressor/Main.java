package com.compressor;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        //Criar um arquivo de testes

        Path input = Path.of("input.txt");
        java.nio.file.Files.writeString(input, "aabbbcccc");

        //Conta as frequencias

        Map<Byte, Integer> frequency = FrequencyCounter.counter(input);

        //Imprime o resultado

        frequency.entrySet().stream()
                .sorted(Map.Entry.<Byte,Integer>comparingByValue().reversed())
                .forEach(e -> System.out.println("'" + (char)(byte)e.getKey() + "' --> " + e.getValue() + "x"));

    }
}
