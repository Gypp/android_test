package fr.emn.gprovost;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import timber.log.Timber;

/**
 * Created by Gwénaël on 24/02/2016.
 */
public class BookList extends Fragment {

    private List<Book> books;

    private OnBookList listener;
    private RecyclerView recyclerView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnBookList) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_list_view, container, false);
        Timber.plant(new Timber.DebugTree());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://henri-potier.xebia.fr/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HenriPotierService henriPotierService = retrofit.create(HenriPotierService.class);
        Call<List<Book>> call = henriPotierService.listBooks();
        books = new ArrayList<>();
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Response<List<Book>> response, Retrofit retrofit) {
                for (Book book : response.body()) {
                    Timber.i(book.getTitle());
                    books.add(book);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Timber.i("Fail");
            }
        });
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new BookAdapter(this.getContext(), books));
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View item = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if(item != null) {
                    Book book = books.get(recyclerView.getChildAdapterPosition(item));
                    listener.onClickItemList(book);
                    return true;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        return view;
    }

    public interface OnBookList {
        void onClickItemList(Book book);
    }
}
