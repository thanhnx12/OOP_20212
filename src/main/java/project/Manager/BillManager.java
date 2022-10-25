package project.Manager;

import project.model.Bill;

import java.util.ArrayList;

public class BillManager {
    public static ArrayList<Bill> billList = new ArrayList<>();

    public static void add(Bill bill) {
        billList.add(bill);
    }
    public static Bill get(String id) {
        try {
            for (Bill bill : billList) {
                if (bill.getId().compareTo(id)==0) return bill;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public static boolean containsID(String id) {
        for (Bill bill: billList) {
            if (bill.getId().compareTo(id)==0) return true;
        }
        return false;
    }


}
