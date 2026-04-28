package com.compa.menunavegable_4c.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.compa.menunavegable_4c.R;

public class SlideshowFragment extends Fragment {
    public SlideshowFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_slideshow, container, false);
    }
}
