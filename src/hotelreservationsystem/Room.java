
package hotelreservationsystem;


public class Room {
    private String number;
    private RoomCategory category;
    private double price;
    private boolean isAvailable;
    
    public enum RoomCategory {
        SINGLE,
        DOUBLE,
        SUITE
    }
    
    public Room(String number, RoomCategory category, double price){
        this.number = number;
        this.category = category;
        this.price = price;
        this.isAvailable = true;    
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public RoomCategory getCategory() {
        return category;
    }

    public void setCategory(RoomCategory category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    
    @Override
    public String toString(){
        return "Room{ number: " + number + ", Category: " + category + ", Available: " + isAvailable + ", price: " + price + " }"; 
    }
}
