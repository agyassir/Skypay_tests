package Service;

import Model.Booking;
import Model.Room;
import Model.RoomType;
import Model.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Service {
    private  ArrayList<Room> rooms = new ArrayList<>();
    private  ArrayList<User> users = new ArrayList<>();
    private  ArrayList<Booking> bookings = new ArrayList<>();

    public void setUser(int userId, int balance) {
        // Attempt to find an existing user
        User existingUser = findUser(userId);

        if (existingUser != null) {
            // If user exists, remove the old record to replace it (since records are immutable).
            users.remove(existingUser);
            System.out.println("Updating user ID " + userId + " with new balance: " + balance);
        } else {
            System.out.println("Creating new user ID " + userId + " with balance: " + balance);
        }

        // Add the new or updated user record to the list.
        users.add(new User(userId, balance));
    }

    public void setRoom(int id, RoomType roomType, int price) {
        Room room = findRoom(id);
        if (room != null) {
            room.setType(roomType);
            room.setPrice(price);
            System.out.println("Updated " + room);
        } else {
            Room newRoom = new Room(id, roomType, price);
            rooms.add(newRoom);
            System.out.println("Created " + newRoom.toString());
        }
    }

    public void bookRoom(int userId, int roomNumber, LocalDate checkIn, LocalDate checkOut) {
        try {
            if (checkIn == null || checkOut == null || !checkIn.isBefore(checkOut)) {
                throw new IllegalArgumentException("❌ Invalid date range: Check-in must be before check-out.");
            }

            User user = findUser(userId);
            if (user == null) {
                throw new IllegalArgumentException("❌ User not found (ID: " + userId + ").");
            }

            Room room = findRoom(roomNumber);
            if (room == null) {
                throw new IllegalArgumentException("❌ Room not found (Number: " + roomNumber + ").");
            }

            if (!isRoomAvailable(roomNumber, checkIn, checkOut)) {
                throw new IllegalStateException("⚠️ Room " + roomNumber + " is not available from " + checkIn + " to " + checkOut + ".");
            }

            long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
            if (nights <= 0) nights = 1;

            int totalCost = (int) nights * room.getPrice();
            if (user.getBalance() < totalCost) {
                throw new IllegalStateException("💸 Insufficient balance: Required = " + totalCost + ", Available = " + user.getBalance());
            }

            Booking newBooking = new Booking(room, user, checkIn, checkOut, totalCost, LocalDate.now());
            bookings.add(newBooking);

            int newBalance = user.getBalance() - totalCost;
            users.remove(user);
            users.add(new User(user.getId(), newBalance));

            System.out.printf("✅ Booking Confirmed!%n- User ID: %d%n- Room Number: %d (%s)%n- Check-in: %s%n- Check-out: %s%n- Nights: %d%n- Total Cost: %d%n- New Balance: %d%n",
                    userId, roomNumber, room.getType(), checkIn, checkOut, nights, totalCost, newBalance);

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.printf("❌ Booking failed for User %d: %s%n", userId, e.getMessage());
        }
    }


    public void printAllUsers() {
        System.out.println("\n--- All Users (Latest to Oldest) ---");
        ArrayList<User> reversedUsers = new ArrayList<>(users);
        Collections.reverse(reversedUsers); // Reverse for chronological order display
        for (User user : reversedUsers) {
            System.out.println("User ID: " + user.getId() + ", Balance: " + user.getBalance());
        }
        System.out.println("------------------------------------");
    }

    public void printAll() {
        System.out.println("\n--- All Rooms Data (Latest to Oldest) ---");
        ArrayList<Room> reversedRooms = new ArrayList<>(rooms);
        Collections.reverse(reversedRooms); // Reverse for chronological order display
        reversedRooms.forEach(System.out::println);
        System.out.println("---------------------------------------");

        System.out.println("\n--- All Bookings Data (Latest to Oldest) ---");
        // Sort bookings by the time they were created, descending.
        bookings.sort((b1, b2) -> b2.getBookingTime().compareTo(b1.getBookingTime()));
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
        System.out.println("------------------------------------------");
    }

    private User findUser(int userId) {
        for (User user : users) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }

    private Room findRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getId() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    private boolean isRoomAvailable(int roomNumber, LocalDate newCheckIn, LocalDate newCheckOut) {
        for (Booking existingBooking : bookings) {
            if (existingBooking.getRoom().getId() == roomNumber) {
                LocalDate existingCheckIn = existingBooking.getCheckIn();
                LocalDate existingCheckOut = existingBooking.getCheckOut();

                // Check if the new booking overlaps with existing one
                boolean overlaps = !(newCheckOut.isBefore(existingCheckIn) || newCheckIn.isAfter(existingCheckOut));
                if (overlaps) {
                    return false; // Conflict found
                }
            }
        }
        return true; // No conflicts
    }

}
