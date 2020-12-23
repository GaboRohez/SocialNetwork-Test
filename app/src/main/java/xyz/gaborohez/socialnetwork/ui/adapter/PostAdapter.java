package xyz.gaborohez.socialnetwork.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import xyz.gaborohez.socialnetwork.R;
import xyz.gaborohez.socialnetwork.data.models.Publications;
import xyz.gaborohez.socialnetwork.databinding.ItemPostBinding;
import xyz.gaborohez.socialnetwork.ui.utils.AppUtils;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private Context context;
    private List<Publications> list;
    private static final String TAG = "PostAdapter";

    public PostAdapter(Context context, List<Publications> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemPostBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context)
                .asBitmap()
                .load(list.get(position).getUser().getImage())
                .into(holder.binding.ivProfile);

        holder.binding.tvName.setText(String.format(context.getString(R.string.user_name), list.get(position).getUser().getName(), list.get(position).getUser().getSurname()));

        Log.d(TAG, "onBindViewHolder: "+AppUtils.getElapsedTime(list.get(position).getCreated_at()));
        holder.binding.tvDate.setText(AppUtils.getElapsedTime(list.get(position).getCreated_at()));

        if (list.get(position).getText() != null && !list.get(position).getText().isEmpty()){
            holder.binding.tvComment.setVisibility(View.VISIBLE);
            holder.binding.tvComment.setText(list.get(position).getText());
        }

        if (list.get(position).getFile() != null && !list.get(position).getFile().isEmpty()){
            holder.binding.ivImage.setVisibility(View.VISIBLE);

            Glide.with(context)
                    .load(Base64.decode(list.get(position).getFile(), Base64.DEFAULT))
                    .into(holder.binding.ivImage);

        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemPostBinding binding;

        public ViewHolder(ItemPostBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
