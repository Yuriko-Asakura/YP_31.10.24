package com.example.pr3_bd.service;

import com.example.pr3_bd.enity.Profile;

import java.util.List;

public interface ProfileService {
    List<Profile> findAllProfile();
    Profile createProfile (Profile profile);
    Profile updateProfile (Profile profile);
    Profile findProfilebyId (Long id);
    void deleteProfile (Long id);
}
