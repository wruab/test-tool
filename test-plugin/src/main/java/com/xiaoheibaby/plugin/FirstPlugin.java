package com.xiaoheibaby.plugin;

import org.pf4j.Extension;

@Extension
public class FirstPlugin implements ToolPlugin {

    @Override
    public String testPrint(String inputStr) {
        return "Hello world!" + inputStr;
    }
}