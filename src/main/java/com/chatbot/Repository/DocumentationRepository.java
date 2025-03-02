package com.chatbot.Repository;

import com.chatbot.Entity.Documentation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentationRepository extends JpaRepository<Documentation, Long> {
   Optional<Documentation> findByPlatform(String platform);
}