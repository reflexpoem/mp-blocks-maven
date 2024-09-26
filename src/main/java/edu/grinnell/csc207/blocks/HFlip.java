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
  *   The original block.
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
  *
  * @return row i, flipped horizontally.
  *
  * @exception Exception
  *   If the row is invalid.
  */
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
 public int height() {
   return block.height();
 } // height()


 /**
  * Determine how many columns are in the block.
  *
  * @return the number of columns
  */
 public int width() {
   return block.width();
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
   // Check if both blocks have the same height and width
   if (this.height() != other.height() || this.width() != other.width()) {
     return false;
   }
  
   // Compare each row in the flipped block to the other block's corresponding row
   try {
     for (int i = 0; i < this.height(); i++) {
       if (!this.row(i).equals(other.row(i))) {
         return false;
       }
     }
   } catch (Exception e) {
     return false;
   }
  
   return true;
 } // eqv(AsciiBlock)
} // class HFlip
