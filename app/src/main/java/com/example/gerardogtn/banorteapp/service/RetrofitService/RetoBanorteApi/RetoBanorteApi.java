package com.example.gerardogtn.banorteapp.service.RetrofitService.RetoBanorteApi;

import com.example.gerardogtn.banorteapp.data.model.Movement;
import com.example.gerardogtn.banorteapp.data.model.MovementType;
import com.example.gerardogtn.banorteapp.data.model.Product;
import com.example.gerardogtn.banorteapp.data.model.User;
import com.example.gerardogtn.banorteapp.data.model.UserProductResponse;

import java.math.BigDecimal;
import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by gerardogtn on 9/23/15.
 */
public interface RetoBanorteApi {

    @GET(RetoBanorteApiConstants.URL_USERS)
    void getAllUsers(Callback<List<User>> callback);

    @GET(RetoBanorteApiConstants.URL_USERS)
    void getUser(@Query("ClienteID") int userId, Callback<User> callback);

    @GET(RetoBanorteApiConstants.URL_USER_PRODUCTS)
    void getUserProducts(@Query("ClienteID") int userId, Callback<UserProductResponse> callback);

    @GET(RetoBanorteApiConstants.URL_PRODUCTS)
    void getProducts(Callback<List<Product>> callback);

    @GET(RetoBanorteApiConstants.URL_PRODUCTS)
    void getProduct(@Query("ProductoID") int productId, Callback<Product> callback);

    @GET(RetoBanorteApiConstants.URL_ACCOUNT_BALANCE)
    BigDecimal getAccountBalance(@Query("ClienteID") int userId, @Query("NoCuenta") int accountId);

    @GET(RetoBanorteApiConstants.URL_MOVEMENT_TYPES)
    void getAllMovementTypes(Callback<List<MovementType>> callback);

    @GET(RetoBanorteApiConstants.URL_ACCOUNT_MOVEMENTS)
    void getMovements(@Query("ClienteID") int userId, @Query("NoCuenta") int accountId,
                      Callback<List<Movement>> callback);

    @GET(RetoBanorteApiConstants.URL_TRANSFER)
    boolean makeTransfer(@Query("ClienteIDOrigen") int originUserId,
                         @Query("NoCuentaOrigen") int originAccountId,
                         @Query("ClienteIDDestino") int destinationUserId,
                         @Query("NoCuentaDestino") int destinationAccountId,
                         @Query("Monto") BigDecimal amount);


}
