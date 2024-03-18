
package hotelreservationsystem;

import hotelreservationsystem.Room.RoomCategory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class ReservationService {
    private final ArrayList<Reservation> reservations = new ArrayList<>();
    private final RoomService roomService;
    
    public ReservationService(RoomService roomService){
        this.roomService = roomService;
    }
    
    public Reservation makeReservation(RoomCategory category, Date checkInDate, Date checkOutDate, String customerName){
        
        List<Room> availableRooms = roomService.searchAvailableRooms(category);
        
        // Check if there are available rooms
        if (availableRooms.isEmpty()) {
            System.out.println("No available rooms in the selected category.");
            return null;
        }
        
        // Find the first available room
        Room room = availableRooms.get(0);
        
        // Check if the room is available for the entire duration
        for (Reservation existingReservation : reservations) {
            if (existingReservation.getRoom().equals(room)) {
                if (!(checkOutDate.before(existingReservation.getCheckInDate()) || checkInDate.after(existingReservation.getCheckOutDate()))) {
                    System.out.println("Room "+ room.getNumber() +" is not available for the selected dates from "+checkInDate+" to "+checkOutDate);
                    return null;
                }
            }
        }
        
        room.setIsAvailable(false);
        
        String reservationID = UUID.randomUUID().toString();
        Reservation reservation = new Reservation(reservationID, room, checkInDate, checkOutDate, customerName);
        reservations.add(reservation);
        
        return reservation;
    }
    
    
    private double calculateReservsationAmount(Room room, Date checkInDate, Date checkOutDate){
        long StayDuration = checkOutDate.getTime() - checkInDate.getTime();
        long durationInDays = StayDuration / (1000 * 60 * 60 * 24); // Convert milliseconds to days
        return room.getPrice() * durationInDays;
    }
    
    public Reservation getReservationByID(String reservationID){
        for(Reservation reservation : reservations){
            if(reservation.getReservationID().equals(reservationID)){
                return reservation;
            }
        }
        return null;
    } 
    
    public List<Reservation> getAllReservations(){
        return new ArrayList<>(reservations);
    }
    
}
