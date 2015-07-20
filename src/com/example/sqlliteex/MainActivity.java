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
    		// insert �޼���� ����
    		row = new ContentValues();
    		row.put("eng", "boy");
    		row.put("han", "�ӽ���");
    		db.insert("dic", null, row);
    		// SQL ������� ����
    		db.execSQL("INSERT INTO dic VALUES (null, 'girl', '���ó�');");
    		mHelper.close();
    		mText.setText("Insert Success");
    		break;
    	case R.id.delete:
    		db = mHelper.getWritableDatabase();
    		// delete �޼���� ����
    		db.delete("dic", null, null);
    		// SQL ��ɾ�� ����
    		// db.execSQL("DELETE FROM dic;");
    		mHelper.close();
    		mText.setText("Delete Success");
    		break;
    	case R.id.update:
    		db = mHelper.getWritableDatabase();
    		// update �޼���� ����
    		row = new ContentValues();
    		row.put("han", "�ҳ�");
    		db.update("dic", row, "eng = 'boy'", null);
    		// SQL ������� ����
    		// db.execSQL("UPDATE dic SET han = '�ҳ�' WHERE eng='boy';");
    		mHelper.close();
    		mText.setText("Update Success");
    		break;
    	case R.id.select:
    		db = mHelper.getReadableDatabase();
    		Cursor cursor;
    		// query �޼���� �б�
    		// cursor = db.query("dic", new String[] {"eng", "han"}, null, null, null, null, null );
    		// SQL ������� �б�
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
