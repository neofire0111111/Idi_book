package com.example.f.idi_navigation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriaFragment extends Fragment {


    private BookData bookData;


    public CategoriaFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View view =inflater.inflate(R.layout.fragment_categoria, container, false);

        bookData = new BookData(getContext());
        bookData.open();

        List<Book> values =sort( bookData.getAllBooks());


        String[] item2= new String[values.size()];

        for(int i=0;i<values.size();i++){
            item2[i]=values.get(i).getTitle()+" - "+values.get(i).getAuthor()+ " - " + String.valueOf(values.get(i).getYear());
        }

        ListView l_view= (ListView) view.findViewById(R.id.lista_categoria);
        ArrayAdapter adapter= new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,item2);
        l_view.setAdapter(adapter);

        bookData.close();

        return view ;
    }

    private List<Book> sort(List<Book> val){
        List<Book> tmp = val;

        int left = 0;

        int right = tmp.size()-1;

 //bubble sort bad sort but now it work

        for(int i=right;i>1;i--){
              for(int j=left;j<i;j++) {
                    //  if (getValueString(val.get(j).getTitle()) > getValueString(val.get(j + 1).getTitle())) {
                          if (more(val.get(j), val.get(j+1))) {
                          Book a= val.get(j);
                          val.set(j,val.get(j+1));
                          val.set(j+1,a);


                }
            }
        }


        return tmp;
    }

    public int getValueString(String word){
        int value=0;
          for(int i=1;i<word.length()+1;i++){
                value+=(i)*10*Integer.valueOf(word.charAt(word.length()-i));
        }
        return value;
    }

    public boolean more(Book a, Book b){
        boolean flag = false;

        int min_lenght=a.getTitle().length();
        if(b.getTitle().length()<=a.getTitle().length())min_lenght=b.getTitle().length();

        for(int i=0;i<min_lenght && flag==false ;i++){
            if(Integer.valueOf((a.getTitle()).toLowerCase().charAt(i))>=Integer.valueOf((b.getTitle().toLowerCase()).charAt(i)) ){
                return true;

            }
        }
        if(a.getTitle().length()>b.getTitle().length()){
            return true;
        }


        return flag;
    }

}
