# hw1- Manual Review

The homework will be based on this project named "Expense Tracker",where users will be able to add/remove daily transaction. 

## Compile

To compile the code from terminal, use the following command:
```
cd src
javac ExpenseTrackerApp.java
java ExpenseTracker
```

You should be able to view the GUI of the project upon successful compilation. 

## Java Version
This code is compiled with ```openjdk 17.0.7 2023-04-18```. Please update your JDK accordingly if you face any incompatibility issue.

## New Functionality
Two text fields have been added to allow the user to be able to filter by amount or category. These fields will only accept valid amounts and valid categories and the user will not be able to enter two filters at a time. 

After the user enters a valid amount or category to the input text boxes provided, they can then click on the filter button which will highlight the rows of the table that have that same value. To reset the table (where no rows are highlighted) the user can empty both filter text boxes and click the filter button again.