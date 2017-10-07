package com.example.user.eshtri_first_pafge;

/**
 * Created by pratap.kesaboyina on 24-12-2014.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SectionListDataAdapter extends RecyclerView.Adapter<SectionListDataAdapter.SingleItemRowHolder> {

    private ArrayList<SingleItemModel> itemsList;
    private Context mContext;

    public SectionListDataAdapter(Context context, ArrayList<SingleItemModel> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_single_card, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {

        SingleItemModel singleItem = itemsList.get(i);
        holder.tvTitle.setText(singleItem.getName());
        holder.itemImage.setImageResource(R.mipmap.test);

        holder.name = singleItem.getName();
        holder.det = singleItem.getDetails();
        holder.pro = singleItem.getProperties();
        holder.address = singleItem.getAddress();
        holder.price = singleItem.getPrice();
        holder.owner = singleItem.getOwner();
        holder.phone = singleItem.getPhone();


        //setting info of each product




       /* Glide.with(mContext)
                .load(feedItem.getImageURL())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.bg)
                .into(feedListRowHolder.thumbView);*/
    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tvTitle;

        protected ImageView itemImage;

        protected String name;
        protected String det;
        protected String pro;
        protected String address;
        protected String price;
        protected String owner;
        protected String phone;


        public SingleItemRowHolder(View view) {
            super(view);

            this.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            this.itemImage = (ImageView) view.findViewById(R.id.itemImage);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Toast.makeText(v.getContext(), tvTitle.getText(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, ProductPreview.class);

                    intent.putExtra("name", name);
                    intent.putExtra("det", det);
                    intent.putExtra("pro", pro);
                    intent.putExtra("address", address);
                    intent.putExtra("price", price);
                    intent.putExtra("owner", owner);
                    intent.putExtra("phone", phone);
                    intent.putExtra("publicProduct", true);


                    mContext.startActivity(intent);

                }
            });


        }

    }

}
