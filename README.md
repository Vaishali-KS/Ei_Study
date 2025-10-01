## **Exercise 1: Design Patterns – Console App**

**Description:**  
This console application demonstrates **6 real-time use cases** of **Behavioral, Creational, and Structural design patterns**. The program is **interactive** and takes **user input** to perform different actions.

### **Use Cases & Behavior**

**1. Observer Pattern – Stock Market Ticker**  
- Users subscribe to stock updates.  
- Whenever a stock price changes, all subscribers are notified automatically.  

**Sample Output:**
Subscribe to stock: AAPL
Subscribe to stock: TSLA
Update AAPL price to 150
Notification for AAPL: New price = 150
Update TSLA price to 720
Notification for TSLA: New price = 720

markdown
Copy code

**2. Strategy Pattern – Route Finder**  
- User inputs **source** and **destination**.  
- Chooses strategy: Shortest, Fastest, or Scenic route.  

**Sample Output:**
Enter source: Home
Enter destination: Office
Choose route strategy: 1) Shortest 2) Fastest 3) Scenic
Enter choice: 2
Fastest route from Home to Office: Home -> Highway -> Office

markdown
Copy code

**3. Factory Pattern – Vehicle Rental Service**  
- User selects vehicle type (**Car/Bike/Truck**).  
- Factory returns appropriate vehicle object with details.  

**Sample Output:**
Choose vehicle type to rent: Car
Vehicle created: Car [Seats=4, Fuel=Petrol]

markdown
Copy code

**4. Builder Pattern – Computer Assembly**  
- User selects type of computer: Gaming or Office.  
- Builder assembles CPU, RAM, Storage automatically.  

**Sample Output:**
Build Gaming Computer
Your Gaming Computer: CPU=Intel i9, RAM=32GB, Storage=1TB SSD
Build Office Computer
Your Office Computer: CPU=Intel i5, RAM=16GB, Storage=512GB SSD

vbnet
Copy code

**5. Adapter Pattern – Currency Converter**  
- Converts USD to INR using an adapter to match expected interface.  

**Sample Output:**
Enter amount in USD: 100
Converted amount in INR: 8250

markdown
Copy code

**6. Decorator Pattern – Coffee Shop**  
- User chooses base coffee and can add **Milk, Sugar, or other toppings**.  
- Final cost and description is displayed dynamically.  

**Sample Output:**
Base Coffee selected: Espresso
Add Milk? yes
Add Sugar? yes
Final Coffee: Espresso + Milk + Sugar
Total Price: 60

markdown
Copy code

---

## **Exercise 2: Smart Office – Console App**

**Description:**  
Simulates a **Smart Office environment**. The console app allows **room booking, occupancy detection**, and **automation** using **Singleton, Observer, and Command patterns**. Users can configure rooms, book, cancel, add occupants, and see room status.

### **Overview**
Console-based Smart Office simulation demonstrating:  
- **Room configuration** and capacity management  
- **Booking and cancellation**  
- **Occupancy detection** via sensors (room is "occupied" when >= 2 persons)  
- **Auto-release** of bookings if no occupancy within configured time (default 15s for testing)  
- **Design patterns:** Singleton (**OfficeConfig**), Observer (**RoomObserver → Light/AC**), Command (**Book/Cancel/AddOccupant**)

### **Commands**
- `config room count <n>`  
- `config room maxcapacity <roomId> <capacity>`  
- `block room <roomId> <HH:mm> <durationMinutes> <bookedBy>`  
- `cancel room <roomId>`  
- `add occupant <roomId> <count>`  
- `status room <roomId>`  
- `stats`  
- `help`  
- `exit`

### **Sample Output**
config room count 3
Total rooms configured: 3

config room maxcapacity 1 5
Room 1 max capacity set to 5

block room 1 10:00 30 Alice
Room 1 booked by Alice from 10:00 for 30 minutes

add occupant 1 2
Added 2 occupants to Room 1
Room 1 is now occupied. Lights and AC turned ON

status room 1
Room 1 Status:
Booked by: Alice
Occupants: 2
Light: ON
AC: ON

cancel room 1
Room 1 booking canceled
Lights and AC turned OFF

pgsql
Copy code

**Note:**  
- **Auto-release:** If a booked room has no occupancy within 15 seconds (for testing), the booking is canceled automatically.  
- Interactive commands include: **block room, cancel room, add occupant, status room, stats, help, 
