package com.example.springreact.springreact.web.rest;
import com.example.springreact.springreact.web.errors.BadRequestException;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import com.example.springreact.springreact.web.models.Guest;
import com.example.springreact.springreact.services.GuestServices;

@RestController
@RequestMapping("/api/guests/")
public class GuestRestController {
    private final GuestServices guestService;

    public GuestRestController(GuestServices guestService){
        this.guestService = guestService;
    }

    @GetMapping
    public List<Guest> getAll(@RequestParam(name="email", required= false)String email){
        return this.guestService.getAllGuest(email);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Guest createGuest(@RequestBody Guest guest){
        return this.guestService.createOrUpdate(guest);
    }

    @GetMapping("/{id}")
    public Guest getGuest(@PathVariable("id")int id){
        return this.guestService.getGuest(id);
    }

    @PutMapping("/{id}")
    public Guest updadteGuest(@PathVariable("id")int id, @RequestBody Guest guest){
        if(id != guest.getId()){
            throw new BadRequestException("id not matching!");
        }
        return this.guestService.createOrUpdate(guest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteGuest(@PathVariable("id")int id){
        this.guestService.deleteGuest(id);
    }
}
