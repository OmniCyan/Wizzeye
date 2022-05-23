package app.wizzeye.app.fragments;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.wizzeye.app.R;
import app.wizzeye.app.SettingsActivity;
import app.wizzeye.app.service.CallState;

public class ChatFragment extends InRoomFragment{



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.headwork_hud, container, false);
        return view;
    }

}
