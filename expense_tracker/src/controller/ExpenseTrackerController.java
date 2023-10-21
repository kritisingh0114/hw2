package controller;

import view.ExpenseTrackerView;

import java.awt.Color;
import java.awt.Component;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import model.ExpenseTrackerModel;
import model.Transaction;
import model.CategoryFilter;
import model.AmountFilter;

public class ExpenseTrackerController {
  private static final int AMOUNT_COL = 1;
  private static final int CATEGORY_COL = 2;
  
  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;

  public ExpenseTrackerController(ExpenseTrackerModel model, ExpenseTrackerView view) {
    this.model = model;
    this.view = view;

    // Set up view event handlers
  }

  public void refresh() {

    // Get transactions from model
    List<Transaction> transactions = model.getTransactions();

    // Pass to view
    view.refreshTable(transactions);

  }

  public boolean addTransaction(double amount, String category) {
    if (!InputValidation.isValidAmount(amount)) {
      return false;
    }
    if (!InputValidation.isValidCategory(category)) {
      return false;
    }
    
    Transaction t = new Transaction(amount, category);
    model.addTransaction(t);
    view.getTableModel().addRow(new Object[]{t.getAmount(), t.getCategory(), t.getTimestamp()});
    refresh();
    return true;
  }

  //This function applies the filter to the jTable giving the amount or the category the user would like to filter by
  public List<Transaction> applyFilter(JTable transactionsTable, String amount, String category, boolean emptyAmountInput) {
    boolean isCategoryValid = true;
    boolean isAmountValid = true;
    List<Transaction> retList = Collections.emptyList();
    //checks if the amount is valid
    for (int i = 0; i < amount.length(); i++){
      if (amount.equals("-1") && emptyAmountInput){
        isAmountValid = true;
        break;
      }
      try {
        Double doub = Double.parseDouble(amount);
      }catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(view, "Invalid amount filter or category filter entered");
        view.toFront();
        return Collections.emptyList();
      }
    }
    if (!InputValidation.isValidAmount(Double.parseDouble(amount))) {
      if(Double.parseDouble(amount) == -1 && emptyAmountInput) {
        isAmountValid = true;
      } else {
        isAmountValid = false;
      }
    }
    //check if category is valid
    if (!InputValidation.isValidCategory(category)) {
      if(category.trim().isEmpty()) {
        isCategoryValid = true;
      } else {
        isCategoryValid = false;
      }
    }
    //if either amount or category are invalid then display a message to the user
    if(!isAmountValid || !isCategoryValid){
      JOptionPane.showMessageDialog(view, "Invalid amount filter or category filter entered");
      view.toFront();
      return Collections.emptyList();
    }
    // if amount and category is valid but both are entered in, display a message to the user
    if(InputValidation.isValidCategory(category) && InputValidation.isValidAmount(Double.parseDouble(amount))) {
      JOptionPane.showMessageDialog(view, "Enter one filter at a time!");
      view.toFront();
      return Collections.emptyList();
    }
    //perform the filtering for category
    if(InputValidation.isValidCategory(category)) {
      CategoryFilter categoryFilter = new CategoryFilter();
      retList = categoryFilter.filter(model.getTransactions(), category);
    }
    if (InputValidation.isValidCategory(category)){
      getCatergoryRenderedTable(transactionsTable, category, CATEGORY_COL);
    }
    //perform the filtering for amount
    if(InputValidation.isValidAmount(Double.parseDouble(amount))) {
      AmountFilter amountFilter = new AmountFilter();
      retList = amountFilter.filter(model.getTransactions(), amount);
    }
    if (InputValidation.isValidAmount(Double.parseDouble(amount))){
      getAmountRenderedTable(transactionsTable, Double.parseDouble(amount), AMOUNT_COL);
    }
    //reset the table to no highlighted rows
    if (Double.parseDouble(amount) == -1 && category.trim().isEmpty()){
      getCatergoryRenderedTable(transactionsTable, category, CATEGORY_COL);
    }
    refresh();
    return retList;
  }
  // Other controller methods

  //gets a new highlited defaults rendered table with the category
  private static void getCatergoryRenderedTable(JTable table, String filterInput, int column) {
    table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
      @Override
      public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
          Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
          String curRowItem = (String)table.getModel().getValueAt(row, column);
          if (filterInput.equals(curRowItem)) {
            c.setBackground(new Color(173, 255, 168));
          } else {
            c.setBackground(table.getBackground());
          }       
          return c;
      }   
    });
  }
  //gets a new highlited defaults rendered table with the category
  private static void getAmountRenderedTable(JTable table, double filterInput, int column) {
  table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
        double curRowItem = -1;
        if(table.getModel().getValueAt(row, column) != null){
          curRowItem = (double)table.getModel().getValueAt(row, column);
        }
        if (filterInput == curRowItem) {
          c.setBackground(new Color(173, 255, 168));
        } else {
          c.setBackground(table.getBackground());
        }       
        return c;
      }   
    });
  }

}

