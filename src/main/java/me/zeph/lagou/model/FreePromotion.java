package me.zeph.lagou.model;

public class FreePromotion extends Promotion {

    public FreePromotion(int priority, int freeCount) {
        super(priority);
        this.freeCount = freeCount;
    }

    private int freeCount;

    public int getFreeCount() {
        return freeCount;
    }

    public void setFreeCount(int freeCount) {
        this.freeCount = freeCount;
    }
}
