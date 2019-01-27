package com.ichack.educate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GroupsFragment extends Fragment {

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    //TODO: Display a ListView of group channels ordered by assignment date? or try most recent message.
    //TODO: Click on ListView to open new Messenger activity, load messages for that activity...
    return inflater.inflate(R.layout.fragment_groups, container, false);
  }
}
