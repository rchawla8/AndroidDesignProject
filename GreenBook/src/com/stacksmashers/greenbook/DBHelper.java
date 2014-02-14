package com.stacksmashers.greenbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	public static final String Database_Name = "main_database.db";
	static  int version = 1;
	
	public static final String _id = "_id";
	public static final String USER_TABLE = "USERS";
	public static final String USERS_ID = USER_TABLE + "._id";
	public static final String USER_TYPE = "USER_TYPE";
	public static final String USER_NAME = "USER_NAME";
	public static final String USER_DISPLAY_NAME = "USER_DISPLAY_NAME";
	public static final String USER_PIC = "USER_PIC";
	public static final String USER_PASS = "USER_PASS";
	
	public static final String ACCOUNT_TABLE = "ACCOUNTS";
	public static final String ACCOUNT_ID = ACCOUNT_TABLE + "._id";
	public static final String ACCOUNT_NAME = "ACCOUNT_NAME";
	public static final String ACCOUNT_USER = "ACCOUNT_USER";
	public static final String ACCOUNT_BALANCE= "ACCOUNT_BALANCE";
	public static final String ACCOUNT_INTEREST = "ACCOUNT_INTEREST";
	
	
	public static final String TRANSACTION_TABLE = "TRANSACTIONS";
	public static final String TRANSACTION_ID = TRANSACTION_TABLE + ".id";
	public static final String TRANSACTION_USER = "TRANSACTION_USER";
	public static final String TRANSACTION_ACCOUNT = "TRANSACTION_ACCOUNT";
	public static final String TRANSACTION_NAME = "TRANSACTION_NAME";
	public static final String TRANSACTION_TYPE = "TRANSACTION_TYPE";
	public static final String TRANSACTION_VALUE = "TRANSACTION_VALUE";
	public static final String TRANSACTION_WITHRAWAL_REASON = "TRANSACTION_WITHRAWAL_REASON";
	public static final String TRANSACTION_DEPOSIT_SOURCE = "TRANSACTION_DEPOSIT_SOURCE";
	public static final String TRANSACTION_CATEGORY = "TRANSACTION_CATEGORY";
	public static final String TRANSACTION_POSTED = "TRANSACTION_POSTED";
	public static final String TRANSACTION_AFFECTED = "TRANSACTION_AFFECTED";
	
	
	

	public static final String TAG = "DBHelper";
	
	//public static int LETTER_GRADING = 0x0;
	//public static int PASS_FAIL = 0x1;
	

	public DBHelper(Context context)
	{
		super(context, Database_Name, null, version);
	}
	
	@Override
	public void onOpen(SQLiteDatabase db) {
	    super.onOpen(db);
	    if (!db.isReadOnly()) {
	        // Enable foreign key constraints
	        db.execSQL("PRAGMA foreign_keys=ON;");
	    }
	}




	@Override
	public void onCreate(SQLiteDatabase dbse) {
		// TODO Auto-generated method stub
		
		//Queries for creating the four tables
		final String createUserTable = "CREATE TABLE " + USER_TABLE + "( " + _id + " INTEGER PRIMARY KEY AUTOINCREMENT," + 
																						  USER_TYPE + " TEXT," +
																						  USER_NAME + " TEXT," +
																						  USER_DISPLAY_NAME + " TEXT," + 
																						  USER_PIC + " TEXT," +
																						  USER_PASS + " TEXT" + ");";
		
		final String createAccountTable = "CREATE TABLE " + ACCOUNT_TABLE + "( " + _id + " INTEGER PRIMARY KEY AUTOINCREMENT," + 
																						  ACCOUNT_USER + " TEXT," + 
				                                                                          ACCOUNT_NAME + " TEXT," + 
																						  ACCOUNT_BALANCE + " INTEGER" + ");";
		
		
					
		final String createTransactionTable = "CREATE TABLE " + TRANSACTION_TABLE + "( " + _id + " INTEGER PRIMARY KEY AUTOINCREMENT," + 
		                                                                                  TRANSACTION_USER + " TEXT," + 
		                                                                                  TRANSACTION_ACCOUNT + " TEXT," + 
		                                                                                  TRANSACTION_NAME + " TEXT," + 
		                                                                                  TRANSACTION_TYPE + " TEXT," + 
		                                                                                  TRANSACTION_VALUE + " INTEGER," +
		                                                                                  TRANSACTION_WITHRAWAL_REASON + " TEXT," +
		                                                                                  TRANSACTION_DEPOSIT_SOURCE + " TEXT," + 
		                                                                                  TRANSACTION_CATEGORY + " TEXT," + 
		                                                                                  TRANSACTION_POSTED + " DATETIME DEFAULT," + 
		                                                                                  TRANSACTION_AFFECTED + " DATETIME," + ");";
		
		
	
		Log.i(TAG,"SQL Statements created");
		dbse.execSQL(createUserTable);
		dbse.execSQL(createAccountTable);
		dbse.execSQL(createTransactionTable);

	}


	@Override
	public void onUpgrade(SQLiteDatabase dbse, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	
	}

}