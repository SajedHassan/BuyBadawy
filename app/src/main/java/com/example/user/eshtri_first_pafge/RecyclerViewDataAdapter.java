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

    public RecyclerViewDataAdapter(final Context context, final ArrayList<SectionDataModel> dataList) {
        super();
        this.dataList = dataList;
        this.mContext = context;
    }

    @Override
    public final RecyclerViewDataAdapter.ItemRowHolder onCreateViewHolder(final ViewGroup viewGroup, final int i) {
        final View v = LayoutInflater.from(viewGroup.getContext()).inflate(layout.list_item, null);
        return new ItemRowHolder(v);
    }

    @Override
    public final void onBindViewHolder(final RecyclerViewDataAdapter.ItemRowHolder itemRowHolder, final int i) {

        final String sectionName = this.dataList.get(i).getHeaderTitle();

        final ArrayList singleSectionItems = this.dataList.get(i).getAllItemsInSection();

        itemRowHolder.itemTitle.setText(sectionName);

        final SectionListDataAdapter itemListDataAdapter = new SectionListDataAdapter(this.mContext, singleSectionItems);

        itemRowHolder.recycler_view_list.setHasFixedSize(true);
        itemRowHolder.recycler_view_list
                .setLayoutManager(new LinearLayoutManager(this.mContext, LinearLayoutManager.HORIZONTAL, false));
        itemRowHolder.recycler_view_list.setAdapter(itemListDataAdapter);

        itemRowHolder.btnMore.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {

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
    public final int getItemCount() {
        return (this.dataList != null) ? this.dataList.size() : 0;
    }

    public class ItemRowHolder extends ViewHolder {

        final TextView itemTitle;

        RecyclerView recycler_view_list;

        Button btnMore;

        public ItemRowHolder(final View view) {
            super(view);

            this.itemTitle = view.findViewById(id.itemTitle);
            this.recycler_view_list = view.findViewById(id.recycler_view_list);
            this.btnMore = view.findViewById(id.btnMore);

        }

    }

}
