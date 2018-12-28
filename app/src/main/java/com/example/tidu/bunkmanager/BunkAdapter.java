package com.example.tidu.bunkmanager;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class BunkAdapter extends RecyclerView.Adapter<BunkAdapter.BunkViewHolder> {
    private Cursor mcursor;
    private Context mcontext;
    private OnItemClickListener mlistener;
    public BunkAdapter(Context context, Cursor cursor)
    {
        mcontext = context;
        mcursor = cursor;

    }
    public interface OnItemClickListener {
        void onpplusclick(int position);

    }

    public void setOnItemClickListner(OnItemClickListener listener)
    {

        mlistener = listener;

    }

    public static class BunkViewHolder extends RecyclerView.ViewHolder
    {
        public TextView nameText;
        public TextView min;
        public Button pplus;


        public BunkViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            nameText = itemView.findViewById(R.id.textview_name);
            min = itemView.findViewById(R.id.textview_min);
            pplus = itemView.findViewById(R.id.p);
            pplus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null)
                    {


                    }

                }
            });
        }
    }
    @NonNull
    @Override
    public BunkViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.subject,viewGroup,false);
        return new BunkViewHolder(view,mlistener);
    }

    @Override
    public void onBindViewHolder(@NonNull BunkViewHolder bunkViewHolder, int i) {
    if(!mcursor.moveToPosition(i))
        return;
    String name = mcursor.getString(mcursor.getColumnIndex(BunkContract.BunkEntry.COLUMN_NAME));
    String min = mcursor.getString(mcursor.getColumnIndex(BunkContract.BunkEntry.COLUMN_MIN));
    Long id = mcursor.getLong(mcursor.getColumnIndex(BunkContract.BunkEntry._ID));
    bunkViewHolder.nameText.setText(name);
    bunkViewHolder.min.setText(min + "%");
    bunkViewHolder.itemView.setTag(id);

    bunkViewHolder.pplus.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    });
    }

    @Override
    public int getItemCount() {
        return mcursor.getCount();
    }



    public void swapCursor (Cursor newCursor)
    {
       if(mcursor != null)
       {
           mcursor.close();
       }

       mcursor = newCursor;
       if(newCursor != null)
       {
         notifyDataSetChanged();

       }
    }



}
