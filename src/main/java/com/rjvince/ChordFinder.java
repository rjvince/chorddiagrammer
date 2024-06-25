package com.rjvince;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ChordFinder {
    private Note root;

    private static Map<String, List<Integer>> chordForms;

    static {
        chordForms = new HashMap<>();
        chordForms.put("major", List.of(0, 4, 7));
        chordForms.put("minor", List.of(0, 3, 7));
        chordForms.put("7", List.of(0, 4, 7, 10));
        chordForms.put("9", List.of(0, 4, 7, 10, 14));
    }

    public ChordFinder(Note rootNote) {
        this.root = rootNote;
    }

    public List<Note> majorChord() {
        return buildChord("major");
    }

    public List<Note> minorChord() {
        return buildChord("minor");
    }

    private List<Note> buildChord(String name) {
        List<Integer> intervals = chordForms.get(name);
        List<Note> noteList = new LinkedList<>();
        for (int interval : intervals) {
            noteList.add(root.shiftUp(interval));
        }
        return noteList;
    }
}
