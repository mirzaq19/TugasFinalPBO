# Tentang TapTapBall
TapTapGame merupakan sebuah permainan yang bertujuan untuk menghancurkan objek lain seperti batu bata dengan menggunakan bola. Pada permainan ini, pemain akan menggunakan sebuah objek *paddle* yang berfungsi untuk memantulkan bola agar tidak jatuh ke bawah. 

Permainan ini memiliki tiga level yang terbagi menjadi *easy*, *medium*, dan *hard* dengan masing-masing level terdapat *brick* yang memiliki tingkat kesulitan yang berbeda.

Kemudian ada beberapa kelas yang digunakan untuk membuat permainan ini dengan terbagi menjadi dua kelompok:

* *Class* untuk fitur di permainan
  1. [GameObject](src/Develop/GameObject.java)
  2. [Ball](src/Develop/Ball.java)
  3. [Brick](src/Develop/Brick.java)
  4. [WhiteBrick](src/Develop/WhiteBrick.java)
  5. [RedBrick](src/Develop/RedBrick.java)
  6. [BlueBrick](src/Develop/BlueBrick.java)
  7. [Paddle](src/Develop/Paddle.java)
  8. [DifficultLevel](src/Develop/DifficultLevel.java)
  9. [ScoreManager](bin/Develop/ScoreManager.class)
  10. [Board](src/Develop/Board.java)
  11. [Game](src/Develop/Game.java)
  12. [MainApp](src/Develop/MainApp.java)

* *Class* untuk GUI
  1. [MainMenuPanel](src/Develop/MainMenuPanel.java)
  2. [LeaderboardsPanel](src/Develop/LeaderboardsPanel.java)
  3. [GuiScreen](src/Develop/GuiScreen.java)
  4. [GuiPanel](src/Develop/GuiPanel.java)
  5. [GuiButton](src/Develop/GuiButton.java)
  6. [DifficultyPanel](src/Develop/DifficultyPanel.java)
  7. [CreditPanel](src/Develop/CreditPanel.java) 

## 1. Kelas `GameObject`
Kelas ini merupakan kelas parent dari *subclass* `Ball`, `Paddle`, dan `Brick` yang berisi fungsi dasar dari objeknya, yakni koordinat, tinggi, lebar, dan warna.

```JAVA
public GameObject(int x, int y, int width, int height) {
  this.x = x;
  this.y = y;
  this.width = width;
  this.height = height;
}
public GameObject(int x, int y, int width, int height, Color color) {
  this(x,y,width,height);
  this.color = color;
}
```
Di atas merupakan dua *constructor* dari `GameObject` yang nantinya akan diterapkan di objek-objek yang lain. Perbedaan dari kedua *constructor* tersebut adalah penggunaan variabel **color**. Lalu, di setiap variabel yang berada di dalam *constructor* dilakukan *generate* *Setter* dan *Getter*.

```JAVA
public int getX() {
  return x;
}

public void setX(int x) {
  this.x = x;
}

public int getY() {
  return y;
}

public void setY(int y) {
  this.y = y;
}

public int getWidth() {
  return width;
}

public void setWidth(int width) {
  this.width = width;
}

public int getHeight() {
  return height;
}

public void setHeight(int height) {
  this.height = height;
}

public Color getColor() {
  return color;
}

public void setColor(Color color) {
  this.color = color;
}
```

## 2. Kelas `Ball`
Kelas ini merupakan anak dari kelas `GameObject` yang digunakan untuk mendefinisikan sifat dasar bola dalam permainan.

Terdapat inisiasi beserta pembuatan *Setter* dan *Getter* pada variabel **ballXdir** dan **ballYdir** yang berfungsi untuk mengatur kecepatan dan arah bola pada awal permainan. 

```JAVA
private int ballXdir;
private int ballYdir;

public int getBallXdir() {
  return ballXdir;
}

public void setBallXdir(int ballXdir) {
  this.ballXdir = ballXdir;
}

public int getBallYdir() {
  return ballYdir;
}

public void setBallYdir(int ballYdir) {
  this.ballYdir = ballYdir;
}
```

Kemudian, terdapat fungsi **defaultSpeed()** yang mengatur kecepatan bola pada setiap tingkat kesulitan di permainan. 

