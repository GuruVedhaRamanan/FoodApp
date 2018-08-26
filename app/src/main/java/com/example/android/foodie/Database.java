package com.example.android.foodie;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.android.foodie.Model.Order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;


public class Database extends SQLiteAssetHelper {

    private static final  String Database ="EatIt.db";

    private static final int DB_VER = 1;


    public Database(Context context) {
        super(context, Database, null,DB_VER);
    }



    public List<Order> getCarts()
{
    SQLiteDatabase db  = getReadableDatabase();

    SQLiteQueryBuilder qb  = new SQLiteQueryBuilder();

    String [] sqlSelect ={"OrderId","ProductName","ProductId","ProductQuantity","ProductPrice","Discount"};

    String sqlTable = "OrderDetails";

    qb.setTables(sqlTable);

    Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);

    final List<Order> result = new ArrayList<>();
    if(cursor.moveToFirst())
    {
            do
        {
             result.add(new  Order(
                     cursor.getInt(cursor.getColumnIndex("OrderId")),
                     cursor.getString(cursor.getColumnIndex("ProductId")) ,
                    cursor.getString(cursor.getColumnIndex("ProductName")),
                    cursor.getString(cursor.getColumnIndex("ProductPrice")),
                   cursor.getString(cursor.getColumnIndex("ProductQuantity")),
                      cursor.getString(cursor.getColumnIndex("Discount"))
             ));

        }
        while(cursor.moveToNext());

    }
    return result;
}

public void addTOCart(Order order)
{
    SQLiteDatabase db = getReadableDatabase();

    String query = String.format("INSERT INTO OrderDetails(ProductId,ProductName,ProductQuantity,ProductPrice,Discount) VALUES('%s','%s','%s','%s','%s');",
             order.getProductId(),
            order.getProductName(),
            order.getProductQuantity(),
            order.getProductPrice(),
            order.getDiscount());
    db.execSQL(query);
}

//public void

public void updateQuantity(Order order)
{
    SQLiteDatabase db = getReadableDatabase();

   String query = String.format("UPDATE OrderDetails SET ProductQuantity = %s WHERE OrderId = %d",order.getProductQuantity(),order.getOrderId());

    db.execSQL(query);


}
    public void clean()
    {
        SQLiteDatabase db = getReadableDatabase();

        String query = ("DELETE FROM OrderDetails");

        db.execSQL(query);
    }
}
