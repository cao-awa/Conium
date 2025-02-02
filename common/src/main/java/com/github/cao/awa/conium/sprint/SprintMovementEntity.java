package com.github.cao.awa.conium.sprint;

public interface SprintMovementEntity {
    default boolean canStartSprint() {
        return conium$canStartSprint();
    }

    default void setCanStartSprint(boolean canStartSprint) {
        conium$setCanStartSprint(canStartSprint);
    }

    boolean conium$canStartSprint();
    void conium$setCanStartSprint(boolean canStartSprint);
}
