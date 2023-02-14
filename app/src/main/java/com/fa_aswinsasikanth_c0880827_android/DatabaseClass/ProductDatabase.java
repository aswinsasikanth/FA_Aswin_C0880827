package com.fa_aswinsasikanth_c0880827_android.DatabaseClass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.fa_aswinsasikanth_c0880827_android.Models.ProductModel;


import java.util.ArrayList;
import java.util.List;


public class ProductDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "products.db";
    private static final int VERSION_DB = 1;
    private static final String TABLE_NAME = "PRODUCTS";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_NAME = "PNAME";
    private static final String COLUMN_DESCRIPTION = "PDESCRIPTION";
    private static final String COLUMN_PRICE = "PPRICE";
    private static final String COLUMN_LOCATION = "PLOCATION";

    public ProductDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String db_creation = "CREATE TABLE " + TABLE_NAME +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " VARCHAR,"
                + COLUMN_DESCRIPTION + " VARCHAR, "
                + COLUMN_PRICE + " VARCHAR, "
                + COLUMN_LOCATION + " VARCHAR )";

        db.execSQL(db_creation);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addProduct(ProductModel model) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, model.getName());
        values.put(COLUMN_DESCRIPTION, model.getDescription());
        values.put(COLUMN_PRICE, model.getPrice());
        values.put(COLUMN_LOCATION, model.getLocation());

        db.insert(TABLE_NAME, null, values);
    }

    public void deleteContact(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String sqlDelete = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=" + id + ";";
        db.execSQL(sqlDelete);
    }

    public void updateProduct(ProductModel model) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, model.getName());
        contentValues.put(COLUMN_DESCRIPTION, model.getDescription());
        contentValues.put(COLUMN_PRICE, model.getPrice());
        contentValues.put(COLUMN_LOCATION, model.getLocation());
        db.update(TABLE_NAME, contentValues, COLUMN_ID + " = " + model.getId(), null);
    }

    public List<ProductModel> getAllProducts() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlFindUser = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(sqlFindUser, null);
        List<ProductModel> products = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                ProductModel productModel = new ProductModel();
                productModel.setId(cursor.getInt(0));
                productModel.setName(cursor.getString(1));
                productModel.setDescription(cursor.getString(2));
                productModel.setPrice(cursor.getString(3));
                productModel.setLocation(cursor.getString(4));

                products.add(productModel);
            } while (cursor.moveToNext());
        }
        return products;
    }
}
