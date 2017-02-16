package com.austinzeller.newsreadingapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class InformationAdapter extends ArrayAdapter<Information> {
    private ArrayList<Information> mInformationStories;

    InformationAdapter(Context context, ArrayList<Information> informationStories) {
        super(context, 0, informationStories);

        mInformationStories = informationStories;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        Information information = mInformationStories.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        ViewHolder viewHolder = new ViewHolder();
        viewHolder.title = (TextView) convertView.findViewById(R.id.title);
        viewHolder.section = (TextView) convertView.findViewById(R.id.section);
        viewHolder.date = (TextView) convertView.findViewById(R.id.date);

        viewHolder.title.setText(information.getTitle());
        viewHolder.section.setText(information.getSection());
        viewHolder.date.setText(information.getDate());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = mInformationStories.get(position).getUrl();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                getContext().startActivity(intent);
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        TextView title;
        TextView section;
        TextView date;
    }
}