package app.wizzeye.app.fragments;

import android.app.Fragment;
import android.content.ClipData;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;

import java.util.ArrayList;
import java.util.Arrays;

import app.wizzeye.app.MainActivity;
import app.wizzeye.app.R;
import app.wizzeye.app.WizzeyeApplication;
import app.wizzeye.app.Data;
import app.wizzeye.app.JSONResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatFragment extends InRoomFragment {

    int i = 0;
    Handler mHandler;
    ListView l;
    ArrayList<String> items;
    ArrayList<Data> contentList;
    String varTest = "";
    private String longitude;
    private String latitude;
    private MenuItem button_back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("url", "je suis dans le onCreateView de CallFragment");
        View view = inflater.inflate(R.layout.fragment_tchat, container, false);
        setHasOptionsMenu(true);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        l = view.findViewById(R.id.chatlist);

        items = new ArrayList<>();
        l.setAdapter(new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_list_item_1,items));
        //l.setAdapter(new ArrayAdapter<String>(view.getContext(),R.layout.item_chat,items));
        mHandler = new Handler();
        mHandler.postDelayed(mAction, 0);


        button_back = view.findViewById(R.id.back_link);
        //button_back.setOnMenuItemClickListener(v -> onClickBack());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.inchat, menu);

        MenuItem backLinkItem = menu.findItem(R.id.back_link);
        backLinkItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return onClickBack();
            }
        });


        super.onCreateOptionsMenu(menu, inflater);
    }

    /*@Override
    public boolean onOptionItemsSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.back_link:
                onClickBack();
                return true;
            default :
                super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }*/

    private boolean onClickBack(){
        Log.d("url","DEBUT du onClickChat ");
        Fragment f = new CallFragment();
        getFragmentManager().beginTransaction()
            .replace(android.R.id.content, f)
            .commit();
        Log.d("url","FIN du onClickChat ");
        return true;
    }

    @Override
    public void onStop() {
        /* Called when the presentation is dismissed. Do cleanup here. */
        mHandler.removeCallbacks(mAction);
        super.onStop();
    }

    private final Runnable mAction = new Runnable() {
        @Override
        public void run() {
            //latitude = ((MainActivity)getActivity()).getLatitude();
            //longitude = ((MainActivity)getActivity()).getLongitude();
            longitude = "48.117266";
            latitude = "-1.67777926";
            Call<JSONResponse> response = WizzeyeApplication.getService().serviceResponse(latitude, longitude);
            response.enqueue(new Callback<JSONResponse>() {
                @Override
                public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {

                    JSONResponse jsonResponse = response.body();

                    if(jsonResponse.getContents() != null) {
                        contentList = new ArrayList<Data>(Arrays.asList(jsonResponse.getContents()));

                        if(!varTest.equals(contentList.get(contentList.size()-1).getTexte())){
                            varTest = contentList.get(contentList.size()-1).getTexte();

                            items.add(contentList.get(contentList.size()-1).getName() + " : " + varTest);
                            Log.d("url1", varTest);
                            l.invalidateViews();
                            l.invalidate();
                        }
                    }
                }
                @Override
                public void onFailure(Call<JSONResponse> call, Throwable t) {
                    Log.d("url", t.getMessage());
                }
            });

            mHandler.postDelayed(this, 1000);
        }
    };
}
