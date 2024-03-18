
package hotelreservationsystem;

import hotelreservationsystem.Room.RoomCategory;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class RoomService {
    private final ArrayList<Room> rooms = new ArrayList<>();
    
    public void addRoom(Room room){
        rooms.add(room);
    }
    
    public List<Room> searchAvailableRooms(RoomCategory category){
        return rooms.stream().filter(room -> room.getCategory().equals(category) && room.isIsAvailable()).collect(Collectors.toList());
    }
    
    public boolean markRoomUnavailable(String roomNumber){
        for(Room room : rooms){
            if (room.getNumber().equals(roomNumber) && room.isIsAvailable()){
                room.setIsAvailable(false);
                return true; //Succesfully marked as unavailable
            }
        }
        return false;    //Room not found or is already booked     
    }
    
    public boolean markRoomAvailable(String roomNumber){
        for(Room room : rooms){
            if(room.getNumber().equals(roomNumber) && room.isIsAvailable()){
                room.setIsAvailable(true);
                return true;  //successfully marked as Available
            }
        }
        return false;  //Room not found or not booked
    }
    
    public List<Room> getAllRooms(){
        return new ArrayList<>(rooms);
    }
}
