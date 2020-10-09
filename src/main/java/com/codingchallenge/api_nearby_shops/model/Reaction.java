package com.codingchallenge.api_nearby_shops.model;

import java.time.LocalDateTime;

public class Reaction {
    private ReactionType reactionType;
    private String userId;
    private LocalDateTime createdAt;

    public Reaction() {
    }

    public Reaction(ReactionType reactionType, String userId, LocalDateTime createdAt) {
        this.reactionType = reactionType;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public ReactionType getReactionType() {
        return reactionType;
    }

    public void setReactionType(ReactionType reactionType) {
        this.reactionType = reactionType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
