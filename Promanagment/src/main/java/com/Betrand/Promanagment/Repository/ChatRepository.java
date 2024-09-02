package com.Betrand.Promanagment.Repository;

import com.Betrand.Promanagment.Model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat,Long> {
}
