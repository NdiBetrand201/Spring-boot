package com.Betrand.Promanagment.Implemetation;

import com.Betrand.Promanagment.Model.Chat;
import com.Betrand.Promanagment.Repository.ChatRepository;
import com.Betrand.Promanagment.Service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    ChatRepository chatRepository;

    @Override
    public Chat createChat(Chat chat) {
        return chatRepository.save(chat);
    }
}
