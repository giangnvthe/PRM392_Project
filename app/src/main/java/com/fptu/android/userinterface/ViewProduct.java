package com.fptu.android.userinterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewProduct extends AppCompatActivity {

    private Context context;
    private RecyclerView rcvUser;
    ProductAdapter mProductAdapter;
    private List<Product> productList;
    private DatabaseReference databaseReferenceProduct;
    Product product;

    private void bindingView() {
        rcvUser = findViewById(R.id.rcv_users);
        rcvUser.setLayoutManager(new LinearLayoutManager(ViewProduct.this));
        //co dong ke de phan cach giua cac item
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvUser.addItemDecoration(dividerItemDecoration);

    }

    private void bindingAction() {
        getListUserFromRealTimeDatabase();
    }

    private void getListUserFromRealTimeDatabase() {


        databaseReferenceProduct = FirebaseDatabase.
                getInstance("https://userinterface2-default-rtdb.firebaseio.com")
                .getReference("/Product/");
        //Product product = new Product(123, "hello");


        databaseReferenceProduct.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    //Log.d("aaa", "onDataChange: " + dataSnapshot.getValue());
                    Product product = dataSnapshot.getValue(Product.class);
                    productList.add(product);
                }
                mProductAdapter = new ProductAdapter(productList);
                rcvUser.setAdapter(mProductAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewProduct.this, "No result", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);
        bindingView();
        bindingAction();
    }



}