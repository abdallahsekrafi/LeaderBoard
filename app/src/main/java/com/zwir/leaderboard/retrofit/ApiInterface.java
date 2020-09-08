package com.zwir.leaderboard.retrofit;

import com.zwir.leaderboard.model.Student;


import java.util.ArrayList;

import javax.xml.transform.Result;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("/api/skilliq")
    Call<ArrayList<Student>> getSkillIQLeaders();
    @GET("/api/hours")
    Call<ArrayList<Student>> getLearningLeaders();
    @FormUrlEncoded
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    Call<Boolean> sendProject(
            @Field("entry.1824927963") String emailAddress,
            @Field("entry.1877115667") String firstName,
            @Field("entry.2006916086") String lastName,
            @Field("entry.284483984") String projectLink);

}
