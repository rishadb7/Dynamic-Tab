package developers.rbtech.com.restuarentabyu.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rishad on 18/1/18.
 */

public class Category {

    @SerializedName("id")
    private String objectId;
    @SerializedName("category_id")
    private String categoryId;
    @SerializedName("category_name")
    private String categoryName;
    @SerializedName("image")
    private String categoryImage;

    public Category(){}

    public String getObjectId(){ return objectId;}
    public void setObjectId(String objectId){this.objectId=objectId;}
    public String getCategoryId(){
        return categoryId;
    }
    public  void setCategoryId(String categoryId){
        this.categoryId=categoryId;
    }
    public  String getCategoryName(){
        return categoryName;
    }
    public  void setCategoryName(String categoryName){
        this.categoryName=categoryName;
    }

    public  String getCategoryImage(){
        return categoryImage;
    }
    public void setCategoryImage(String categoryImage){
        this.categoryImage=categoryImage;
    }





}
