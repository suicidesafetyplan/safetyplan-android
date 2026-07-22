package com.moodtools.crisis.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle arg0) {

        setCountryValues(getUserCountry(getApplicationContext()));



       SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        super.onCreate(arg0);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setElevation(0);

        boolean previouslyStarted = prefs.getBoolean(getString(R.string.prefpreviouslystarted), false);

        if(!previouslyStarted){
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(getString(R.string.prefpreviouslystarted), Boolean.TRUE);
            edit.apply();
            Intent intent = new Intent(this, FirstLaunch.class);
            startActivity(intent);
        }

        //Put the following in a boolean at the beginning and then save it to sharedpreferences!

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager2 viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(this));

        TabLayout tabs = findViewById(R.id.crisistabs);
        String[] tabTitles = {
                getString(R.string.plan),
                getString(R.string.guide),
                getString(R.string.crisis)
        };

        new TabLayoutMediator(tabs, viewPager,
                (tab, position) -> tab.setText(tabTitles[position]))
                .attach();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mainquestionbutton) {
            questionclick();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private void questionclick() {
        Intent intent = new Intent(this, instructions.class);
        startActivity(intent);
    }

    /**
     * Get ISO 3166-1 alpha-2 country code for this device (or null if not available)
     * @param context Context reference to get the TelephonyManager instance from
     * @return country code or null
     */
    public static String getUserCountry(Context context) {
        try {
            final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            final String simCountry = tm.getSimCountryIso();
            if (simCountry != null && simCountry.length() == 2) { // SIM country code is available
                return simCountry.toLowerCase(Locale.US); //The locale.US is superfluous, just means lowercase it in english
            }
            else if (tm.getPhoneType() != TelephonyManager.PHONE_TYPE_CDMA) { // device is not 3G (would be unreliable)
                String networkCountry = tm.getNetworkCountryIso();
                if (networkCountry != null && networkCountry.length() == 2) { // network country code is available
                    return networkCountry.toLowerCase(Locale.US);
                }
            }
        }
        catch (Exception e) { }
        return "nocountryfound";
    }

    /**
     * If country code = US, AU, CA, GB, DE, IE, IN, NZ, PH, SG, ZA, NL, SE then show phone numbers
     * Only If country code = US , then can also show crisis text line
     * If country code is anything else, including null, then replace phone number with a befrienders.org number
     * @param string the country code!
     */
    public void setCountryValues(String string) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();

        if(string.equalsIgnoreCase("us")) {
            editor.putBoolean("countryshowtextnumber", true); //set text number as true
            editor.putBoolean("countryshowphonenumbers", true); //set phone as true
        }
        else if(string.equalsIgnoreCase("au")) {
            editor.putBoolean("countryshowtextnumber", false); //set text number as false
            editor.putBoolean("countryshowphonenumbers", true); //set phone as true
        }
        else if(string.equalsIgnoreCase("ca")) {
            editor.putBoolean("countryshowtextnumber", false); //set text number as false
            editor.putBoolean("countryshowphonenumbers", true); //set phone as true
        }
        else if(string.equalsIgnoreCase("gb")) {
            editor.putBoolean("countryshowtextnumber", false); //set text number as false
            editor.putBoolean("countryshowphonenumbers", true); //set phone as true
        }
        else if(string.equalsIgnoreCase("de")) {
            //only show phone numbers!
            editor.putBoolean("countryshowtextnumber", false); //set text number as false
            editor.putBoolean("countryshowphonenumbers", true); //set phone as true
        }
        else if(string.equalsIgnoreCase("ie")) {
            editor.putBoolean("countryshowtextnumber", false); //set text number as false
            editor.putBoolean("countryshowphonenumbers", true); //set phone as true
        }
        else if(string.equalsIgnoreCase("in")) {
            editor.putBoolean("countryshowtextnumber", false); //set text number as false
            editor.putBoolean("countryshowphonenumbers", true); //set phone as true
        }
        else if(string.equalsIgnoreCase("nz")) {
            editor.putBoolean("countryshowtextnumber", false); //set text number as false
            editor.putBoolean("countryshowphonenumbers", true); //set phone as true
        }
        else if(string.equalsIgnoreCase("ph")) {
            editor.putBoolean("countryshowtextnumber", false); //set text number as false
            editor.putBoolean("countryshowphonenumbers", true); //set phone as true
        }
        else if(string.equalsIgnoreCase("sg")) {
            editor.putBoolean("countryshowtextnumber", false); //set text number as false
            editor.putBoolean("countryshowphonenumbers", true); //set phone as true
        }
        else if(string.equalsIgnoreCase("za")) {
            editor.putBoolean("countryshowtextnumber", false); //set text number as false
            editor.putBoolean("countryshowphonenumbers", true); //set phone as true
        }
        else if(string.equalsIgnoreCase("nl")) {
            editor.putBoolean("countryshowtextnumber", false); //set text number as false
            editor.putBoolean("countryshowphonenumbers", true); //set phone as true
        }
        else if(string.equalsIgnoreCase("se")) {
            editor.putBoolean("countryshowtextnumber", false); //set text number as false
            editor.putBoolean("countryshowphonenumbers", true); //set phone as true
        }
        else {
            //show nothing! including the value null
            editor.putBoolean("countryshowtextnumber", false);
            editor.putBoolean("countryshowphonenumbers", false);
        }


        //commit changes
        editor.commit();
    }
}
