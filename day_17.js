/*
	From your puzzle input, press F12 to open the JavaScript console, paste this code, and press enter
*/

let input = document.body.innerText.split('\n').map((x) => x.trim().split(''));

function countAround(x, y, z, w, map, isPart2) {
    let count = 0;
    for (let ww = (isPart2 ? w - 1 : 0); ww <= (isPart2 ? w + 1 : 0); ww++) {
        for (let zz = z - 1; zz <= z + 1; zz++) {
            for (let yy = y - 1; yy <= y + 1; yy++) {
                for (let xx = x - 1; xx <= x + 1; xx++) {
                    if ((xx !== x || yy !== y || zz !== z || ww !== w) && map[`${xx},${yy},${zz},${ww}`]) {
                        count++;
                    }
                }
            }
        }
    }
    return count;
}

function solve(input, isPart2) {
    let map = {};
    for (let y = 0; y < input.length; y++) {
        for (let x = 0; x < input[0].length; x++) {
            if (input[y][x] === '#') {
                map[`${x},${y},0,0`] = true;
            }
        }
    }
    let height = [0, input.length];
    let width = [0, input[0].length];
    let depth = [0, 1];
    let hyper = [0, 1];
    for (let t = 0; t < 6; t++) {
        let newMap = {};
        depth[0]--;
        depth[1]++;
        width[0]--;
        width[1]++;
        height[0]--;
        height[1]++;
        if (isPart2) {
            hyper[0]--;
            hyper[1]++;
        }
        for (let w = hyper[0]; w < hyper[1]; w++) {
            for (let z = depth[0]; z < depth[1]; z++) {
                for (let y = width[0]; y < width[1]; y++) {
                    for (let x = height[0]; x < height[1]; x++) {
                        let neigh = this.countAround(x, y, z, w, map, isPart2);
                        const isActive = map[`${x},${y},${z},${w}`];
                        if (neigh === 3 || (neigh === 2 && isActive)) {
                            newMap[`${x},${y},${z},${w}`] = true;
                        }
                    }
                }
            }
        }

        map = newMap;
    }
    return Object.keys(map).length;
}

console.log('Part 1', solve(input, false));
console.log('Part 2', solve(input, true));
