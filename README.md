# Skypay Technical Test 1: Banking Service

## 📝 Overview

This project is a simple simulation of a core banking system developed as part of the **Skypay Technical Test 1**. The main features include the ability to:

- Deposit money
- Withdraw money
- Print a transaction statement

The system is implemented in a clean, testable, and extensible manner, adhering to fundamental software engineering principles.

---

## 🚀 Features

- Deposit and withdraw integer amounts
- Track all transactions with date
- Generate printable bank statements
- Basic error handling for invalid operations
- Uses in-memory storage (via `ArrayList`)

---

## 🛠️ Technologies

- **Language:** Java
- **Data Structures:** ArrayList
- **Precision:** `int` used for simplicity (as specified)

---

## 📄 Usage Example

Here’s how the application behaves based on the acceptance criteria:

### Scenario:

```text
Given:
- A deposit of 1000 on 10-01-2012
- A deposit of 2000 on 13-01-2012
- A withdrawal of 500 on 14-01-2012

When:
- The client prints their statement

Then:
- The output will be:
    
Date       | Amount | Balance  
-----------|--------|---------
14-01-2012 | -500   | 2500  
13-01-2012 | +2000  | 3000  
10-01-2012 | +1000  | 1000  
