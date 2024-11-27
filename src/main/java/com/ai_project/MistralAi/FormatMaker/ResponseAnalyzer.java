package com.ai_project.MistralAi.FormatMaker;

import java.util.*;
import java.util.regex.*;

public class ResponseAnalyzer {

    public static Map<String, Object> analyzeResponse(String response) {
        List<Map<String, String>> result = new ArrayList<>();

        // Regular expression to match code blocks
        String codeBlockRegex = "```(?:[a-zA-Z]+)?\n([\\s\\S]*?)\n```";  // Match everything between code block markers
        Pattern pattern = Pattern.compile(codeBlockRegex);
        Matcher matcher = pattern.matcher(response);

        // To track the position of the last code block
        int lastIndex = 0;

        while (matcher.find()) {
            // Add text before the current code block as a message
            String beforeCode = response.substring(lastIndex, matcher.start()).trim();
            if (!beforeCode.isEmpty()) {
                result.add(createMessageMap(beforeCode));
            }

            // Add the code block itself
            String code = matcher.group(1).trim();
            if (!code.isEmpty()) {
                result.add(createCodeMap(code));
            }

            // Update lastIndex to point after the current code block
            lastIndex = matcher.end();
        }

        // Add any remaining text after the last code block as a message
        String afterLastCode = response.substring(lastIndex).trim();
        if (!afterLastCode.isEmpty()) {
            result.add(createMessageMap(afterLastCode));
        }

        // Wrap the result in a final map
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("response", result);

        return resultMap;
    }

    // Helper method to create a message map
    private static Map<String, String> createMessageMap(String content) {
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put("type", "message");
        messageMap.put("content", content);
        return messageMap;
    }

    // Helper method to create a code map
    private static Map<String, String> createCodeMap(String content) {
        Map<String, String> codeMap = new HashMap<>();
        codeMap.put("type", "code");
        codeMap.put("content", content);
        return codeMap;
    }

//    public static void main(String[] args) {
//        String response = "This is a normal message.\n\n```java\npublic static void main(String[] args) {\n  System.out.println(\"Hello, World!\");\n}```\n\nThis is another normal message.";
//
//        Map<String, Object> analyzedResponse = analyzeResponse(response);
//
//        // Output the result map
//        System.out.println(analyzedResponse);
//    }
}

