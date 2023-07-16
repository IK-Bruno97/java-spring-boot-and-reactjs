package com.example.springreact.springreact.data.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.springreact.springreact.data.entities.GuestEntity;

@Repository
public interface GuestRepository extends CrudRepository<GuestEntity, Integer> {
    GuestEntity findByEmail(String email);
}
