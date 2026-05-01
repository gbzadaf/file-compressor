package com.compressor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FrequencyCounter {

    public static Map<Byte, Integer> counter (Path filePath) throws IOException {

        byte[] bytes = Files.readAllBytes(filePath);

        Map<Byte, Integer> frequency = new HashMap<>();

        for (byte b : bytes) {
            frequency.put(b, frequency.getOrDefault(b, 0) + 1);
        }

        return frequency;
    }
}
