package com.example.sqlliteex;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WordDBHelper extends SQLiteOpenHelper{
	public WordDBHelper(Context context){
		super(context, "EngWord.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE dic( _id INTEGER PRIMARY KEY AUTOINCREMENT, "+"eng TEXT, han TEXT);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS dic");
		onCreate(db);
	}
	
	
}
