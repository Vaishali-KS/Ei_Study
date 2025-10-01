README.md
# Placement Exercises Repository

This repository contains **two placement exercises** developed in Java, designed for placement rounds and practical demonstrations of OOP concepts, user interaction, and design patterns.

---

## **Exercise 1: Design Patterns – Console App**

This exercise demonstrates **6 real-time use cases** of **Behavioral, Creational, and Structural design patterns**. All examples are **interactive** and take input from the user.

### **Use Cases**

| Pattern Type | Design Pattern | Example / Scenario |
|--------------|----------------|-----------------|
| Behavioral | Observer Pattern | **Stock Market Ticker** – Users subscribe to stock updates; notifications sent automatically when stock prices change. |
| Behavioral | Strategy Pattern | **Route Finder / GPS Navigation** – User inputs source & destination; selects strategy (Shortest, Fastest, Scenic). |
| Creational | Factory Pattern | **Vehicle Rental Service** – User chooses vehicle type (Car/Bike/Truck); factory returns appropriate vehicle object. |
| Creational | Builder Pattern | **Computer Assembly** – User selects components (CPU, RAM, Storage); builder creates computer object dynamically. |
| Structural | Adapter Pattern | **Currency Converter** – Converts USD → INR; adapter allows incompatible interfaces to work together. |
| Structural | Decorator Pattern | **Coffee Shop** – Base coffee decorated dynamically with Milk, Sugar, etc.; adds cost and description dynamically. |
| Structural | Proxy Pattern (optional) | **Document Access** – Users access documents; proxy controls access and lazy-loads documents based on authorization. |

### **Folder Structure**



Exercise1_DesignPatterns/
├── observer/
├── strategy/
├── factory/
├── builder/
├── adapter/
├── decorator/
├── proxy/
└── MainApp.java


### **How to Run**

1. Open terminal or IDE.
2. Navigate to `Exercise1_DesignPatterns`.
3. Compile and run:

```bash
javac */*.java MainApp.java
java MainApp



Exercise 2: Smart Office – Console App (Java)

A console-based Smart Office simulation demonstrating room management, occupancy detection, and automation.

Features

Room configuration and capacity management

Booking and cancellation of rooms

Occupancy detection via sensors (room is "occupied" when ≥ 2 persons)

Auto-release of bookings if no occupancy within configured time (default 15s for testing)

Uses design patterns:

Singleton (OfficeConfig)

Observer (RoomObserver -> Light/AC)

Command (BookRoomCommand, CancelRoomCommand, AddOccupantCommand)

Folder Structure
smart-office/
 ├─ pom.xml
 └─ src/
    ├─ main/
    │  ├─ java/
    │  │  └─ com/example/smartoffice/
    │  │     ├─ app/
    │  │     │  └─ ConsoleAppRunner.java
    │  │     ├─ config/
    │  │     │  └─ OfficeConfig.java
    │  │     ├─ model/
    │  │     │  ├─ Room.java
    │  │     │  ├─ Booking.java
    │  │     │  └─ User.java
    │  │     ├─ repository/
    │  │     │  ├─ RoomRepository.java
    │  │     │  └─ BookingRepository.java
    │  │     ├─ service/
    │  │     │  ├─ BookingService.java
    │  │     │  └─ RoomService.java
    │  │     ├─ command/
    │  │     │  ├─ Command.java
    │  │     │  ├─ BookRoomCommand.java
    │  │     │  ├─ CancelRoomCommand.java
    │  │     │  └─ CommandManager.java
    │  │     ├─ observer/
    │  │     │  ├─ RoomSubject.java
    │  │     │  ├─ OccupancySensor.java
    │  │     │  ├─ LightController.java
    │  │     │  └─ ACController.java
    │  │     ├─ scheduler/
    │  │     │  └─ AutoReleaseScheduler.java
    │  │     ├─ util/
    │  │     │  └─ TimeUtils.java
    │  │     └─ exceptions/
    │  │        └─ (custom exceptions)
    │  └─ resources/
    └─ test/
       └─ java/ (unit tests)

Build & Run

Requires JDK 17 and Maven.

Build:

mvn clean package


Run (from project root):

mvn exec:java -Dexec.mainClass="com.example.smartoffice.app.ConsoleAppRunner"

Commands
Command	Description
config room count <n>	Configure total number of rooms
config room maxcapacity <roomId> <capacity>	Set max capacity for a room
block room <roomId> <HH:mm> <durationMinutes> <bookedBy>	Book a room
cancel room <roomId>	Cancel room booking
add occupant <roomId> <count>	Add occupants to a room
status room <roomId>	Check room status
stats	Show overall statistics
help	Show available commands
exit	Exit the application
Repository Structure
PlacementExercises/
 ├── Exercise1_DesignPatterns/
 └── Exercise2_SmartOffice/

