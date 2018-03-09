package org.cord4handai.amechan.Model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.MalformedJsonException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.cord4handai.amechan.R;

import java.util.List;

/**
 * Created by ryosuke on 2018/03/09.
 */

public class ClaimAdapter extends RecyclerView.Adapter<ClaimAdapter.MyViewHolder> {



    private Context context;
    private OnClaimItemClickListener onClaimItemClickListener;
    private List<ClaimService.Claim> items;

    static class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView claimContent;
        private final TextView claimSubmit;
        private final TextView claimAge;
        //private final TextView claimCreatedAt;
        private final TextView claimSex;



        public MyViewHolder(View itemView) {
            super(itemView);
            claimContent = (TextView) itemView.findViewById(R.id.claim_content);
            claimSubmit= (TextView) itemView.findViewById(R.id.claim_body);
            claimAge = (TextView) itemView.findViewById(R.id.age);
            //claimCreatedAt = (TextView) itemView.findViewById(R.id.claim_created_at);
            claimSex = (TextView) itemView.findViewById(R.id.sex);

        }
    }

    public ClaimAdapter(Context context, OnClaimItemClickListener onClaimItemClickListener){

        this.context = context;
        this.onClaimItemClickListener = onClaimItemClickListener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        final View view = LayoutInflater.from(context).inflate(R.layout.claim_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final ClaimService.Claim item = getItemAt(position);

        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                onClaimItemClickListener.onClaimItemClick(item);
            }
        });



        holder.claimContent.setText(item.content);
        holder.claimSubmit.setText(Where.judgeWhereFromInt(item.submit).name());
        holder.claimAge.setText(String.valueOf(item.age) + "代");
        holder.claimSex.setText(Sex.judgeSexFromInt(item.sex).name());
        //holder.claimCreatedAt.setText("createdAt:" + String.valueOf(item.age));

        Log.d("TAG" , item.toString());




    }

    private ClaimService.Claim getItemAt(int position) {
        return items.get(position);
    }

    public void setItemAndRefresh(List<ClaimService.Claim> items){
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

    public interface OnClaimItemClickListener {
        void onClaimItemClick(ClaimService.Claim item);
    }



}
