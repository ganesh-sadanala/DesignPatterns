package Behavioral.Iterator;

public interface SocialNetwork {
    public  ProfileIterator createFriendsIterator(String profileId);
    public ProfileIterator createCoworkersIterator(String profileId);
}
