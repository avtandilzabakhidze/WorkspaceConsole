package com.example.workspaceconsole.serviceTest;

import com.example.workspaceconsole.domain.Booking;
import com.example.workspaceconsole.domain.TimeManagement;
import com.example.workspaceconsole.domain.Workspace;
import com.example.workspaceconsole.dto.BookingDTO;
import com.example.workspaceconsole.repository.BookingRepository;
import com.example.workspaceconsole.repository.WorkspaceRepository;
import com.example.workspaceconsole.service.BookingService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    @Mock
    private BookingRepository repository;

    @Mock
    private WorkspaceRepository workspaceRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BookingService bookingService;

    private BookingDTO bookingDTO;
    private Booking booking;
    private Workspace workspace;

    @BeforeEach
    public void setUp() {
        bookingDTO = new BookingDTO();
        bookingDTO.setId(1);
        bookingDTO.setWorkspaceId(1);
        bookingDTO.setUserId(1);
        bookingDTO.setStartTime(LocalDateTime.now());
        bookingDTO.setAdditionalRequirements("Test requirement");

        workspace = new Workspace();
        workspace.setId(1);
        workspace.setTimeManagement(TimeManagement.HOUR);

        booking = new Booking();
        booking.setId(1);
        booking.setWorkspace(workspace);
        booking.setStartTime(LocalDateTime.now());
        booking.setEndTime(LocalDateTime.now().plusHours(1));
    }

    @Test
    public void testSaveBooking() {
        when(modelMapper.map(bookingDTO, Booking.class)).thenReturn(booking);
        when(workspaceRepository.findById(bookingDTO.getWorkspaceId())).thenReturn(Optional.of(workspace));
        when(repository.save(booking)).thenReturn(booking);
        when(modelMapper.map(booking, BookingDTO.class)).thenReturn(bookingDTO);

        BookingDTO result = bookingService.saveBooking(bookingDTO);

        assertEquals(bookingDTO, result);
        assertTrue(workspace.isOccupied());
    }

    @Test
    public void testSaveBookingWorkspaceNotFound() {
        when(workspaceRepository.findById(bookingDTO.getWorkspaceId())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> bookingService.saveBooking(bookingDTO));
    }

    @Test
    public void testDeleteBookingById() {
        long id = 1;
        when(repository.findById(id)).thenReturn(Optional.of(booking));

        bookingService.deleteBookingById(id);

        assertFalse(workspace.isOccupied());
        verify(repository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteBookingByIdNotFound() {
        long id = 1;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> bookingService.deleteBookingById(id));
    }
}
