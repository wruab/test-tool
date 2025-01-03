package com.xiaoheibaby.plugin.extension;

import org.pf4j.ExtensionPoint;

public interface ToolPlugin extends ExtensionPoint {
    String testPrint(String inputStr);
}
