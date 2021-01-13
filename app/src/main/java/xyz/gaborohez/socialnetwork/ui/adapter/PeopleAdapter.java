package xyz.gaborohez.socialnetwork.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import xyz.gaborohez.socialnetwork.R;
import xyz.gaborohez.socialnetwork.data.models.User;
import xyz.gaborohez.socialnetwork.databinding.ItemPeopleBinding;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolder> {

    private final List<User> list;
    private final Context context;
    private final PeopleIn listener;

    public interface PeopleIn{
        void showProfile(User user);
    }

    public PeopleAdapter(List<User> list, Context context, PeopleIn listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemPeopleBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (list.get(position).getImage() != null){
            Glide.with(context)
                    .asBitmap()
                    .load(list.get(position).getImage())
                    .into(holder.binding.ivProfile);
        }else {
            Glide.with(context)
                    .load(context.getDrawable(R.drawable.ic_person))
                    .into(holder.binding.ivProfile);
        }

        holder.binding.tvName.setText(String.format(context.getString(R.string.user_name), list.get(position).getName(), list.get(position).getSurname()));

        holder.binding.tvUsername.setText(list.get(position).getNick());
        holder.binding.btnViewProfile.setOnClickListener(v -> listener.showProfile(list.get(holder.getAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final ItemPeopleBinding binding;

        public ViewHolder(ItemPeopleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
