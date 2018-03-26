package me.itzhu.common.webapp.common;

import org.junit.Test;

import me.itzhu.common.ui.dialog.Config;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void maptest(){
        int a = Config.INSTANCE.getAnimMaps().get("aa");
        System.out.print("a"+a);
    }
}