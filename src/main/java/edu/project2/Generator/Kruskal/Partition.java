package edu.project2.Generator.Kruskal;

public record Partition(int i, int j, Type type) {
    public enum Type { HORIZONTAL, VERTICAL }
}
