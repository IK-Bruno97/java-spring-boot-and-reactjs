package com.example.springreact.springreact.services;
import com.example.springreact.springreact.data.entities.GuestEntity;
import com.example.springreact.springreact.data.repositories.GuestRepository;
import com.example.springreact.springreact.web.errors.NotFoundException;
import com.example.springreact.springreact.web.models.Guest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class GuestServices {
    
    private final GuestRepository guestRepository;

    public GuestServices(GuestRepository guestRepository){
        this.guestRepository = guestRepository;
    }

    public List<Guest> getAllGuest(String filterEmail){
        List<Guest> guests = new ArrayList<>();
        if(StringUtils.hasLength(filterEmail)){
            GuestEntity entity = this.guestRepository.findByEmail(filterEmail);
            guests.add(this.translateDbToWeb(entity));
        }else{
            Iterable<GuestEntity> entities = this.guestRepository.findAll();
            entities.forEach(entity ->{
                guests.add(this.translateDbToWeb(entity));
            }); 
        }
        return guests;
    }

    public Guest getGuest(int id){
        Optional<GuestEntity> optional = this.guestRepository.findById(id);
        if(optional.isEmpty()){
            throw new NotFoundException("guest not found with id");
        }
        return this.translateDbToWeb(optional.get());
    }

    public Guest createOrUpdate(Guest guest){
        GuestEntity entity = this.translateWebTodb(guest);
        entity = this.guestRepository.save(entity);
        return this.translateDbToWeb(entity);
    }

    public void deleteGuest(int id){
        this.guestRepository.deleteById(id);
    }

    private GuestEntity translateWebTodb(Guest guest){
        GuestEntity entity = new GuestEntity();
        entity.setId(guest.getId());
        entity.setFirstName(guest.getFirstName());
        entity.setLastName(guest.getLastName());
        entity.setEmail(guest.getEmail());
        entity.setPhoneNumber(guest.getPhoneNumber());
        entity.setAddress(guest.getAddress());
        return entity;
    }

    private Guest translateDbToWeb(GuestEntity entity){
        return new Guest(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getPhoneNumber(), entity.getAddress());
    }
}
