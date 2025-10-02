# CODING EXERCISES

This repository contains **two Java console applications** demonstrating **Behavioural, Creational, and Structural Design Patterns** with **real-world, interactive examples**. Both exercises are designed with **strong OOP principles**, **user input-driven functionality**, and **well-structured code**.

* **`Name`** : Vaishali K S
* **`Roll No`** : 224130
* **`College`** : K L N College Of Engineering
---

## **Exercise 1: Design Patterns – Real-Time Use Cases**

**Overview:**
This console application demonstrates **6 real-time use cases** covering **Behavioural, Creational, and Structural design patterns**. The program is **interactive** and takes **user input** to perform different actions.

**Use Cases & Design Patterns**

**Behavioural Patterns** – Define communication and responsibilities between objects:

1. **Observer Pattern – Stock Market Ticker**: Users subscribe to stock updates. Notifies all subscribers automatically when stock prices change.
2. **Strategy Pattern – Route Finder**: User inputs source and destination. Selects strategy: **Shortest, Fastest, or Scenic route**.

**Creational Patterns** – Object creation flexibility and scalability:

3. **Factory Pattern – Vehicle Rental Service**: User selects vehicle type (**Car, Bike, Truck**). Factory creates the appropriate vehicle object.
4. **Builder Pattern – Computer Assembly**: User selects type of computer (**Gaming or Office**). Builder assembles **CPU, RAM, Storage** automatically.

**Structural Patterns** – Combine objects/classes into larger structures:

5. **Adapter Pattern – Currency Converter**: Converts **USD to INR** using an adapter to match expected interface.
6. **Decorator Pattern – Coffee Shop**: User chooses base coffee and can add **Milk, Sugar, or other toppings**. Dynamically calculates final description and price.

**Class & Package Overview**

* **Packages**: `behavioral` (Observer & Strategy), `creational` (Factory & Builder), `structural` (Adapter & Decorator), `app` (MainApp class)
* **Key Classes**: `Stock`, `User`, `RouteStrategy`, `VehicleFactory`, `ComputerBuilder`, `CurrencyAdapter`, `Coffee`, `CoffeeDecorator`, `MainApp`

**Sample Outputs**

**Observer Pattern – Stock Market:**

Subscribe to stock: AAPL

Subscribe to stock: TSLA

Update AAPL price to 150

Notification for AAPL: New price = 150

Update TSLA price to 720

Notification for TSLA: New price = 720

**Strategy Pattern – Route Finder:**

Enter source: Home

Enter destination: Office

Choose route strategy: 1) Shortest 2) Fastest 3) Scenic

Enter choice: 2

Fastest route from Home to Office: Home -> Highway -> Office

**Factory Pattern – Vehicle Rental:**

Choose vehicle type to rent: Car

Vehicle created: Car [Seats=4, Fuel=Petrol]

**Builder Pattern – Computer Assembly:**

Build Gaming Computer

Your Gaming Computer: CPU=Intel i9, RAM=32GB, Storage=1TB SSD

Build Office Computer

Your Office Computer: CPU=Intel i5, RAM=16GB, Storage=512GB SSD

**Adapter Pattern – Currency Converter:**

Enter amount in USD: 100

Converted amount in INR: 8250

**Decorator Pattern – Coffee Shop:**

Base Coffee selected: Espresso

Add Milk? yes

Add Sugar? yes

Final Coffee: Espresso + Milk + Sugar

Total Price: 60

---

## **Exercise 2: Smart Office Management System**

**Overview:**
A **console-based Smart Office simulation** for managing conference rooms, occupancy detection, and automated control of lights and AC. Uses **Singleton, Observer, and Command patterns**.

**Key Features :**

* `Office Setup`: Easily configure the number of conference rooms and assign names. The Singleton pattern guarantees only one configuration instance exists.
* `Room Reservation & Cancellation`: Book or cancel rooms with real-time updates. The system can automatically release rooms that remain unoccupied beyond a predefined duration.
* `Occupancy Monitoring`: Tracks room occupancy using sensors. Observers, such as lights and AC systems, are notified immediately when occupancy changes.
* `Automated Environment Control`: Lights and air conditioning respond automatically based on occupancy, enhancing energy efficiency and comfort.

**Applied Design Patterns:**

* `Singleton`: Ensures a single, centralized configuration for the office environment.
* `Observer`: Enables rooms to notify associated systems (lights, AC) whenever occupancy changes.
* `Command`: Encapsulates room operations (booking, cancellation, updates) into independent commands, promoting maintainability and flexibility.

**Class Descriptions**

* `Observer.java` – Defines the Observer interface with the update() method.
* `ControlSystem.java` – Represents controllable systems (lights, AC) that react to room status changes.
* `Sensor.java` – Implements the Observer interface to monitor room occupancy.
* `Room.java` – Represents a conference room, managing bookings, occupancy, and observer notifications.
* `SmartOffice.java` – Singleton class managing overall office setup and operations.
* `Command.java` – Base interface for command classes with the execute() method.
* `ConfigureRoomsCommand.java` – Handles creation and setup of conference rooms.
* `SetRoomCapacityCommand.java` – Assigns maximum capacity to each room.
* `BookRoomCommand.java` – Processes room booking requests.
* `AddOccupantsCommand.java` – Adds occupants to a room while updating observers automatically.
* `CancelBookingCommand.java` – Handles booking cancellations.

**Commands**

* **config room count <n>**
* **config room maxcapacity <roomId> <capacity>**
* **block room <roomId> [HH:mm](HH:mm) <durationMinutes> <bookedBy>**
* **cancel room <roomId>**
* **add occupant <roomId> <count>**
* **status room <roomId>**
* **stats**
* **help**
* **exit**

**Sample Output**

> config room count 3
> Total rooms configured: 3

> config room maxcapacity 1 5
> Room 1 max capacity set to 5

> block room 1 10:00 30 Alice
> Room 1 booked by Alice from 10:00 for 30 minutes

> add occupant 1 2
> Added 2 occupants to Room 1
> Room 1 is now occupied. Lights and AC turned ON

> status room 1
> Room 1 Status:
> Booked by: Alice
> Occupants: 2
> Light: ON
> AC: ON

> cancel room 1
> Room 1 booking canceled
> Lights and AC turned OFF

**Note:**

* **Auto-release:** Unoccupied rooms are automatically canceled after a set time (default 300s for testing).
* Interactive commands include: block room, cancel room, add occupant, status room, stats, help, exit.

---

**Summary**
This repository demonstrates:

* **Strong OOP principles**: Encapsulation, Inheritance, Polymorphism, Interfaces
* **Real-time interactive applications**
* **Proper use of Behavioural, Creational, and Structural design patterns**
* **User input-driven programs** with clear sample outputs

---


