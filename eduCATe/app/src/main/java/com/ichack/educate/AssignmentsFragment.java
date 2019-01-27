package com.ichack.educate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class AssignmentsFragment extends Fragment {

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_assignments, container, false);

    //TODO: Add data somehow
    String[] assignNames = {"PMT1", "HW Coursework 2", "Java Picture Processing Lab"};
    String[] courseNames = {"CO142: Discrete Structures", "CO112: Hardware", "CO120.2: Programming II"};
    long[] unixDeadlines = {1548590583, 1556029804, 1556323200};
    String[] deadlineDates = new String[3];
    String[] deadlineTimes = new String[3];

    int nOfAssignments = assignNames.length;
    final String[][] assignmentData = new String[nOfAssignments][4];
    for (int i = 0; i < nOfAssignments; i++) {
      assignmentData[i][0] = assignNames[i];
      assignmentData[i][1] = courseNames[i];
      deadlineDates[i] = Utils.unixToDate(unixDeadlines[i]);
      deadlineTimes[i] = Utils.unixToTime(unixDeadlines[i]);
      assignmentData[i][2] = Utils.unixToDate(unixDeadlines[i]);
      assignmentData[i][3] = Utils.unixToTime(unixDeadlines[i]);
    }


    //TODO: Insert Assignments ListView, retrieve Data of assignments order them by date and display
    //TODO: ListView is clickable to show assignment information and files in a new activity.

    ListView assignmentsListView = view.findViewById(R.id.assignmentsListView);
    AssignmentListAdapter assignListAdapter = new AssignmentListAdapter(getContext(), assignNames, courseNames, deadlineDates, deadlineTimes);
    assignmentsListView.setAdapter(assignListAdapter);

    assignmentsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent assignmentDetailIntent = new Intent(view.getContext(), AssignmentDetailActivity.class);
        assignmentDetailIntent.putExtra("Name", assignmentData[position][0]);
        assignmentDetailIntent.putExtra("Course", assignmentData[position][1]);
        assignmentDetailIntent.putExtra("DateDue", assignmentData[position][2]);
        assignmentDetailIntent.putExtra("TimeDue", assignmentData[position][3]);
      }
    });





    return view;
  }
}
