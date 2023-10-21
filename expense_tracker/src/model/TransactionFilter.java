package model;
import java.util.List;
interface TransactionFilter {
    //return a list of transactions based on the filter value provided
    public List<Transaction> filter(List<Transaction> transactions, String filterValue);
 }