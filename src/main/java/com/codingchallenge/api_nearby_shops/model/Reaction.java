package com.codingchallenge.api_nearby_shops.model;

public class Reaction {
    private ReactionType reactionType;
    private String UserId;

    public Reaction() {
    }

    public Reaction(ReactionType reactionType, String userId) {
        this.reactionType = reactionType;
        UserId = userId;
    }

    public ReactionType getReactionType() {
        return reactionType;
    }

    public void setReactionType(ReactionType reactionType) {
        this.reactionType = reactionType;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
