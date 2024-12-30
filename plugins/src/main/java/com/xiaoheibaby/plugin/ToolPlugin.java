package com.xiaoheibaby.plugin;

import org.pf4j.ExtensionPoint;

public interface ToolPlugin extends ExtensionPoint {
    String testPrint(String inputStr);
}
