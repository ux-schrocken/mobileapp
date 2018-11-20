package in.appnow.ypo.android.ui.dashboard_contact;

import android.content.Context;
import android.os.Bundle;
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
import in.appnow.ypo.android.ui.add_new_contact.AddNewContactActivity;
import in.appnow.ypo.android.ui.dashboard_contact.mvp.DashboardContactPresenter;
import in.appnow.ypo.android.ui.dashboard_contact.mvp.view.DashboardContactView;
import in.appnow.ypo.android.ui.main.MainActivity;
import in.appnow.ypo.android.ui.meeting_request.MeetingRequestActivity;
import in.appnow.ypo.android.user_auth.LoginActivity;
import in.appnow.ypo.android.utils.FragmentUtils;

/**
 * Created by sonu on 18:21, 18/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class DashboardContactFragment extends Fragment {

    private static final String ARG_CONTACT = "arg_contact";
    private Context context;

    @Inject
    DashboardContactView view;
    @Inject
    DashboardContactPresenter presenter;

    private int isContact;

    public DashboardContactFragment() {
    }

    public static DashboardContactFragment newInstance(int isContact) {

        Bundle args = new Bundle();
        args.putInt(ARG_CONTACT, isContact);
        DashboardContactFragment fragment = new DashboardContactFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isContact = getArguments().getInt(ARG_CONTACT, 0);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((MainActivity) getActivity()).getComponent().inject(this);
        presenter.setUpFragment(isContact);
        presenter.onCreate();
        if (isContact == FragmentUtils.DASHBOARD) {
            setHasOptionsMenu(true);
        } else {
            setHasOptionsMenu(false);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.dashboard_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new_contact:
                AddNewContactActivity.openAddNewContactActivity(getContext());
                break;
            case R.id.action_schedule_meeting:
                MeetingRequestActivity.openMeetingRequestActivity(getContext(),null);
                break;
            case R.id.action_logout:
                LoginActivity.openAddNewLoginActivity(getContext());
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
