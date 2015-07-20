package com.example.sqlliteex;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends Activity {
	
	WordDBHelper mHelper;
	EditText mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mHelper = new WordDBHelper(this);
        mText = (EditText)findViewById(R.id.edittext);
    }
    
    public void mOnClick(View v){
    	SQLiteDatabase db;
    	ContentValues row;
    	switch(v.getId()){
    	case R.id.insert:
    		db = mHelper.getWritableDatabase();
    		// insert 메서드로 삽입
    		row = new ContentValues();
    		row.put("eng", "boy");
    		row.put("han", "머스마");
    		db.insert("dic", null, row);
    		// SQL 명령으로 삽입
    		db.execSQL("INSERT INTO dic VALUES (null, 'girl', '가시나');");
    		mHelper.close();
    		mText.setText("Insert Success");
    		break;
    	case R.id.delete:
    		db = mHelper.getWritableDatabase();
    		// delete 메서드로 삭제
    		db.delete("dic", null, null);
    		// SQL 명령어로 삭제
    		// db.execSQL("DELETE FROM dic;");
    		mHelper.close();
    		mText.setText("Delete Success");
    		break;
    	case R.id.update:
    		db = mHelper.getWritableDatabase();
    		// update 메서드로 갱신
    		row = new ContentValues();
    		row.put("han", "소년");
    		db.update("dic", row, "eng = 'boy'", null);
    		// SQL 명령으로 갱신
    		// db.execSQL("UPDATE dic SET han = '소년' WHERE eng='boy';");
    		mHelper.close();
    		mText.setText("Update Success");
    		break;
    	case R.id.select:
    		db = mHelper.getReadableDatabase();
    		Cursor cursor;
    		// query 메서드로 읽기
    		// cursor = db.query("dic", new String[] {"eng", "han"}, null, null, null, null, null );
    		// SQL 명령으로 읽기
    		cursor = db.rawQuery("SELECT eng,  han FROM dic", null);
    		
    		String result = "";
    		while(cursor.moveToNext()){
    			String eng = cursor.getString(0);
    			String han = cursor.getString(1);
    			result += (eng+" = "+ han + "\n");
    		}
    		
    		if(result.length() == 0){
    			mText.setText("Empty Set");
    		}else{
    			mText.setText(result);
    		}
    		
    		cursor.close();
    		mHelper.close();
    		break;
    	}
    }
}
