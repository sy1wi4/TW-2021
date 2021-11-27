function printAsync(s, cb) {
    const delay = Math.floor((Math.random() * 1000) + 500);
    setTimeout(function () {
        console.log(s);
        if (cb) cb();
    }, delay);
}

// Napisz funkcje (bez korzytania z biblioteki async) wykonujaca 
// rownolegle funkcje znajdujace sie w tablicy 
// parallelFunctions. Po zakonczeniu wszystkich funkcji
// uruchamia sie funkcja finalFunction. Wskazowka:  zastosowc
// licznik zliczajacy wywolania funkcji rownoleglych 


function inParallel(parallelFunctions, finalFunction) {
    let ctr = parallelFunctions.length;

    function decrement() {
        ctr--;
        if (ctr === 0) {
            finalFunction();
        }
    }

    parallelFunctions.forEach(function (parallelFunction) {
        parallelFunction(decrement);
    });

}

A = function (cb) {
    printAsync("A", cb);
}
B = function (cb) {
    printAsync("B", cb);
}
C = function (cb) {
    printAsync("C", cb);
}
D = function (cb) {
    printAsync("Done", cb);
}

inParallel([A, B, C], D)

// kolejnosc: A, B, C - dowolna, na koncu zawsze "Done"