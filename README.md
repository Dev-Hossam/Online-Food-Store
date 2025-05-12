# Java Food Ordering System

A desktop food-ordering application built with Java Swing (GUI), JDBC, and MySQL.  
It features a layered architecture:

* **GUI layer** – intuitive menu, cart, and order-tracking screens  
* **Service / DAO layer** – business logic and JDBC data access  
* **MySQL database** – SQL80 schema for users, menu items, orders, order details

## Key features
- Browse menu items, add to cart, checkout
- Staff dashboard for create / update / delete of menu items and orders
- Persistent local MySQL database (set up via `schema.sql`)
- Clean code with MVC separation for easy maintenance

## Build and run
```bash
# compile
javac -classpath .;mysql-connector-j.jar -d out src/**/*.java

# run
java -classpath out;mysql-connector-j.jar com.example.Main
