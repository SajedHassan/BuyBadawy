package com.example.user.eshtri_first_pafge;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 7/30/2017.
 */
public class CA extends ArrayAdapter<Product> {

    private Context context;
    private List<Product> objects;

    /**
     * The one and only constructor.
     *
     * @param context context
     * @param objects list of products
     */
    public CA(@NonNull final Context context, @NonNull final List<Product> objects) {
        super(context, 0, objects);
        this.context = context;
        this.objects = objects;
        //Log.v("hna CS", objects.get(0).details);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull final ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(this.getContext()).inflate(
                    R.layout.custom_adabtor_lay, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the
        // list
        final Product currentAndroidFlavor = this.getItem(position);

        // Find the TextView in the list_item.xml layout with the ID
        // version_name
        final TextView nameTextView = listItemView.findViewById(R.id.productName);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        Log.v("product loaded from CA", " hnaaaaaaaaa");

        nameTextView.setText(currentAndroidFlavor.productN);
        final TextView pn = listItemView.findViewById(R.id.productName);

        final ImageButton removeBtn = listItemView.findViewById(R.id.imageButton);
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final ProductCDBH deleteProduct = new ProductCDBH(CA.this.context, new AsyncResponse() {
                    @Override
                    public void processFinish(final String response) {
                        Log.v("delete response", response);
                        final AlertDialog.Builder builder = new AlertDialog.Builder(CA.this.context);
                        builder.setCancelable(true);
                        builder.setTitle("Server response:");
                        builder.setMessage(response);
                        builder.show();
                        if ("Product deleted successfully".equals(response)) {
                            CA.this.objects.remove(position);
                            CA.this.notifyDataSetChanged();
                        }
                    }
                });
                final String task = "delete";
                deleteProduct.execute(task, Integer.valueOf(currentAndroidFlavor.productId));
            }
        });

        return listItemView;
    }
}
