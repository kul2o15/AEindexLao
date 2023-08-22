package com.kuro15.jo.aeindexlao;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jo on 07-05-17.
 */
public class SearchActivity extends Activity {
    SQLiteDatabase mDb;
    MyDbHelper mHelper;
    Cursor mCursor, mCursor2;
    ListIndexAdapter adapter;
    ListView listView1;
    LaoIndexModel mlaoIndexModel = null;
    List<LaoIndexModel> mLaoIndexModelList = new ArrayList<>();
    EditText editText1;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        listView1 = (ListView) findViewById(R.id.listview_search);
        editText1 = (EditText) findViewById(R.id.edtSearch);


        checkDatabase();

        mHelper = new MyDbHelper(this);
        mDb = mHelper.getWritableDatabase();

        //recent search sang listview kub model mai
        mCursor2 = mDb.rawQuery("SELECT Index_id FROM " + MyDbHelper.TABLE_NAME3 , null);

        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String CHCK = editText1.getText().toString();

                    if (CHCK.matches("\\d+(?:\\.\\d+)?")) {
                        mCursor = mDb.rawQuery("SELECT * FROM " + MyDbHelper.TABLE_NAME + " WHERE Id" +
                                " LIKE '" + editText1.getText() + "%'", null);
                    } else {
                        mCursor = mDb.rawQuery("SELECT * FROM " + MyDbHelper.TABLE_NAME + " WHERE Lao" +
                                " LIKE '%" + editText1.getText() + "%'", null);
                    }

                    filterQuerry();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //detail search
                mCursor = mDb.rawQuery("SELECT * FROM " + MyDbHelper.TABLE_NAME + " WHERE Id = " +
                        id, null);

                filterQuerry();

                //Insert log
                ContentValues values = new ContentValues();
                values.put("Index_id", id);
                mDb.insert(MyDbHelper.TABLE_NAME3, null, values);
            }
        });

    }

    public void checkDatabase() {
        String url = "/data/data/" + getPackageName() + "/databases/myDatabase.db";
        File f = new File(url);
        if (!f.exists()) {
            try {
                mHelper = new MyDbHelper(this);
                mDb = mHelper.getWritableDatabase();
                mDb.close();
                mHelper.close();
                InputStream in = getAssets().open("myDatabase.db");
                OutputStream out = new FileOutputStream(url);
                byte[] buffer = new byte[in.available()];
                in.read(buffer);
                out.write(buffer, 0, buffer.length);
                in.close();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onPause() {
        super.onPause();
        mHelper.close();
        mDb.close();
    }

    public void filterQuerry() {
        mLaoIndexModelList.clear();
        mCursor.moveToFirst();
        while (!mCursor.isAfterLast()) {
            mlaoIndexModel = new LaoIndexModel(mCursor.getInt(0), mCursor.getString(1), mCursor.getString(2));
            mLaoIndexModelList.add(mlaoIndexModel);
            mCursor.moveToNext();
        }
        adapter = new ListIndexAdapter(this, mLaoIndexModelList);
        listView1.setAdapter(adapter);

    }

}
