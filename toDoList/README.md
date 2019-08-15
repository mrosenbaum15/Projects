# Run to do list app locally:

    1. Clone git repo
    2. In terminal, navigate to directory of repository and go to toDoList folder (Git Bash is preferred)
    3. Check if npm is installed by executing: npm -v
        3a. If it's installed, the command should return a version like 6.10.3
        3b. If not, download and install here: https://docs.npmjs.com/downloading-and-installing-node-js-and-npm
    4. Run 'npm install'
    5. Run 'npm start' to launch desktop app

# Build app in production:

    1. In scripts/app.js, uncomment line 10 (// process.env.NODE_ENV = 'production';) to set   
    production mode to on, disabling developer tools
    2. Run 'npm run package-SYSTEM', but replace SYSTEM with mac, linux, or win depending on the operating system you are using
    3. There will be a new folder called release-build, enter that
        3a. If on windows, the file named Todo.exe is the desktop app in production
        3b. If on mac, the file named Todo is the desktop app in production
