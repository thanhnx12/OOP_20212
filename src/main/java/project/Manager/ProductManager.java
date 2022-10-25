package project.Manager;

import project.model.Product;

import java.util.ArrayList;

public class ProductManager {
    public static ArrayList<Product> productList= new ArrayList<>();

    public static boolean add(Product product){
        try {
            productList.add(product);
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }
    public static boolean delete(Product product){
        try {
            productList.remove(product);
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }
    public static Product get(String id){
        try{
            for (Product product : productList)
                if (product.getId().compareTo(id)==0 )    return product;
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    public static boolean containsID(String id){
        for (Product product : productList)
            if (product.getId().compareTo(id)==0 )    return true;
        return false;
    }
}

