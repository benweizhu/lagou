package me.zeph.lagou.model;

public class Promotion implements Comparable<Promotion> {

    private int priority;

    public Promotion(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Promotion comparedPromotion) {
        int comparePriority = comparedPromotion.getPriority();
        return this.priority - comparePriority;
    }
}
