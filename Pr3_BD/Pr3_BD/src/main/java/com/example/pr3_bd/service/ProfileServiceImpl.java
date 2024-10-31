package com.example.pr3_bd.service;

import com.example.pr3_bd.enity.Profile;
import com.example.pr3_bd.enity.customer;
import com.example.pr3_bd.repository.CustomerRepository;
import com.example.pr3_bd.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository repository;

    public ProfileServiceImpl(ProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Profile> findAllProfile() {
        return repository.findAll();
    }

    @Override
    public Profile createProfile(Profile profile) {
        return repository.save(profile);
    }

    @Override
    public Profile updateProfile(Profile profile) {
        return repository.save(profile);
    }

    @Override
    public Profile findProfilebyId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteProfile(Long id) {
        repository.deleteById(id);
    }

}
