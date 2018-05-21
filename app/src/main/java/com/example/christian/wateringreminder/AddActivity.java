package com.example.christian.wateringreminder;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import com.example.christian.wateringreminder.Database.AppDatabase;
import com.example.christian.wateringreminder.Database.Plant;
import java.util.Calendar;

//import static com.example.christian.wateringreminder.Database.AppDatabase.MIGRATION_1_2;

public class AddActivity extends Activity {
    private EditText mName;
    private EditText mSpecies;
    private Button mPickDate;
    private TextView mChosenDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private EditText mCycle;
    private Button mAddPlant;
    private String mNextDate;
    private String mLastDate;
    private Calendar cal;

    private static final String TAG = "AddActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add);

        mName = (EditText) findViewById(R.id.etNewName);
        mSpecies = (EditText) findViewById(R.id.etNewSpecies);
        mChosenDate = (TextView) findViewById(R.id.tvChosenDate);
        mPickDate = (Button) findViewById(R.id.btnLastWatering);
        mCycle = (EditText) findViewById(R.id.etCycle);
        mAddPlant = (Button)findViewById(R.id.btnAddDone);


        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "inventory")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        Log.d(TAG, "db build");

        mPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                mLastDate = dayOfMonth + "-" + month + "-" + year;
                mChosenDate.setText(mLastDate);
            }
        };

        mAddPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(mCycle.getText())) {
                    cal.add(Calendar.DATE, Integer.parseInt(mCycle.getText().toString()));
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    month = month + 1;
                    mNextDate = day + "-" + month + "-" + year;

                    db.plantDao().insertAll(new Plant(mName.getText().toString(), mSpecies.getText().toString(), mNextDate, mLastDate, Integer.parseInt(mCycle.getText().toString())));
                    startActivity(new Intent(AddActivity.this, MainActivity.class));
                }
            }
        });
    }
}
