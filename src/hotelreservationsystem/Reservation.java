
package hotelreservationsystem;

import java.util.Date;


public class Reservation {
    private String reservationID;
    private String customerName;
    private Room room;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(String reservationID, Room room, Date checkInDate, Date checkOutDate, String customerName){
        this.room = room;
        this.reservationID = reservationID;
        this.customerName = customerName;
        this.checkOutDate = checkOutDate; 
        this.checkInDate = checkInDate;
    }

    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCutomerName(String customerName) {
        this.customerName = customerName;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
    
    @Override
    public String toString(){
        return "Reservation{ ReservationID: " + reservationID + ", Customer Name: " + customerName + ", Room is: " + room + ", CheckIn Date: " + checkInDate + ", CheckOut Date: " + checkOutDate + " }";
    }
    
}
