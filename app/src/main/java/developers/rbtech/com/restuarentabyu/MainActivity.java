package developers.rbtech.com.restuarentabyu;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import developers.rbtech.com.restuarentabyu.adapter.MyFragmentPageAdapter;
import developers.rbtech.com.restuarentabyu.fragment.HomeFragment;
import developers.rbtech.com.restuarentabyu.fragment.HomeFragment2;
import developers.rbtech.com.restuarentabyu.fragment.ProductFragment;
import developers.rbtech.com.restuarentabyu.interfaces.ApiService;
import developers.rbtech.com.restuarentabyu.models.Category;
import developers.rbtech.com.restuarentabyu.models.UrlServer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity{


    TabLayout tabLayout;
    ViewPager viewPager;
   // ViewPagerAdapter adapter;
    List<Category> categoryList;
    MyFragmentPageAdapter mPageAdapter;
    List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoryList=new ArrayList<>();
        getCategory();
        fragments = new ArrayList<android.support.v4.app.Fragment>();
        viewPager = (ViewPager) findViewById(R.id.pager);




        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(View.FOCUS_LEFT);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int position = tab.getPosition();
                Toast.makeText(getApplicationContext(),String.valueOf(position),Toast.LENGTH_SHORT).show();

                // tabLayout.getChildAt(position).setVisibility(View.INVISIBLE);




            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {



            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });






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
                   // adapter = new ViewPagerAdapter(getSupportFragmentManager());
                    for (int i = 0; i < list.size(); i++) {
                        category = new Category();



                        String objectId = list.get(i).getObjectId();
                        String categoryId = list.get(i).getCategoryId();
                        String categoryName = list.get(i).getCategoryName();
                        String categoryImage = UrlServer.categoryImagePath+list.get(i).getCategoryImage();

                      //    Toast.makeText(MainActivity.this, categoryName, Toast.LENGTH_SHORT).show();

                       // adapter.addFrag(new ProductFragment(), categoryName);
                        category.setObjectId(objectId);
                        category.setCategoryId(categoryId);
                        category.setCategoryName(categoryName);
                        category.setCategoryImage(categoryImage);
                        categoryList.add(category);



                        Bundle b = new Bundle();
                        b.putString("categoryId", categoryList.get(i).getCategoryId());
                        b.putString("categoryName", categoryList.get(i).getCategoryName());
                      //  fragments.add(Fragment.instantiate(getApplicationContext(),ProductFragment.class.getName(),b));

                        fragments.add(Fragment.instantiate(getApplicationContext(),HomeFragment2.class.getName(),b));


                    }
                    mPageAdapter = new MyFragmentPageAdapter(getApplicationContext(),getSupportFragmentManager(), fragments, categoryList);
                    viewPager.setAdapter(mPageAdapter);


                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    // Toast.makeText(getActivity(),"No items available",Toast.LENGTH_SHORT).show();


                }


            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });
    }





}



