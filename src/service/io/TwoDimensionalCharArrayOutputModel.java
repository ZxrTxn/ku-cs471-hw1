/*
 * Name:    Supakrit Pamakama
 * ID:      6510450968
 */

package service.io;

import java.util.Arrays;

import core.constant.CharacterConstant;

public abstract class TwoDimensionalCharArrayOutputModel extends OutputModel<char[][]> {
    @Override
    char[][] buildModel() {
        final char[][] model = this.getModel();

        if (
            !this.hasBorder() &&
            this.getPadding().getTop() == 0 &&
            this.getPadding().getRight() == 0 &&
            this.getPadding().getBottom() == 0 &&
            this.getPadding().getLeft() == 0
        ) {
            return model;
        }

        final int offset = (this.hasBorder()) ? 2 : 0;

        final int width = model[0].length + this.padding.getLeft() + this.padding.getRight() + offset;
        final int height = model.length + this.padding.getTop() + this.padding.getBottom() + offset;

        final char[][] buffer = new char[height][width];

        Arrays.stream(buffer).forEach(row -> Arrays.fill(row, CharacterConstant.TRANSPARENT));

        if (this.hasBorder()) {
            for (int x = 0; x < width; x++) {
                buffer[0][x] = CharacterConstant.BORDER_TOP;
                buffer[height - 1][x] = CharacterConstant.BORDER_BOTTOM;
            }

            for (int y = 0; y < height; y++) {
                buffer[y][0] = CharacterConstant.BORDER_LEFT;
                buffer[y][width - 1] = CharacterConstant.BORDER_RIGHT;
            }
        }

        for (int y = 0; y < model.length; y++) {
            for (int x = 0; x < model[y].length; x++) {
                buffer[y + this.padding.getTop() + (offset / 2)][x + this.padding.getLeft() + (offset / 2)] = model[y][x];
            }
        }

        return buffer;
    }
}
