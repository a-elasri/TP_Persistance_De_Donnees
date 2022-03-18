package ma.enset.tp4persistancededonnes;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import ma.enset.tp4persistancededonnes.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {
    private static final String FILE_NAME="myFile.dat";
    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listViewEtu=binding.listViewEtudiants;

        List<Etudiant> data=new ArrayList<>();

        try {
            FileInputStream fis= getActivity().openFileInput(FILE_NAME);
            BufferedInputStream bis =new BufferedInputStream(fis);
            ObjectInputStream ois=new ObjectInputStream(fis);

            data.addAll((List<Etudiant>)ois.readObject());
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ListEtudiantsAdapter adapter=new ListEtudiantsAdapter(getContext(),R.layout.list_etudiant_item,data);
        listViewEtu.setAdapter(adapter);


        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}