package cat.tecnocampus.mobileapps.practica3.HomarMasachsFrancesc.meninoSuredaPau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Adapter.OnNoteListener {

    List<String> imageList;
    FragmentPhoto fragmentPhoto;
    FragmentBigPhoto fragmentBigPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageList = new ArrayList<String>();

        int orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            createList();
        }else{
            createLandscape();
        }

    }

    public void createList(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentPhoto = new FragmentPhoto();

        fragmentTransaction.add(R.id.fragment_container, fragmentPhoto);
        fragmentTransaction.commit();
    }

    public void createLandscape(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentPhoto = new FragmentPhoto();
        fragmentTransaction.add(R.id.fragmentPhoto, fragmentPhoto);
        fragmentTransaction.commit();

        FragmentManager fragmentManagerBig = getSupportFragmentManager();
        FragmentTransaction fragmentTransactionBig = fragmentManagerBig.beginTransaction();
        fragmentBigPhoto = new FragmentBigPhoto();
        fragmentTransactionBig.replace(R.id.fragmentBigPhoto, fragmentBigPhoto);
        fragmentTransactionBig.commit();
    }


    @Override
    public void onNoteClick(String image) {
        int orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentBigPhoto = new FragmentBigPhoto();
            Bundle bundle = new Bundle();
            bundle.putString("image",image);
            fragmentBigPhoto.setArguments(bundle);
            fragmentTransaction.replace(R.id.fragment_container, fragmentBigPhoto);
            fragmentTransaction.commit();
        }else{
            fragmentBigPhoto.changePhoto(image);
        }

    }

    public void changeFragments(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragmentPhoto);
        fragmentTransaction.commit();
    }


}