Adapun untuk variabel y apabila kecepatan bernilai negatif, maka bola akan mengarah ke atas. Dan begitu juga sebaliknya, apabila kecepatan di variabel y bernilai positif, maka bola akan bergerak ke bawah. Sedangkan di variabel x berlaku pada umumnya. jika positif, maka bola akan bergerak ke kanan dan berlaku kebalikannya.

```JAVA
public void defaultSpeed(String diff){
  if(diff == "easy") {
    this.ballXdir = -2;
    this.ballYdir = -4;
  }else if(diff == "medium"){
    this.ballXdir = -3;
    this.ballYdir = -5;
  } else if(diff == "hard"){
    this.ballXdir = -4;
    this.ballYdir = -6;
  }
}
```

Kemudian, ada *constructor* di kelas ini yang memanggil *constructor* dari kelas utamanya, yakni `GameObject`. Selanjutnya, terdapat fungsi **move()** yang berguna untuk mengatur perpindahan atau perubahan gerak serta arah dari bola. 

Terdapat variabel x dan y yang mengatur perpindahan dari bola. Kemudian, ada dua percabangan untuk variabel x dan y juga, yang mana apabila bola mengenai di bagian atas, kanan, ataupun kiri *frame*, maka arah bola diubah ke arah yang berlawanan atau bisa dikatakan bola akan memantul.

```JAVA
public Ball(int x, int y, int width, int height, Color color) {
  super(x, y, width, height, color);
}

public void move() {
  x+=ballXdir;
  y+=ballYdir;
  
  if(x<0 || x > 678) {
    ballXdir = -ballXdir;
  }
  if(y<0) {
    ballYdir = -ballYdir;
  }
}
```

Fungsi selanjutnya adalah fungsi **draw()** yang menampilkan bola dalam permainan, baik dari segi warna, ukuran, dan posisinya.

```JAVA
public void draw(Graphics g) {
  g.setColor(color);
  g.fillOval(x,y, width, height);
}
```

Kemudian yang terakhir dalam kelas ini terdapat dua fungsi **inverseDirX()** dan **inverseDirY()** yang digunakan untuk mengubah arah bola, baik dalam koordinat x ataupun y.

```JAVA
public void inverseDirX() {
  ballXdir = -ballXdir;
}

public void inverseDirY() {
  ballYdir = -ballYdir;
}
```

## 3. Kelas `Brick`
Kelas ini berfungsi untuk menampilkan *brick* atau semacam batu bata yang nantinya akan dihancurkan pemain menggunakan bola dalam permainan.

Terdapat *constructor* yang sama seperti *constructor* pada kelas utamanya. Lalu, ada fungsi **draw()** yang kegunaannya untuk mengatur warna, posisi, panjang, serta lebar dari *brick*.

```JAVA
public Brick(int x, int y, int width, int height) {
  super(x, y, width, height);
}

public void draw(Graphics2D g) {
  g.setColor(color);
  g.fillRect(x, y, width, height);
  g.setStroke(new BasicStroke(3));
  g.setColor(Color.black);
  g.drawRect(x, y, width, height);
}	
```

Selanjutnya, terdapat penggunaan variabel **value** yang berguna untuk mengatur beberapa jenis *brick* yang memiliki tingkat kekuatan yang berbeda.

*Brick* yang berwarna putih tingkat kekuatannya sebesar satu, warna merah sebesar dua, dan warna biru sebesar tiga. Sehingga, kelas ini memiliki *subclass* `WhiteBrick`, `RedBrick` dan `BlueBrick` yang menerapkan konsep polimorfisme.

```JAVA
protected int value;

public int getValue() {
  return value;
}

public void setValue(int value) {
  this.value = value;
  if(value == 1) {
    this.color = Color.white;
  }else if(value == 2) {
    this.color = Color.red;
  }
}
```

## 4. Kelas `WhiteBrick`
Kelas ini merupakan *subclass* dari `Brick` yang berisikan *constructor* dan fungsi **defaultValue()** untuk mengatur warna dan tingkat kesulitan dari *brick*.

```JAVA
public WhiteBrick(int x, int y, int width, int height) {
  super(x,y,width,height);
  this.color = Color.white;
  this.value = 1;
}

public void defaultValue() {
  this.value = 1;
  this.color = Color.white;
}
```

## 5. Kelas `RedBrick`
Sama seperti kelas `WhiteBrick`, hanya berbeda di warna dan tingkat kesulitan.

