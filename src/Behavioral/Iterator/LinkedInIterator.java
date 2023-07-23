package Behavioral.Iterator;

public class LinkedInIterator implements ProfileIterator {
    private LinkedIn linkedIn;
    private String profileId;
    private String type;

    private int currentPosition=0;

    private Profile []cache;

    public void lazyInit() {
        if (cache == null) {
            // cache = linkedIn.socialGraphRequest(profileId, type)
        }
    }
    public LinkedInIterator(LinkedIn linkedIn, String profileId, String type) {
        this.linkedIn = linkedIn;
        this.profileId = profileId;
        this.type = type;
    }

    @Override
    public Profile getNext() {
        if (hasMore())
            currentPosition++;
        return cache[currentPosition];
    }

    @Override
    public boolean hasMore() {
        lazyInit();
        return currentPosition < cache.length;
    }
}
