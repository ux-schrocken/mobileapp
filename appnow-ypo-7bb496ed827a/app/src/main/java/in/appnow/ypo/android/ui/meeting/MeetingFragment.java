package in.appnow.ypo.android.ui.meeting;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import in.appnow.ypo.android.R;
import in.appnow.ypo.android.ui.main.MainActivity;
import in.appnow.ypo.android.ui.meeting.mvp.MeetingPresenter;
import in.appnow.ypo.android.ui.meeting.mvp.view.MeetingView;

/**
 * Created by sonu on 18:21, 18/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class MeetingFragment extends Fragment {

    private Context context;
    @Inject
    MeetingView view;
    @Inject
    MeetingPresenter presenter;
    private boolean isEditMode;

    private MenuItem editMenu;
private Handler handler= new Handler();
    public MeetingFragment() {
    }

    public static MeetingFragment newInstance() {

        Bundle args = new Bundle();

        MeetingFragment fragment = new MeetingFragment();
        fragment.setArguments(args);
        return fragment;
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((MainActivity) getActivity()).getComponent().inject(this);
        presenter.onCreate();
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.edit_menu, menu);
        editMenu = menu.findItem(R.id.action_edit);

    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (editMenu != null) {
            if (isEditMode) {
                editMenu.setTitle("Done");
            } else {
                editMenu.setTitle("Edit");
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                if (view != null) {
                    isEditMode = !isEditMode;
                    view.toggleEditMode(isEditMode);
                    if (getActivity() != null)
                        getActivity().invalidateOptionsMenu();

                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroy();

    }
}
