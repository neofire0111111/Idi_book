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
    private List<Book> values;
    private String[]items2;
    private ListView l_view;
   private  ArrayAdapter adapter ;

    private View view_class;

    public SearchFragment() {

    }

    public List<Book> getValues() {
        return values;
    }

    public void setValues(List<Book> values) {
        this.values = values;
    }

    public ArrayAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(ArrayAdapter adapter) {
        this.adapter = adapter;
    }

    public void reInitList(){
        this.items2= new String[values.size()];

        for(int i=0;i<values.size();i++){
            this.items2[i]=values.get(i).getTitle()+" - "+values.get(i).getAuthor();
        }
        this.l_view = (ListView) view_class.findViewById(R.id.listView_search);
        adapter  = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,items2);
        l_view.setAdapter(adapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_search, container, false);
        bookData = new BookData(getContext());
        bookData.open();
        values = bookData.getAllBooks();
        this.view_class=view;

          this.items2= new String[values.size()];

        for(int i=0;i<values.size();i++){
           this.items2[i]=values.get(i).getTitle()+" - "+values.get(i).getAuthor();
        }

       this.l_view = (ListView) view.findViewById(R.id.listView_search);
      adapter  = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,items2);
        l_view.setAdapter(adapter);


        bookData.close();


        return view;
    }



}
