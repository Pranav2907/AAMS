package com.example.attendanceMonitoringSystem;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SubjectDetail extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_detail);

        setupUIViews();
        initToolbar();
        setupListView();
    }

    private void setupUIViews(){
        toolbar = (Toolbar)findViewById(R.id.ToolbarSubjectDetail);
        listView = (ListView)findViewById(R.id.lvSubjectDetail);



    }

    private void initToolbar(){

        getSupportActionBar().setTitle("Syllabus");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void setupListView(){
        String subject_selected = SubjectActivity.subjectPreferences.getString(SubjectActivity.SUB_PREF, null);

        String[] syllabus = new String[]{};
        String[] titles = getResources().getStringArray(R.array.titles);


        syllabus = getResources().getStringArray(R.array.ICTT);

        SubjectDetailsAdapter subjectDetailsAdapter = new SubjectDetailsAdapter(this, titles, syllabus);
        listView.setAdapter(subjectDetailsAdapter);

    }

    public class SubjectDetailsAdapter extends BaseAdapter {


        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView title, syllabus;
        private String[] titleArray;
        private String[] syllabusArray;


        public SubjectDetailsAdapter(Context context, String[] title, String[] syllabus) {
            mContext = context;
            titleArray = title;
            syllabusArray = syllabus;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return titleArray.length;
        }

        @Override
        public Object getItem(int position) {
            return titleArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.subject_details_single_item, null);
            }
            title = (TextView) convertView.findViewById(R.id.tvSubjectTitle);
            syllabus = (TextView) convertView.findViewById(R.id.tvSyllabus);


            title.setText(titleArray[position]);
            syllabus.setText(syllabusArray[position]);


            return convertView;
        }



    }
}