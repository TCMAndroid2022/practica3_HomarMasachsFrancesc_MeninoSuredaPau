package cat.tecnocampus.mobileapps.practica3.HomarMasachsFrancesc.meninoSuredaPau;

import android.os.Bundle;
import android.provider.DocumentsContract;
import android.sax.RootElement;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;


public class FragmentPhoto extends Fragment {

    Adapter adapter;
    List<String> images = new ArrayList<String>();
    RecyclerView imageList;
    View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_layout, container, false);

        imageList = rootView.findViewById(R.id.imageList);
        imageList.setLayoutManager(new LinearLayoutManager(getContext()));

        if (adapter==null){
            adapter = new Adapter(getContext(), images, (Adapter.OnNoteListener) getActivity());
            setData();
        }

        imageList.setAdapter(adapter);



        return rootView;
    }

    private void setData(){
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference listRef = firebaseStorage.getReference().child("images");
        listRef.listAll()
                .addOnSuccessListener(new OnSuccessListener<ListResult>(){
                    @Override
                    public void onSuccess(ListResult listResult) {
                        for(StorageReference item : listResult.getItems()){
                            images.add(item.getPath());
                        }
                        adapter.notifyDataSetChanged();
                    }
                })
                .addOnFailureListener(new OnFailureListener(){

                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.v("Error: ",e.getMessage());
                    }
                });
    }
}
