package cat.tecnocampus.mobileapps.practica3.HomarMasachsFrancesc.meninoSuredaPau;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    List<String> images;
    FirebaseStorage firebaseStorage;
    OnNoteListener listener;

    public Adapter(Context context, List<String> images, OnNoteListener onNoteListener) {
        this.context = context;
        this.images = images;
        firebaseStorage = FirebaseStorage.getInstance();
        this.listener = onNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_img, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.id.setText(images.get(position));
        StorageReference imgReference = firebaseStorage.getReferenceFromUrl("gs://practica-3-f5c9a.appspot.com/"+images.get(position));
        Glide.with(context).load(imgReference).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView id;
        ImageView image;
        CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.idIMG);
            image = itemView.findViewById(R.id.imageSmall);
            cardView = itemView.findViewById(R.id.cardView);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onNoteClick(images.get(getBindingAdapterPosition()));
        }
    }

    public interface OnNoteListener{
        void onNoteClick(String image);
    }
}


