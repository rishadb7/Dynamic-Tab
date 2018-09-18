package developers.rbtech.com.restuarentabyu.viewholder;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import developers.rbtech.com.restuarentabyu.R;

/**
 * Created by rishad on 7/6/18.
 */

public class CategoryViewHolder extends GroupViewHolder {

    private TextView categoryName;

    public CategoryViewHolder(View itemView) {
        super(itemView);

        categoryName = (TextView) itemView.findViewById(R.id.category_name);
    }

    @Override
    public void expand() {
        categoryName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.down_arrow, 0);
        Log.i("Adapter", "expand");
    }

    @Override
    public void collapse() {
        Log.i("Adapter", "collapse");
        categoryName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.up_arrow, 0);
    }

    public void setGroupName(ExpandableGroup group) {
        categoryName.setText(group.getTitle());
    }
}
