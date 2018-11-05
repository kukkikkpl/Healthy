package com.example.kukkik.healthy.weight;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kukkik.healthy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * Created by LAB203_40 on 27/8/2561.
 */

public class WeightFragment extends Fragment {
    FirebaseFirestore _firestore;
    FirebaseAuth _auth;
    ArrayList<Weight> weights = new ArrayList<>();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        _firestore = FirebaseFirestore.getInstance();
        _auth = FirebaseAuth.getInstance();
        /* weights.add(new Weight("01 Jan 2018", 63, "UP"));
        weights.add(new Weight("02 Jan 2018", 62, "DOWN"));
        weights.add(new Weight("03 Jan 2018", 64, "UP")); */
        getWeightList();
        initAddWeightBtn();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight, container, false);
    }

    void initAddWeightBtn() {
        TextView _backBtn = (TextView) getView().findViewById(R.id.weight_add_weight_btn);
        _backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new WeightFormFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void getWeightList() {
        String _uid = _auth.getCurrentUser().getUid();
        _firestore.collection("myfitness").document(_uid).collection("weight")
                .orderBy("date", Query.Direction.ASCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        weights.clear();
                        if (task.isSuccessful()) {
                            int countLoop = 0;
                            int checkWeight = 0;
                            String status = "";
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String date = document.getData().get("date").toString();
                                int weight = Integer.parseInt(document.getData().get("weight").toString());
                                if (countLoop != 0) {
                                    if (checkWeight > weight) {
                                        status = "DOWN";
                                    } else if (checkWeight < weight) {
                                        status = "UP";
                                    } else if (checkWeight == weight) {
                                        status = "";
                                    }
                                    //String status = document.getData().get("status").toString();
                                }
                                checkWeight = weight;
                                weights.add(new Weight(date, weight, status));
                                ListView _weightList = (ListView) getView().findViewById(R.id.weight_list);
                                WeightAdapter _weightAdapter = new WeightAdapter(getActivity(), R.layout.fragment_weight_item, weights);
                                _weightList.setAdapter(_weightAdapter);
                                countLoop += 1;
                            }
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

    }
}
