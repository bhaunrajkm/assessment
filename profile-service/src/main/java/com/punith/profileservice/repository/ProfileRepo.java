package com.punith.profileservice.repository;

import com.punith.profileservice.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProfileRepo extends JpaRepository<Profile, Long> {
    List<Profile> findByLastNameIgnoreCase(String lastName);
    List<Profile> findByDob(Date dob);
    List<Profile> findByLastNameIgnoreCaseAndDob(String lastName, Date dob);
}
