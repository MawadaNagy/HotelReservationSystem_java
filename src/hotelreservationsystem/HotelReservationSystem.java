
package hotelreservationsystem;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class HotelReservationSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        RoomService roomService = new RoomService();
        ReservationService reservationService = new ReservationService(roomService);

        // Sample rooms
        roomService.addRoom(new Room("101", Room.RoomCategory.SINGLE, 100));
        roomService.addRoom(new Room("102", Room.RoomCategory.DOUBLE, 200));
        roomService.addRoom(new Room("103", Room.RoomCategory.SUITE, 300));
        roomService.addRoom(new Room("104", Room.RoomCategory.SINGLE, 100));
        roomService.addRoom(new Room("105", Room.RoomCategory.DOUBLE, 200));
        roomService.addRoom(new Room("106", Room.RoomCategory.SUITE, 300));
        roomService.addRoom(new Room("107", Room.RoomCategory.SINGLE, 100));
        roomService.addRoom(new Room("108", Room.RoomCategory.DOUBLE, 200));
        roomService.addRoom(new Room("109", Room.RoomCategory.SUITE, 300));

        System.out.println("Welcome to the Hotel Reservation System!");

        while (true) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. View available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View all reservations");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter room category (SINGLE, DOUBLE, SUITE): ");
                    String categoryStr = sc.nextLine().toUpperCase();
                    try {
                        Room.RoomCategory category = Room.RoomCategory.valueOf(categoryStr);
                        List<Room> availableRooms = roomService.searchAvailableRooms(category);
                        if (availableRooms.isEmpty()) {
                            System.out.println("No available rooms for the category: " + category);
                        } else {
                            System.out.println("Available rooms:");
                            for (Room room : availableRooms) {
                                System.out.println(room);
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid category. Please try again.");
                    }
                    break;
                case 2:
                    System.out.print("Enter room category for reservation (SINGLE, DOUBLE, SUITE): ");
                    String reservationCategoryStr = sc.nextLine().toUpperCase();
                    try {
                        Room.RoomCategory reservationCategory = Room.RoomCategory.valueOf(reservationCategoryStr);
                        List<Room> availableReservationRooms = roomService.searchAvailableRooms(reservationCategory);
                        if (availableReservationRooms.isEmpty()) {
                            System.out.println("No available rooms for the category: " + reservationCategory);
                            break;
                        }

                        System.out.println("Available rooms for " + reservationCategory + ":");
                        for (Room room : availableReservationRooms) {
                            System.out.println(room);
                        }

                        System.out.print("Enter room number from available rooms: ");
                        String selectedRoomNumber = sc.nextLine();
                        Room selectedRoom = availableReservationRooms.stream()
                                .filter(r -> r.getNumber().equals(selectedRoomNumber))
                                .findFirst()
                                .orElse(null);

                        if (selectedRoom == null) {
                            System.out.println("Invalid room number or room not available.");
                            break;
                        }

                        System.out.print("Enter check-in date (yyyy-MM-dd): ");
                        String checkIn = sc.nextLine();
                        System.out.print("Enter check-out date (yyyy-MM-dd): ");
                        String checkOut = sc.nextLine();
                        System.out.print("Enter customer name: ");
                        String customerName = sc.nextLine();

                        try {
                            Date checkInDate = dateFormat.parse(checkIn);
                            Date checkOutDate = dateFormat.parse(checkOut);

                            Reservation reservation = reservationService.makeReservation(selectedRoom.getCategory(), checkInDate, checkOutDate, customerName);
                            if (reservation != null) {
                                System.out.println("Reservation successful: " + reservation);
                            } else {
                                System.out.println("Failed to make a reservation. Please try different dates or room.");
                            }
                        } catch (ParseException e) {
                            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid category. Please try again.");
                    }
                    break;

                case 3:
                    List<Reservation> allReservations = reservationService.getAllReservations();
                    if (allReservations.isEmpty()) {
                        System.out.println("No reservations found.");
                    } else {
                        for (Reservation reservation : allReservations) {
                            System.out.println(reservation);
                        }
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.out.println("\tThank you for using our Hotel Reservation System!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}