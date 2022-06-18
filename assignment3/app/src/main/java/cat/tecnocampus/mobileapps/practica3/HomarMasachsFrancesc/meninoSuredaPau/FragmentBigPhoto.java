package cat.tecnocampus.mobileapps.practica3.HomarMasachsFrancesc.meninoSuredaPau;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FragmentBigPhoto extends Fragment {

    View rootView;
    ImageView imageView;
    String imagePath;
    FirebaseStorage firebaseStorage;
    StorageReference imgReference;
    Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_big_photo, container, false);

        button = rootView.findViewById(R.id.backButton);

        int orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            button.setVisibility(View.VISIBLE);
        }else{
            button.setVisibility(View.GONE);
        }

        Bundle bundle = getArguments();
        if (bundle==null){
            imagePath = "gs://practica-3-f5c9a.appspot.com/images/1.png";
        }else{
            imagePath = "gs://practica-3-f5c9a.appspot.com/"+bundle.getString("image");

        }
        imageView = rootView.findViewById(R.id.imageBig);

        firebaseStorage = FirebaseStorage.getInstance();
        imgReference = firebaseStorage.getReferenceFromUrl(imagePath);

        Glide.with(getContext()).load(imgReference).into(imageView);

        return rootView;
    }

    public void changePhoto(String image) {
        imgReference = firebaseStorage.getReferenceFromUrl("gs://practica-3-f5c9a.appspot.com/"+image);
        Glide.with(getContext()).load(imgReference).into(imageView);
    }
}
