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
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AssignmentsFragment extends Fragment {

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_assignments, container, false);

    /*
    RequestQueue queue = Volley.newRequestQueue(getContext());
    queue.start();
    String url = "http://yeetr.me/assignments.php?token=YczmZ72iy74wPtKVQbUrXNMswrtInyTl";
    final String[] jsonDataRaw = new String[1];

    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        jsonDataRaw[0] = response;
      }
    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        jsonDataRaw[0] = "";
      }
    });

    queue.add(stringRequest);

    */

    String jsonDataRaw =
        "{\"status\":1,\"content\":[{\"id\":1,\"course\":\"CO120.1 - DPLL\",\"start\":1547458577,\"end\":1550136977},{\"id\":2,\"course\":\"CO140 - First Order Natural Deduction\",\"start\":1546853777,\"end\":1547544977}]}";


    JSONObject jsonObj;
    JSONArray assignments;
    try {
      jsonObj = new JSONObject(jsonDataRaw);
      assignments = jsonObj.getJSONArray("content");

      int nOfAssignments = assignments.length();
      String[] assignNames = new String[nOfAssignments];
      String[] courseNames = new String[nOfAssignments];
      long[] unixDeadlines = new long[nOfAssignments];
      String[] deadlineDates = new String[3];
      String[] deadlineTimes = new String[3];
      final String[][] assignmentData = new String[nOfAssignments][4];

      for (int i = 0; i < assignments.length(); i++) {
        String[] names = assignments.getJSONObject(i).getString("course").split(" - ");
        courseNames[i] = names[0];
        assignNames[i] = names[1];
        unixDeadlines[i] = assignments.getJSONObject(i).getLong("start");
        deadlineDates[i] = Utils.unixToDate(unixDeadlines[i]);
        deadlineTimes[i] = Utils.unixToTime(unixDeadlines[i]);

        assignmentData[i][0] = assignNames[i];
        assignmentData[i][1] = courseNames[i];
        assignmentData[i][2] = Utils.unixToDate(unixDeadlines[i]);
        assignmentData[i][3] = Utils.unixToTime(unixDeadlines[i]);
      }

      // TODO: Insert Assignments ListView, retrieve Data of assignments order them by date and
      // display
      // TODO: ListView is clickable to show assignment information and files in a new activity.

      ListView assignmentsListView = view.findViewById(R.id.assignmentsListView);
      AssignmentListAdapter assignListAdapter =
          new AssignmentListAdapter(
              getContext(), assignNames, courseNames, deadlineDates, deadlineTimes);
      assignmentsListView.setAdapter(assignListAdapter);

      assignmentsListView.setOnItemClickListener(
          new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Intent assignmentDetailIntent =
                  new Intent(view.getContext(), AssignmentDetailActivity.class);
              assignmentDetailIntent.putExtra("Name", assignmentData[position][0]);
              assignmentDetailIntent.putExtra("Course", assignmentData[position][1]);
              assignmentDetailIntent.putExtra("DateDue", assignmentData[position][2]);
              assignmentDetailIntent.putExtra("TimeDue", assignmentData[position][3]);
              startActivity(assignmentDetailIntent);
            }
          });

    } catch (JSONException e) {
      e.printStackTrace();
    }

    return view;
  }
}
