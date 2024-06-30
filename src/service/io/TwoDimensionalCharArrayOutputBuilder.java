/*
 * Name:    Supakrit Pamakama
 * ID:      6510450968
 */

package service.io;

import java.util.Arrays;
import java.util.ArrayList;

import core.constant.CharacterConstant;

public final class TwoDimensionalCharArrayOutputBuilder extends OutputBuilder<char[][], char[][]> {
    @Override
    char[][] build() {
        final ArrayList<char[][]> models = new ArrayList<>();

        int width = Integer.MIN_VALUE;
        int height = Integer.MIN_VALUE;

        for (OutputModel<char[][]> outputModel : this.outputModels) {
            models.add(outputModel.buildModel());
        }

        for (int i = 0; i < this.outputModels.size(); i++) {
            width = Math.max(this.outputModels.get(i).getPosition().getX() + models.get(i)[0].length, width);
            height = Math.max(this.outputModels.get(i).getPosition().getY() + models.get(i).length, height);
        }

        final char[][] buffer = new char[height][width];

        Arrays.stream(buffer).forEach(row -> Arrays.fill(row, CharacterConstant.TRANSPARENT));

        for (int i = 0; i < this.outputModels.size(); i++) {
            for (int y = 0; y < models.get(i).length; y++) {
                for (int x = 0; x < models.get(i)[y].length; x++) {
                    char ch = models.get(i)[y][x];

                    if (!(this.outputModels.get(i).isTransparent() && ch == CharacterConstant.TRANSPARENT)) {
                        buffer[this.outputModels.get(i).getPosition().getY() + y][this.outputModels.get(i).getPosition().getX() + x] = models.get(i)[y][x];
                    }
                }
            }
        }

        return buffer;
    }
}
