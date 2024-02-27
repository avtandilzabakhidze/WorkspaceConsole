package com.example.workspaceconsole.service;

import com.example.workspaceconsole.domain.Booking;
import com.example.workspaceconsole.domain.Workspace;
import com.example.workspaceconsole.dto.BookingDTO;
import com.example.workspaceconsole.repository.BookingRepository;
import com.example.workspaceconsole.repository.WorkspaceRepository;
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
    private final WorkspaceRepository workspaceRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BookingService(BookingRepository repository, WorkspaceRepository workspaceRepository, ModelMapper modelMapper) {
        this.repository = repository;
        this.workspaceRepository = workspaceRepository;
        this.modelMapper = modelMapper;
    }

    public Set<BookingDTO> getAllBooking() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(booking -> modelMapper.map(booking, BookingDTO.class))
                .collect(Collectors.toSet());
    }


    public BookingDTO saveBooking(BookingDTO bookingDTO) {
        //TODO need to add some check
        Booking booking = modelMapper.map(bookingDTO, Booking.class);
        Workspace workspace = workspaceRepository.findById(bookingDTO.getWorkspaceId())
                .orElseThrow(() -> new EntityNotFoundException("Workspace with id " + bookingDTO.getWorkspaceId() + " not found"));
        booking.setWorkspace(workspace);
        workspace.setOccupied(true);
        workspaceRepository.save(workspace);
        booking = repository.save(booking);
        return modelMapper.map(booking, BookingDTO.class);
    }

    public void deleteBookingById(long id) {
        Booking booking = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking with id " + id + " not found"));
        Workspace workspace = booking.getWorkspace();
        workspace.setOccupied(false);
        workspaceRepository.save(workspace);
        repository.deleteById(id);
    }
}