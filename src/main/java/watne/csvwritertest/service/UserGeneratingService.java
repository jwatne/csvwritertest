package watne.csvwritertest.service;

import java.util.List;

import watne.csvwritertest.model.user;

public class UserGeneratingService {
    public static List<user> getUserList() {
        final List<user> userList = List.of(new user(0, "john.watne@state.mn.us", "John Watne"),
                new user(1, "doneil@state.mn.us", "Dave O'Neil"));
        return userList;
    }
}
