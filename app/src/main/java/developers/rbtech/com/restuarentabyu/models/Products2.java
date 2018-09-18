package developers.rbtech.com.restuarentabyu.models;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by rishad on 18/2/18.
 */

public class Products2 implements Parcelable {

    private String name;
    private String catName;
    private String productDescription;
    private String productImage;


    public Products2(Parcel in) {
        name = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }


    public String getProductDescription(){return productDescription;}

    public void setProductDescription(String productDescription){this.productDescription=productDescription;}


    public String getProductImage(){return productImage;}
    public void setProductImage(String productImage){this.productImage=productImage;}


    public Products2(String name,String catName,String productDescription,String productImage) {
        this.name = name;
        this.catName=catName;
        this.productDescription=productDescription;
        this.productImage=productImage;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(catName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Products2> CREATOR = new Creator<Products2>() {
        @Override
        public Products2 createFromParcel(Parcel in) {
            return new Products2(in);
        }

        @Override
        public Products2[] newArray(int size) {
            return new Products2[size];
        }
    };
}
