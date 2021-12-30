package com.jnu.accountbook.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jnu.accountbook.R;
import com.jnu.accountbook.bean.AccountBean;
import com.jnu.accountbook.bean.TypeBean;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private static SQLiteDatabase sqLiteDatabase;

    public static void initDB(Context context) {
        DBOpenHelper dbOpenHelper = new DBOpenHelper(context);
        sqLiteDatabase = dbOpenHelper.getWritableDatabase();
    }

    public static List<TypeBean> getTypeBeanList(int kind) {
        List<TypeBean> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from typetb", null);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") int imageId = cursor.getInt(cursor.getColumnIndex("imageId"));
            @SuppressLint("Range") int sImageId = cursor.getInt(cursor.getColumnIndex("sImageId"));
            @SuppressLint("Range") String typename = cursor.getString(cursor.getColumnIndex("typename"));
            @SuppressLint("Range") int kind1 = cursor.getInt(cursor.getColumnIndex("kind"));
            TypeBean typeBean = new TypeBean(id, imageId, sImageId, typename, kind1);
            list.add(typeBean);
        }
        cursor.close();
        return list;
    }

    public static void insertAccountBean(AccountBean bean) {
        String sql = "insert into accounttb(imageId,typename,accountType,remark,money,date) values(?,?,?,?,?,?)";
        sqLiteDatabase.execSQL(sql, new Object[]{bean.getImageId(), bean.getTypename(), bean.getAccountType(), bean.getRemark(), bean.getMoney(), bean.getDate()});
    }

    public static void deleteAccountBean(int id) {
        String sql = "delete from accounttb where id = " + id;
        sqLiteDatabase.execSQL(sql);
    }

    public static List<AccountBean> getAccountBean() {
        List<AccountBean> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from accounttb", null);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") int imageId = cursor.getInt(cursor.getColumnIndex("imageId"));
            @SuppressLint("Range") String typename = cursor.getString(cursor.getColumnIndex("typename"));
            @SuppressLint("Range") String accountType = cursor.getString(cursor.getColumnIndex("accountType"));
            @SuppressLint("Range") String remark = cursor.getString(cursor.getColumnIndex("remark"));
            @SuppressLint("Range") float money = cursor.getFloat(cursor.getColumnIndex("money"));
            @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex("date"));
            AccountBean accountBean = new AccountBean(id, imageId, typename, accountType, remark, money, date);
            list.add(accountBean);
        }
        return list;
    }

    public static void modifyAccountBean(AccountBean bean) {
        String sql = "update accounttb set imageId = ?, typename = ?, accountType = ?, remark = ?, money = ?, date = ? where id = ?";
        sqLiteDatabase.execSQL(sql, new Object[]{bean.getImageId(), bean.getTypename(), bean.getAccountType(), bean.getRemark(), bean.getMoney(), bean.getDate(), bean.getId()});
    }
}
