package online.ghuri.com.beacondoctorapp.JournalDetail.Repository;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import online.ghuri.com.beacondoctorapp.JournalDetail.Repository.NewModel.NewJournalResponse;
import online.ghuri.com.beacondoctorapp.Preferences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tanvir on 3/13/2018.
 */

public class JournalDetailDbHandler {

    private Context mContext;
    private ApiEndPoint mApiEndPoint;
    private static final String BASE_URL="http://www.flexibac.com.bd/api/";

    public JournalDetailDbHandler(Context mContext) {
        this.mContext = mContext;
        Retrofit retrofit=new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
        mApiEndPoint=retrofit.create(ApiEndPoint.class);
    }


    public void getJournal(String categoryId, final JournalDetailRepositoryCallback callback){
        String token= "Bearer "+ Preferences.getToken(mContext);
        Call<NewJournalResponse> responseCall=mApiEndPoint.getJournalList(token, categoryId);
        responseCall.enqueue(new Callback<NewJournalResponse>() {
            @Override
            public void onResponse(Call<NewJournalResponse> call, Response<NewJournalResponse> response) {
                if (response.isSuccessful()){
                    callback.getJournal(response.body().getJournals());
                }else {
                    callback.errorOccured("Wrong response");
                }
            }

            @Override
            public void onFailure(Call<NewJournalResponse> call, Throwable t) {
                callback.errorOccured(t.getMessage());
            }
        });




    }

  /*  private Journal getDummyData(){
        Journal journal=new Journal();


        List<Blog> blogs=new ArrayList<>();
        Blog blog=new Blog();
        blog.setName("January");
        blogs.add(blog);

         blog=new Blog();
        blog.setName("February");
        blogs.add(blog);

         blog=new Blog();
        blog.setName("March");
        blogs.add(blog);


        List<Issue> issues=new ArrayList<>();

        Issue issue=new Issue();
        issue.setBlogs(blogs);
        issue.setName("The Bone and Joint Journal ");
        issues.add(issue);


        issue=new Issue();
        issue.setBlogs(blogs);
        issue.setName("Journal of ArthoPlasty ");

        issues.add(issue);


        issue=new Issue();
        issue.setBlogs(blogs);
        issue.setName("Europian Spine Journal ");

        issues.add(issue);


        journal.setIssues(issues);

        return journal;

    }*/
}
