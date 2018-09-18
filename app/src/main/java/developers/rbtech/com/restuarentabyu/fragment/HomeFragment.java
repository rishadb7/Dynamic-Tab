package developers.rbtech.com.restuarentabyu.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import developers.rbtech.com.restuarentabyu.R;
import developers.rbtech.com.restuarentabyu.adapter.CategoryAdapter;
import developers.rbtech.com.restuarentabyu.interfaces.ApiService;
import developers.rbtech.com.restuarentabyu.models.Category;
import developers.rbtech.com.restuarentabyu.models.UrlServer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rishad on 18/2/18.
 */

public class HomeFragment extends Fragment {


    SwipeRefreshLayout mSwipeRefreshLayout;
    ProgressBar progressBar;
    List<Category> categoryList;
    CategoryAdapter recyclerAdapter;
    RecyclerView recycle;
    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.home_fragment, container, false);




        categoryList=new ArrayList<>();
        recycle = (RecyclerView)rootView.findViewById(R.id.recycle);
        progressBar=(ProgressBar)rootView.findViewById(R.id.circular_progress_bar);
        mSwipeRefreshLayout = (SwipeRefreshLayout)rootView.findViewById(R.id.swipeRefreshLayout);


        recyclerAdapter = new CategoryAdapter(categoryList,getActivity());
       // RecyclerView.LayoutManager recyce = new GridLayoutManager(getActivity(),1);

        LinearLayoutManager recyce =new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recycle.setLayoutManager(recyce);
        recycle.setItemAnimator( new DefaultItemAnimator());

        recycle.setAdapter(recyclerAdapter);

        ObjectAnimator anim = ObjectAnimator.ofInt(progressBar, "progress", 0, 100);
        anim.setDuration(15000);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                categoryList.clear();
                recyclerAdapter.notifyDataSetChanged();
                getCategory();
            }
        });













       /* recycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                Toast.makeText(getActivity(),String.valueOf(newState),Toast.LENGTH_SHORT).show();
            }
        });*/






        getCategory();






        return rootView;
    }


    public void getCategory() {

        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl(UrlServer.serverUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService service = retrofit2.create(ApiService.class);

        Call<List<Category>> call = service.getCategories(UrlServer.apiKey);

        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {

                List<Category> list = response.body();
                Category category = null;

                try {
                    for (int i = 0; i < list.size(); i++) {
                        category = new Category();



                        String objectId = list.get(i).getObjectId();
                        String categoryId = list.get(i).getCategoryId();
                        String categoryName = list.get(i).getCategoryName();
                        String categoryImage = UrlServer.categoryImagePath+list.get(i).getCategoryImage();

                        //  Toast.makeText(MainActivity.this, categoryName, Toast.LENGTH_SHORT).show();


                        category.setObjectId(objectId);
                        category.setCategoryId(categoryId);
                        category.setCategoryName(categoryName);
                        category.setCategoryImage(categoryImage);
                        categoryList.add(category);

                        recyclerAdapter.notifyDataSetChanged();



                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    // Toast.makeText(getActivity(),"No items available",Toast.LENGTH_SHORT).show();


                }
                progressBar.setVisibility(View.GONE);
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });
    }





}
