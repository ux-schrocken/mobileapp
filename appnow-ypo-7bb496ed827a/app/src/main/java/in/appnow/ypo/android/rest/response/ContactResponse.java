package in.appnow.ypo.android.rest.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sonu on 12:52, 23/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class ContactResponse  {


    @SerializedName("contactList")
    private List<Contacts> contacts;
    @SerializedName("contactsCount")
    private int contactsCount;

    public List<Contacts> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contacts> contacts) {
        this.contacts = contacts;
    }

    public int getContactsCount() {
        return contactsCount;
    }

    public void setContactsCount(int contactsCount) {
        this.contactsCount = contactsCount;
    }
}
