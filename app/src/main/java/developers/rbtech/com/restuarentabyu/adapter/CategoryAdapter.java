package developers.rbtech.com.restuarentabyu.adapter;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import developers.rbtech.com.restuarentabyu.R;
import developers.rbtech.com.restuarentabyu.models.Category;

/**
 * Created by rishad on 19/1/18.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {

        List<Category> list;
        Context context;
        Activity activity;
        View view;


public CategoryAdapter(List<Category> list, Context context) {
        this.list = list;
        this.context = context;
        }

@Override
public CategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.category_card,parent,false);
        CategoryHolder categoryholder = new CategoryHolder(view);



        return categoryholder;

}

@Override
public void onBindViewHolder(final CategoryHolder categoryHolder, int position) {
        final Category categoryList = list.get(position);

    categoryHolder.categoryName.setText(categoryList.getCategoryName());



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

class CategoryHolder extends RecyclerView.ViewHolder{
    TextView categoryName;

    View view3;

    public CategoryHolder(final View itemView) {
        super(itemView);

        view=itemView;
        view3=itemView;

        Typeface tf = Typeface.createFromAsset(context.getAssets(),"fonts/Handlee-Regular.ttf");

        categoryName= (TextView) itemView.findViewById(R.id.categoryName);

        categoryName.setTypeface(tf);

    }
}

}
