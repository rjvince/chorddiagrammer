package com.rjvince;

public record ChordDiagrammerOptions(
        boolean verbose,
        boolean suppressNoteNames,
        boolean clean,
        int transposeSteps,
        String inFile) {}
