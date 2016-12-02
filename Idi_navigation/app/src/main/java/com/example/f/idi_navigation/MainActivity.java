package com.example.f.idi_navigation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.f.idi_navigation.R.id.editText1;
import static com.example.f.idi_navigation.R.id.thing_proto;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private MySQLiteHelper db;




    public  EditText editText_Author ;
    public  EditText editText_Year;
    public  EditText editText_Publisher ;
    public  EditText editText_Category;
    public  EditText editText_Personal_Evaluation;


    CategoriaFragment categoriaFragment = new CategoriaFragment();
    SearchFragment searchFragment = new SearchFragment();
    AddFragment addFragment = new AddFragment();

    FragmentManager manager = getSupportFragmentManager();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        manager.beginTransaction().replace(R.id.relativeLayour_fragment, this.categoriaFragment, this.categoriaFragment.getTag()).commit();

        db = new MySQLiteHelper(this); //object database


        Log.d("LUNGHEZZA",String.valueOf(db.getSize()));




        //*-Button per drawer manager-*//
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        //*-Button per drawer manager-*//






        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();



    }


    void add_Book(){
        EditText  editText_Year = (EditText)findViewById(R.id.editText3);
        int n =Integer.valueOf(editText_Year.getText().toString());
        String year = String.valueOf(n);
        boolean flag =true;

        for(int i=0;i<year.length() && flag;i++){
            if(year.charAt(i)!='0'&&
                    year.charAt(i)!='1' &&
                    year.charAt(i)!='2' &&
                    year.charAt(i)!='3' &&
                    year.charAt(i)!='4' &&
                    year.charAt(i)!='5' &&
                    year.charAt(i)!='6' &&
                    year.charAt(i)!='7' &&
                    year.charAt(i)!='8' &&
                    year.charAt(i)!='9'
                    ){
                flag = false;
            }
        }

        Book book = new Book();
        EditText editeText_Title=(EditText)findViewById(R.id.editText1);
        EditText  editText_Author = (EditText)findViewById(R.id.editText2);

        EditText  editText_Publisher = (EditText)findViewById(R.id.editText4);
        EditText   editText_Category= (EditText)findViewById(R.id.editText5);
        EditText   editText_Personal_Evaluation = (EditText)findViewById(R.id.editText6);

        String va=editeText_Title.getText().toString();

        book.setTitle(editeText_Title.getText().toString());
        book.setAuthor(editText_Author.getText().toString());

        book.setYear(Integer.valueOf(editText_Year.getText().toString()));
        book.setPublisher(editText_Publisher.getText().toString());
        book.setCategory(editText_Category.getText().toString());
        book.setPersonal_evaluation(editText_Personal_Evaluation.getText().toString());
        db.addBook(book);
        Log.d("LIBRO AGGIUNTO",va);


        Toast.makeText(getApplicationContext(),"Book added successfully",Toast.LENGTH_SHORT).show();

    }
    void clear_fields(){

        EditText editeText_Title=(EditText)findViewById(R.id.editText1);
       EditText  editText_Author = (EditText)findViewById(R.id.editText2);
        EditText  editText_Year = (EditText)findViewById(R.id.editText3);
        EditText  editText_Publisher = (EditText)findViewById(R.id.editText4);
        EditText   editText_Category= (EditText)findViewById(R.id.editText5);
        EditText   editText_Personal_Evaluation = (EditText)findViewById(R.id.editText6);
        editeText_Title.setText("");
        editText_Author.setText("");
        editText_Year.setText("");
        editText_Publisher.setText("");
        editText_Category.setText("");
        editText_Personal_Evaluation.setText("");



    }

    public void onClick(View view) {
        @SuppressWarnings("unchecked")
        int id = view.getId();
        switch (view.getId()) {
            case R.id.button3: // ADD BUTTON
                add_Book();
                clear_fields();
                 break;
            case R.id.button4:
                clear_fields();
                break;
            case R.id.button2:
                Log.d("ATTACCATO","SCHIA");



        }
    }
    private void setListAdapter(ArrayAdapter<Book> adapter) {
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        // Handle the camera action
        if (id == R.id.nav_category) {

         manager.beginTransaction().replace(R.id.relativeLayour_fragment, this.categoriaFragment, this.categoriaFragment.getTag()).commit();

        } else if (id == R.id.search_book) {

            manager.beginTransaction().replace(R.id.relativeLayour_fragment, this.searchFragment,this.searchFragment.getTag()).commit();

        } else if (id == R.id.nav_add) {

            manager.beginTransaction().replace(R.id.relativeLayour_fragment, this.addFragment, this.addFragment.getTag()).commit();

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
