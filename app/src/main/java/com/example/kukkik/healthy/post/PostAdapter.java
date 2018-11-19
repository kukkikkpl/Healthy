package com.example.kukkik.healthy.post;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kukkik.healthy.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class PostAdapter extends ArrayAdapter {
    ArrayList<JSONObject> postList;
    Context context;
    public PostAdapter(Context context, int resource, ArrayList<JSONObject> objects) {
        super(context, resource, objects);
        this.postList = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View postItem = LayoutInflater.from(context).inflate(R.layout.fragment_post_item, parent, false);
        TextView title = (TextView) postItem.findViewById(R.id.post_item_title);
        TextView body = (TextView) postItem.findViewById(R.id.post_item_body);
        JSONObject object = postList.get(position);
        try {

            title.setText(object.getString("id") + " : " + object.getString("title"));
            body.setText(object.getString("body"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return postItem;
    }

}
