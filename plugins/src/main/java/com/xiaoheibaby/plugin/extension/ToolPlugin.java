package com.xiaoheibaby.plugin.extension;

import org.pf4j.ExtensionPoint;

public interface ToolPlugin extends ExtensionPoint {
    String testPrint(String inputStr);

    String testPrint2(String inputStr);

    String testPrint3(String inputStr);

    String testPrint4(String inputStr);
}
