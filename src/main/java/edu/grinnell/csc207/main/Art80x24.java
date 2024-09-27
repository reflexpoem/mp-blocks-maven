package edu.grinnell.csc207.main;


import java.io.PrintWriter;
import java.util.Random;


import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.Boxed;
import edu.grinnell.csc207.blocks.HAlignment;
import edu.grinnell.csc207.blocks.HComp;
import edu.grinnell.csc207.blocks.HFlip;
import edu.grinnell.csc207.blocks.Line;
import edu.grinnell.csc207.blocks.Lines;
import edu.grinnell.csc207.blocks.Rect;
import edu.grinnell.csc207.blocks.VAlignment;
import edu.grinnell.csc207.blocks.VComp;
import edu.grinnell.csc207.blocks.VFlip;


/**
 * Creates and prints an amazing 80x24 ASCII artwork filled with hearts and modified randomly.
 */
public class Art80x24 {


  /**
   * Main method to generate the ASCII art.
   *
   * @param args the command-line arguments (currently ignored)
   * @throws Exception if something goes wrong with one of the underlying classes
   */
  public static void main(String[] args) throws Exception {
    // Create a PrintWriter for console output
    PrintWriter pen = new PrintWriter(System.out, true);
    Random random = new Random();


    // Create a basic heart pattern (20 hearts per line to fill 80 characters wide)
    String heartLine = "<3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3";

    // Create 24 lines of heart pattern to fill the 80x24 grid
    AsciiBlock heartBlock = new Lines(new String[] {heartLine, heartLine, heartLine, heartLine,
        heartLine, heartLine, heartLine, heartLine, heartLine, heartLine, heartLine, heartLine,
        heartLine, heartLine, heartLine, heartLine, heartLine, heartLine, heartLine, heartLine,
        heartLine, heartLine, heartLine, heartLine}); // ascii block


    // Randomly apply transformations
    AsciiBlock transformedBlock = heartBlock;
    for (int i = 0; i < 5; i++) {
      int transformation = random.nextInt(5);
      switch (transformation) {
        case 0:
          transformedBlock = new HFlip(transformedBlock);
          break;
        case 1:
          transformedBlock = new VFlip(transformedBlock);
          break;
        case 2:
          transformedBlock = new Boxed(transformedBlock);
          break;
        case 3:
          transformedBlock = new HComp(VAlignment.CENTER,
              new AsciiBlock[] {transformedBlock, new Rect('*', 5, 5)});
          break;
        case 4:
          transformedBlock = new VComp(HAlignment.CENTER,
              new AsciiBlock[] {new Line("Random hearts"), transformedBlock});
          break;
        default:
          break;
      } // switch case
    } // for


    // Print the final transformed heart block
    AsciiBlock.print(pen, transformedBlock);


    // Close the PrintWriter
    pen.close();
  } // main(String[])
} // class Art80x24


