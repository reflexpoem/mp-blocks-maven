package edu.grinnell.csc207.blocks;

/**
 * A horizontally flipped ASCII block.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 */
public class HFlip implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /** The original block. */
  AsciiBlock block;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block with the specified contents.
   *
   * @param original The original block.
   */
  public HFlip(AsciiBlock original) {
    this.block = original;
  } // HFlip(AsciiBlock)

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get one row from the block, flipped horizontally.
   *
   * @param i the number of the row
   * @return row i, flipped horizontally.
   * @exception Exception If the row is invalid.
   */
  @Override
  public String row(int i) throws Exception {
    if (i < 0 || i >= block.height()) {
      throw new Exception("Row index out of bounds.");
    }
    String originalRow = block.row(i);
    return new StringBuilder(originalRow).reverse().toString();
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

  @Override
  public boolean eqv(AsciiBlock other) {
    if (other instanceof HFlip) {
      HFlip otherHFlip = (HFlip) other;
      return this.block.eqv(otherHFlip.block);
    }
    return false;
  }
} // class HFlip
