package Behavioral.Iterator;

public class LinkedIn implements SocialNetwork{
    @Override
    public ProfileIterator createFriendsIterator(String profileId) {
        return new LinkedInIterator(this, profileId, "friends");
    }

    @Override
    public ProfileIterator createCoworkersIterator(String profileId) {
        return new LinkedInIterator(this, profileId, "coworkers");
    }
}
