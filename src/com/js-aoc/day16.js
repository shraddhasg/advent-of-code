const fs = require('fs');
const read = fs.readFileSync("day_16.txt");
let data = read.toString().split('\n')
let count = new Map()
let myTicket = data.slice(data.indexOf('')+2,data.indexOf('',data.indexOf('')+1)).join('').split(',').map(Number)
let rules = data.slice(0,data.indexOf('')).map(el=>el.split(':')).map(el=>{
    let l = el[1].split(' ').filter(el=>!isNaN(el[0])).map(el=>el.split('-').map(Number))
    let b = new Map()
    count.set(el[0],b)
    return [el[0],l[0],l[1]] 
})
let rulesPone = rules.map(el=>{return [el[1],el[2]]}).flat()
let tickets = data.slice(data.indexOf('')+5).map(el=>el.split(',').map(Number))

///// Part one
let pOne = 0
let invalid = []
tickets.forEach((ticket,i)=>{
    for(let k=0;k<ticket.length;k++){
        let valid = false
        rulesPone.every(rule=>{
            if(ticket[k]>=rule[0] && ticket[k]<=rule[1]){
                valid = true
                return false
            }else{
                return true
            }
        })
        valid==false?(invalid.push(i),pOne+=ticket[k]) : undefined  
    }
})
console.log("Part one = " +pOne)

///// Part two
tickets =  tickets.filter((el,i)=>invalid.indexOf(i)==-1)
tickets.push(myTicket)
let start 
let k
let done = []

rules.forEach(el=>{
    tickets.forEach(ticket=>{
        for(let k=0;k<ticket.length;k++){ 
            if((ticket[k]>=el[1][0] && ticket[k]<=el[1][1]) || (ticket[k]>=el[2][0] && ticket[k]<=el[2][1])){
                let a = count.get(el[0])
                a.has(k) ? a.set(k,a.get(k)+1): a.set(k,1)
                count.set(el[0],a)
            }
        }
    })
})

count.forEach((val,key,map)=>{
    let occ = [...val.entries()].filter((e) => e[1]==Math.max(...val.values())).map(el=>el[0])
    occ.length==1 ? (start=occ[0],k=key) :undefined
    map.set(key,occ)
})

function recursive(map,pos,k){
    map.forEach((val,key,map)=>{ key!==k ? map.set(key,val.filter(el=>el!==pos)):undefined})   
    done.push(k)
    let single = [...map.entries()].find(el=>el[1].length==1 && done.indexOf(el[0])==-1)
    if(!single){
        let r = tickets[tickets.length-1]
        return  r[map.get('departure location')[0]]*r[map.get('departure station')[0]]*r[map.get('departure platform')[0]]
                *r[map.get('departure track')[0]]*r[map.get('departure date')[0]]*r[map.get('departure time')[0]]
    }else{
        return recursive(map,single[1][0],single[0])
    }

}
console.log("Part two = " + recursive(count,start,k))