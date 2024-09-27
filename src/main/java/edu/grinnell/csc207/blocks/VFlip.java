package edu.grinnell.csc207.blocks;

/**
 * A vertically flipped ASCII block.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 */
public class VFlip implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The original block.
   */
  AsciiBlock block;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block with the specified contents.
   *
   * @param original
   *                 The original block.
   */
  public VFlip(AsciiBlock original) {
    this.block = original;
  } // VFlip(AsciiBlock)

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get one row from the block.
   *
   * @param i the number of the row
   *
   * @return row i.
   *
   * @exception Exception
   *                      If the row is invalid.
   */
  @Override
  public String row(int i) throws Exception {
    int flippedRowIndex = block.height() - 1 - i;
    if (flippedRowIndex < 0 || flippedRowIndex >= block.height()) {
      throw new Exception("Row index out of bounds");
    }
    return block.row(flippedRowIndex);
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  @Override
  public int height() {
    return block.height();
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  @Override
  public int width() {
    return block.width();
  } // width()

  /**
   * Determine if another block is structurally equivalent to this block.
   *
   * @param other
   *              The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and
   *         false otherwise.
   */

  @Override
  public boolean eqv(AsciiBlock other) {
    if (other instanceof HFlip) {
      HFlip otherHFlip = (HFlip) other;
      return this.block.eqv(otherHFlip.block);
    }
    return false;
  }

} // class VFlip
