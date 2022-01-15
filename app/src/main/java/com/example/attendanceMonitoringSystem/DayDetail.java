package com.example.attendanceMonitoringSystem;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.attendanceMonitoringSystem.Utils.LetterImageView;

public class DayDetail extends AppCompatActivity {

    private ListView listView;
    private androidx.appcompat.widget.Toolbar toolbar;
    public static String[] Monday;
    public static String[] Tuesday;
    public static String[] Wednesday;
    public static String[] Thursday;
    public static String[] Friday;
    public static String[] Saturday;
    public static String[] Time_Monday;
    public static String[] Time_Tuesday;
    public static String[] Time_Wednesday;
    public static String[] Time_Thursday;
    public static String[] Time_Friday;
    public static String[] Time_Saturday;
    private String[] PreferredDay;
    private  String[] PreferredTime;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_detail);


        setupUIViews();
        initToolbar();
        setupListView();
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupUIViews (){
        listView = (ListView)findViewById(R.id.lvDayDetail);
        toolbar = (Toolbar) findViewById(R.id.ToolbarDayDetail);
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(WeekActivity.sharedPreferences.getString(WeekActivity.SEL_DAY,null));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void setupListView(){

        Monday = getResources().getStringArray(R.array.Monday);
        Tuesday = getResources().getStringArray(R.array.Tuesday);
        Wednesday = getResources().getStringArray(R.array.Wednesday);
        Thursday = getResources().getStringArray(R.array.Thursday);
        Friday = getResources().getStringArray(R.array.Friday);
        Saturday = getResources().getStringArray(R.array.Saturday);

        Time_Monday = getResources().getStringArray(R.array.TIME_MONDAY);
        Time_Tuesday = getResources().getStringArray(R.array.TIME_TUESDAY);
        Time_Wednesday = getResources().getStringArray(R.array.TIME_WEDNESDAY);
        Time_Thursday = getResources().getStringArray(R.array.TIME_THURSDAY);
        Time_Friday = getResources().getStringArray(R.array.TIME_FRIDAY);
        Time_Saturday = getResources().getStringArray(R.array.TIME_SATURDAY);

        String selected_day = WeekActivity.sharedPreferences.getString(WeekActivity.SEL_DAY,null);

        if(selected_day.equalsIgnoreCase("Monday")){

            PreferredDay = Monday;
            PreferredTime = Time_Monday;
        }else if(selected_day.equalsIgnoreCase("Tuesday")){

            PreferredDay = Tuesday;
            PreferredTime = Time_Tuesday;
        }else if(selected_day.equalsIgnoreCase("Tuesday")){

            PreferredDay = Wednesday;
            PreferredTime = Time_Wednesday;
        }else if(selected_day.equalsIgnoreCase("Tuesday")){

            PreferredDay = Thursday;
            PreferredTime = Time_Thursday;
        }else if(selected_day.equalsIgnoreCase("Tuesday")){

            PreferredDay = Friday;
            PreferredTime = Time_Friday;
        }else {

            PreferredDay = Saturday;
            PreferredTime = Time_Saturday;
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(DayDetail.this, PreferredDay, PreferredTime);
        listView.setAdapter(simpleAdapter);

    }
    public void setSupportActionBar(Toolbar toolbar) {
    }

    public class SimpleAdapter extends BaseAdapter {


        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView subject, time;
        private String[] subjectArray;
        private String[] timeArray;
        private LetterImageView letterImageView ;

        public SimpleAdapter(Context context, String[] subjectArray, String[] timeArray){
            mContext = context;
            this.subjectArray = subjectArray;
            this.timeArray = timeArray;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return subjectArray.length;
        }

        @Override
        public Object getItem(int position) {
            return subjectArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = layoutInflater.inflate(R.layout.day_detail_single_item, null);
            }
            subject = (TextView)convertView.findViewById(R.id.tvSubjectDayDetails);
            time= (TextView)convertView.findViewById(R.id.tvTimeDayDetail);
            letterImageView = (LetterImageView)convertView.findViewById(R.id.ivDayDetails);

            subject.setText(subjectArray[position]);
            time.setText(timeArray[position]);

            letterImageView.setOval(true);
            letterImageView.setLetter(subjectArray[position].charAt(0));


            return convertView;
        }
    }

}