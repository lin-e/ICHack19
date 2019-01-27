package com.ichack.educate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AssignmentsFragment extends Fragment {

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_assignments, container, false);

    //TODO: Insert Assignments ListView, retrieve Data of assignments order them by date and display
    //TODO: ListView is clickable to show assignment information and files in a new activity.

    return view;
  }
}
