package com.zwir.leaderboard.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zwir.leaderboard.R;
import com.zwir.leaderboard.adapter.ListStudentAdapter;
import com.zwir.leaderboard.model.Student;
import com.zwir.leaderboard.retrofit.ApiClient;
import com.zwir.leaderboard.retrofit.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BoardingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BoardingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private final String BASE_GADS_URL ="https://gadsapi.herokuapp.com";
    // TODO: Rename and change types of parameters
    private String mParam1;
    ListStudentAdapter listStudentAdapter;
    public BoardingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment LearningFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BoardingFragment newInstance(String param1) {
        BoardingFragment fragment = new BoardingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_boarding, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.studentRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        listStudentAdapter=new ListStudentAdapter(new ArrayList<>(),mParam1,getContext());
        recyclerView.setAdapter(listStudentAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        ApiInterface apiService = ApiClient.getClient(BASE_GADS_URL).create(ApiInterface.class);
        Call<ArrayList<Student>> call;
        if (mParam1.equals("hours")) {
           call = apiService.getLearningLeaders();
        }
        else {
            call = apiService.getSkillIQLeaders();
        }
        call.enqueue(new Callback<ArrayList<Student>>() {
            @Override
            public void onResponse(Call<ArrayList<Student>> call, Response<ArrayList<Student>> response) {
                listStudentAdapter.setListStudent(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Student>> call, Throwable t) {
            }
        });
        return view;
    }

}