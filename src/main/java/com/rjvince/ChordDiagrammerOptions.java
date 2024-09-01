package com.rjvince;

public record ChordDiagrammerOptions(
        boolean verbose,
        Note find,
        boolean suppressNoteNames,
        boolean clean,
        int transposeSteps,
        String inFile) {}
