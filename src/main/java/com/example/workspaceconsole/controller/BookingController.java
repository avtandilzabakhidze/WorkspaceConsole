package com.example.workspaceconsole.controller;

import com.example.workspaceconsole.dto.BookingDTO;
import com.example.workspaceconsole.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("booking")
@Validated
public class BookingController {
    private final BookingService service;

    @Autowired
    public BookingController(BookingService service) {
        this.service = service;
    }

    @Operation(summary = "Get all bookings")
    @GetMapping
    public ResponseEntity<Set<BookingDTO>> getAllBooking() {
        Set<BookingDTO> bookings = service.getAllBooking();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @Operation(summary = "Create a new booking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
    })
    @PostMapping
    public ResponseEntity<BookingDTO> saveBooking(@Valid @RequestBody BookingDTO bookingDTO) {
        BookingDTO createdBooking = service.saveBooking(bookingDTO);
        return new ResponseEntity<>(createdBooking, HttpStatus.OK);
    }

    @Operation(summary = "Delete a workspace by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Workspace deleted"),
            @ApiResponse(responseCode = "404", description = "Workspace not found"),
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteWorkspaceById(@PathVariable long id) {
        service.deleteBookingById(id);
        return ResponseEntity.noContent().build();
    }

}
