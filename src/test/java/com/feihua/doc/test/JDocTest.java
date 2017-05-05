package com.feihua.doc.test;

import com.feihua.doc.core.JDocCore;
import org.junit.Test;


public class JDocTest {

    @Test
    public void buildSpringmvcApiDoc() {
        new JDocCore().build();

    }
}
