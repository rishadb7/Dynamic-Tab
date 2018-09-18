package developers.rbtech.com.restuarentabyu.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rishad on 18/2/18.
 */

public class Products {

    @SerializedName("id")
    private String dbId;

    @SerializedName("category_id")
    private String categoryId;

    @SerializedName("product_id")
    private String productId;

    @SerializedName("product_name")
    private String productName;

    @SerializedName("product_name_arabic")
    private String productNameArabic;

    @SerializedName("image1")
    private String productImage1;

    @SerializedName("image2")
    private String productImage2;

    @SerializedName("image3")
    private String productImage3;

    @SerializedName("image4")
    private String productImage4;

    @SerializedName("qatar_price")
    private String productPrice;

    @SerializedName("description")
    private String productDescription;

    @SerializedName("type")
    private String productType;

    private String Quantity;
    private String Status;
    private String subTotal;
    private String productComment;


    public Products(){}

    public String getDbId(){ return dbId;}
    public void setDbId(String dbId){this.dbId=dbId;}

    public String getCategoryId(){return categoryId;}

    public void setCategoryId(String categoryId){this.categoryId=categoryId;}



    public String getProductId(){return productId;}
    public void setProductId(String productId){this.productId=productId;}

    public String getProductName(){return productName;}

    public void setProductName(String productName){this.productName=productName;}

    public String getProductNameArabic(){return productNameArabic;}

    public void setProductNameArabic(String productNameArabic){this.productNameArabic=productNameArabic;}




    public String getProductImage1(){return productImage1;}
    public void setProductImage1(String productImage1){this.productImage1=productImage1;}

    public String getProductImage2(){return productImage2;}
    public void setProductImage2(String productImage2){this.productImage2=productImage2;}

    public String getProductImage3(){return productImage3;}
    public void setProductImage3(String productImage3){this.productImage3=productImage3;}

    public String getProductImage4(){return productImage4;}
    public void setProductImage4(String productImage4){this.productImage4=productImage4;}



    public String getProductPrice(){return productPrice;}

    public void setProductPrice(String productPrice){this.productPrice=productPrice;}

    public String getProductDescription(){return productDescription;}

    public void setProductDescription(String productDescription){this.productDescription=productDescription;}

    public String getProductType(){return productType;}
    public void setProductType(String productType){this.productType=productType;}


    public String getSubTotal(){return subTotal;}
    public void setSubTotal(String subTotal){this.subTotal=subTotal;}

    public String getQuantity() {
        return Quantity;
    }
    public void setQuantity(String quantity) {
        Quantity = quantity;
    }
    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }


    public String getProductComment(){return productComment;}
    public void setProductComment(String productComment){
        this.productComment=productComment;
    }

}
