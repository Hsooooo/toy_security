package com.toy.hsmall.common.model;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

public class Id<R, V> {

    private final Class<R> reference;
    private final V value;

    private Id(Class<R> reference, V value) {
        this.reference = reference;
        this.value = value;
    }

    public static <R, V> Id<R, V> of(Class<R> reference, V value) {
        checkNotNull(reference, "reference must be provided.");
        checkNotNull(value, "value must be provided");

        return new Id<>(reference, value);
    }

    public V value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Id<?, ?> id = (Id<?, ?>) o;
        return Objects.equals(reference, id.reference) && Objects.equals(value, id.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference, value);
    }
}
