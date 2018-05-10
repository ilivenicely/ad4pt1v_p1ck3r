package com.example.robertmccormick.mccormickrobert_ce04;

// Mccormick Robert
// JAV1 - MDV3810-O 01
// MainActivity.java



import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

//import com.android.adapter.model.Person;

import com.example.robertmccormick.mccormickrobert_ce04.Person;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String[] FirstnameList = {"First name 1", "First name 2", "First name 3", "First name 4", "First name 5",
            "First name 6", "First name 7", "First name 8", "First name 9", "First name 10", "First name 11"};

    String[] LastnameList = {"Last name 1", "Last name 2", "Last name 3", "Last name 4", "Last name 5",
            "Last name 6", "Last name 7", "Last name 8", "Last name 9", "Last name 10", "Last name 11"};

    String[] BrithdayList = {"12/01/2009", "12/01/2010", "12/01/2011", "12/02/2009", "12/03/2009", "12/04/2009",
            "12/05/2009", "12/01/2014", "12/01/2015", "12/01/2004", "12/01/2002"};

    int[] ImageList = {R.drawable.bee, R.drawable.buffalo, R.drawable.bull, R.drawable.butterfly, R.drawable.cow,
            R.drawable.dolphin, R.drawable.duck, R.drawable.firefly, R.drawable.fish, R.drawable.giraffe, R.drawable.horse};

    //using for array adapter
    private String[] personFullname = new String[FirstnameList.length];
    //using for simple adapter
    private List<Map<String, Object>> simpleAdapterList;
    //using for Base adapter
    private ArrayList<Person> allPersonArrayList;

    private Spinner first_spinner;
    private Spinner second_spinner;
    private ListView list_view;
    private GridView gridView;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initUi();
    }

    private void initUi() {

        PrepareData();

        first_spinner = (Spinner) this.findViewById(R.id.first_spinner);
        second_spinner = (Spinner) this.findViewById(R.id.second_spinner);

        list_view = (ListView) this.findViewById(R.id.list_view);
        gridView = (GridView) this.findViewById(R.id.gridView);


        first_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                seletedSpinner();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        second_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                seletedSpinner();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showMessage(i);

            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showMessage(i);
            }
        });
        seletedSpinner();

    }

    public void seletedSpinner() {

        ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, personFullname);
        SimpleAdapter mSimpleAdapter = new SimpleAdapter(this, simpleAdapterList,
                R.layout.row_simpledadaper, new String[]{"name", "birthday"},
                new int[]{R.id.person_name,
                        R.id.person_birthday});
        CustomBaseAdapter mCustomBaseAdapter = new CustomBaseAdapter(mContext, allPersonArrayList);


        int positionFirst = first_spinner.getSelectedItemPosition();
        int positionSecond = second_spinner.getSelectedItemPosition();
        if (positionFirst == 0) {
            gridView.setVisibility(View.GONE);
            list_view.setVisibility(View.VISIBLE);
            if (positionSecond == 0) {
                list_view.setAdapter(mArrayAdapter);
                mArrayAdapter.notifyDataSetChanged();
            } else if (positionSecond == 1) {
                list_view.setAdapter(mSimpleAdapter);
                mSimpleAdapter.notifyDataSetChanged();

            } else if (positionSecond == 2) {
                list_view.setAdapter(mCustomBaseAdapter);
                mCustomBaseAdapter.notifyDataSetChanged();
            }

        } else {
            gridView.setVisibility(View.VISIBLE);
            list_view.setVisibility(View.GONE);
            if (positionSecond == 0) {
                gridView.setAdapter(mArrayAdapter);
                mArrayAdapter.notifyDataSetChanged();
            } else if (positionSecond == 1) {
                gridView.setAdapter(mSimpleAdapter);
                mSimpleAdapter.notifyDataSetChanged();

            } else if (positionSecond == 2) {
                gridView.setAdapter(mCustomBaseAdapter);
                mCustomBaseAdapter.notifyDataSetChanged();
            }
        }


    }

    private void PrepareData() {
        simpleAdapterList = new ArrayList<Map<String, Object>>();
        allPersonArrayList = new ArrayList<Person>();
        Map<String, Object> item;
        Person mPerson;
        for (int index = 0; index < FirstnameList.length; index++) {
            personFullname[index] = FirstnameList[index] + " " + LastnameList[index];

            item = new HashMap<String, Object>();
            item.put("name", FirstnameList[index] + " " + LastnameList[index]);
            item.put("birthday", BrithdayList[index]);
            item.put("image", ImageList[index]);
            simpleAdapterList.add(item);


            mPerson = new Person();
            mPerson.setName(FirstnameList[index] + " " + LastnameList[index]);
            mPerson.setbirthday(BrithdayList[index]);
            mPerson.setImage(ImageList[index]);

            allPersonArrayList.add(mPerson);

        }
    }

    public void showMessage(int position) {
        Person mPerson = allPersonArrayList.get(position);
        final AlertDialog.Builder aBuilder = new AlertDialog.Builder(mContext);
        aBuilder.setTitle(mPerson.getName());
        aBuilder.setIcon(mPerson.getImage());
        aBuilder.setMessage(mPerson.getbirthday());

        aBuilder.setPositiveButton("Close", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                dialog.dismiss();
            }

        });

        AlertDialog dialog = aBuilder.show();
        TextView messageView = (TextView) dialog.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.CENTER);
    }


}
