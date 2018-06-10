package com.example.bochkarevfy.bcontact;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private List<User> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;


    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, List<User> data) {
        this.mInflater = LayoutInflater.from(context);
//        this.context = context;
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        holder.firstTextView.setText(mData.get(position).getFirstName());
        holder.surTextView.setText(mData.get(position).getSurName());

//        Context context = holder.po //<----- Add this line
//        RssItem item = rssItems.get(i);
//
        //change context here
//        Picasso.with(context).load(item.imageLink).into(viewHolder.postImage);

//        ImageView imageView = holder.myImageView;
//        new DownloadImageTask(imageView).execute("http://www.icon100.com/up/4189/128/15-contacts.png");

        Picasso.with(mInflater.getContext())
                .load("http://nbics.net" + mData.get(position).getImage())
                .error(R.mipmap.ic_launcher)
                .into(holder.myImageView);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView firstTextView;
        TextView surTextView;
        ImageView myImageView;

        ViewHolder(View itemView) {
            super(itemView);
            firstTextView = (TextView) itemView.findViewById(R.id.firstName);
            surTextView = (TextView) itemView.findViewById(R.id.surName);
            myImageView = (ImageView) itemView.findViewById(R.id.user_imageview);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    User getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
//            pd.show();
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
//                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
//            pd.dismiss();
            bmImage.setImageBitmap(result);
        }
    }
}


