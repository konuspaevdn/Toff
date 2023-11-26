package edu.hw3.Task2;

import java.util.ArrayList;

public class BracketSequence {
    private BracketSequence() {

    }

    public static ArrayList<String> clusterize(String seq) throws UnbalancedBSException {
        ArrayList<String> clusters = new ArrayList<>();
        StringBuilder cluster = new StringBuilder();
        int balance = 0;
        for (char c : seq.toCharArray()) {
            cluster.append(c);
            if (c == ')') {
                --balance;
                if (balance < 0) {
                    throw new UnbalancedBSException();
                }
                if (balance == 0) {
                    clusters.add(cluster.toString());
                    cluster.setLength(0);
                }
            } else {
                ++balance;
            }
        }
        if (balance != 0) {
            throw new UnbalancedBSException();
        }
        return clusters;
    }
}
