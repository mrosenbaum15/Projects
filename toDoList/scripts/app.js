const electron = require('electron');
const url = require('url');
const path = require('path');

const isMacPlatform = (process.platform == 'darwin');

const {app, BrowserWindow, Menu, ipcMain} = electron;

// set environment
// process.env.NODE_ENV = 'production';

let mainWindow;
let addWindow = null;

app.on('ready', function() {

    // create mainWindow
    mainWindow = new BrowserWindow({
        webPreferences: {
            nodeIntegration: true
        }
    });

    // load in html
    mainWindow.loadURL(url.format({
        pathname: path.join(__dirname, '../views/home.html'),
        protocol: 'file:',
        slashes: true
    }));

    // Quit app when closed
    mainWindow.on('closed', function() {
        app.quit();
    })

    // build a menu using mainMenuTemplate
    const mainMenu = Menu.buildFromTemplate(mainMenuTemplate);

    // insert menu
    Menu.setApplicationMenu(mainMenu);

});

// handle createAddWindow
function createAddWindow() {

    // create add window
    addWindow = new BrowserWindow({
        width: 400,
        height: 300,
        title: 'Add To-Do List Item',
        webPreferences: { nodeIntegration: true }
    });

    // load in html
    addWindow.loadURL(url.format({
        pathname: path.join(__dirname, '../views/addWindow.html'),
        protocol: 'file:',
        slashes: true
    }));

    // Garbage collection handle
    addWindow.on('close', function() {
        addWindow = null;
    });
}

// catch scheduleItem:add
ipcMain.on('scheduleItem:add', function(e, item, time) {
    mainWindow.webContents.send('scheduleItem:add', item, time);
    addWindow.close();
});


// catch scheduleItem:open
ipcMain.on('scheduleItem:open', function() {
    createAddWindow();
});

// making a menu template
const mainMenuTemplate = [
    {
        label: 'File',
        submenu: [

            {
                label: 'Add item',
                accelerator: isMacPlatform ? 'Command+Shift+A' : 'Ctrl+Shift+A',
                click() {
                    createAddWindow();
                }
            },

            {
                label: 'Clear all items',
                accelerator: isMacPlatform ? 'Command+Shift+C' : 'Ctrl+Shift+C',
                click() {
                    mainWindow.webContents.send('scheduleItem:clear');
                }
            },

            {
                label: 'Quit',
                accelerator: isMacPlatform ? 'Command+Q': 'Ctrl+Q',
                click() {
                    app.quit();
                }
            }

        ]
    },

    {
        label: 'Window',
        submenu: [

            {
                label: 'Minimize',
                accelerator: isMacPlatform ? 'Command+M' : 'Ctrl+M',
                click() {
                    mainWindow.minimize();
                    if(addWindow != null) {
                        addWindow.minimize();
                    }
                }
            },

            {
                label: 'Open',
                accelerator: isMacPlatform ? 'Command+O' : 'Ctrl+O',
                click() {
                    mainWindow.show();
                    if(addWindow != null) {
                        addWindow.show();
                    }
                }
            },

            {
                label: 'Close',
                accelerator: isMacPlatform ? 'Command+W': 'Ctrl+W',
                click() {
                    app.quit();
                }
            }

        ]
    }
];

// If OSX, add empty object to menu
if(isMacPlatform){
  mainMenuTemplate.unshift({
      label: ''
  });
}

// Add dev tools if not in production mode
if(process.env.NODE_ENV !== 'production') {
    mainMenuTemplate.push({
        label: 'Developer Tools',
        submenu: [

            {
                role: 'Reload'
            },

            {
                label: 'Toggle DevTools',
                accelerator: isMacPlatform ? 'Command+Shift+I' : 'Ctrl+Shift+I',
                click(item, focusedWindow) {
                    focusedWindow.toggleDevTools();
                }
            }
        ]
    })
}
