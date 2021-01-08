package xyz.gaborohez.socialnetwork.ui.adapter;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import xyz.gaborohez.socialnetwork.R;
import xyz.gaborohez.socialnetwork.data.models.Publications;
import xyz.gaborohez.socialnetwork.databinding.ItemPostBinding;
import xyz.gaborohez.socialnetwork.ui.utils.AppUtils;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private Context context;
    private PostIn listener;
    private List<Publications> list;
    private static final String TAG = "PostAdapter";

    public PostAdapter(Context context, List<Publications> list, PostIn listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    public interface PostIn {
        void deletePost(String id, int position);
        void editPost(Publications publication);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemPostBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Log.d(TAG, "onBindViewHolder: "+list.get(position).getUser().getImage());
        Glide.with(context)
                .asBitmap()
                .load(list.get(position).getUser().getImage())
                .into(holder.binding.ivProfile);

        holder.binding.tvName.setText(String.format(context.getString(R.string.user_name), list.get(position).getUser().getName(), list.get(position).getUser().getSurname()));

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

        holder.binding.btnOption.setOnClickListener(v -> showPopUpMenu(holder));
    }

    private void showPopUpMenu(ViewHolder holder) {
        PopupMenu popup = new PopupMenu(context, holder.binding.btnOption);
        popup.getMenuInflater().inflate(R.menu.popup_menu_post, popup.getMenu());
        popup.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()){
                case R.id.edit:
                    listener.editPost(list.get(holder.getAdapterPosition()));
                    break;
                case R.id.delete:
                    listener.deletePost(list.get(holder.getAdapterPosition()).getId(), holder.getAdapterPosition());
                    break;
            }
            return true;
        });
        popup.show();
    }

    public void removePost(int position) {
        list.remove(position);
        notifyDataSetChanged();
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
