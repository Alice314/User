package com.wusui.user;

        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;

/**
 * Created by fg on 2016/2/15.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView mRecyclerView;
    private List<String>mDatas;
    private DataAdapter mAdapter;
    private EditText editText;
    private TextView textView;
    private MyDatabaseHelper dbHelper;
    private SQLiteDatabase db;

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        editText = (EditText)findViewById(R.id.edit_view);
        textView = (TextView)findViewById(R.id.text_view);

        dbHelper = new MyDatabaseHelper(this,"User.db",null,1);



        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter = new DataAdapter(MainActivity.this, mDatas));

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);
        mAdapter.notifyDataSetChanged();
    }
    protected void initData(){
        mDatas = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {

        insertWord(dbHelper.getWritableDatabase(),editText.getText().toString());
        Cursor cursor = db.rawQuery("select * from user",null);
        if (cursor.moveToFirst()){
          do {
              String content = cursor.getString(cursor.getColumnIndex("content"));
              mDatas.add(content);
          }
          while (cursor.moveToNext());
        }
        cursor.close();

    }

    public void insertWord(SQLiteDatabase db,String content){
        final String INSERT_WORD = "insert into user(id,content)values(null,?)";

    }



}
