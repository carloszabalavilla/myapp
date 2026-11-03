const assert=require('node:assert/strict');
const results=[];
async function request(url,method='GET',body,headers={}){
 const res=await fetch(url,{method,headers:body?{'Content-Type':'application/json',...headers}:headers,body:body?JSON.stringify(body):undefined,redirect:'manual',signal:AbortSignal.timeout(15000)});
 const text=await res.text();let data;try{data=JSON.parse(text);}catch{data=text;}
 assert(res.status<500,method+' '+url+' returned '+res.status);
 return {res,data};
}
async function myapp(){
 const base='http://127.0.0.1:8082';
 assert.equal((await request(base+'/')).res.status,200);
 let r=await request(base+'/artists','POST',{name:"Demo O'Connor",description:'Local test',category:'Jazz',years:1});
 assert.equal(r.res.status,201);const artist=r.data.id;assert(artist>0);
 r=await request(base+'/songs','POST',{title:'Smoke song',category:'Jazz',duration:3.5,artistId:artist});
 assert.equal(r.res.status,201);const song=r.data.id;assert(song>0);
 assert.equal((await request(base+'/songs/'+song,'PUT',{title:'Edited song',category:'Jazz',duration:4,artistId:artist})).data.title,'Edited song');
 assert.equal((await request(base+'/songs/'+song)).data.artist.name,"Demo O'Connor");
 assert.equal((await request(base+'/songs/'+song,'DELETE')).res.status,200);
 assert.equal((await request(base+'/artists/'+artist,'DELETE')).res.status,200);
 assert.equal((await request(base+'/artists/'+artist)).res.status,404);
 results.push('myapp: HTML, artistas/canciones CRUD, relación y 404 correctos');
}

myapp().then(()=>console.log(results.join("\n"))).catch(error=>{console.error(error.message);process.exitCode=1;});
