package com.example.lab4harman;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lab4harman.ChatMessage;

import java.util.ArrayList;

public class ChatRoomViewModel extends ViewModel {

    // LiveData to hold the list of chat messages
    public MutableLiveData<ArrayList<ChatMessage>> messages = new MutableLiveData<>(new ArrayList<>());
}
