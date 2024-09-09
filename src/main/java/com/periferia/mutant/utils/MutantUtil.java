package com.periferia.mutant.utils;

import org.springframework.stereotype.Component;

@Component
public class MutantUtil {

    private static final int MUTANT_SEQUENCE_LENGTH = 4;
    private static final int[][] DETECT_DIRECTION = {
            {0, 1},   // Horizontal
            {1, 0},   // Vertical
            {1, 1},   // Diagonal principal
            {1, -1}   // Diagonal secundaria
    };

    public boolean isMutantValid(String[] dna) {
        int sizeAdn = dna.length;
        int sequenceCount = 0;

        for (int i = 0; i < sizeAdn; i++) {
            for (int j = 0; j < sizeAdn; j++) {
                for (int[] direction : DETECT_DIRECTION) {
                    if (isSequence(dna, i, j, direction)) {
                        sequenceCount++;
                        if (sequenceCount >= 2) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isSequence(String[] dna, int row, int col, int[] direction) {
        char baseLetter = dna[row].charAt(col);
        int newRow, newCol;

        for (int i = 1; i < MUTANT_SEQUENCE_LENGTH; i++) {
            newRow = row + i * direction[0];
            newCol = col + i * direction[1];

            if (newRow >= dna.length || newRow < 0 || newCol >= dna.length || newCol < 0) {
                return false;
            }

            if (dna[newRow].charAt(newCol) != baseLetter) {
                return false;
            }
        }

        return true;
    }
}