```JAVA
public RedBrick(int x, int y, int width, int height) {
  super(x,y,width,height);
  this.color = Color.red;
  this.value = 2;
}

public void defaultValue() {
  this.value = 2;
  this.color = Color.red;
}
```

## 6. Kelas `BlueBrick`
Sama seperti kelas `WhiteBrick`, hanya berbeda di warna dan tingkat kesulitan.

```JAVA
public BlueBrick(int x, int y, int width, int height) {
  super(x,y,width,height);
  this.color = Color.blue;
  this.value = 3;
}

public void defaultValue() {
  this.value = 3;
  this.color = Color.blue;
}
```

## 7. Kelas `Paddle`
Kelas ini digunakan untuk inisiasi dari *paddle* yang nantinya berfungsi sebagai pencegah jatuhnya bola dalam permainan dengan cara memantulkan bola. 

Sama seperti subkelas `Balls` dan `Bricks`, di kelas ini terdapat *constructor* yang berasal dari kelas utamanya.

```JAVA
public Paddle(int x, int y, int width, int height, Color color) {
  super(x, y, width, height, color);
}
```

Kemudian, terdapat fungsi **move()** yang memanfaatkan *Event Handling* menggunakan *mouse*. Di fungsi ini diatur batas kiri dan kanan dari *paddle* agar tidak keluar dari *frame* ketika berada di bagian ujung sisi kiri ataupun sisi kanan.

```JAVA
public void move(MouseEvent e) {
  x = e.getX()-width/2;
  if(x<10) {
    x=10;
  }
  
  if(x>590) {
    x=590;
  }
}
```

Juga terdapat fungsi **draw()** yang kegunaannya sama seperti di subkelas yang lain, yakni menampilkan warna, posisi, serta luas dari *paddle*.

```JAVA
public void draw(Graphics g) {
  g.setColor(color);
  g.fillRect(x, y, width, height);
}
```

## 8. Kelas `DifficultLevel`
Di kelas ini mengatur jumlah dari *brick* di setiap level. terdapat variabel **eCOLS** dan **eROWS** yang bermakna jumlah kolom serta baris di level *easy*. Dan sama halnya dengan itu, variabel **mCOLS**, **mROWS**, **hCOLS**, serta **hROWS** untuk level *medium* dan *hard*.

```JAVA
public static int eCOLS = 7;
public static int eROWS = 3;

public static int mCOLS = 9;
public static int mROWS = 4;
    
public static int hCOLS = 10;
public static int hROWS = 5;
```

## 9. Kelas `ScoreManager`
Kelas ini digunakna untuk mengatur penyimpanan skor pemain dalam file biner yang nantinya digunakan penentuan *highscore* di permainan.

terdapat variabel **easyScore**, **mediumScore**, dan **hardScore** bertipe integer yang digunakan untuk menyimpan skor ketika permainan berlangsung. Begitu juga dengan variabel **currentEasy**, **currentMedium**, serta **currentHard** yang dipakai untuk menyimpan skor terbaru.

```JAVA
public static int easyScore;
public static int mediumScore;
public static int hardScore;
private int currentEasy;
private int currentMedium;
private int currentHard;

public int getCurrentEasy() {
    return currentEasy;
}

public void setCurrentEasy(int currentEasy) {
    this.currentEasy = currentEasy;
    ScoreManager.easyScore = currentEasy;
}

public int getCurrentMedium() {
    return currentMedium;
}

public void setCurrentMedium(int currentMedium) {
    this.currentMedium = currentMedium;
    ScoreManager.mediumScore = currentMedium;
}

public int getCurrentHard() {
    return currentHard;
}

public void setCurrentHard(int currentHard) {
    this.currentHard = currentHard;
    ScoreManager.hardScore = currentHard;
}
```

Kemudian terdapat *constructor* **ScoreManager()** beserta fungsi **loadScore()**, **createSaveData()**, dan **SaveScore()** yang digunakan untuk membuat file yang akan menyimpan skor dalam permainan.

