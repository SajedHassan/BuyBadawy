package com.example.user.eshtri_first_pafge;

/**
 * Created by pratap.kesaboyina on 24-12-2014.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.eshtri_first_pafge.R.id;
import com.example.user.eshtri_first_pafge.R.layout;
import com.example.user.eshtri_first_pafge.SectionListDataAdapter.SingleItemRowHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * For displaying list of a section.
 */
public class SectionListDataAdapter extends Adapter<SingleItemRowHolder> {

    private final ArrayList<SingleItemModel> itemsList;
    private static Context mContext;



    public SectionListDataAdapter(final Context context, final ArrayList<SingleItemModel> itemsList) {
        super();
        this.itemsList = itemsList;
        this.mContext = context;
    }

    @Override
    public final SectionListDataAdapter.SingleItemRowHolder onCreateViewHolder(
            final ViewGroup viewGroup, final int i) {
        final View v = LayoutInflater.from(viewGroup.getContext()).inflate(layout.list_single_card, null);
        return new SectionListDataAdapter.SingleItemRowHolder(v);
    }

    @Override
    public final void onBindViewHolder(final SectionListDataAdapter.SingleItemRowHolder holder, final int i) {

        final SingleItemModel singleItem = this.itemsList.get(i);
        holder.tvTitle.setText(singleItem.getName());

        Picasso.with(mContext).load(
                "http://eshtrybadawy.000webhostapp.com/uploads/" + singleItem.getImageName()).into(holder.itemImage);

//        holder.itemImage.setImageResource(mipmap.test);

        holder.name = singleItem.getName();
        holder.det = singleItem.getDetails();
        holder.pro = singleItem.getProperties();
        holder.address = singleItem.getAddress();
        holder.price = singleItem.getPrice();
        holder.owner = singleItem.getOwner();
        holder.phone = singleItem.getPhone();
        holder.image = singleItem.getImageName();


        // setting info of each product

		/*
         * Glide.with(mContext) .load(feedItem.getImageURL())
		 * .diskCacheStrategy(DiskCacheStrategy.ALL) .centerCrop()
		 * .error(R.drawable.bg) .into(feedListRowHolder.thumbView);
		 */
    }

    @Override
    public final int getItemCount() {
        return (this.itemsList != null) ? this.itemsList.size() : 0;
    }

    public class SingleItemRowHolder extends ViewHolder {

        TextView tvTitle;
        ImageView itemImage;
        String name;
        String det;
        String pro;
        String address;
        String price;
        String owner;
        String phone;
        String image;

        public SingleItemRowHolder(final View view) {
            super(view);

            this.tvTitle = view.findViewById(id.tvTitle);
            this.itemImage = view.findViewById(id.itemImage);

            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(final View v) {

                    Toast.makeText(v.getContext(),
                            SectionListDataAdapter.SingleItemRowHolder.this.tvTitle.getText(),
                            Toast.LENGTH_SHORT).show();
                    final Intent intent = new Intent(mContext,
                            ProductPreview.class);

                    intent.putExtra("name", SectionListDataAdapter.SingleItemRowHolder.this.name);
                    intent.putExtra("det", SectionListDataAdapter.SingleItemRowHolder.this.det);
                    intent.putExtra("pro", SectionListDataAdapter.SingleItemRowHolder.this.pro);
                    intent.putExtra("address",
                            SectionListDataAdapter.SingleItemRowHolder.this.address);
                    intent.putExtra("price", SectionListDataAdapter.SingleItemRowHolder.this.price);
                    intent.putExtra("owner", SectionListDataAdapter.SingleItemRowHolder.this.owner);
                    intent.putExtra("phone", SectionListDataAdapter.SingleItemRowHolder.this.phone);
//                    Log.v("baaa", SectionListDataAdapter.SingleItemRowHolder.this.image.length() + "");
                    intent.putExtra("image", SectionListDataAdapter.SingleItemRowHolder.this.image);
                    intent.putExtra("publicProduct", true);

                    SectionListDataAdapter.this.mContext.startActivity(intent);

                }
            });

        }

    }

}
