#  Compactador de Arquivos — Do Zero em Java

Compactador de arquivos construído do zero em Java, sem nenhuma biblioteca externa. O projeto implementa o algoritmo de Huffman completo: contagem de frequências, construção da árvore, geração de códigos binários, compactação e descompactação.

---

##  Arquitetura

```
Arquivo original → FrequencyCounter → HuffmanTree → CodeGenerator → Compressor → arquivo .huff
arquivo .huff → Decompressor → HuffmanTree → arquivo original
```

| Componente | Responsabilidade |
|---|---|
| `FrequencyCounter` | Conta quantas vezes cada byte aparece no arquivo |
| `HuffmanNode` | Representa um nó da árvore de Huffman |
| `HuffmanTree` | Constrói a árvore usando fila de prioridade |
| `CodeGenerator` | Percorre a árvore e gera o código binário de cada byte |
| `Compressor` | Converte o arquivo em bits e salva no formato `.huff` |
| `Decompressor` | Reconstrói o arquivo original a partir do `.huff` |

---

##  Funcionalidades

- Compactação de qualquer arquivo usando o algoritmo de Huffman
- Descompactação com reconstrução fiel ao arquivo original
- Cabeçalho com tabela de frequências para descompactação autônoma
- Padding controlado para evitar bits extras na descompactação

---

##  Como funciona o algoritmo de Huffman

Bytes mais frequentes recebem códigos menores. Por exemplo, para `"aabbbcccc"`:

```
'c' aparece 4x → código: 0       (1 bit)
'b' aparece 3x → código: 11      (2 bits)
'a' aparece 2x → código: 10      (2 bits)
```

A árvore gerada:
```
        [9]
       /   \
     [4]   [5]
      c    /  \
          [2] [3]
           a   b
```

Resultado:
```
Original:   72 bits (9 bytes × 8)
Compactado: 14 bits
```

---

## Exemplo de uso

```java
Path input        = Path.of("input.txt");
Path compressed   = Path.of("output.huff");
Path decompressed = Path.of("output.txt");

Compressor.compress(input, compressed);
Decompressor.decompress(compressed, decompressed);
```

---

##  Formato do arquivo .huff

```
[ tamanho da tabela (int) ]
[ byte (byte) | frequência (int) ] × N
[ total de bits (int) ]
[ bytes compactados ]
```

---

##  Como rodar

1. Clone o repositório
2. Abra no IntelliJ IDEA
3. Defina os caminhos de entrada e saída no `Main.java`
4. Execute a classe `Main.java`

Requer **Java 14+**.

---

##  O que aprendi

- Como funciona o **algoritmo de Huffman** na prática
- Como usar **fila de prioridade** para construção eficiente da árvore
- Como manipular **bits e bytes** diretamente em Java
- Como usar **DataOutputStream/DataInputStream** para arquivos binários
- O conceito de **padding** em arquivos binários
- Como salvar e recuperar **metadados** dentro do próprio arquivo

---

