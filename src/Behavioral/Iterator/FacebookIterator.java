package Behavioral.Iterator;

import java.util.Arrays;

public class FacebookIterator implements ProfileIterator{

    private Facebook facebook;
    private String profileId;
    private String type;

    private int currentPosition=0;

    private Profile []cache;

    public void lazyInit() {
        if (cache == null) {
            // cache = facebook.socialGraphRequest(profileId, type)
        }
    }
    public FacebookIterator(Facebook facebook, String profileId, String type) {
        this.facebook = facebook;
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
