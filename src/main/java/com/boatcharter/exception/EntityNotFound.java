package com.boatcharter.exception;

public class EntityNotFound extends RuntimeException {


    public EntityNotFound (Long id) {
        super("Could not find entity: " + id);

    }
}
