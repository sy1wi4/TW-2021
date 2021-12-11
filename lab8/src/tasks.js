const Fork = function () {
    this.state = 1;
    return this;
};

// zaimplementuj funkcje acquire, tak by korzystala z algorytmu BEB
// (http://pl.wikipedia.org/wiki/Binary_Exponential_Backoff), tzn:
// 1. przed pierwsza proba podniesienia widelca Filozof odczekuje 1ms
// 2. gdy proba jest nieudana, zwieksza czas oczekiwania dwukrotnie
//    i ponawia probe itd.

Fork.prototype.acquire = function (id, cb) {
    let fork = this
    let time = 1;

    // recursively call BEB function if fork is taken
    let BEB = function () {
        if (fork.state === 1) {
            console.log("ph_id: " + id + " -> fork taken")
            // reset time
            time = 1;
            // take semaphore
            fork.state = 0;
            // pick up
            if (cb) cb();

        } else {
            time *= 2;
            // console.log("Fork is taken, time to wait: [%s ms]", time)
            setTimeout(BEB, time);
        }
    }
    setTimeout(BEB, time)
}

Fork.prototype.release = function () {
    this.state = 1;
}

const Philosopher = function (id, forks) {
    this.id = id;
    this.forks = forks;
    this.f1 = id % forks.length;
    this.f2 = (id + 1) % forks.length;
    console.log("Hello from ph_id: " + id)
    return this;
};


// zaimplementuj rozwiazanie naiwne
// kazdy filozof powinien 'count' razy wykonywac cykl
// podnoszenia widelcow -- jedzenia -- zwalniania widelcow

// każdy filozof podnosi najpierw lewy, potem prawy widelec, itd.

Philosopher.prototype.startNaive = function (count) {
    const forks = this.forks,
        f1 = this.f1,
        f2 = this.f2,
        id = this.id;

    if (count > 0) {
        let first = forks[f1]
        let second = forks[f2]

        first.acquire(id, function () {
            second.acquire(id, function () {
                const delay = Math.floor((Math.random() * 1000));
                setTimeout(function () {
                    console.log("ph_id: " + id + " -> eating... ")
                    first.release();
                    second.release();
                }, delay);
            })
        })
    }
}


// zaimplementuj rozwiazanie asymetryczne
// kazdy filozof powinien 'count' razy wykonywac cykl
// podnoszenia widelcow -- jedzenia -- zwalniania widelcow

// filozofowie z nieparzystym numerem najpierw podnoszą widelec lewy, z parzystym -- prawy

Philosopher.prototype.startAsym = function (count) {
    const forks = this.forks,
        f1 = this.f1,
        f2 = this.f2,
        id = this.id;

    let philosopher = this

    if (count > 0) {
        let first = id % 2 === 0 ? forks[f1] : forks[f2]
        let second = id % 2 === 0 ? forks[f2] : forks[f1]

        first.acquire(id, function () {
            second.acquire(id, function () {
                const delay = Math.floor((Math.random() * 1000));
                setTimeout(function () {
                    console.log("ph_id: " + id + " -> eating... ")
                    first.release();
                    second.release();
                }, delay);
                philosopher.startAsym(count - 1)
            })
        })
    }
}


// zaimplementuj rozwiazanie z kelnerem
// kazdy filozof powinien 'count' razy wykonywac cykl
// podnoszenia widelcow -- jedzenia -- zwalniania widelcow

const Conductor = function (maxValue) {
    this.state = maxValue;
    return this;
};

Conductor.prototype.acquire = function (cb) {
    let fork = this

    let waitC = function () {
        if (fork.state !== 0) {
            fork.state--;
            if (cb) cb();

        } else {
            // console.log("Conductor: waiting...")
            setTimeout(waitC, 2);
        }
    }
    setTimeout(waitC, 2)
}

Conductor.prototype.release = function () {
    this.state++;
}

Philosopher.prototype.startConductor = function (count, conductor) {
    const forks = this.forks,
        f1 = this.f1,
        f2 = this.f2,
        id = this.id;

    let philosopher = this

    if (count > 0) {
        let first = forks[f1]
        let second = forks[f2]

        conductor.acquire(function () {
            first.acquire(id, function () {
                second.acquire(id, function () {
                    const delay = Math.floor((Math.random() * 1000));
                    setTimeout(function () {
                        console.log("ph_id: " + id + " -> eating... ")
                        first.release();
                        second.release();
                        conductor.release();
                        philosopher.startConductor(count - 1, conductor)
                    }, delay)
                })
            })
        })
    }
}


const N = 5;
const forks = [];
const philosophers = [];
const conductor = new Conductor(N - 1);

let i;
for (i = 0; i < N; i++) {
    forks.push(new Fork());
}

for (i = 0; i < N; i++) {
    philosophers.push(new Philosopher(i, forks));
}


for (i = 0; i < N; i++) {
    philosophers[i].startAsym(10);
}

for (i = 0; i < N; i++) {
    philosophers[i].startConductor(10, conductor);
}

for (i = 0; i < N; i++) {
    philosophers[i].startNaive(10);
}