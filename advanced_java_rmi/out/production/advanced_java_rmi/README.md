# RMI Price Calculator Application

This is a Java RMI (Remote Method Invocation) client-server application that calculates the selling price of an item after applying a discount.

## Problem Statement
Find the selling price of an item with cost price Rs. 34000 after a discount of Rs. 3000.

## Files Description

1. **PriceCalculator.java** - Remote interface defining the contract for price calculation
2. **PriceCalculatorImpl.java** - Implementation of the remote interface
3. **PriceServer.java** - RMI server that hosts the price calculator service
4. **PriceClient.java** - RMI client that connects to the server and performs calculations

## How to Run in IntelliJ

### Step 1: Compile the Project
1. Open IntelliJ IDEA
2. Open this project folder
3. Make sure all Java files are in the same directory
4. Build the project (Build → Build Project)

### Step 2: Start the RMI Registry (Optional)
If you encounter issues, you can manually start the RMI registry:
```bash
rmiregistry 1099
```

### Step 3: Run the Server
1. Right-click on `PriceServer.java`
2. Select "Run 'PriceServer.main()'"
3. You should see: "Price Calculator Server is running..."

### Step 4: Run the Client
1. Right-click on `PriceClient.java`
2. Select "Run 'PriceClient.main()'"
3. You should see the calculation results

## Expected Output

When you run the client, you should see:
```
=== Price Calculation Results ===
Cost Price: Rs. 34000.0
Discount: Rs. 3000.0
Selling Price: Rs. 31000.0
================================
```

## Troubleshooting

1. **Port already in use**: Make sure no other application is using port 1099
2. **Connection refused**: Ensure the server is running before the client
3. **Security manager issues**: The application doesn't require a security manager for local testing

## Calculation Logic

The selling price is calculated as:
```
Selling Price = Cost Price - Discount
Selling Price = 34000 - 3000 = 31000
``` 