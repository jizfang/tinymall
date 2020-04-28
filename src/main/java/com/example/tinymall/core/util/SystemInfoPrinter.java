package com.example.tinymall.core.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @ClassName SystemInfoPrinter
 * @Description
 * @Author jzf
 * @Date 2020-4-9 16:37
 */
@Slf4j
public class SystemInfoPrinter {
    public static final String CREATE_PART_COPPER = "XOXOXOXOX";

    private static int maxSize = 0;

    public static void printInfo(String title, Map<String, String> infos) {
        setMaxSize(infos);

        printHeader(title);

        for (Map.Entry<String, String> entry : infos.entrySet()) {
            printLine(entry.getKey(), entry.getValue());
        }

        printEnd();
    }

    private static void setMaxSize(Map<String, String> infos) {
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            if (entry.getValue() == null){
                continue;
            }

            int size = entry.getKey().length() + entry.getValue().length();

            if (size > maxSize){
                maxSize = size;
            }
        }

        maxSize = maxSize + 30;
    }

    private static void printHeader(String title) {
        log.info(getLineCopper());
        log.info("");
        log.info("              " + title);
        log.info("");
    }

    private static void printEnd() {
        log.info("  ");
        log.info(getLineCopper());
    }

    private static String getLineCopper() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maxSize; i++) {
            sb.append("=");
        }

        return sb.toString();
    }

    private static void printLine(String head, String line) {
        if (line == null){
            return;
        }

        if (head.startsWith(CREATE_PART_COPPER)) {
            log.info("");
            log.info("    [[  " + line + "  ]]");
            log.info("");
        } else {
            log.info("    " + head + "        ->        " + line);
        }
    }
}
