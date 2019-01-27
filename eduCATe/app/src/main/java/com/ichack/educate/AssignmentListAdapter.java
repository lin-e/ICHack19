package com.ichack.educate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

class AssignmentListAdapter extends BaseAdapter {

  private LayoutInflater mInflater;
  private String[] assignNames;
  private String[] courseNames;
  private String[] deadlineDates;
  private String[] deadlineTimes;

  public AssignmentListAdapter(
      Context context,
      String[] assignNames,
      String[] courseNames,
      String[] deadlineDates,
      String[] deadlineTimes) {

    this.assignNames = assignNames;
    this.courseNames = courseNames;
    this.deadlineDates = deadlineDates;
    this.deadlineTimes = deadlineTimes;
    mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  @Override
  public int getCount() {
    // return assNames.length (where assNames is an array of all upcoming assignments)
    return assignNames.length;
  }

  @Override
  public Object getItem(int position) {
    return assignNames[position];
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View view = mInflater.inflate(R.layout.assignments_listview_detail, null);
    TextView assignNameTV = view.findViewById(R.id.assignNameTV);
    TextView courseNameTV = view.findViewById(R.id.courseNameTV);
    TextView assignDateTV = view.findViewById(R.id.assignDateTV);
    TextView assignTimeTV = view.findViewById(R.id.assignTimeTV);

    assignNameTV.setText(assignNames[position]);
    courseNameTV.setText(courseNames[position]);
    assignDateTV.setText(deadlineDates[position]);
    assignTimeTV.setText(deadlineTimes[position]);
    return view;
  }
}
