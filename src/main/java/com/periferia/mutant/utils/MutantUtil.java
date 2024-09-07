package com.periferia.mutant.utils;

import org.springframework.stereotype.Component;

@Component
public class MutantUtil {

    private static final Long MUTANT_SECUENCE_LENG = 4L;
    private static int[][] DETECT_DIRECTIO = {
            {0,1},
            {1,0},
            {1,1},
            {1,-1}
    };

    public Boolean isMutantValid (String[] dna) {
        int sizeAdn = dna.length;
        int sequenceControl = 0;

        for (int i = 0; i < sizeAdn; i++) {
            for (int j = 0; j < sizeAdn; j++) {
                if (dna[i].charAt(j) != '\0') {
                    for (int[] direction : DETECT_DIRECTIO) {
                        if (isSequence(dna, i, j, direction)) {
                            sequenceControl++;
                            if (sequenceControl > 1) {
                                return  true;
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    private Boolean isSequence (String[] dna, int row, int col, int[] direction) {

        char baseLetter = dna[row].charAt(col);
        int newRow, newCol;

        for (int i = 1; i < MUTANT_SECUENCE_LENG; i++) {

            newRow = row + i * direction[0];
            newCol = col + i * direction[1];

            if (newRow >= dna.length || newRow < 0 || newCol >= dna.length || newCol < 0) {
                return false;  // Si estamos fuera de los límites de la matriz
            }

            if (dna[newRow].charAt(newCol) != baseLetter) {
                return false;  // Si las letras no coinciden, no es una secuencia
            }
        }

        return true;
    }
}