```JAVA
public ScoreManager(){
    try {
        filepath = new File("").getAbsolutePath();
    } catch (Exception e) {
        e.printStackTrace();
    }
    highScore = "Scores";
}

public void loadScore(){
    try {
        File f = new File(filepath,highScore);
        if(!f.exists()){
            createSaveData();
        }
        Scanner reader = new Scanner(f);
        int i=0;
        while(reader.hasNextLine()){
            if(i==0) easyScore = Integer.parseInt(reader.nextLine());
            else if(i==1) mediumScore = Integer.parseInt(reader.nextLine());
            else if(i==2) hardScore = Integer.parseInt(reader.nextLine());
            i++;
        }
        currentEasy = easyScore;
        currentMedium = mediumScore;
        currentHard = hardScore;
        reader.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void createSaveData() {
    try {
        File file = new File(filepath,highScore);
        FileWriter output = new FileWriter(file);
        BufferedWriter writer = new BufferedWriter(output);
        writer.write("0");
        writer.newLine();
        writer.write("0");
        writer.newLine();
        writer.write("0");
        writer.close();
    } catch (IOException ioException) {
        ioException.printStackTrace();
    }
}

public void SaveScore(){
    FileWriter output = null;
    try {
        File f = new File(filepath,highScore);
        output = new FileWriter(f);
        BufferedWriter writer = new BufferedWriter(output);
        writer.write(Integer.toString(getCurrentEasy()));
        writer.newLine();
        writer.write(Integer.toString(getCurrentMedium()));
        writer.newLine();
        writer.write(Integer.toString(getCurrentHard()));
        writer.close();
    } catch (IOException ioException) {
        ioException.printStackTrace();
    }
}
```

## 10. Kelas `Board`
Di kelas `Board` ini adalah tempat di mana permainan berlangsung serta mengatur posisi dari setiap objek pada awal permainan. Tidak hanya itu, setiap objek dalam permainan konfigurasinya berbeda berdasarkan pada tingkat kesulitan yang dipilih, yaitu **Easy**, **Medium**, ataupun **Hard**.

Pada *Constructor* di kelas ini berisi inisiasi objek bola, *paddle*, *brick*, *back button*, serta skor dalam permainan. Adapun untuk objek bola posisi koordinat x akan ditentukan secara random oleh sistem.

```JAVA
public Board() {
  backMenuButton = new GuiButton(Game.BWIDTH/2-buttonWidth/2,330,buttonWidth,60);
  backMenuButton.setText("Back to Menu");
  backMenuButton.addActionListener((ActionEvent e)-> {
    play = false;
    resetBoard();
    GuiScreen.getInstance().setCurrentPanel("Menu");
  });
  bricks = new ArrayList<>();
  ballposX = 150 + randomNumbers.nextInt(100);
  ball = new Ball(ballposX, ballposY, 20, 20, Color.yellow);
  paddle = new Paddle(playerX, 580, 100, 8, Color.green);
  scoreManager = new ScoreManager();
  scoreManager.loadScore();
}
```

Berikutnya ada fungsi untuk menginisiasi objek *brick* atau batu bata yang akan dibuat berdasarkan level kesulitan yang dipilih. Pada fungsi ini diterapkan konsep polimorfisme pada objek *brick*.

```JAVA
public void initBricks(int row, int col) {
  int randomBrick=0;
  if (diff == "easy") {
    brickWidth = 540 / col;
    brickHeight = 150 / row;
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        bricks.add(new WhiteBrick(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight));
      }
    }
  } else if (diff == "medium") {
    brickWidth = 560 / col;
    brickHeight = 180 / row;
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        randomBrick = randomNumbers.nextInt(2);
        if(randomBrick == 0)
          bricks.add(new WhiteBrick(j * brickWidth + 70, i * brickHeight + 50, brickWidth, brickHeight));
        else if(randomBrick == 1)
          bricks.add(new RedBrick(j * brickWidth + 70, i * brickHeight + 50, brickWidth, brickHeight));
      }
    }
  } else if (diff == "hard") {
    brickWidth = 560 / col;
    brickHeight = 200 / row;
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        randomBrick = randomNumbers.nextInt(3);
        if(randomBrick == 0)
          bricks.add(new WhiteBrick(j * brickWidth + 70, i * brickHeight + 50, brickWidth, brickHeight));
        else if(randomBrick == 1)
          bricks.add(new RedBrick(j * brickWidth + 70, i * brickHeight + 50, brickWidth, brickHeight));
        else if(randomBrick == 2)
          bricks.add(new BlueBrick(j * brickWidth + 70, i * brickHeight + 50, brickWidth, brickHeight));
      }
    }
  }
}
```

