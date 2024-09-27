package edu.grinnell.csc207.blocks;

import java.util.Arrays;

/**
 * The diagonally shifted composition of a block.
 *
 * @author Sebastian
 * @author Sunjae
 */
public class DShift implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /** The block. */
  AsciiBlock block;

  /** The number of times the element is repeated vertically. */
  int reps;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+


  /**
   * Build a diagonally shifted composition of multiple blocks.
   *
   * @param blocksToCompose The blocks we will be composing.
   * @param repetitions The number of rows
   */
  public DShift(AsciiBlock blockToCompose, int repetitions) {
    this.block = blockToCompose;
    this.reps = repetitions;
  } // DShift(HAlignment, AsciiBLOCK[])

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
    int blockHeight = this.block.height();
    int totalHeight = this.height();

    if (totalHeight <= i || i < 0) {
      throw new Exception("Invalid row number");
    }

    int blockRow = i % blockHeight;
    String blockRowString = this.block.row(blockRow);

    StringBuilder result = new StringBuilder();
    if (i != 0){
    result.append(" ".repeat(i));
    result.append(blockRowString);
    }
    else {
      result.append(blockRowString);
    }

    return result.toString();
  }

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    return reps * this.block.height();
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    return this.block.width() + (this.height() - 1);
  } // width()

  /**
   * Determine if another block is structurally equivalent to this block.
   *
   * @param other The block to compare to this block.
   * @return true if the two blocks are structurally equivalent and false otherwise.
   */
  public boolean eqv(AsciiBlock other) {
    if (!(other instanceof DShift)) {
      return false;
    }
    DShift oth = (DShift) other;
      if (!(this.block.eqv(oth.block)) && (this.reps == oth.reps)){
        return false;
      }
    return true;
  } // eqv(AsciiBlock)
} // class Dshift
