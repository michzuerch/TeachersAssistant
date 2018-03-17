package com.gmail.michzuerch.LehrerVerwaltung;

import com.gmail.michzuerch.LehrerVerwaltung.backend.util.EsrTool;
import com.gmail.michzuerch.LehrerVerwaltung.backend.util.exception.EsrToolException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: michzuerch
 * Date: 10.08.14
 * Time: 19:22
 * To change this template use File | Settings | File Templates.
 */
@RunWith(Parameterized.class)
public class EsrToolFormatDokumentnummerTest {
    @Parameterized.Parameter
    public String p1;
    @Parameterized.Parameter(value = 1)
    public String p2;
    private EsrTool tool = new EsrTool();

    //@org.jetbrains.annotations.NotNull
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                {"456789", "456789"},
                {"000001", "000001"},
                {"700231", "700231"},
                {"000000", "000000"},
        };
        return Arrays.asList(data);
    }

    @Test
    public void testFormatDokumentnummer() throws EsrToolException {
        assertEquals("EsrTool formatDokumentnummer", tool.formatDokumentnummer(p1), p2);
    }
}
