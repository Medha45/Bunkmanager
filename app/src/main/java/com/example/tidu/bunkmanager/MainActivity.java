package com.example.tidu.bunkmanager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase mDataBase;
private BunkAdapter mAdapter;
    private EditText mEditTextName;
    private EditText mEditTextMin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BunkDBHelper dbHelper = new BunkDBHelper(this);
        mDataBase = dbHelper.getWritableDatabase() ;
        RecyclerView recyclerView = findViewById(R.id.rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new BunkAdapter(this,getAllItems());
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListner(new BunkAdapter.OnItemClickListener() {
            @Override
            public void onpplusclick(int position) {

            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
removeItem((Long)viewHolder.itemView.getTag());

            }
        }).attachToRecyclerView(recyclerView);
        mEditTextName = findViewById(R.id.editText);
        mEditTextMin = findViewById(R.id.editText2);
        Button add = findViewById(R.id.button);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mEditTextName.getText().toString().trim().length() ==0 || mEditTextMin.getText().toString().trim().length() ==0)
                    return;
                String name = mEditTextName.getText().toString();
                String min = mEditTextMin.getText().toString();
                ContentValues contentValues = new ContentValues();
                contentValues.put(BunkContract.BunkEntry.COLUMN_NAME,name);
                contentValues.put(BunkContract.BunkEntry.COLUMN_MIN,min);
                contentValues.put(BunkContract.BunkEntry.COLUMN_PRESENTS,String.valueOf(0));
                contentValues.put(BunkContract.BunkEntry.COLUMN_ABSENTS,String.valueOf(0));
                mDataBase.insert(BunkContract.BunkEntry.TABLE_NAME,null,contentValues);
                mAdapter.swapCursor(getAllItems());
                mEditTextName.getText().clear();
                mEditTextMin.getText().clear();
            }
        });


    }
    private void removeItem(Long id)
    {
        mDataBase.delete(BunkContract.BunkEntry.TABLE_NAME,
                BunkContract.BunkEntry._ID + "=" + id,null);
        mAdapter.swapCursor(getAllItems());

    }


    private Cursor getAllItems()
    {


        return mDataBase.query(BunkContract.BunkEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                BunkContract.BunkEntry.COLUMN_TIMESTAMP + " DESC"
        );

    }
}
