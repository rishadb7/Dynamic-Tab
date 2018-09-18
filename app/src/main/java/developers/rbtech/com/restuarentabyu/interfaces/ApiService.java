package developers.rbtech.com.restuarentabyu.interfaces;



import java.util.List;

import developers.rbtech.com.restuarentabyu.models.Category;
import developers.rbtech.com.restuarentabyu.models.Products;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by rishad on 30/1/18.
 */

public interface ApiService {

    @GET("get_category.php")
    Call<List<Category>> getCategories(@Query("apiKey")String apiKey);


    @GET("get_product.php")
    Call<List<Products>> getProducts(@Query("apiKey")String apiKey, @Query("categoryId")String categoryId);

    @GET("get_all_product.php")
    Call<List<Products>> getAllProducts(@Query("apiKey")String apiKey);



}
