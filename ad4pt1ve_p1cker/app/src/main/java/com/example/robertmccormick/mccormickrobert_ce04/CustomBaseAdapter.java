package com.example.robertmccormick.mccormickrobert_ce04;

// Mccormick Robert
// JAV1 - MDV3810-O 01
// CustomBaseAdapter.java



import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.robertmccormick.mccormickrobert_ce04.Person;

import java.util.ArrayList;


public class CustomBaseAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflter;
    private ArrayList<Person> allPersonArrayList = new ArrayList<>();

    public CustomBaseAdapter(Context applicationContext, ArrayList<Person> _allPersonArrayList) {
        this.context = applicationContext;
        this.allPersonArrayList.clear();
        this.allPersonArrayList.addAll(_allPersonArrayList);
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return allPersonArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public View getView(final int position, View convertView,
                        ViewGroup parent) {
        final MyViewHolder mViewHolder;
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row_baseadaper, null);
            mViewHolder = new MyViewHolder();
            mViewHolder.person_name = (TextView) convertView.findViewById(R.id.person_name);
            mViewHolder.person_birthday = (TextView) convertView.findViewById(R.id.person_birthday);
            mViewHolder.person_image = (ImageView) convertView.findViewById(R.id.person_image);

            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }
        Person mPerson = allPersonArrayList.get(position);
        mViewHolder.person_name.setText(mPerson.getName());
        mViewHolder.person_birthday.setText(mPerson.getbirthday());
        mViewHolder.person_image.setImageResource(mPerson.getImage());

        return convertView;
    }

    private class MyViewHolder {
        TextView person_name;
        TextView person_birthday;
        ImageView person_image;
    }

}