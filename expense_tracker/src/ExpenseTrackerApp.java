import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ExpenseTrackerController;
import model.ExpenseTrackerModel;
import view.ExpenseTrackerView;
import model.Transaction;
import controller.InputValidation;

public class ExpenseTrackerApp {

  public static void main(String[] args) {
    
    // Create MVC components
    ExpenseTrackerModel model = new ExpenseTrackerModel();
    ExpenseTrackerView view = new ExpenseTrackerView();
    ExpenseTrackerController controller = new ExpenseTrackerController(model, view);

    // Initialize view
    view.setVisible(true);

    // Handle add transaction button clicks
    view.getAddTransactionBtn().addActionListener(e -> {
      // Get transaction data from view
      double amount = view.getAmountField();
      String category = view.getCategoryField();
      
      // Call controller to add transaction
      boolean added = controller.addTransaction(amount, category);
      
      if (!added) {
        JOptionPane.showMessageDialog(view, "Invalid amount or category entered");
        view.toFront();
      }
    });

    // Handle add filter button clicks
    view.getFilterButton().addActionListener(e -> {
      // Get filter data from view
      String filteredAmountInput = view.getAmountFilterField();
      String filteredCategoryInput = view.getCategoryFilterField();
      JTable transactionsTable = view.getTransactionsTable();

      // for determining whether amount is valid
      boolean emptyAmountInput = false;
      if (filteredAmountInput.isEmpty()){
        filteredAmountInput = "-1";
        emptyAmountInput = true;

      }
      
      // Call controller to perform filtering
      List<Transaction> filteredTransactions = controller.applyFilter(transactionsTable, filteredAmountInput, filteredCategoryInput, emptyAmountInput);
    });

  }

}