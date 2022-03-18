package ma.enset.tp4persistancededonnes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.net.URL;


        import android.content.Context;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.net.URL;
        import java.util.List;

        import ma.enset.tp4persistancededonnes.R;

public class ListEtudiantsAdapter extends ArrayAdapter<Etudiant> {
    private int resource;
    private Context contextFrag;
    public ListEtudiantsAdapter(@NonNull Context context, int resource, @NonNull List<Etudiant> etudiants) {
        super(context, resource, etudiants);
        this.resource=resource;
        contextFrag=context;
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

       setupImageLoader();
        View listItemView=convertView;
        if(listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(resource,parent,false);
        }
        TextView cne=listItemView.findViewById(R.id.cne);
        TextView nom=listItemView.findViewById(R.id.nomEtudiant);
        TextView prenom=listItemView.findViewById(R.id.PrenomEtudiant);
        TextView Date=listItemView.findViewById(R.id.DateNaissance);


        ImageView imageView=listItemView.findViewById(R.id.imageView);

        cne.setText(getItem(position).getCne());
        nom.setText(getItem(position).getNom());
        prenom.setText(getItem(position).getPrenom());
        Date.setText(getItem(position).getDate());
//        if(sexe.toString()=="F") {
//            imageView.setImageResource(R.drawable.etudiante);
//        }
//        if(sexe.toString()=="M") {
//            imageView.setImageDrawable(getContext().getDrawable(R.drawable.etudiant) );
//        }

        String imageURL = getItem(position).getImage();

        int defaultImage = contextFrag.getResources().getIdentifier("@drawable/image", null, contextFrag.getOpPackageName());

        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(true)
                .showImageForEmptyUri(defaultImage)
                .showImageOnFail(defaultImage)
                .showImageOnLoading(defaultImage).build();

        //download and display image from url
        imageLoader.displayImage(imageURL, imageView, options);

        Log.i("info", "===============>  "+imageView.toString());


        ImageView iv = (ImageView)listItemView.findViewById(R.id.imageView);
        if(getItem(position).getSexe().equals("F"))
        iv.setImageResource(R.drawable.etudiante);
        else iv.setImageResource(R.drawable.etudiant);

        return listItemView;
    }

    private void setupImageLoader()
    {
        // UNIVERSAL IMAGE LOADER SETUP
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                contextFrag)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);
        // END - UNIVERSAL IMAGE LOADER SETUP
    }

}
