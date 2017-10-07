package com.example.user.eshtri_first_pafge;

/**
 * Created by pratap.kesaboyina on 24-12-2014.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
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
import com.example.user.eshtri_first_pafge.R.mipmap;
import com.example.user.eshtri_first_pafge.SectionListDataAdapter.SingleItemRowHolder;

import java.util.ArrayList;

/**
 * For displaying list of a section.
 */
public class SectionListDataAdapter extends Adapter<SingleItemRowHolder> {

	private final ArrayList<SingleItemModel> itemsList;
	private final Context mContext;

	public SectionListDataAdapter(Context context, ArrayList<SingleItemModel> itemsList) {
		this.itemsList = itemsList;
		mContext = context;
	}

	@Override
	public SectionListDataAdapter.SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(layout.list_single_card, null);
		SectionListDataAdapter.SingleItemRowHolder mh = new SectionListDataAdapter.SingleItemRowHolder(v);
		return mh;
	}

	@Override
	public void onBindViewHolder(SectionListDataAdapter.SingleItemRowHolder holder, int i) {

		SingleItemModel singleItem = this.itemsList.get(i);
		holder.tvTitle.setText(singleItem.getName());
		holder.itemImage.setImageResource(mipmap.test);

		holder.name = singleItem.getName();
		holder.det = singleItem.getDetails();
		holder.pro = singleItem.getProperties();
		holder.address = singleItem.getAddress();
		holder.price = singleItem.getPrice();
		holder.owner = singleItem.getOwner();
		holder.phone = singleItem.getPhone();

		// setting info of each product

		/*
		 * Glide.with(mContext) .load(feedItem.getImageURL())
		 * .diskCacheStrategy(DiskCacheStrategy.ALL) .centerCrop()
		 * .error(R.drawable.bg) .into(feedListRowHolder.thumbView);
		 */
	}

	@Override
	public int getItemCount() {
		return null != this.itemsList ? this.itemsList.size() : 0;
	}

	public class SingleItemRowHolder extends ViewHolder {

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

			tvTitle = view.findViewById(id.tvTitle);
			itemImage = view.findViewById(id.itemImage);

			view.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {

					Toast.makeText(v.getContext(), SectionListDataAdapter.SingleItemRowHolder.this.tvTitle.getText(),
							Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(SectionListDataAdapter.this.mContext, ProductPreview.class);

					intent.putExtra("name", SectionListDataAdapter.SingleItemRowHolder.this.name);
					intent.putExtra("det", SectionListDataAdapter.SingleItemRowHolder.this.det);
					intent.putExtra("pro", SectionListDataAdapter.SingleItemRowHolder.this.pro);
					intent.putExtra("address", SectionListDataAdapter.SingleItemRowHolder.this.address);
					intent.putExtra("price", SectionListDataAdapter.SingleItemRowHolder.this.price);
					intent.putExtra("owner", SectionListDataAdapter.SingleItemRowHolder.this.owner);
					intent.putExtra("phone", SectionListDataAdapter.SingleItemRowHolder.this.phone);
					intent.putExtra("publicProduct", true);

					SectionListDataAdapter.this.mContext.startActivity(intent);

				}
			});

		}

	}

}
