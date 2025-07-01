# 🏨 Hotel Reservation System

A simple Java-based hotel reservation system built as part of the **Skypay Technical Test 2**.  
The system manages **Rooms**, **Users**, and **Bookings**, supporting core functionalities such as room creation, booking validation, user balance management, and listing of all system records.

---

## 📌 Features

- ✅ Add or update rooms (without affecting existing bookings)
- ✅ Register new users with an initial balance
- ✅ Book rooms only if:
  - The room is available during the requested period
  - The user has sufficient balance
- ✅ Automatically deducts booking cost from the user's balance
- ✅ Displays all bookings and room data (most recent first)
- ✅ Displays all user data (most recent first)
- 🚫 No persistent storage (in-memory using `ArrayList`)

---

## 📦 Technologies Used

- **Java** (Core)
- **LocalDate / DateTimeFormatter** (for date handling)
- **ArrayList** (for in-memory storage)
- No frameworks or databases used

---

## 🧾 Entities

### 👤 User
- `userId`: Unique integer
- `balance`: Integer amount representing the user's current balance

### 🛏️ Room
- `roomNumber`: Unique integer
- `type`: Enum (`STANDARD`, `JUNIOR`, `MASTER`)
- `pricePerNight`: Cost per night (int)

### 📘 Booking
- `room`, `user`, `checkIn`, `checkOut`
- `totalCost`, `bookingDate` (date of creation)
- Snapshot of room and user at booking time

---

## 🧪 Test Case (Sample)

```text
Rooms Created:
  - Room 1: STANDARD, 1000/night
  - Room 2: JUNIOR, 2000/night
  - Room 3: MASTER, 3000/night

Users Created:
  - User 1: Balance 5000
  - User 2: Balance 10000

Booking Attempts:
✅ User 1 → Room 2 from 30/06/2026 to 07/07/2026 (Success)
❌ User 1 → Room 2 from 07/07/2026 to 30/06/2026 (Invalid date)
✅ User 1 → Room 1 from 07/07/2026 to 08/07/2026 (Success)
❌ User 2 → Room 1 from 07/07/2026 to 09/07/2026 (Overlap)
✅ User 2 → Room 3 from 07/07/2026 to 08/07/2026 (Success)

Room Update:
  - Room 1 updated to MASTER, 10000/night (previous bookings unchanged)

Print Functions:
  - `printAll()` displays all rooms and bookings (latest first)
  - `printAllUsers()` displays all users (latest first)
```
## Bonus Questions
### Question 1: Suppose we put all the functions inside the same service. Is this the recommended approach ?
### Answer : No, It is not recommended, first of all it violates the first letter of the SOLID principles, which is the Single Responsibilty Principle, also to maintain the project's scaliblity and also make the structure of the project easy to read and update.
