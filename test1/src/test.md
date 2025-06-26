# Skypay Technical Test Instructions

This document provides a step-by-step guide for completing the two technical tests from Skypay.

---

## Part 1: Banking Service (Technical Test 1)

[cite_start]**Objective:** Implement a service to handle deposits, withdrawals, and print transaction statements[cite: 32].

### Step-by-Step Instructions:

1.  **Create the `Account` Class:**
    * [cite_start]Write a class named `Account`[cite: 33].
    * [cite_start]This class must implement the `AccountService` interface[cite: 33].

2.  **Implement the `AccountService` Interface:**
    * [cite_start]Define the public interface `AccountService` with the following methods[cite: 33]:
        * `void deposit(int amount)`
        * `void withdraw(int amount)`
        * `void printStatement()`

3.  **Implement Core Methods:**
    * [cite_start]**`deposit(int amount)`:** This method should add the specified `amount` to the account's balance[cite: 32].
    * [cite_start]**`withdraw(int amount)`:** This method should subtract the specified `amount` from the account's balance[cite: 32].
    * [cite_start]**`printStatement()`:** This method should print a formatted history of all transactions[cite: 32].

4.  **Data Storage:**
    * [cite_start]Use `ArrayLists` to store transaction data[cite: 38]. [cite_start]Do not use repositories[cite: 37].

5.  **Statement Formatting:**
    * [cite_start]The printed statement must show the `Date`, `Amount`, and running `Balance` for each transaction[cite: 35].
    * [cite_start]Transactions should be listed in reverse chronological order (most recent first)[cite: 35].
    * [cite_start]The output format should match the example provided[cite: 35].

6.  **Exception Handling:**
    * [cite_start]Implement exception handling for scenarios like invalid inputs[cite: 36].

7.  **Test Your Code:**
    * [cite_start]Execute the provided test case to ensure your implementation is correct[cite: 34]:
        1.  [cite_start]Make a deposit of 1000[cite: 34].
        2.  [cite_start]Make a deposit of 2000[cite: 34].
        3.  [cite_start]Make a withdrawal of 500[cite: 34].
        4.  [cite_start]Call `printStatement()` and verify the output matches the example[cite: 35].

---

## Part 2: Hotel Reservation System (Technical Test 2)

**Objective:** Manage rooms, users, and bookings for a simplified hotel system.

### Step-by-Step Instructions:

1.  **Create Entities:**
    * [cite_start]**`User` Entity:** Must have a `balance`[cite: 4].
    * [cite_start]**`Room` Entity:** Must have a `type` and a `price` per night[cite: 4].
        * [cite_start]Room types are: `standard suite`, `junior suite`, and `master suite`[cite: 5].
    * **`Booking` Entity:** You must design and implement this entity yourself. [cite_start]It should contain all necessary information about the room and user at the time of booking[cite: 7, 15].

2.  **Set up the `Service` Class:**
    * [cite_start]In the `Service` class, create `ArrayLists` to store `Room` and `User` objects[cite: 17].

3.  **Implement Service Methods:**
    * [cite_start]**`setUser(int userId, int balance)`:** Creates a new user if one with the given ID doesn't already exist[cite: 13, 20].
    * **`setRoom(int roomNumber, Room.RoomType roomType, int roomPricePerNight)`:**
        * [cite_start]Creates a room if it does not already exist[cite: 12, 19].
        * [cite_start]This function must **not** impact any previously created bookings[cite: 11].
    * **`bookRoom(int userId, int roomNumber, Date checkin, Date checkOut)`:**
        * [cite_start]Books a room for a user for a specific period[cite: 9].
        * [cite_start]It must check if the user has enough `balance` for the entire stay[cite: 9].
        * [cite_start]It must check if the room is available for the entire period[cite: 9].
        * [cite_start]If the booking is successful, the user's `balance` must be updated[cite: 10].
    * **`printAllUsers()`:**
        * [cite_start]Prints the data for all users[cite: 16].
        * [cite_start]The output must be sorted from the most recently created user to the oldest[cite: 16].
    * **`printAll()`:**
        * [cite_start]Prints data for all rooms and all bookings[cite: 14].
        * [cite_start]The output must be sorted from the most recently created to the oldest[cite: 14].
        * [cite_start]Booking data must include all details about the room and user as they were at the moment of booking[cite: 15].

4.  **Handle Technical Requirements:**
    * [cite_start]When checking dates, consider only the day, month, and year[cite: 17].
    * [cite_start]Implement exception handling for invalid inputs[cite: 18].

5.  **Execute the Test Case:**
    * **Create Rooms:**
        * [cite_start]Room 1: ID 1, Type standard, Price/night 1000[cite: 21].
        * [cite_start]Room 2: ID 2, Type junior, Price/night 2000[cite: 21].
        * [cite_start]Room 3: ID 3, Type suite, Price/night 3000[cite: 21].
    * **Create Users:**
        * [cite_start]User 1: ID 1, Balance 5000[cite: 22].
        * [cite_start]User 2: ID 2, Balance 10000[cite: 22].
    * **Perform Bookings:**
        * [cite_start]User 1 tries to book Room 2 from 30/06/2026 to 07/07/2026[cite: 22].
        * [cite_start]User 1 tries to book Room 2 from 07/07/2026 to 30/06/2026 (invalid dates)[cite: 23].
        * [cite_start]User 1 tries to book Room 1 from 07/07/2026 to 08/07/2026[cite: 24].
        * [cite_start]User 2 tries to book Room 1 from 07/07/2026 to 09/07/2026[cite: 25].
        * [cite_start]User 2 tries to book Room 3 from 07/07/2026 to 08/07/2026[cite: 26].
    * **Update Room:**
        * [cite_start]Call `setRoom(1, suite, 10000)`[cite: 26].
    * **Final Output:**
        * [cite_start]Take screenshots of the output from `printAll()` and `printAllUsers()` at the end[cite: 27].

6.  **Answer Design Questions (Bonus):**
    * **Question 1:** Analyze whether putting all functions into a single service is the recommended approach. [cite_start]Explain your reasoning[cite: 28].
    * **Question 2:** Propose an alternative design for the `setRoom` function. [cite_start]Explain this alternative and recommend which approach is better and why[cite: 30].