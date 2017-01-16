package com.example.administrator.giftelves.sqlutils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySqlHelperUser extends SQLiteOpenHelper {
	public final static String USER_DB = "user.db";
	public final static String USER_TB = "users";
	public final static int VERSION = 1;
	private final static String CREATE_USER = "create table "+USER_TB+" ("
			+"_id integer primary key autoincrement,"
			+"user_name text,"
			+"user_pass text)";
	private final static String DROP_USER = "drop table "+USER_TB;
	public MySqlHelperUser(Context context){
		this(context,USER_DB,null,VERSION);
	}
	public MySqlHelperUser(Context context, String name, CursorFactory factory,
					   int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_USER);
		Log.e("==", "oncreate");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(DROP_USER);
		onCreate(db);
	}

}
