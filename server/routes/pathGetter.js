const express = require("express");
const Logger = require('./../connect/logg');
const pathGetter = express.Router();
const { spawn } = require('child_process'); // using child_process to call a separate file during execution

// fetch-shortest-path calls the ShortestPath.java file which includes the Dijkstra's algorithm for the shortest path
// The ShortestPath.java takes 2 arguments: source and destination, then calculates the shortest path and returns the desired result
pathGetter.post("/fetch-shortest-path", async (req, res) => {
    var start = req.body.start;
    var dest = req.body.dest;
    let data1;
    // Compiling the Java file
    const compile = spawn('javac', ['ShortestPath.java']);

    compile.stderr.on('data', (data) => {
        console.error(`stderr: ${data}`);
    });

    compile.on('close', (code) => {
        // After successful compilation, execute the Java file
        const execute = spawn('java', ['ShortestPath', start, dest]);

        execute.stderr.on('data', (data) => {
            console.error(`stderr: ${data}`);
        });

        execute.stdout.on('data', function(data) {
            console.log(data1 = data);
        });

        // Read the output and send it to the front-end for display
        execute.on('close', (code) => {
            res.send(data1);
        });
    });
});

module.exports = pathGetter;
