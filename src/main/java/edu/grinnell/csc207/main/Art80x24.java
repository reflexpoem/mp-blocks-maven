package edu.grinnell.csc207.main;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.Boxed;
import edu.grinnell.csc207.blocks.Grid;
import edu.grinnell.csc207.blocks.HAlignment;
import edu.grinnell.csc207.blocks.HComp;
import edu.grinnell.csc207.blocks.Rect;
import edu.grinnell.csc207.blocks.VAlignment;
import edu.grinnell.csc207.blocks.VComp;
import java.io.PrintWriter;

/**
 * Create and print an amazing 80x24 ASCII artwork.
 * 
 * This program constructs an ASCII art representation of a flag using various
 * AsciiBlock components and alignment classes.
 * 
 * @author Sunjae Kim
 * @author Sebastian Manza
 */
public class Art80x24 {

  /**
   * Main method to create and print the artwork.
   *
   * @param args Command-line arguments (currently ignored).
   * @throws Exception If something goes wrong with one of the underlying classes.
   */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);

    // Blue dots and white lines for flag representation
    AsciiBlock blueDot = new Boxed(new Rect('~', 1, 1));
    AsciiBlock blueAreaLeft = new Grid(blueDot, 10, 3);
    AsciiBlock blueAreaRight = new Grid(blueDot, 10, 3);
    AsciiBlock whiteLine = new Grid(new Rect('#', 1, 1), 30, 6);
    AsciiBlock whiteColumn = new Grid(new Rect('#', 1, 1), 10, 24);

    // Construct the one-third section of the flag
    AsciiBlock oneThird = new VComp(HAlignment.RIGHT, new AsciiBlock[] { blueAreaLeft, whiteLine, blueAreaRight });

    // Combine the sections into the full flag
    AsciiBlock leftOfFlag = new HComp(VAlignment.TOP, new AsciiBlock[] { oneThird, whiteColumn, oneThird });

    // Print the final artwork
    AsciiBlock.print(pen, leftOfFlag);

    // Close the PrintWriter
    pen.close();
  } // main(String[])
} // class Art80x24
