package com.example.pr3_bd.repository;

import com.example.pr3_bd.enity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
