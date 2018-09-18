package developers.rbtech.com.restuarentabyu.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import developers.rbtech.com.restuarentabyu.R;
import developers.rbtech.com.restuarentabyu.adapter.RecyclerAdapter;
import developers.rbtech.com.restuarentabyu.interfaces.ApiService;
import developers.rbtech.com.restuarentabyu.models.Category;
import developers.rbtech.com.restuarentabyu.models.CategoryItem;
import developers.rbtech.com.restuarentabyu.models.Products;
import developers.rbtech.com.restuarentabyu.models.Products2;
import developers.rbtech.com.restuarentabyu.models.UrlServer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rishad on 7/6/18.
 */

public class HomeFragment2 extends Fragment {

    View rootView;

    private RecyclerView recyclerView;
    private ArrayList<CategoryItem> categoryItems;
    private RecyclerAdapter adapter;
    ArrayList<Products2> iphones;
    ArrayList<Products2> iphones1;
    ArrayList<Products2> iphones2;
    List<Category> categoryList;
    List<Products> productsList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.home_fragment2, container, false);


        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);
        iphones = new ArrayList<>();
        iphones1 = new ArrayList<>();
        iphones2 = new ArrayList<>();
        categoryItems = new ArrayList<>();

        categoryList=new ArrayList<>();
        productsList=new ArrayList<>();


      //  setData();
        setData2();
      //  getItems();

      //  populateData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);




        return rootView;
    }

   /* private void setData() {
        ArrayList<Products2> iphones = new ArrayList<>();
        iphones.add(new Products2("iPhone 4"));
        iphones.add(new Products2("iPhone 4S"));
        iphones.add(new Products2("iPhone 5"));
        iphones.add(new Products2("iPhone 5S"));
        iphones.add(new Products2("iPhone 6"));
        iphones.add(new Products2("iPhone 6Plus"));
        iphones.add(new Products2("iPhone 6S"));
        iphones.add(new Products2("iPhone 6S Plus"));

        ArrayList<Products2> nexus = new ArrayList<>();
        nexus.add(new Products2("Nexus One"));
        nexus.add(new Products2("Nexus S"));
        nexus.add(new Products2("Nexus 4"));
        nexus.add(new Products2("Nexus 5"));
        nexus.add(new Products2("Nexus 6"));
        nexus.add(new Products2("Nexus 5X"));
        nexus.add(new Products2("Nexus 6P"));
        nexus.add(new Products2("Nexus 7"));

        ArrayList<Products2> windowPhones = new ArrayList<>();
        windowPhones.add(new Products2("Nokia Lumia 800"));
        windowPhones.add(new Products2("Nokia Lumia 710"));
        windowPhones.add(new Products2("Nokia Lumia 900"));
        windowPhones.add(new Products2("Nokia Lumia 610"));
        windowPhones.add(new Products2("Nokia Lumia 510"));
        windowPhones.add(new Products2("Nokia Lumia 820"));
        windowPhones.add(new Products2("Nokia Lumia 920"));

        categoryItems.add(new CategoryItem("iOS", iphones));
        categoryItems.add(new CategoryItem("Android", nexus));
        categoryItems.add(new CategoryItem("Window Phone", windowPhones));
    }*/


    public void setData2() {

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

                       // ArrayList<Products2> iphones = new ArrayList<>();

                        getItems(categoryName);


                        categoryItems.add(new CategoryItem(categoryName, iphones));


                      //  ArrayList<Products2> iphones = new ArrayList<>();

                     //   iphones.add(new Products2())
                     //   categoryItems.add(new CategoryItem(categoryName, iphones));
                       /* if(iphones.get(i).getName().equals(categoryName)){


                            categoryItems.add(new CategoryItem(categoryName, iphones));
                        }*/





                    }








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

    public void getItems(final String catName){


        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl(UrlServer.serverUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService service = retrofit2.create(ApiService.class);



        Call<List<Products>> call = service.getAllProducts(UrlServer.apiKey);

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
                        String categoryName=list.get(i).getProductType();
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



                        iphones.add(new Products2(productName,categoryName,productDescription,productImage1));









                          //  iphones.add(new Products2(productName));
                          //  categoryItems.add(new CategoryItem(categoryName, iphones));








                    }
                    adapter =new RecyclerAdapter(getActivity(),categoryItems);
                    recyclerView.setAdapter(adapter);
                    //  populateData();





                 //   adapter =new RecyclerAdapter(getActivity(),categoryItems);
                //    recyclerView.setAdapter(adapter);


                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    // Toast.makeText(getActivity(),"No items available",Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {

            }
        });


    }
/*
    public void populateData(){

        for(int k=0;k<categoryList.size();k++){


            Toast.makeText(getActivity(),productsList.get(k).getProductName(),Toast.LENGTH_SHORT).show();
            Toast.makeText(getActivity(),categoryList.get(k).getCategoryName(),Toast.LENGTH_SHORT).show();
                iphones.add(new Products2(productsList.get(k).getProductName()));
                categoryItems.add(new CategoryItem(categoryList.get(k).getCategoryName(), iphones));






        }

        adapter =new RecyclerAdapter(getActivity(),categoryItems);
        recyclerView.setAdapter(adapter);
    }*/
}
