package com.hotelroom.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import com.hotelroom.entity.Hotel;
import com.hotelroom.exception.ResourceNotFoundException;
import com.hotelroom.repository.HotelRepository;
import com.hotelroom.service.HotelService;
import com.hotelroom.service.HotelServiceImpl;

@SpringBootTest
public class HotelServiceTest {

	@InjectMocks
	private HotelService hotelService = new HotelServiceImpl();

	@Mock
	private HotelRepository repository;

	@Test
	public void getAllHotelsTest() {
		 List<Hotel> hotels = new ArrayList<>();
	        hotels.add(new Hotel(1, "New York", "Hotel California", "Some address", "Nice place", 1900.0,
	                "contact@hotelcal.com", "1234567890", "0987654321", "www.hotelcal.com", new ArrayList<>()));
	        hotels.add(new Hotel(1, "Hotel Raj", "Marathhalli", "Marathhalli Bridge", "Nice place", 2000.0,
	                "@raj.com", "1234567890", "0987654321", "www.hotelraj.com", new ArrayList<>()));

		when(repository.findAll()).thenReturn(hotels);

		List<Hotel> result = hotelService.getAllHotel();

		assertEquals(2, result.size());
		assertEquals("New York", result.get(0).getCity());
		verify(repository).findAll();
	}
	
	@Test
    public void getHotelByIdTest() {
        Hotel hotel = new Hotel(1, "New York", "Hotel California", "Some address", "Nice place", 1900.0,
                "contact@hotelcal.com", "1234567890", "0987654321", "www.hotelcal.com", new ArrayList<>());
        when(repository.findById(1)).thenReturn(Optional.of(hotel));

        Hotel result = hotelService.getHotelById(1);

        assertEquals("Hotel California", result.getHotelName());
        assertEquals("New York", result.getCity());
    }
	
	@Test
    public void getHotelByIdNotFoundTest() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            hotelService.getHotelById(1);
        });
    }

    @Test
    public void createHotelTest() {
    	Hotel hotel = new Hotel(1, "Ranchi", "Hotel Tulip", "butty More", "Nice place", 1600.0,
                "tulip@gmail.com", "1234567890", "0987654321", "www.tulip.com", new ArrayList<>());

        when(repository.save(any(Hotel.class))).thenReturn(hotel);

        // When
        Hotel result = hotelService.createHotel(hotel);

        // Then
        assertEquals("Hotel Tulip", result.getHotelName());
        verify(repository).save(hotel);
    }
    

    @Test
    public void updateHotelTest() {
    	 Hotel existingHotel = new Hotel(1, "Racnhi", "Hotel Mountain View", "lal pur", "Nice place", 2000.0,
                 "mountain@hotelcal.com", "4857345394", "4567398435", "www.mountainview.com", new ArrayList<>());

         when(repository.findById(1)).thenReturn(Optional.of(existingHotel));
         when(repository.save(any(Hotel.class))).thenReturn(existingHotel);

         // When
         Hotel result = hotelService.updateHotel(existingHotel);

         // Then
         assertEquals("Hotel Mountain View", result.getHotelName());
         verify(repository).save(existingHotel);
     }

    @Test
    public void updateHotelNotFoundTest() {
    	 Hotel newHotel = new Hotel(1, "Racnhi", "Hotel Mountain View", "lal pur", "Nice place", 2000.0,
                 "mountain@hotelcal.com", "4857345394", "4567398435", "www.mountainview.com", new ArrayList<>());

         when(repository.findById(1)).thenReturn(Optional.empty());

         // Then
         assertThrows(ResourceNotFoundException.class, () -> {
             hotelService.updateHotel(newHotel);
         });
    }

    @Test
    public void deleteHotelTest() {
    	 Hotel hotel = new Hotel(1, "New York", "Hotel California", "Some address", "Nice place", 200.0,
                 "contact@hotelcal.com", "1234567890", "0987654321", "www.hotelcal.com", new ArrayList<>());

         when(repository.findById(1)).thenReturn(Optional.of(hotel));

         // When
         hotelService.deleteHotel(1);

         // Then
         verify(repository).delete(hotel);
     }
     

    @Test
    public void deleteHotelNotFoundTest() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            hotelService.deleteHotel(1);
        });
    }

}