package com.hotelroom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelroom.entity.RoomDetails;
import com.hotelroom.payload.RoomDetailsPayload;
import com.hotelroom.service.RoomDetailsService;

@RestController
@RequestMapping("/rooms")
public class RoomDetailsController {

  @Autowired
  private RoomDetailsService roomDetailsService;

  @GetMapping("/{Id}")
  public ResponseEntity<RoomDetails> getRoomDetails(@PathVariable("Id") int roomId) {
    RoomDetails roomDetails = roomDetailsService.getRoomDetailsById(roomId);
    
      return new ResponseEntity<>(roomDetails,HttpStatus.OK);
    }


  @PostMapping("/save")
  public ResponseEntity<RoomDetails> createRoomDetails(@RequestBody RoomDetailsPayload roomDetails) {
	  return new ResponseEntity<>(roomDetailsService.createRoomDetails(roomDetails),HttpStatus.CREATED);
	
  }
  
  @DeleteMapping("/{Id}")  
  public ResponseEntity<Void> deleteRoomDetails(@PathVariable("Id") int roomId){
	  roomDetailsService.deleteRoomDetails(roomId);
	  ResponseEntity<Void> repsonseEntity = new ResponseEntity<>(HttpStatus.OK);
	  return repsonseEntity;
  }

  @GetMapping("/all")
	public List<RoomDetails> fetchAllHotels() {

		List<RoomDetails> roomdetails = roomDetailsService.getAllRoomDetails();
		return roomdetails;
	}
  
  @PutMapping("/update")
  public ResponseEntity<RoomDetails> updateRoom(@RequestBody RoomDetails room){
	 return new ResponseEntity<>(roomDetailsService.updateRoom(room),HttpStatus.OK);
 }
  
}
