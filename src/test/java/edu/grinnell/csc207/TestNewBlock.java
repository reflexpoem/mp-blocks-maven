package edu.grinnell.csc207;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.Boxed;
import edu.grinnell.csc207.blocks.DShift;
import edu.grinnell.csc207.blocks.Empty;
import edu.grinnell.csc207.blocks.DShift;
import edu.grinnell.csc207.blocks.HAlignment;
import edu.grinnell.csc207.blocks.HComp;
import edu.grinnell.csc207.blocks.HFlip;
import edu.grinnell.csc207.blocks.DShift;
import edu.grinnell.csc207.blocks.DShift;
import edu.grinnell.csc207.blocks.Line;
import edu.grinnell.csc207.blocks.Padded;
import edu.grinnell.csc207.blocks.Rect;
import edu.grinnell.csc207.blocks.Surrounded;
import edu.grinnell.csc207.blocks.Trimmed;
import edu.grinnell.csc207.blocks.VAlignment;
import edu.grinnell.csc207.blocks.VComp;
import edu.grinnell.csc207.blocks.VFlip;

/** Tests of the new block. */
public class TestNewBlock {
  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  /** Testing of basic DShift structures. */
  @Test
  void TestDShiftBasic() throws Exception {
    assertNotNull(new DShift(new Line("Hello"), 3), "R: Can DShift text lines");
    assertNotNull(new DShift(new Rect('B', 3, 4), 2), "R: Can DShift rectangles");
  } // TestDShiftBasic()

  /** Testing of basic DShift lines. */
  @Test
  void TestDShiftLine() {
    AsciiBlock DShiftLine = new DShift(new Line("Hello"), 3);
    assertEquals(7, DShiftLine.width(), "M: Correct width for a DShift line");
    assertEquals(3, DShiftLine.height(), "M: Correct height for a DShift line");
    assertEquals(
        """
                 Hello
                  Hello
                   Hello
                 """,
        TestUtils.toString(DShiftLine),
        "M: Correct contents for a DShiftded line");
  } // TestDShiftLine()

  /** Testing of DShiftding Surrounded Lines. */
  @Test
  void TestDShiftBox() throws Exception {
    AsciiBlock DShiftBox = new DShift(new Surrounded(new Line("A"), 'C'), 2);
    assertEquals(8, DShiftBox.width(), "M: Correct width for a DShiftded box");
    assertEquals(6, DShiftBox.height(), "M: Correct height for a DShiftded box");
    assertEquals(
      """
          CCC
           CAC
            CCC
             CCC
              CAC
               CCC
          """,
        TestUtils.toString(DShiftBox),
        "M: Correct contents for a DShiftded surrounded line");
  } // TestDShiftRect()

  /**
   * Make sure two simple DShifts are equivalent.
   *
   * @throws Exception If the `Rect` constructor throws an exception.
   */
  @Test
  public void testEqvDShift() throws Exception {
    assertTrue(
        (new DShift(new Line("XYZ"), 1)).eqv(new DShift(new Line("XYZ"), 1)),
        "M: Two 1 high DShifts are equivalent");
    assertTrue(
        (new DShift(new Rect('x', 3, 3), 4).eqv(new DShift(new Rect('x', 3, 3), 4))),
        "M: Two 4 high DShifts are equivalent");
  } //testEqvDShift

   /**
   * Make sure that a DShift is not equivalent to any other type of block.
   *
   * @throws Exception If the `DShift` constructor happens to do so.
   */
  @Test
  public void testNotEqvDShift() throws Exception {
    AsciiBlock DShift = new DShift(new Line("G"), 3);
    assertFalse(DShift.eqv(new Line("GGGGGGGGGGGG")), "M: Sample DShift is not equivalent to a line");
    assertFalse(
        DShift.eqv(new DShift(new Line("g"), 3)),
        "M: Sample DShift is not eqv to a diff DShift of same height");
    assertFalse(DShift.eqv(new Boxed(DShift)), "M: Sample DShift is not equivalent to a boxed DShift");
    assertFalse(
        DShift.eqv(new Surrounded(new Empty(), 'X')),
        "E: Sample DShift is not eqv to a surrounded empty");
    assertFalse(
        DShift.eqv(new HComp(VAlignment.CENTER, new AsciiBlock[] {})),
        "E: Sample DShift is not equivalent to an empty horiz composition");
    assertFalse(
        DShift.eqv(new HComp(VAlignment.BOTTOM, new AsciiBlock[] {DShift})),
        "M: Sample DShift is not equivalent to a singleton hcomp");
    AsciiBlock rg = new Rect('G', 1, 2);
    assertFalse(
        DShift.eqv(new HComp(VAlignment.TOP, new AsciiBlock[] {rg, rg, rg})),
        "E: Sample DShift is not equivalent to a similar hcomp");
    assertFalse(
        DShift.eqv(new VComp(HAlignment.LEFT, new AsciiBlock[] {})),
        "E: Sample DShift is not equivalent to an empty vertical composition");
    assertFalse(
        DShift.eqv(new VComp(HAlignment.RIGHT, new AsciiBlock[] {new Line("GGG")})),
        "M: Sample DShift is not equivalent to a singleton vcomp");
    assertFalse(
        DShift.eqv(new VComp(HAlignment.LEFT, new AsciiBlock[] {new Line("GGG"), new Line(" GGG")})),
        "E: Sample DShift is not equivalent to a similar-appearing vcomp");
    assertFalse(
        DShift.eqv(new HFlip(DShift)),
        "M: Sample DShift is not equivalent to horizontally flipped version");
    assertFalse(
        DShift.eqv(new VFlip(DShift)),
        "M: Sample DShift is not equivalent to vertically flipped version");
  } // testNotEqvDShift()



} // class TestNewBlock
