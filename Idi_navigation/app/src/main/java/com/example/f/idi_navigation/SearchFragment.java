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
public class SearchFragment extends Fragment {


    private BookData bookData;
    public SearchFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_search, container, false);

        bookData = new BookData(getContext());
        bookData.open();
        List<Book> values = bookData.getAllBooks();

        String[] item2= new String[values.size()];

        for(int i=0;i<values.size();i++){
            item2[i]=values.get(i).getTitle()+" "+values.get(i).getAuthor();
        }

        ListView l_view= (ListView) view.findViewById(R.id.listView_search);
        ArrayAdapter adapter= new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,item2);
        l_view.setAdapter(adapter);

        bookData.close();


        return view;
    }



}
