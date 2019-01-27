package com.ichack.educate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class AssignmentsFragment extends Fragment {

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_assignments, container, false);

    String[] assignNames;
    String[] courseNames;
    long[] unixDeadlines;

    //TODO: Add data somehow

    //TODO: Insert Assignments ListView, retrieve Data of assignments order them by date and display
    //TODO: ListView is clickable to show assignment information and files in a new activity.

    ListView assignmentsListView = view.findViewById(R.id.assignmentsListView);
    AssignmentListAdapter assignListAdapter = new AssignmentListAdapter(getContext(), null, null, null, null);
    assignmentsListView.setAdapter(assignListAdapter);

    return view;
  }
}
