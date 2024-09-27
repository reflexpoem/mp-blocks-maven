package edu.grinnell.csc207.experiments;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.Boxed;
import edu.grinnell.csc207.blocks.Empty;
import edu.grinnell.csc207.blocks.HAlignment;
import edu.grinnell.csc207.blocks.HComp;
import edu.grinnell.csc207.blocks.HFlip;
import edu.grinnell.csc207.blocks.Line;
import edu.grinnell.csc207.blocks.Lines;
import edu.grinnell.csc207.blocks.Rect;
import edu.grinnell.csc207.blocks.VAlignment;
import edu.grinnell.csc207.blocks.VComp;
import edu.grinnell.csc207.blocks.DShift;
import java.io.PrintWriter;

/**
 * Experiments with ASCII blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 * @author Your Name Here
 */
public class Blocks {
  /**
   * Print a separator.
   *
   * @param pen What we use to print the separator.
   */
  static void separator(PrintWriter pen) {
    pen.printf("\n%s\n\n", "=".repeat(60));
  } // separator(PrintWriter)

  /**
   * Print a single AsciiBlock with a separator and a caption.
   *
   * @param pen The PrintWriter to use for printing.
   * @param caption The caption to print.
   * @param block The block to print.
   */
  static void figure(PrintWriter pen, String caption, AsciiBlock block) {
    separator(pen);
    pen.println(caption);
    pen.println("-".repeat(caption.length()));
    pen.println();
    AsciiBlock.print(pen, block);
  } // figure

  /**
   * Run the experiments.
   *
   * @param args The command-line parameters (ignored).
   */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);

    Line line = new Line("Hello");
    Rect exes = new Rect('X', 3, 3);
    AsciiBlock boxedLine = new Boxed(line);
    AsciiBlock boxedboxedLine = new Boxed(boxedLine);
    AsciiBlock boxedExes = new Boxed(exes);

    pen.println("Original Values");
    figure(pen, "line", line);
    figure(pen, "exes", exes);
    figure(pen, "boxedLine", boxedLine);
    figure(pen, "boxedboxedLine", boxedboxedLine);
    figure(pen, "boxedExes", boxedExes);

    separator(pen);
    pen.println("After changing the line.");
    line.update("Goodbye");
    figure(pen, "line", line);
    figure(pen, "boxedLine", boxedLine);
    figure(pen, "boxedboxedLine", boxedboxedLine);

    separator(pen);
    pen.println("After widening exes");
    exes.wider();
    figure(pen, "exes", exes);
    figure(pen, "boxedExes", boxedExes);

    separator(pen);
    pen.println("After shortening exes");
    exes.shorter();
    figure(pen, "exes", exes);
    figure(pen, "boxedExes", boxedExes);

    separator(pen);
    pen.println("After making exes narrower twice and taller twice");
    exes.narrower();
    exes.narrower();
    exes.taller();
    exes.taller();
    figure(pen, "exes", exes);
    figure(pen, "boxedExes", boxedExes);

    separator(pen);
    pen.println("Multi-line boxes");
    figure(
        pen, "Using an array", new Lines(new String[] {"this", "and", "that", "or", "whatever"}));
    figure(
        pen,
        "Using a multi-line string",
        new Lines(
            """
                  multi-line strings
                  were
                  introduced
                  in
                  Java 13
                  """));
    figure(pen, "Using a string with newlines", new Lines("alpha\nbeta\ngamma\ndelta\nepsilon"));

    separator(pen);
    pen.println("Fun with horizontal composition");
    AsciiBlock a = new Lines(new String[] {"Hello", "World"});
    AsciiBlock b = new Rect('B', 3, 3);
    AsciiBlock c = new Rect('C', 2, 6);
    figure(pen, "a", a);
    figure(pen, "b", b);
    figure(pen, "c", c);
    figure(pen, "Top composition", new HComp(VAlignment.TOP, new AsciiBlock[] {a, b, c}));
    figure(pen, "Center composition", new HComp(VAlignment.CENTER, new AsciiBlock[] {a, b, c}));
    figure(pen, "Bottom composition", new HComp(VAlignment.BOTTOM, new AsciiBlock[] {a, b, c}));

    separator(pen);
    pen.println("Fun with vertical composition");
    AsciiBlock v1 = new Line("One");
    AsciiBlock v7 = new Line("Seven");
    AsciiBlock v11 = new Line("Eleven");
    AsciiBlock v19 = new Line("Nineteen");
    figure(pen, "v1", v1);
    figure(pen, "v7", v7);
    figure(pen, "v11", v11);
    figure(pen, "v19", v19);
    figure(
        pen, "Left composition", new VComp(HAlignment.LEFT, new AsciiBlock[] {v1, v7, v11, v19}));
    figure(
        pen, "Left composition", new VComp(HAlignment.CENTER, new AsciiBlock[] {v1, v7, v11, v19}));
    figure(
        pen, "Left composition", new VComp(HAlignment.RIGHT, new AsciiBlock[] {v1, v7, v11, v19}));

    DShift test1 = new DShift(new Boxed(new Empty()), 3);
    figure(pen, "DShift", test1);

    AsciiBlock test2 = new Rect('a', 3, 4);
    figure(pen, "rect1", test2);

    VComp broken = new VComp(HAlignment.CENTER, new AsciiBlock[] {a, b, c});
    figure(pen, "broken one", broken);

    VComp test4 = new VComp(HAlignment.LEFT, new AsciiBlock[] {v1, v7, v11, v19});
    figure(pen, "VComp1", test4);

    VComp test5 = new VComp(HAlignment.CENTER, new AsciiBlock[] {v1, v7, v11, v19});
    figure(pen, "VComp1", test5);

    VComp test6 = new VComp(HAlignment.RIGHT, new AsciiBlock[] {v1, v7, v11, v19});
    figure(pen, "VComp1", test6);

    // Create individual Line blocks
    AsciiBlock line1 = new Line("alfa");
    AsciiBlock line2 = new Line("bravo");
    AsciiBlock line3 = new Line("charlie");
    AsciiBlock line4 = new Line("delta");
    // Combine the lines vertically using VComp
    AsciiBlock abcd = new VComp(HAlignment.LEFT, new AsciiBlock[] {line1, line2, line3, line4});
    // Now create a horizontally flipped version of the block
    HFlip test7 = new HFlip(abcd);
    // "pen" object is assumed for output, here we'll just pass null or leave it out
    figure(pen, "HFlip test 1 is-", test7);

    pen.close();
  } // main(String[])
} // class Blocks
