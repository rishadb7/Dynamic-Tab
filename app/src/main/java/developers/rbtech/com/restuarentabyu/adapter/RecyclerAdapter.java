package developers.rbtech.com.restuarentabyu.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import developers.rbtech.com.restuarentabyu.R;
import developers.rbtech.com.restuarentabyu.models.CategoryItem;
import developers.rbtech.com.restuarentabyu.models.Products2;
import developers.rbtech.com.restuarentabyu.viewholder.CategoryViewHolder;
import developers.rbtech.com.restuarentabyu.viewholder.ProductViewHolder;

/**
 * Created by rishad on 7/6/18.
 */

public class RecyclerAdapter extends ExpandableRecyclerViewAdapter<CategoryViewHolder, ProductViewHolder> {

  //  private Activity activity;
    private Context activity;

  /*  public RecyclerAdapter(Activity activity, List<? extends ExpandableGroup> groups) {
        super(groups);
        this.activity = activity;
    }*/

    public RecyclerAdapter(Context activity, List<? extends ExpandableGroup> groups) {
        super(groups);
        this.activity = activity;
    }

    @Override
    public CategoryViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.group_view_holder, parent, false);

        return new CategoryViewHolder(view);
    }

    @Override
    public ProductViewHolder onCreateChildViewHolder(ViewGroup parent, final int viewType) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.child_view_holder, parent, false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(ProductViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final Products2 phone = ((CategoryItem) group).getItems().get(childIndex);
        holder.onBind(phone,group);
    }

    @Override
    public void onBindGroupViewHolder(CategoryViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setGroupName(group);
    }
}
