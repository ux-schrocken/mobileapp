package in.appnow.ypo.android.ui.dashboard_contact.mvp.view.contact;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import in.appnow.ypo.android.R;
import in.appnow.ypo.android.rest.response.ContactResponse;
import in.appnow.ypo.android.rest.response.Contacts;
import in.appnow.ypo.android.ui.dashboard_contact.mvp.view.dashboard.DashboardViewHolder;

/**
 * Created by sonu on 14:54, 23/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Contacts> contacts = new ArrayList<>(0);
    private ContactViewHolder.OnContactMoreOptionListener onContactMoreOptionListener;

    private int isRequestMeetingCall;

    public ContactAdapter(int isRequestMeetingCall) {
        this.isRequestMeetingCall = isRequestMeetingCall;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contact_custom_row_layout, viewGroup, false);
        return new ContactViewHolder(viewGroup.getContext(), view, onContactMoreOptionListener,isRequestMeetingCall);
    }

    public void setOnContactMoreOptionListener(ContactViewHolder.OnContactMoreOptionListener onContactMoreOptionListener) {
        this.onContactMoreOptionListener = onContactMoreOptionListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ContactViewHolder) viewHolder).bindData(contacts.get(i));
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void swapData(List<Contacts> contacts) {
        if (contacts != null && contacts.size() > 0) {
            this.contacts.clear();
            this.contacts.addAll(contacts);
            notifyDataSetChanged();
        }
    }
}
