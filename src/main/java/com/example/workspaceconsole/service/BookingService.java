package com.example.workspaceconsole.service;

import com.example.workspaceconsole.domain.Booking;
import com.example.workspaceconsole.dto.BookingDTO;
import com.example.workspaceconsole.repository.BookingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookingService {
    private final BookingRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public BookingService(BookingRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public Set<BookingDTO> getAllBooking() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(booking -> modelMapper.map(booking, BookingDTO.class))
                .collect(Collectors.toSet());
    }

    public BookingDTO saveBooking(BookingDTO bookingDTO) {
        Booking booking = repository.save(modelMapper.map(bookingDTO, Booking.class));
        return modelMapper.map(booking, BookingDTO.class);
    }

    public void deleteBookingById(long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Booking with id " + id + " not found");
        }
        repository.deleteById(id);
    }
}