Kemudian terdapat fungsi **render()** yang menampilkan objek ketika permainan berlangsung, juga mengatur posisi dan kapan ditampilkannya objek tersebut. Seperti teks "Game Over" akan ditampilkan ketika bola jatuh ke bawah atau keluar dari *frame* bagian bawah.

```JAVA
public void render(Graphics2D g) {
  // background
  g.setColor(Color.black);
  g.fillRect(0, 0, 700, 600);

  // borders
  g.setColor(Color.yellow);
  g.fillRect(0, 0, 3, 600);
  g.fillRect(0, 0, 700, 3);
  g.fillRect(697, 0, 3, 600);

  // the scores
  g.setColor(Color.white);
  g.setFont(new Font("serif", Font.BOLD, 25));
  g.drawString("Score: " + score, 575, 30);
  
  //highscore
  g.setColor(Color.green);
  g.setFont(new Font("serif", Font.BOLD, 20));
  g.drawString("Highscore: "+currentHighscore, 20,30);

  // brick
  drawBricks((Graphics2D) g);

  // the paddle
  paddle.draw(g);

  // ball
  ball.draw(g);

  if (!play && score == 0) {
    g.setColor(Color.RED);
    g.setFont(new Font("serif", Font.BOLD, 30));
    g.drawString("Welcome to TapTapBall Game", 160, 300);

    g.setColor(Color.GRAY);
    g.setFont(new Font("serif", Font.BOLD, 20));
    g.drawString("Press (Space) to Play This Game", 200, 350);
  }
  if(totalBricks<=0||ball.getY()>600){
    play = false;
    over = true;
    ball.setBallXdir(0);
    ball.setBallYdir(0);
    g.setColor(new Color(30, 30, 30,alpha));
    g.fillRect(0, 0, Game.BWIDTH, Game.BHEIGHT);
    if (totalBricks <= 0) {
      g.setColor(Color.GREEN);
      g.setFont(overTitleFont);
      g.drawString("You Won, Score: "+score, Game.BWIDTH/2-GuiButton.getMessageWidth("You Won, Score: "+score, overTitleFont, g)/2, 150);
    }
    if (ball.getY() > 600) {
      g.setColor(Color.RED);
      g.setFont(overTitleFont);
      g.drawString("Game Over, Score: " + score,Game.BWIDTH/2-GuiButton.getMessageWidth("Game Over, Score: " + score, overTitleFont, g)/2, 150);
    }
    drawNewHighscore(g);
    g.setColor(Color.GRAY);
    g.setFont(overDesFont);
    g.drawString("Press (Enter) to Restart", Game.BWIDTH/2-GuiButton.getMessageWidth("Press (Enter) to Restart"+ score, overDesFont, g)/2, 250);
    
    if(!added){
      added = true;
      add(backMenuButton);
    }

    if(!saved){
      saveData();
      saved = true;
    }
  }
  super.render(g);
}
```
Lalu, terdapat fungsi **drawNewHighscore()** digunakan untuk menampilkan teks mengenai skor tertinggi baru yang digapai pemain.

```JAVA
public void drawNewHighscore(Graphics2D g){
  if(score>prevHighscore){
    g.setColor(Color.GREEN);
    g.setFont(overDesFont);
    g.drawString("New Highcore!!",Game.BWIDTH/2-GuiButton.getMessageWidth("New Highcore!!", overDesFont, g)/2,190);
  }
}
```

Selanjutnya terdapat fungsi **saveData()** yang berfungsi untuk menyimpan skor dari pemain di setiap tingkat kesulitannya.

```JAVA
public void saveData(){
  if(diff == "easy" && score>ScoreManager.easyScore) scoreManager.setCurrentEasy(score);
  else if(diff == "medium" && score>ScoreManager.mediumScore) scoreManager.setCurrentMedium(score);
  else if(diff == "hard" && score>ScoreManager.hardScore) scoreManager.setCurrentHard(score);
  scoreManager.SaveScore();
}
```

Kemudian terdapat fungsi **drawBricks()** yang berguna untuk menampilkan *brick* atau batu bata di permainan, serta terdapat **setter** dan **getter** untuk variabel **diff** bertipe string yang digunakan untuk penamaan level di permainan.

