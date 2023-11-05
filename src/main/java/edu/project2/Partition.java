package edu.project2;

public record Partition(int i, int j, Type type) {
    public enum Type { HORIZONTAL, VERTICAL }
}
