package developers.rbtech.com.restuarentabyu.adapter;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import developers.rbtech.com.restuarentabyu.R;
import developers.rbtech.com.restuarentabyu.fragment.HomeFragment2;
import developers.rbtech.com.restuarentabyu.models.Products;

/**
 * Created by rishad on 19/1/18.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

        private List<Products> list;
        private  Context context;
        Activity activity;
        private View view;
        private int flag = 0;
        SQLiteDatabase db ;

public ProductAdapter(List<Products> list, Context context) {
        this.list = list;
        this.context = context;
        }

@Override
public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.product_card,parent,false);
        ProductHolder productholder = new ProductHolder(view);


        return productholder;
        }

@Override
public void onBindViewHolder(final ProductHolder productHolder, int position) {
        final Products productsList = list.get(position);

    productHolder.productName.setText(productsList.getProductName());
    productHolder.productPrice.setText(productsList.getProductPrice());
    Glide.with(context).load(productsList.getProductImage1()).apply(RequestOptions.circleCropTransform()).into(productHolder.productImage);




   /* productHolder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            Fragment myFragment = new HomeFragment2();
            Bundle bundle =new Bundle();
           // bundle.putString("categoryId",productsList.getCategoryId());
           // bundle.putString("categoryName",productsList.getProductType());

            myFragment.setArguments(bundle);

            activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHolder, myFragment).commit();



        }
    });*/



    productHolder.productImage.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {







        }
    });



        }





@Override
public int getItemCount() {

        int arr = 0;

        try{
        if(list.size()==0){

        arr = 0;

        }
        else{

        arr=list.size();
        }



        }catch (Exception e){



        }

        return arr;

        }

class ProductHolder extends RecyclerView.ViewHolder{
    TextView productName,productPrice;
    ImageView productImage;


    public ProductHolder(final View itemView) {
        super(itemView);

        view=itemView;

        Typeface tf = Typeface.createFromAsset(context.getAssets(),"fonts/Handlee-Regular.ttf");


        productName= (TextView) itemView.findViewById(R.id.productName);
        productPrice=(TextView) itemView.findViewById(R.id.productPrice);
        productImage= (ImageView) itemView.findViewById(R.id.productImage);

        productName.setTypeface(tf);
        productPrice.setTypeface(tf);

    }
}







}
