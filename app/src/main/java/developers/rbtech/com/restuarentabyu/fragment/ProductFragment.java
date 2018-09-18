package developers.rbtech.com.restuarentabyu.fragment;

import android.animation.ObjectAnimator;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import developers.rbtech.com.restuarentabyu.R;
import developers.rbtech.com.restuarentabyu.adapter.ProductAdapter;
import developers.rbtech.com.restuarentabyu.interfaces.ApiService;
import developers.rbtech.com.restuarentabyu.models.Products;
import developers.rbtech.com.restuarentabyu.models.UrlServer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rishad on 18/2/18.
 */

public class ProductFragment extends Fragment  {

    View rootView;
    String categoryId,categoryName;
    SwipeRefreshLayout mSwipeRefreshLayout;
    ProgressBar progressBar;
    List<Products> productsList;
    ProductAdapter recyclerAdapter;
    RecyclerView recycle;
    String cat;
    TextView categoryNameTex;

    public ProductFragment(){

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.product_fragment, container, false);


        categoryName=getArguments().getString("categoryName");
        categoryId=getArguments().getString("categoryId");
        categoryNameTex=(TextView)rootView.findViewById(R.id.catName);

        categoryNameTex.setText(categoryName);

      //  Toast.makeText(getActivity(),categoryId,Toast.LENGTH_SHORT).show();

        productsList=new ArrayList<>();
        recycle = (RecyclerView)rootView.findViewById(R.id.recycle);
        progressBar=(ProgressBar)rootView.findViewById(R.id.circular_progress_bar);
        mSwipeRefreshLayout = (SwipeRefreshLayout)rootView.findViewById(R.id.swipeRefreshLayout);


        recyclerAdapter = new ProductAdapter(productsList,getActivity());
        RecyclerView.LayoutManager recyce = new GridLayoutManager(getActivity(),1);
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
                productsList.clear();
                recyclerAdapter.notifyDataSetChanged();
                getProductsDetails();
            }
        });


        getProductsDetails();



        return rootView;
    }



    public void getProductsDetails(){

        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl(UrlServer.serverUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService service = retrofit2.create(ApiService.class);

        Call<List<Products>> call = service.getProducts(UrlServer.apiKey,categoryId);

        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {

                List<Products> list = response.body();
                Products products = null;

                try {
                    for (int i = 0; i < list.size(); i++) {
                        products = new Products();



                        String dbId = list.get(i).getDbId();
                        String productId=list.get(i).getProductId();
                        String categoryId = list.get(i).getCategoryId();
                        String productName = list.get(i).getProductName();
                        String productNameArabic=list.get(i).getProductNameArabic();
                        String productDescription=list.get(i).getProductDescription();
                        String productPrice =list.get(i).getProductPrice();
                        String productType=list.get(i).getProductType();
                        String productImage1 = UrlServer.productImagePath+list.get(i).getProductImage1();
                        String productImage2 = UrlServer.productImagePath+list.get(i).getProductImage2();
                        String productImage3 = UrlServer.productImagePath+list.get(i).getProductImage3();
                        String productImage4 = UrlServer.productImagePath+list.get(i).getProductImage4();


                        products.setDbId(dbId);
                        products.setProductId(productId);
                        products.setCategoryId(categoryId);
                        products.setProductDescription(productDescription);
                        products.setProductPrice(productPrice);
                        products.setProductName(productName);
                        products.setProductNameArabic(productNameArabic);
                        products.setProductImage1(productImage1);
                        products.setProductImage2(productImage2);
                        products.setProductImage3(productImage3);
                        products.setProductImage4(productImage4);
                        products.setProductType(productType);
                        productsList.add(products);

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
            public void onFailure(Call<List<Products>> call, Throwable t) {

            }
        });




    }







}
