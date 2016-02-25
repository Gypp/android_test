package fr.emn.gprovost;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

/**
 * Created by Gwénaël on 24/02/2016.
 */
public class BookDetail extends Fragment {

    private ImageView coverDetail;
    private TextView titleDetail;
    private TextView isbnDetail;
    private TextView priceDetail;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_detail_view, container, false);
        Book book = getArguments().getParcelable("BOOK");
        titleDetail = (TextView) view.findViewById(R.id.titleDetail);
        titleDetail.setText(book.getTitle());
        coverDetail = (ImageView) view.findViewById(R.id.coverDetail);
        Picasso.with(this.getContext())
                .load(book.getCover())
                .fit()
                .centerCrop()
                .into(coverDetail);
        priceDetail = (TextView) view.findViewById((R.id.priceDetail));
        priceDetail.setText(book.getPrice());
        isbnDetail = (TextView) view.findViewById((R.id.isbnDetail));
        isbnDetail.setText(book.getIsbn());

        return view;
    }

}
