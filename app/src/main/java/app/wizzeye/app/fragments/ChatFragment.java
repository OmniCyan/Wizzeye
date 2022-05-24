package app.wizzeye.app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.iristick.smartglass.support.app.HudPresentation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import app.wizzeye.app.BaseActivity;
import app.wizzeye.app.R;
import app.wizzeye.app.SettingsActivity;
import app.wizzeye.app.WizzeyeApplication;
import app.wizzeye.app.fragments.InRoomFragment;
import app.wizzeye.app.headwork.Data;
import app.wizzeye.app.service.CallState;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatFragment extends InRoomFragment{

    int i = 0;
    private TextView mDisplayText;
    Handler mHandler;
    ListView l;
    ArrayList<String> items;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("url", "je suis dans le onCreateView de CallFragment");
        View view = inflater.inflate(R.layout.activity_tchat, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view,  Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        //Set layout.
        /*
        setContentView(R.layout.headwork_hud);
        // Get widgets defined in the layout.
        mDisplayText = findViewById(R.id.clock);
        // Start updating the clock.
        */

        l = view.findViewById(R.id.chatlist);

        items = new ArrayList<>();
        l.setAdapter(new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_list_item_1,items));
        //l.setAdapter(new ArrayAdapter<String>(view.getContext(),R.layout.item_chat,items));

        //mDisplayText = view.findViewById(R.id.clock);
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
            i = i + 1;
            Call<Data> response = WizzeyeApplication.getService().serviceResponse(String.valueOf(i));
            response.enqueue(new Callback<Data>() {
                @Override
                public void onResponse(Call<Data> call, Response<Data> response) {
                    //zLog.d("url", response.body().getContent());
                    //mDisplayText.setText(response.body().getContent());
                    //l.getAdapter().notify();
                    items.add(response.body().getContent());
                    l.invalidate();
                    //Log.d("HandlerPerso", mHandler.toString());
                }

                @Override
                public void onFailure(Call<Data> call, Throwable t) {
                    Log.d("url", t.getMessage());
                }
            });

            //mHandler.postDelayed(this, 1000);
        }
    };
}
