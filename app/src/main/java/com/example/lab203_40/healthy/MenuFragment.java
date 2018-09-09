package com.example.lab203_40.healthy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lab203_40.healthy.weight.WeightFragment;

import java.util.ArrayList;

/**
 * Created by LAB203_40 on 27/8/2561.
 */

public class MenuFragment extends Fragment {

    ArrayList<String> menu = new ArrayList<>();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        menu.clear();
        menu.add("BMI");
        menu.add("Weight");
        menu.add("Setup");
        ListView menuList = (ListView) getView().findViewById(R.id.menu_list);
        final ArrayAdapter<String> menuAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                menu
        );
        menuList.setAdapter(menuAdapter);
        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("MENU", "Select " + menu.get(position));
                if (menu.get(position).equals("BMI")) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new BMIFragment()).addToBackStack(null).commit();
                } else if (menu.get(position).equals("Weight")) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new WeightFragment()).addToBackStack(null).commit();
                }
                //menu.add("new value");
                //menuAdapter.notifyDataSetChanged();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }
}

