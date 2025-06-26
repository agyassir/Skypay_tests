package Model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Booking {
    private static   int id=0;
    private Room room;
    private User client;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private int totalCost;

    private LocalDate BookingTime;

    public Booking(Room room, User client, LocalDate checkIn, LocalDate checkOut, int totalCost, LocalDate BookingTime) {
        this.id ++;
        this.room = room;
        this.client = client;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalCost = totalCost;
        this.BookingTime=BookingTime;
    }

    public Booking() {
    }

    public LocalDate getBookingTime() {
        return BookingTime;
    }

    public void setBookingTime(LocalDate bookingTime) {
        BookingTime = bookingTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format(
                "🧾 Booking Summary\n" +
                        "--------------------------\n" +
                        "👤 Client ID       : %d\n" +
                        "🛏️ Room          : %s\n" +
                        "📅 Check-in      : %s\n" +
                        "📅 Check-out     : %s\n" +
                        "💰 Total Cost    : %d\n",
                client.getId(),
                room,
                checkIn.format(formatter),
                checkOut.format(formatter),
                totalCost
        );
    }

}
