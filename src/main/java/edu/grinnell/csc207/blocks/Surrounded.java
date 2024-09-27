package edu.grinnell.csc207.blocks;

/**
 * A text block surrounded by a single letter.
 *
 * @author Samuel A. Rebelsky
 * @author Sebastian and Sunjae
 */
public class Surrounded implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /** The stuff in the box. */
  AsciiBlock contents;

  /** The character we put around the box. */
  String surroundChar;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block with the specified contents.
   *
   * @param blockContents The contents of the block.
   * @param theChar The character that we use to surround the block.
   */
  public Surrounded(AsciiBlock blockContents, char theChar) {
    this.contents = blockContents;
    this.surroundChar = Character.toString(theChar);
  } // Surrounded(AsciiBlock)

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get one row from the block.
   *
   * @param i the number of the row
   * @return row i.
   * @exception Exception If the row is invalid.
   */
  public String row(int i) throws Exception {
    int h = this.contents.height();
    if (i == 0) {
      // The top
      return surroundChar.repeat(this.contents.width() + 2);
    } else if (i == h + 1) {
      // The bottom
      return surroundChar.repeat(this.contents.width() + 2);
    } else if ((i > 0) && (i <= h)) {
      // Stuff within the box
      return surroundChar + this.contents.row(i - 1) + surroundChar;
    } else {
      throw new Exception("Invalid row " + i);
    } // if/else
  } // row(int) // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    return (this.contents.height() + 2);
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the numbrer of columns
   */
  public int width() {
    return (this.contents.width() + 2);
  } // width()

  /**
   * Determine if another block is structurally equivalent to this block.
   *
   * @param other The block to compare to this block.
   * @return true if the two blocks are structurally equivalent and false otherwise.
   */
  @Override
  public boolean eqv(AsciiBlock other) {
    if (other instanceof Surrounded) {
      Surrounded otherSurrounded = (Surrounded) other;
      // Check if the surround character and the contents are equivalent
      return this.surroundChar.equals(otherSurrounded.surroundChar)
          && this.contents.eqv(otherSurrounded.contents);
    } //if
    return false;
  } // eqv(AsciiBlock)
} // class Surrounded
