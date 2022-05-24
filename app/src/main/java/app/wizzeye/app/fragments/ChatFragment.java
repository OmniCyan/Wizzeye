package app.wizzeye.app.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import app.wizzeye.app.R;
import app.wizzeye.app.WizzeyeApplication;
import app.wizzeye.app.Data;
import app.wizzeye.app.JSONResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatFragment extends InRoomFragment{

    int i = 0;
    Handler mHandler;
    ListView l;
    ArrayList<String> items;
    ArrayList<Data> contentList;
    String varTest = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("url", "je suis dans le onCreateView de CallFragment");
        View view = inflater.inflate(R.layout.activity_tchat, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view,  Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);

        l = view.findViewById(R.id.chatlist);

        items = new ArrayList<>();
        l.setAdapter(new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_list_item_1,items));

        //l.setAdapter(new ArrayAdapter<String>(view.getContext(),R.layout.item_chat,items));

        mHandler = new Handler();
        mHandler.postDelayed(mAction, 0);

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
            Call<JSONResponse> response = WizzeyeApplication.getService().serviceResponse(String.valueOf(i));
            response.enqueue(new Callback<JSONResponse>() {
                @Override
                public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {

                    JSONResponse jsonResponse = response.body();
                    contentList = new ArrayList<Data>(Arrays.asList(jsonResponse.getContents()));

                    if(!varTest.equals(contentList.get(contentList.size()-1).getTexte())){
                        varTest = contentList.get(contentList.size()-1).getTexte();

                        items.add(contentList.get(contentList.size()-1).getName() + " : " + varTest);
                        Log.d("url1", varTest);
                        l.invalidateViews();
                        l.invalidate();
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
