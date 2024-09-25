package edu.grinnell.csc207.blocks;

import java.util.Arrays;


/**
 * The vertical composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 * @author Your Name Here
 */
public class VComp implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The blocks.
   */
  AsciiBlock[] blocks;

  /**
   * How the blocks are aligned.
   */
  HAlignment align;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a vertical composition of two blocks.
   *
   * @param alignment
   *   The way in which the blocks should be aligned.
   * @param topBlock
   *   The block on the top.
   * @param bottomBlock
   *   The block on the bottom.
   */
  public VComp(HAlignment alignment, AsciiBlock topBlock,
      AsciiBlock bottomBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {topBlock, bottomBlock};
  } // VComp(HAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a vertical composition of multiple blocks.
   *
   * @param alignment
   *   The alignment of the blocks.
   * @param blocksToCompose
   *   The blocks we will be composing.
   */
  public VComp(HAlignment alignment, AsciiBlock[] blocksToCompose) {
    this.align = alignment;
    this.blocks = Arrays.copyOf(blocksToCompose, blocksToCompose.length);
  } // VComp(HAlignment, AsciiBLOCK[])

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
   *   if i is outside the range of valid rows.
   */
  public String row(int i) throws Exception {
    StringBuilder rowStr = new StringBuilder();
    if ((i < 0) || (i > this.height())){
      throw new Exception("Row is outside of bounds");
    } else if (this.align == HAlignment.LEFT) {
      for (int j = 0; j < this.blocks.length; j++){
        String blockStr = this.blocks[j].row(i % this.blocks[j].height());
        rowStr.append(blockStr);
        rowStr.append(" ".repeat(this.width() - this.blocks[j].width()));
      }
    } else if (this.align == HAlignment.CENTER) {
      for (int j = 0; j < this.blocks.length; j++){
        int diffLeft = (this.width() - this.blocks[j].width()) / 2;
        int diffRight = diffLeft;
        if ((this.width() - this.blocks[j].width()) % 2 != 0){
          diffRight++;
        }
        rowStr.append(" ".repeat(diffLeft));
        String blockStr = this.blocks[j].row(i % this.blocks[j].height());
        rowStr.append(blockStr);  
        rowStr.append(" ".repeat(diffRight));
      }
    } else if (this.align == HAlignment.RIGHT) {
      for (int j = 0; j < this.blocks.length; j++) {
        int diff = this.width() - this.blocks[j].width();
        rowStr.append(" ".repeat(diff));
        String blockStr = this.blocks[j].row(i % this.blocks[j].height());
        rowStr.append(blockStr);
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
    int finalHeight = 0;
    for (int i = 0; i < this.blocks.length; i++){
      finalHeight += this.blocks[i].height();
    }
    return finalHeight;   // STUB
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    int maxWidth = 0;
    for (int i = 0; i < this.blocks.length; i++){
      if (this.blocks[i].width() > maxWidth){
        maxWidth = this.blocks[i].width();
      }
    }
    return maxWidth;
  } // width()

  /**
   * Determine if another block is structurally equivalent to this block.
   *
   * @param other
   *   The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and
   *    false otherwise.
   */
  public boolean eqv(AsciiBlock other) {
    return false;       // STUB
  } // eqv(AsciiBlock)
} // class VComp
