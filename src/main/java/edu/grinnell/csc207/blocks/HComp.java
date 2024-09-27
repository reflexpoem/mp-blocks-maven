package edu.grinnell.csc207.blocks;

import java.util.Arrays;

/**
 * The horizontal composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Sebastian and Sunjae
 */
public class HComp implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /** The blocks. */
  AsciiBlock[] blocks;

  /** How the blocks are aligned. */
  VAlignment align;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a horizontal composition of two blocks.
   *
   * @param alignment The way in which the blocks should be aligned.
   * @param leftBlock The block on the left.
   * @param rightBlock The block on the right.
   */
  public HComp(VAlignment alignment, AsciiBlock leftBlock, AsciiBlock rightBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {leftBlock, rightBlock};
  } // HComp(VAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a horizontal composition of multiple blocks.
   *
   * @param alignment The alignment of the blocks.
   * @param blocksToCompose The blocks we will be composing.
   */
  public HComp(VAlignment alignment, AsciiBlock[] blocksToCompose) {
    this.align = alignment;
    this.blocks = Arrays.copyOf(blocksToCompose, blocksToCompose.length);
  } // HComp(Alignment, AsciiBLOCK[])

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get one row from the block.
   *
   * @param i the number of the row
   * @return row i.
   * @exception Exception if i is outside the range of valid rows.
   */
  public String row(int i) throws Exception {
    StringBuilder rowStr = new StringBuilder();
    if ((i < 0) || (i >= this.height())) {
      throw new Exception("Invalid row" + i);
    } // If the row is not within the block
    else if (this.align == VAlignment.TOP) {
      for (int j = 0; j < this.blocks.length; j++) {
        if (i < this.blocks[j].height()) {
          String blockStr = this.blocks[j].row(i % this.blocks[j].height());
          rowStr.append(blockStr);
        } else {
          rowStr.append(" ".repeat(this.blocks[j].width()));
        }
      }
    } else if (this.align == VAlignment.CENTER) {
      for (int j = 0; j < this.blocks.length; j++) {
        int diffTop = (this.height() - this.blocks[j].height()) / 2;
        int diffBottom = diffTop;
        if (((this.height() - this.blocks[j].height()) % 2) != 0) {
          diffBottom++;
        }
        if (i < diffTop) {
          rowStr.append(" ".repeat(this.blocks[j].width()));
        } else if (i < this.height() - diffBottom) {
          String blockStr = this.blocks[j].row(i % this.blocks[j].height());
          rowStr.append(blockStr);
        } else {
          rowStr.append(" ".repeat(this.blocks[j].width()));
        }
      }
    } else if (this.align == VAlignment.BOTTOM) {
      for (int j = 0; j < this.blocks.length; j++) {
        int diff = this.height() - this.blocks[j].height();
        if (i < diff) {
          rowStr.append(" ".repeat(this.blocks[j].width()));
        } else {
          String blockStr = this.blocks[j].row(i % this.blocks[j].height());
          rowStr.append(blockStr);
        }
      }
    }
    return rowStr.toString();
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    int maxHeight = 0;
    for (int k = 0; k < this.blocks.length; k++) {
      if (this.blocks[k].height() > maxHeight) {
        maxHeight = this.blocks[k].height();
      }
    }
    return maxHeight;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    int totalWidth = 0;
    for (int i = 0; i < this.blocks.length; i++) {
      totalWidth += this.blocks[i].width();
    }
    return totalWidth;
  } // width()

  /**
   * Determine if another block is structurally equivalent to this block.
   * 
   * @param other The block to compare to this block.
   * @return true if the two blocks are structurally equivalent and false otherwise.
   */
  public boolean eqv(AsciiBlock other) {
    if (!(other instanceof HComp)) {
      return false;
    } //if 
    HComp comp = (HComp) other;
    if ((this.blocks.length != comp.blocks.length) || (this.align != comp.align)) {
      return false;
    }
    for (int i = 0; i < this.blocks.length; i++) {
      if (!this.blocks[i].eqv(comp.blocks[i])) {
        return false;
      }
    }
    return true;
  } // eqv(AsciiBlock)
} // class HComp
