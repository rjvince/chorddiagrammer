package com.rjvince;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a note in the 12-step chromatic scale
 *
 */
public enum Note {
    C(0), Df(1), D(2), Ef(3), E(4),
    F(5), Fs(6), G(7), Af(8), A(9),
    Bf(10), B(11);

    private int index;

    Note(int x) {
        this.index = x % 12;
    }

    /**
     * Produce a new note, shifted by a number of semitones from this one
     *
     * @param x can be positive or negative
     * @return
     */
    public Note shift(int x) {
        return values()[Math.floorMod(this.index + x, 12)];
    }

    /**
     * Return alternate names for sharp notes.
     * E.g. the note between C and D could be a c-sharp or a d-flat
     * depending on context.
     *
     * @return
     */
    public String useSharps() {
        return switch (this) {
            case Df -> "Cs";
            case Ef -> "Ds";
            case Af -> "Gs";
            case Bf -> "As";
            default -> this.name();
        };
    }

    /**
     * Return alternate names for flat notes.
     * E.g. the note between C and D could be a c-sharp or a d-flat
     * depending on context.
     *
     * @return
     */
    public String useFlats() {
        if (this == Fs) {
            return "Gf";
        }
        else {
            return this.name();
        }
    }

    public List<Note> majorChord() {
        ArrayList<Note> chord = new ArrayList<>();
        chord.add(this);
        chord.add(Interval.MAJOR_THIRD.findFrom(this));
        chord.add(Interval.PERFECT_FIFTH.findFrom(this));
        return chord;
    }
}
