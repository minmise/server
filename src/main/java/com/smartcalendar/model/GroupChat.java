package com.smartcalendar.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "group_chats")
@NoArgsConstructor
@AllArgsConstructor
public class GroupChat {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    @JsonBackReference(value = "admin_chats")
    private User admin;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "messages_in_group_chat")
    private List<GroupMessage> messages;

    @ManyToMany(mappedBy = "groupChats", fetch = FetchType.LAZY)
    //@JsonBackReference(value = "common_chats")
    @JsonIgnore
    private List<User> users;
}
