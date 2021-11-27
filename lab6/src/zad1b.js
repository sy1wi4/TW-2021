let async = require("async")

function printAsync(s, cb) {
    const delay = Math.floor((Math.random() * 1000) + 500);
    setTimeout(function () {
        console.log(s);
        if (cb) cb();
    }, delay);
}

function task1(cb) {
    printAsync("1", function () {
        task2(cb);
    });
}

function task2(cb) {
    printAsync("2", function () {
        task3(cb);
    });
}

function task3(cb) {
    printAsync("3", cb);
}

function loop(n) {
    let tasksArr = []
    for (let i = 0; i < n; i++) {
        tasksArr.push(task1)
    }
    async.waterfall(tasksArr, function () {
        console.log("Done!");
    });
}

loop(4)
