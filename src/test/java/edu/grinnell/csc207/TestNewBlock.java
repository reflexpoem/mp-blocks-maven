package edu.grinnell.csc207;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.DShift;
import edu.grinnell.csc207.blocks.Line;
import edu.grinnell.csc207.blocks.Rect;
import edu.grinnell.csc207.blocks.Surrounded;

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

} // class TestNewBlock
