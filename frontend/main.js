const { app, BrowserWindow } = require('electron');

let mainWindow;

function createWindow() {
    // 创建浏览器窗口
    mainWindow = new BrowserWindow({
        width: 800,
        height: 600,
        webPreferences: {
            nodeIntegration: true
        }
    });

    // 加载 HTML 文件
    mainWindow.loadFile('index.html');

    // 当窗口关闭时触发
    mainWindow.on('closed', () => {
        mainWindow = null;
    });
}

// Electron 初始化完成后创建窗口
app.on('ready', createWindow);

// 所有窗口关闭时退出程序
app.on('window-all-closed', () => {
    if (process.platform !== 'darwin') {
        app.quit();
    }
});

app.on('activate', () => {
    if (mainWindow === null) {
        createWindow();
    }
});