```JAVA
public void drawBricks(Graphics2D g) {
  for (Brick brick : bricks) {
    if (brick.getValue() > 0) {
      brick.draw(g);
    }
  }
}
public static String getDiff() {
  return diff;
}

public static void setDiff(String diff) {
  Board.diff = diff;
}
```

Juga terdapat fungsi **newGame()** untuk menampilkan *highscore* sebelumnya, beserta *brick* di masing-masing level. Juga fungsi **resetBoard()** untk kembali ke tampilan awal.

```JAVA
public void newGame(){
  if (diff == "easy") {
    prevHighscore = ScoreManager.easyScore;
    currentHighscore = ScoreManager.easyScore;
    COLS = DifficultLevel.eCOLS;
    ROWS = DifficultLevel.eROWS;
  } else if (diff == "medium") {
    prevHighscore = ScoreManager.mediumScore;
    currentHighscore = ScoreManager.mediumScore;
    COLS = DifficultLevel.mCOLS;
    ROWS = DifficultLevel.mROWS;
  } else if (diff == "hard") {
    prevHighscore = ScoreManager.hardScore;
    currentHighscore = ScoreManager.hardScore;
    COLS = DifficultLevel.hCOLS;
    ROWS = DifficultLevel.hROWS;
  }
  remove(backMenuButton);
  ballposX = 150 + randomNumbers.nextInt(100);
  ball.setX(ballposX);
  ball.setY(ballposY);
  ball.defaultSpeed(diff);
  score=0;
  totalBricks = COLS * ROWS;
  bricks.clear();
  initBricks(ROWS, COLS);
  NewGame = false;
}

public void resetBoard(){
  over = false;
  NewGame = true;
  alpha = 0;
  added = false;
  saved = false;
}
```

Dan selanjutnya ada fungsi **update()** yang digunakan pada saat permainan berlangsung, seperti memperbarui skor ketika *brick* berhasil dihancurkan.

```JAVA
@Override
public void update() {
  diff = getDiff();
  
  if(NewGame) {
    newGame();
  }
  if(currentHighscore<score){
    currentHighscore = score;
  }
  if (play) {

    if (new Rectangle(ball.getX(), ball.getY(), 20, 20).intersects(new Rectangle(paddle.getX(), paddle.getY(), 30, 8))) {
      ball.defaultSpeed(diff);
    }
    else if(new Rectangle(ball.getX(), ball.getY(), 20, 20).intersects(new Rectangle(paddle.getX()+30, paddle.getY(), 30, 8))) {
      ball.inverseDirY();
      if(ball.getBallXdir() < 0) ball.setBallXdir(ball.getBallXdir() + 1);
      else ball.setBallXdir(ball.getBallXdir()-1);
    }
    else if(new Rectangle(ball.getX(), ball.getY(), 20, 20).intersects(new Rectangle(paddle.getX()+70, paddle.getY(), 30, 8))) {
      ball.defaultSpeed(diff);
      ball.inverseDirX();
    }

    A: for (Brick brick : bricks) {
      if (brick.getValue() > 0) {
        Rectangle brickRect = new Rectangle(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
        Rectangle ballRect = new Rectangle(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());

        if (ballRect.intersects(brickRect)) {
          brick.setValue(brick.getValue()-1);
          if(brick.getValue() == 0) {							
            totalBricks--;
          }
          score += 5;
          if (ball.getX() + ball.getWidth() - 1 <= brickRect.x
              || ball.getX() + 1 >= brickRect.x + brickRect.width) {
            ball.inverseDirX();
          } else {
            ball.inverseDirY();
          }
          break A;
        }
      }
    }
    ball.move();
  }
  if(alpha<200 && over) alpha+=2;
}
```

Dan yang terakhir terdapat *event handling* menggunakan *mouse* dan *keyboard* yang digunakan dalam permainan. Yakni fungsi **mouseMoved()** digunakan untuk menggerakkan *paddle*, kemudian fungsi **keyPressed()** dimanfaatkan untuk memulai atau mengulangi permainan.

```JAVA
@Override
public void mouseMoved(MouseEvent e) {
  if (play)
    paddle.move(e);
  super.mouseMoved(e);
}

@Override
public void keyPressed(KeyEvent e) {
  if (e.getKeyCode() == KeyEvent.VK_SPACE) {
    if (!play)
      play = true;
  }
  if (e.getKeyCode() == KeyEvent.VK_ENTER) {
    if (!play && over) {
      play = true;
      resetBoard();
      remove(backMenuButton);
    }
  }
}
```

