package project.model;

import kotlin.Pair;

import java.util.ArrayList;

public class Order {
    private ArrayList<SoldProduct> list;

    public Order() {
        list=new ArrayList<>();
    }

    public Order(ArrayList<SoldProduct> list) {
        this.list = list;
    }

    public void add(Product p , Integer soLuong ,Integer donGia){
        list.add(new SoldProduct(p,soLuong,donGia));
    }
    public ArrayList<SoldProduct> getList() {
        return list;
    }

    public void setList(ArrayList<SoldProduct> list) {
        this.list = list;
    }

    public int totalImportPrice(){
        int p = 0;
        for(SoldProduct x : this.getList()) {
            p +=  ( x.product.getImportPrice() ) * x.count;
//            System.out.println(x.product.getImportPrice());
//            System.out.println(x.count);
        }
//        System.out.println("---------------");
        return p;
    }

    public int totalPrice(){
        int p = 0;
        for(SoldProduct x : this.getList()){
            p += (x.product.getPrice()) * x.count;
        }
        return p;
    }
}
