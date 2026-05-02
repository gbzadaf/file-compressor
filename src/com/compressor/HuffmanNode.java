package com.compressor;

public class HuffmanNode implements Comparable<HuffmanNode> {

    public final Byte value;    //(null se for nó interno)
    public final int frequency;
    public final HuffmanNode left;
    public final HuffmanNode right;

    //Nó folha (tem valor)

    public HuffmanNode(Byte value, int frequency) {

        this.value = value;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    //Nó interno (sem valor, só filhos)

    public HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
        this.value = null;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf() {

        return left == null && right == null;

    }

    @Override
    public int compareTo(HuffmanNode other) {
        return Integer.compare(this.frequency, other.frequency);

    }

}