## 11. Kelas `Game`
Kelas ini digunakan untuk mengatur *event handling* ketika pemain berada di menu utama permainan.

```JAVA
private static final long serialVersionUID = 1L;
public static final int BWIDTH = 700;
public static final int BHEIGHT = 600;
private Image BoardBackground;
private int DELAY = 8;
private Timer timer;
public static final Font mfont = new Font("serif", Font.BOLD, 35);
private GuiScreen screen;

public Game() {
  setFocusable(true);
  setPreferredSize(new Dimension(BWIDTH, BHEIGHT));
  addKeyListener(this);
  addMouseListener(this);
  addMouseMotionListener(this);
  setBackground(Color.black);
  screen = GuiScreen.getInstance();
  screen.add("Menu", new MainMenuPanel());
  screen.add("Difficulty", new DifficultyPanel());
  screen.add("Board",new Board());
  screen.add("Leaderboards",new LeaderboardsPanel());
      screen.add("Credit", new CreditPanel());
  screen.setCurrentPanel("Menu");
  timer = new Timer(DELAY, this);
  timer.start();
}

public void paint(Graphics g) {

  drawBackground((Graphics2D) g);
  screen.render((Graphics2D) g);
  g.dispose();

}

public void drawBackground(Graphics2D g) {
  ImageIcon bg = new ImageIcon("src/resources/GameBoard.png");
  BoardBackground = bg.getImage();
  g.drawImage(BoardBackground, 0, 0, this);
}

@Override
public void keyTyped(KeyEvent e) {
  // TODO Auto-generated method stub

}

@Override
public void keyPressed(KeyEvent e) {
  // TODO Auto-generated method stub
  screen.keyPressed(e);
}

@Override
public void keyReleased(KeyEvent e) {
  // TODO Auto-generated method stub

}

@Override
public void actionPerformed(ActionEvent e) {
  screen.update();
  repaint();
}

@Override
public void mouseDragged(MouseEvent e) {
  screen.mouseDragged(e);

}

@Override
public void mouseMoved(MouseEvent e) {
  screen.mouseMoved(e);
}

@Override
public void mouseClicked(MouseEvent e) {

}

@Override
public void mousePressed(MouseEvent e) {
  screen.mousePressed(e);
}

@Override
public void mouseReleased(MouseEvent e) {
  screen.mouseReleased(e);
}

@Override
public void mouseEntered(MouseEvent e) {

}

@Override
public void mouseExited(MouseEvent e) {

}
```

## 12. Kelas `MainApp`
Kelas ini digunakan untuk menjalankan program.

```JAVA
public class MainApp {
	public static void main(String[] args) {
		JFrame window = new JFrame("Tap Tap Ball !!");
		Game game = new Game();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setVisible(true);
		window.add(game);
		window.pack();
		window.setLocationRelativeTo(null);
	}
}
```


## Tampilan Game

**Tampilan Awal**

![MainMenu](https://raw.githubusercontent.com/mirzaq19/TugasFinalPBO/main/Final%20Project/TapTapBall/src/resources/mainmenu.gif)

**Tampilan Easy Level**

![Easy](https://raw.githubusercontent.com/mirzaq19/TugasFinalPBO/main/Final%20Project/TapTapBall/src/resources/easylevel.gif)

**Tampilan Medium Level**

![Medium](https://raw.githubusercontent.com/mirzaq19/TugasFinalPBO/main/Final%20Project/TapTapBall/src/resources/mediumlevel.gif)

**Tampilan Hard Level**

![Hard](https://raw.githubusercontent.com/mirzaq19/TugasFinalPBO/main/Final%20Project/TapTapBall/src/resources/hardLevel.gif)

## `Class Diagram`
Hubungan antar Class dapat dilihat pada diagram berikut:

![Main](https://user-images.githubusercontent.com/40484843/103629414-d8f91280-4f72-11eb-8928-c8e26b5a928b.jpg)

Referensi:
1. [Github](https://github.com/awaismirza/Java-Game-Brick-Breaker)
2. [Youtube](https://youtu.be/K9qMm3JbOH0)
