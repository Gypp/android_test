package fr.emn.gprovost;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private List<Book> books;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView nameTextView;
        public ImageView coverImageView;
        public ViewHolder(View v) {
            super(v);
            nameTextView = (TextView) v.findViewById(R.id.nameTextView);
            coverImageView = (ImageView) v.findViewById(R.id.coverImageView);
        }
    }

    public BookAdapter(Context context, List<Book> books){
        this.books = books;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_list_item_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Book book = books.get(position);
        holder.nameTextView.setText(book.getTitle());
        Picasso.with(holder.coverImageView.getContext())
                .load(book.getCover())
                .centerCrop()
                .into(holder.coverImageView);
    }

    @Override
    public int getItemCount() {
        return this.books.size();
    }

}