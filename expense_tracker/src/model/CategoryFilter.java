package model;
import java.util.*;

// A class that implements the TrasactionFilter interface.
public class CategoryFilter implements TransactionFilter {
    //returns the filtered list of transactions based on category provided
    public List<Transaction> filter(List<Transaction> transactions, String filterValue){
        List<Transaction> filtered_transactions = Collections.emptyList();
        for(Transaction curTransaction: filtered_transactions) {
            String curCategory = curTransaction.getCategory();
            if(curCategory.equals(filterValue)) {
                filtered_transactions.add(curTransaction);
            }
        }
        return filtered_transactions;
    }
}