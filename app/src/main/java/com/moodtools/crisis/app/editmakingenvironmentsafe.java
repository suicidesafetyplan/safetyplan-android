package com.moodtools.crisis.app;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;


public class editmakingenvironmentsafe extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editmakingenvironmentsafe);
          getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    @Override
       public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
