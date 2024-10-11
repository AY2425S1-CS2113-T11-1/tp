package seedu.duke.parserTest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testParseCommand_Add() {
        try {
            assertEquals(0, new Parser("add",1).parseCommand());
        } catch (Exception e) {
            assertEquals("Non-Numerical Error", e.getMessage());
        }
    }

    @Test
    public void testParseCommand_Add_Index() {
        try {
            assertEquals(1, new Parser("add 1",1).parseCommand());
        } catch (Exception e) {
            assertEquals("Non-Numerical Error", e.getMessage());
        }
    }

    @Test
    public void testParseCommand_list() {
        assertEquals(1, new Parser("list",0).parseCommand());
        assertEquals(2, new Parser("list",1).parseCommand());
    }

    @Test
    public void testParseCommand_Del() {
        try {
            assertEquals(0, new Parser("delete", 1).parseCommand());
        } catch (Exception e) {
            assertEquals("Non-Numerical Error", e.getMessage());
        }
    }

    @Test
    public void testParseCommand_Del_Index() {
        assertEquals(4, new Parser("delete 5",1).parseCommand());
    }
}