function formatJson(inputJson) {
    try {
        // 将输入的 JSON 字符串解析为对象
        const jsonObject = JSON.parse(inputJson);
        // 将对象格式化为 JSON 字符串并显示
        return JSON.stringify(jsonObject, null, 4);  // 4 个空格缩进
    } catch (error) {
        return ''; // 解析失败时返回空字符串
    }
}
