package de.teamgamma.cansat.app.fragments;

import java.util.Locale;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import de.teamgamma.cansat.app.R;

public class ValueFragment extends Fragment {
    public static final String ARG_SLIDEMENU_VALUES = "slidemenu_values";

    public ValueFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_empty, container, false);
        int i = getArguments().getInt(ARG_SLIDEMENU_VALUES);
        String planet = getResources().getStringArray(R.array.slidemenu_array)[i];

        int imageId = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()),
                        "drawable", getActivity().getPackageName());
        ((ImageView) rootView.findViewById(R.id.image)).setImageResource(imageId);
        getActivity().setTitle(planet);
        return rootView;
    }
}