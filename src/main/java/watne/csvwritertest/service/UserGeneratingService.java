package watne.csvwritertest.service;

import java.util.List;

import watne.csvwritertest.model.user;

public class UserGeneratingService {
    public static List<user> getUserList() {
        final List<user> userList = List.of(new user(0, "first.person@dev.null", "First Person"),
                new user(1, "second.person@bogus.org", "Second Person"));
        return userList;
    }
}
