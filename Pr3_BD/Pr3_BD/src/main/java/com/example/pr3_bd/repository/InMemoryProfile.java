package com.example.pr3_bd.repository;


import com.example.pr3_bd.enity.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class InMemoryProfile {
    private final List<Profile> PROFILE = new ArrayList<>();
    public List<Profile> findAllProfile(Long id) {
        return PROFILE;
    }

    public Profile findProfile(Long id) {
        return PROFILE
                .stream()
                .filter(element -> element.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    public Profile createProfile(Profile profile) {
        PROFILE.add(profile);
        return profile;
    }

    public Profile updateProfile(Profile profile) {
        var ProfileIndex = IntStream.range(0, PROFILE.size())
                .filter(index -> PROFILE.get(index).getId().equals(profile.getId()))
                .findFirst()
                .orElse(-1);
        if (ProfileIndex == -1) {
            return null;
        }
        PROFILE.set(ProfileIndex, profile);
        return profile;
    }

    public void deleteProfile(Long id) {
        var profile = findAllProfile(id);
        if (profile != null) {
            PROFILE.remove(profile);
        }
    }
}
