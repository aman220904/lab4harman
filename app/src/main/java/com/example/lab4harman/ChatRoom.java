package com.example.lab4harman;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatRoom extends AppCompatActivity {
    private ChatRoomViewModel chatModel;
    private ArrayList<ChatMessage> messages;
    private MessageAdapter adapter;
    private ActivityChatRoomBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize ViewBinding
        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Set up RecyclerView
        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);
        // Get chat messages from ViewModel
        messages = chatModel.messages.getValue();
        if (messages == null) {
            messages = new ArrayList<>();  // Initialize the messages list if null
            chatModel.messages.setValue(messages);  // Set it in ViewModel
        }
        // Initialize adapter with the messages list
        adapter = new MessageAdapter(messages);
        recyclerView.setAdapter(adapter);  // Attach adapter to RecyclerView

        // Set OnClickListener for the send button
        binding.sendButton.setOnClickListener(view -> {
            String messageText = binding.editMessage.getText().toString();  // Get input text
            if (!messageText.isEmpty()) {  // Check if not empty
                // Create a new ChatMessage for the sent message
                ChatMessage message = new ChatMessage(messageText, true);
                messages.add(message);  // Add message to list
                chatModel.messages.setValue(messages);  // Update messages in ViewModel
                adapter.notifyItemInserted(messages.size() - 1);  // Notify adapter about new item
                binding.editMessage.setText("");  // Clear the input field
            }
        });
        binding.receiveButton.setOnClickListener(view -> {
            String messageText = binding.editMessage.getText().toString();

            if (!messageText.isEmpty()) {
                ChatMessage message = new ChatMessage(messageText, false);  // Received message
                messages.add(message);
                chatModel.messages.setValue(messages);
                adapter.notifyItemInserted(messages.size() - 1);
                binding.editMessage.setText("");
            }
        });}
}