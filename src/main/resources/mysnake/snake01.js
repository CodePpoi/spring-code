const canvas = document.getElementById('snake');
const ctx = canvas.getContext('2d');


const ground =new Image();
ground.src = "img/ground.png"

const foodImage = new Image();
foodImage.src = "img/food.png"

const box = 32;
var snake = []
snake[0] = {
  x: 9 * box,
  y: 10 * box
}


var food = {
    x: Math.floor(Math.random() * 17 + 1) * box,
    y: Math.floor(Math.random() * 15 + 3) * box
}

var score = 0;


var d
document.addEventListener("keydown", direction)

function direction(event) {
   var key = event.keyCode
   if (key == 37 && d != 'RIGHT') {
      d = "LEFT"
      console.log(37);
   } else if (key == 38 && d != 'DOWN') {
      console.log(38);
      d = "UP"
   } else if (key == 39  && d != 'LEFT') {
      console.log(39);
      d = "RIGHT"
   } else if (key == 40 && d != 'UP') {
      console.log(40);
      d = "DOWN"
   }
};



function collision(head, array) {

 for(var i=0; i< array.length; i++) {
      if(head.x == array[i].x && head.y == array[i].y) {
        return true
      }
 }

 return false
}



function draw() {
    ctx.drawImage(ground, 0, 0)
    for(var i=0; i<snake.length; i++) {
        ctx.fillStyle = (i==0) ? "green": "white";
        ctx.fillRect(snake[i].x, snake[i].y, box, box);

        ctx.strokeStyle="red";
        ctx.strokeRect(snake[i].x, snake[i].y, box, box);
    }

    ctx.drawImage(foodImage, food.x, food.y);

    ctx.fillStyle = "white"
    ctx.font = "45px Changa one"
    ctx.fillText(score, 2 * box, 1.6 * box);


    var snakeX = snake[0].x;
    var snakeY = snake[0].y;

    if (d == "LEFT") snakeX -= box;
    if (d == "RIGHT") snakeX += box;

    if (d == "UP") snakeY -= box;
    if (d == "DOWN") snakeY += box;


    if(snakeX == food.x && snakeY == food.y) {
        score++;
        console.log(score)
        food = {
            x: Math.floor(Math.random() * 17 + 1) * box,
            y: Math.floor(Math.random() * 15 + 3) * box
        }
    } else {
        snake.pop()
    }


    var newSnake = {
        x: snakeX,
        y: snakeY
    }

    if(snakeX < box || snakeX > 17 * box || snakeY < 3 * box || snakeY > 17 * box || collision(newSnake, snake)) {
        clearInterval(game);
    }
    snake.unshift(newSnake)

}



game = setInterval(draw, 150);