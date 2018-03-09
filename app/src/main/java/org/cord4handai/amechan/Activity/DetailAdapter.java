package org.cord4handai.amechan.Activity;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.cord4handai.amechan.Model.ClaimService;
import org.cord4handai.amechan.R;

import java.util.List;

/**
 * Created by ryosuke on 2018/03/09.
 */

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {

    private List<ClaimService.Comment> mItems;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // each data item is just a string in this case
        public TextView user_name;
        public TextView comment_body;

        public ViewHolder(View v) {
            super(v);
            user_name = (TextView)v.findViewById(R.id.user_name_title);
            comment_body = (TextView)v.findViewById(R.id.user_comment);

        }
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);

        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        ClaimService.Comment item = mItems.get(position);

        holder.user_name.setText(item.userName);
        holder.comment_body.setText(item.content);

        Log.d("TAG" , item.toString());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (mItems != null) {
            return mItems.size();
        }
        return 0;
    }

    public void setItemAndReflesh(List<ClaimService.Comment> items){
        this.mItems  =  items;
        notifyDataSetChanged();

    }
}
