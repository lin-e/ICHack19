package com.ichack.educate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SettingsFragment extends Fragment {

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

      View view = inflater.inflate(R.layout.fragment_settings, container, false);

      //TODO: Add Log out functionality, other settings???
      //TODO: Settings perhaps include archive delay between assignment deadline, and chat being moved to course archive

      return view;
  }
}
