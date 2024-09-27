package edu.grinnell.csc207.main;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.Grid;
import edu.grinnell.csc207.blocks.HAlignment;
import edu.grinnell.csc207.blocks.HFlip;
import edu.grinnell.csc207.blocks.Line;
import edu.grinnell.csc207.blocks.Rect;
import edu.grinnell.csc207.blocks.VComp;
import java.io.PrintWriter;

/**
 * Create and print an amazing 80x24 ASCII artwork.
 *
 * @author Your Name Here
 * @author Your Name Here
 */
public class Art80x24 {
  /**
   * Create the artwork.
   *
   * @param args Command-line arguments (currently ignored).
   * @exception Exception If something goes wrong with one of the underlying classes.
   */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);
    AsciiBlock art = new Rect('^', 80, 24);
    
    Grid test1 = new Grid(new Line("Hello"), 3, 4);

    // Create individual Line blocks
    AsciiBlock line1 = new Line("$^&(*$@*!())");
    AsciiBlock line2 = new Line("$&@!*($&(@*!))");
    AsciiBlock line3 = new Line("$@!*^$*@");
    AsciiBlock line4 = new Line("$&*!@$&*(@!&$(*@!&$*(&@!)))");
    // Combine the lines vertically using VComp
    AsciiBlock abcd = new VComp(HAlignment.LEFT, new AsciiBlock[] {line1, line2, line3, line4});
    // Now create a horizontally flipped version of the block
    HFlip test7 = new HFlip(abcd);

    AsciiBlock.print(pen, abcd);
  } // main(String[])
} // class Art80x24
