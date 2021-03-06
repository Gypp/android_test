package fr.emn.gprovost;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import timber.log.Timber;

/**
 * Created by Gwénaël on 22/01/2016.
 */
public class MainActivity extends AppCompatActivity implements BookList.OnBookList {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.list_view_fragment, new BookList())
                .commit();
    }

    @Override
    public void onClickItemList(Book book) {
        Timber.i("ClickOnItem");
        BookDetail bookDetail = new BookDetail();
        Bundle bundle = new Bundle();
        bundle.putParcelable("BOOK", book);
        bookDetail.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.list_view_fragment, bookDetail, BookDetail.class.getSimpleName())
                .addToBackStack(BookList.class.getSimpleName())
                .commit();
    }
}
