const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').split('\n');

if (input[0] != ' ') console.log(input[0].trim().split(/\s+/).length);
else console.log(0);
