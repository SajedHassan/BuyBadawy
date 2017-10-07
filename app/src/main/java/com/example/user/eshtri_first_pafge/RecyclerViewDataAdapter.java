package com.example.user.eshtri_first_pafge;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.eshtri_first_pafge.R.id;
import com.example.user.eshtri_first_pafge.R.layout;
import com.example.user.eshtri_first_pafge.RecyclerViewDataAdapter.ItemRowHolder;

import java.util.ArrayList;

/**
 * Extends the adapter class.
 */
public class RecyclerViewDataAdapter extends Adapter<ItemRowHolder> {

	private final ArrayList<SectionDataModel> dataList;
	private final Context mContext;

	public RecyclerViewDataAdapter(Context context, ArrayList<SectionDataModel> dataList) {
		this.dataList = dataList;
		mContext = context;
	}

	@Override
	public RecyclerViewDataAdapter.ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(layout.list_item, null);
		RecyclerViewDataAdapter.ItemRowHolder mh = new RecyclerViewDataAdapter.ItemRowHolder(v);
		return mh;
	}

	@Override
	public void onBindViewHolder(RecyclerViewDataAdapter.ItemRowHolder itemRowHolder, int i) {

		final String sectionName = this.dataList.get(i).getHeaderTitle();

		ArrayList singleSectionItems = this.dataList.get(i).getAllItemsInSection();

		itemRowHolder.itemTitle.setText(sectionName);

		SectionListDataAdapter itemListDataAdapter = new SectionListDataAdapter(this.mContext, singleSectionItems);

		itemRowHolder.recycler_view_list.setHasFixedSize(true);
		itemRowHolder.recycler_view_list
				.setLayoutManager(new LinearLayoutManager(this.mContext, LinearLayoutManager.HORIZONTAL, false));
		itemRowHolder.recycler_view_list.setAdapter(itemListDataAdapter);

		itemRowHolder.btnMore.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Toast.makeText(v.getContext(), "click event on more, " + sectionName, Toast.LENGTH_SHORT).show();

			}
		});

		/*
		 * Glide.with(mContext) .load(feedItem.getImageURL())
		 * .diskCacheStrategy(DiskCacheStrategy.ALL) .centerCrop()
		 * .error(R.drawable.bg) .into(feedListRowHolder.thumbView);
		 */
	}

	@Override
	public int getItemCount() {
		return null != this.dataList ? this.dataList.size() : 0;
	}

	public class ItemRowHolder extends ViewHolder {

		protected TextView itemTitle;

		protected RecyclerView recycler_view_list;

		protected Button btnMore;

		public ItemRowHolder(View view) {
			super(view);

			itemTitle = view.findViewById(id.itemTitle);
			recycler_view_list = view.findViewById(id.recycler_view_list);
			btnMore = view.findViewById(id.btnMore);

		}

	}

}
