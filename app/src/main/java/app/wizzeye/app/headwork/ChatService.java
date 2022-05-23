package app.wizzeye.app.headwork;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Display;
import android.widget.TextView;

import com.iristick.smartglass.support.app.HudPresentation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import app.wizzeye.app.R;
import app.wizzeye.app.WizzeyeApplication;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatService extends HudPresentation {

    int i = 0;
    private TextView mDisplayText;
    @NonNull private final Handler mHandler;
    @NonNull private final DateFormat mFormat;

    public ChatService(@NonNull Context outerContext, @NonNull Display display) {
        super(outerContext, display);
        mHandler = new Handler(getContext().getMainLooper());
        mFormat = SimpleDateFormat.getTimeInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* Set layout. */
        setContentView(R.layout.headwork_hud);
        /* Get widgets defined in the layout. */
        mDisplayText = findViewById(R.id.clock);
        /* Start updating the clock. */
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
            //mDisplayText.setText(mFormat.format(new Date()));
            i = i + 1;
            Call<Data> response = WizzeyeApplication.getService().serviceResponse(String.valueOf(i));
            response.enqueue(new Callback<Data>() {
                @Override
                public void onResponse(Call<Data> call, Response<Data> response) {
                    //Log.d("url", response.body().getContent());
                    mDisplayText.setText(response.body().getContent());
                    Log.d("HandlerPerso", mHandler.toString());
                }

                @Override
                public void onFailure(Call<Data> call, Throwable t) {
                    Log.d("url", t.getMessage());
                }
            });

            mHandler.postDelayed(this, 1000);
        }
    };
}
