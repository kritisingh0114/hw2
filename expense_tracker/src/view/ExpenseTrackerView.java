package view;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import controller.InputValidation;
import controller.ExpenseTrackerController;



import java.awt.*;
import java.text.NumberFormat;

import model.Transaction;
import java.util.List;
import javax.swing.table.TableColumn;

import java.awt.event.*;
import java.util.regex.*;
import javax.swing.table.*;



public class ExpenseTrackerView extends JFrame {
  private static final int AMOUNT_COL = 1;
  private static final int CATEGORY_COL = 2;


  private JTable transactionsTable;
  private JButton addTransactionBtn;
  private JFormattedTextField amountField;
  private JTextField categoryField;
  private DefaultTableModel model;

  private JButton filterButton;
  private JFormattedTextField amountFilterField;
  private JTextField categoryFilterField;

  

  public ExpenseTrackerView() {
    setTitle("Expense Tracker"); // Set title
    setSize(600, 400); // Make GUI larger

    String[] columnNames = {"serial", "Amount", "Category", "Date"};
    this.model = new DefaultTableModel(columnNames, 0);

    addTransactionBtn = new JButton("Add Transaction");

    // Create UI components
    JLabel amountLabel = new JLabel("Amount:");
    NumberFormat format = NumberFormat.getNumberInstance();

    amountField = new JFormattedTextField(format);
    amountField.setColumns(10);

    
    JLabel categoryLabel = new JLabel("Category:");
    categoryField = new JTextField(10);

    // Create table
    transactionsTable = new JTable(model);
  
    // Layout components
    JPanel inputPanel = new JPanel();
    inputPanel.add(amountLabel);
    inputPanel.add(amountField);
    inputPanel.add(categoryLabel); 
    inputPanel.add(categoryField);
    inputPanel.add(addTransactionBtn);
  
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(addTransactionBtn);

    // Create UI components
    JLabel amountFilterLabel = new JLabel("Filter by Amount:");
    amountFilterField = new JFormattedTextField(format);
    amountFilterField.setFocusLostBehavior(JFormattedTextField.COMMIT);
    amountFilterField.setColumns(10);
    //add to the layout
    inputPanel.add(amountFilterLabel);
    inputPanel.add(amountFilterField);

    // Create UI componenets
    JLabel categoryFilterLabel = new JLabel("Filter by Category:");
    categoryFilterField = new JTextField(10);
    //add to the layout
    inputPanel.add(categoryFilterLabel);
    inputPanel.add(categoryFilterField);
    
    filterButton = new JButton("Filter");
    buttonPanel.add(filterButton);
  
    // Add panels to frame
    add(inputPanel, BorderLayout.NORTH);
    add(new JScrollPane(transactionsTable), BorderLayout.CENTER); 
    add(buttonPanel, BorderLayout.SOUTH);
  
    // Set frame properties
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  
  }

  public void refreshTable(List<Transaction> transactions) {
      // Clear existing rows
      model.setRowCount(0);
      // Get row count
      int rowNum = model.getRowCount();
      double totalCost=0;
      // Calculate total cost
      for(Transaction t : transactions) {
        totalCost+=t.getAmount();
      }
      // Add rows from transactions list
      for(Transaction t : transactions) {
        model.addRow(new Object[]{rowNum+=1,t.getAmount(), t.getCategory(), t.getTimestamp()}); 
      }
        // Add total row
        Object[] totalRow = {"Total", null, null, totalCost};
        model.addRow(totalRow);
  
      // Fire table update
      transactionsTable.updateUI();
  
    }  
  

  public JButton getFilterButton(){
    return filterButton;
  }

  public String getAmountFilterField(){
    return amountFilterField.getText();
  }

    public String getCategoryFilterField(){
    return categoryFilterField.getText();
  }

  public void setAmountFilterField(JFormattedTextField amountFilterField){
    this.amountFilterField = amountFilterField;
  }

  public void setCategoryFilterField(JTextField categoryFilterField){
    this.categoryFilterField = categoryFilterField;
  }
  
  public JButton getAddTransactionBtn() {
    return addTransactionBtn;
  }
  public DefaultTableModel getTableModel() {
    return model;
  }
  // Other view methods
    public JTable getTransactionsTable() {
    return transactionsTable;
  }

  public double getAmountField() {
    if(amountField.getText().isEmpty()) {
      return 0;
    }else {
    double amount = Double.parseDouble(amountField.getText());
    return amount;
    }
  }

  public void setAmountField(JFormattedTextField amountField) {
    this.amountField = amountField;
  }

  
  public String getCategoryField() {
    return categoryField.getText();
  }

  public void setCategoryField(JTextField categoryField) {
    this.categoryField = categoryField;
  }
}
