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
        List<Book> values = bookData.getAllBooks();

        String[] item2= new String[values.size()];

        for(int i=0;i<values.size();i++){
            item2[i]=values.get(i).getTitle()+" "+values.get(i).getAuthor();
        }

        ListView l_view= (ListView) view.findViewById(R.id.lista_categoria);
        ArrayAdapter adapter= new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,item2);
        l_view.setAdapter(adapter);

        bookData.close();

        return view ;
    }

}
