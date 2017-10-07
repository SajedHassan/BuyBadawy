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

;

/**
 * Created by user on 7/30/2017.
 */

public class CA extends ArrayAdapter<Product> {

    Context context;
    List<Product> objects;

    public CA(@NonNull Context context, @NonNull List<Product> objects) {
        super(context, 0, objects);
        this.context = context;
        this.objects = objects;
        Log.v("hna CS", objects.get(0).details);
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
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.custom_adabtor_lay, parent, false);
        }


        // Get the {@link AndroidFlavor} object located at this position in the list
        final Product currentAndroidFlavor = (Product) getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.productName);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        Log.v("product loaded from CA", " hnaaaaaaaaa");

        nameTextView.setText(currentAndroidFlavor.productN);
        final TextView pn = ((TextView) listItemView.findViewById(R.id.productName));

        ImageButton removeBtn = (ImageButton) listItemView.findViewById(R.id.imageButton);
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductCDBH deleteProduct = new ProductCDBH(context, new AsyncResponse() {
                    @Override
                    public void processFinish(String response) {
                        Log.v("delete response", response);
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setCancelable(true);
                        builder.setTitle("Server response:");
                        builder.setMessage(response);
                        builder.show();
                        if (response.equals("Product deleted successfully")) {
                            objects.remove(position);
                            notifyDataSetChanged();
                        }
                    }
                });
                String task = "delete";
                deleteProduct.execute(task, currentAndroidFlavor.productId);
            }
        });


        return listItemView;
    }
}
