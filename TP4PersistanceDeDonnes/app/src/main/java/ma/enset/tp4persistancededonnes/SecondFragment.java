package ma.enset.tp4persistancededonnes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import ma.enset.tp4persistancededonnes.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {
    private static final String FILE_NAME="myFile.dat";
    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        List<Etudiant> listEtu=new ArrayList<>();
        try {
            FileInputStream fis= getActivity().openFileInput(FILE_NAME);
            ObjectInputStream ois =new ObjectInputStream(fis);
            listEtu=(List<Etudiant>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Etudiant> list=listEtu;
        binding.buttonEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Etudiant etudiant=new Etudiant();
                etudiant.setCne(binding.cneEtudiant.getText().toString());
                etudiant.setNom(binding.NomEtudiant.getText().toString());
                etudiant.setPrenom(binding.PreEtudiant.getText().toString());
                etudiant.setSexe(binding.SexeEtudiant.getText().toString());
                etudiant.setDate(binding.DateNEtu.getText().toString());
//                if(etudiant.getSexe().equals("F")) {
//                    etudiant.setImage("drawable/"+R.drawable.etudiante);
//                }
//                if(etudiant.getSexe().equals("M")) {
//                    etudiant.setImage("drawable/"+R.drawable.etudiant);
//                }
                try {
                    FileOutputStream fos=getActivity().openFileOutput(FILE_NAME, Context.MODE_PRIVATE);

                    ObjectOutputStream oos=new ObjectOutputStream(fos);
                    list.add(etudiant);
                    oos.writeObject(list);

                    oos.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}