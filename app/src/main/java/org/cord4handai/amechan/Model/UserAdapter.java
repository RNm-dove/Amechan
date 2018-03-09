package org.cord4handai.amechan.Model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.cord4handai.amechan.R;
import org.cord4handai.amechan.User;

import java.util.List;

/**
 * Created by ryosuke on 2018/03/09.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {



    private Context context;
    private List<User> items;

    static class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView userName;
        private final TextView favNumver;


        public MyViewHolder(View itemView) {
            super(itemView);
            userName = (TextView) itemView.findViewById(R.id.user_rank_name);
            favNumver = (TextView) itemView.findViewById(R.id.fav_numver_text);

        }
    }

    public UserAdapter(Context context){

        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        final View view = LayoutInflater.from(context).inflate(R.layout.row_user_rank, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final User item = getItemAt(position);

        holder.userName.setText(item.userName);
        holder.favNumver.setText(String.valueOf(item.favNumver));
        Log.d("TAG" , item.toString());
    }


    private User getItemAt(int position) {
        return items.get(position);
    }

    public void setItemAndRefresh(List<User> items){
        this.items = items;
        notifyDataSetChanged();
    }



    @Override
    public int getItemCount() {
        if (items != null) {
            return items.size();
        }
        return 0;
    }



}
