import Model.RoomType;
import Service.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        try {
            Service service = new Service();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            System.out.println("--- Setting up rooms ---");
            service.setRoom(1, RoomType.Standard, 1000);
            service.setRoom(2, RoomType.Junior, 2000);
            service.setRoom(3, RoomType.Master, 3000);

            System.out.println("\n--- Setting up users ---");
            service.setUser(1, 5000);
            service.setUser(2, 10000);

            System.out.println("\n--- Running booking test cases ---");

            try {
                service.bookRoom(1, 2,
                        LocalDate.parse("30/06/2026", formatter),
                        LocalDate.parse("07/07/2026", formatter)); // ✅ Valid
            } catch (Exception e) {
                System.err.println("Failed: " + e.getMessage());
            }

            try {
                service.bookRoom(1, 2,
                        LocalDate.parse("07/07/2026", formatter),
                        LocalDate.parse("30/06/2026", formatter)); // ❌ Invalid dates
            } catch (Exception e) {
                System.err.println("Expected failure (date error): " + e.getMessage());
            }

            try {
                service.bookRoom(1, 1,
                        LocalDate.parse("07/07/2026", formatter),
                        LocalDate.parse("08/07/2026", formatter)); // ✅ Valid
            } catch (Exception e) {
                System.err.println("Failed: " + e.getMessage());
            }

            try {
                service.bookRoom(2, 1,
                        LocalDate.parse("07/07/2026", formatter),
                        LocalDate.parse("09/07/2026", formatter)); // ❌ Overlap
            } catch (Exception e) {
                System.err.println("Expected failure (overlap): " + e.getMessage());
            }

            try {
                service.bookRoom(2, 3,
                        LocalDate.parse("07/07/2026", formatter),
                        LocalDate.parse("08/07/2026", formatter)); // ✅ Valid
            } catch (Exception e) {
                System.err.println("Failed: " + e.getMessage());
            }

            System.out.println("\n--- Updating a room ---");
            service.setRoom(1, RoomType.Master, 10000);

            System.out.println("\n--- FINAL STATE ---");
            service.printAll();
            service.printAllUsers();

        } catch (DateTimeParseException e) {
            System.err.println("An error occurred while parsing dates. Please check the date format.");
            e.printStackTrace();
        }

    }
}