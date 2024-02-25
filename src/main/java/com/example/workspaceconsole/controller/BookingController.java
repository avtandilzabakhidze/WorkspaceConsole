package com.example.workspaceconsole.controller;

import com.example.workspaceconsole.dto.BookingDTO;
import com.example.workspaceconsole.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("booking")
public class BookingController {
    private final BookingService service;

    @Autowired
    public BookingController(BookingService service) {
        this.service = service;
    }

    @GetMapping
    public Set<BookingDTO> getAllBooking() {
        return service.getAllBooking();
    }

    @PostMapping
    public BookingDTO saveBooking(@RequestBody BookingDTO bookingDTO) {
        return service.saveBooking(bookingDTO);
    }

    @DeleteMapping("{id}")
    public void deleteBookingById(@PathVariable long id) {
        service.deleteBookingById(id);
    }
}
