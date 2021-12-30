package com.jnu.accountbook.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.jnu.accountbook.R;

public class DBOpenHelper extends SQLiteOpenHelper {

    public DBOpenHelper(@Nullable Context context) {
        super(context, "accountbook.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table typetb(id integer primary key autoincrement,imageId integer,sImageId integer,typename varchar(10),kind integer)");
        insertType(sqLiteDatabase);

        sqLiteDatabase.execSQL("create table accounttb(id integer primary key autoincrement,imageId integer,typename varchar(10),accountType varchar(10),remark varchar(50),money float,date varchar(10))");
    }


    public void insertType(SQLiteDatabase sqLiteDatabase) {
        String sql = "insert into typetb(imageId,sImageId,typename,kind) values(?,?,?,?)";
        sqLiteDatabase.execSQL(sql, new Object[]{R.mipmap.ic_qita, R.mipmap.ic_qita_fs, "其它", 0});
        sqLiteDatabase.execSQL(sql, new Object[]{R.mipmap.ic_fushi, R.mipmap.ic_fushi_fs, "服饰", 0});
        sqLiteDatabase.execSQL(sql, new Object[]{R.mipmap.ic_gouwu, R.mipmap.ic_gouwu_fs, "购物", 0});
        sqLiteDatabase.execSQL(sql, new Object[]{R.mipmap.ic_jiaotong, R.mipmap.ic_jiaotong_fs, "交通", 0});
        sqLiteDatabase.execSQL(sql, new Object[]{R.mipmap.ic_riyongpin, R.mipmap.ic_riyongpin_fs, "日用品", 0});
        sqLiteDatabase.execSQL(sql, new Object[]{R.mipmap.ic_renqingwanglai, R.mipmap.ic_renqingwanglai_fs, "人情往来", 0});
        sqLiteDatabase.execSQL(sql, new Object[]{R.mipmap.ic_xuexi, R.mipmap.ic_xuexi_fs, "学习", 0});
        sqLiteDatabase.execSQL(sql, new Object[]{R.mipmap.ic_yanjiu, R.mipmap.ic_yanjiu_fs, "研究", 0});
        sqLiteDatabase.execSQL(sql, new Object[]{R.mipmap.ic_yiliao, R.mipmap.ic_yiliao_fs, "医疗", 0});
        sqLiteDatabase.execSQL(sql, new Object[]{R.mipmap.ic_yule, R.mipmap.ic_yule_fs, "娱乐", 0});
        sqLiteDatabase.execSQL(sql, new Object[]{R.mipmap.ic_shicai, R.mipmap.ic_shicai_fs, "食材", 0});
        sqLiteDatabase.execSQL(sql, new Object[]{R.mipmap.ic_tongxun, R.mipmap.ic_tongxun_fs, "通讯", 0});
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
