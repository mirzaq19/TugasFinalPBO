# Tentang TapTapBall
TapTapGame merupakan sebuah permainan yang bertujuan untuk menghancurkan objek lain seperti batu bata dengan menggunakan bola. Pada permainan ini, pemain akan menggunakan sebuah objek *paddle* yang berfungsi untuk memantulkan bola agar tidak jatuh ke bawah. 

Permainan ini memiliki tiga level yang terbagi menjadi *easy*, *medium*, dan *hard* dengan masing-masing level terdapat *brick* yang memiliki tingkat kesulitan yang berbeda.

Kemudian ada beberapa kelas yang digunakan untuk membuat permainan ini dengan terbagi menjadi dua kelompok:

* *Class* untuk fitur di permainan
  1. GameObject
  2. Ball
  3. Brick
  4. WhiteBrick
  5. RedBrick
  6. BlueBrick
  7. Paddle
  8. DifficultLevel
  9. Board
  10. MainApp

* *Class* untuk GUI
  1. MainMenuPanel
  2. GuiScreen
  3. GuiPanel
  4. GuiButton
  5. DifficultyPanel
  6. CreditPanel 

## Kelas `GameObject`
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

## Kelas `Ball`
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

Kemudian, terdapat fungsi **easySpeed()**, **mediumSpeed()**, dan **hardSpeed()** yang mengatur kecepatan bola pada setiap tingkat kesulitan di permainan. 

Adapun untuk variabel y apabila kecepatan bernilai negatif, maka bola akan mengarah ke atas. Dan begitu juga sebaliknya, apabila kecepatan di variabel y bernilai positif, maka bola akan bergerak ke bawah. Sedangkan di variabel x berlaku pada umumnya. jika positif, maka bola akan bergerak ke kanan dan berlaku kebalikannya.

```JAVA
public void easySpeed() {
  this.ballXdir = -2;
  this.ballYdir = -4;
}
public void mediumSpeed() {
  this.ballXdir = -3;
  this.ballYdir = -5;
}
public void hardSpeed() {
  this.ballXdir = -4;
  this.ballYdir = -6;
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

## Kelas `Brick`
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

*Brick* yang berwarna putih tingkat kekuatannya sebesar satu, warna merah sebesar dua, dan warna biru sebesar tiga. Sehingga, kelas ini memiliki *subclass* `WhiteBrick`, `RedBrick` dan `BlueBrick`.

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

## Kelas `WhiteBrick`
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

## Kelas `RedBrick`
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

## Kelas `BlueBrick`
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

## Kelas `Paddle`
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

## Kelas `DifficultLevel`
Di kelas ini mengatur jumlah dari *brick* di setiap level. terdapat variabel **eCOLS** dan **eROWS** yang bermakna jumlah kolom serta baris di level *easy*. Dan sama halnya dengan itu, variabel **mCOLS**, **mROWS**, **hCOLS**, serta **hROWS** untuk level *medium* dan *hard*.

```JAVA
public static int eCOLS = 7;
public static int eROWS = 3;

public static int mCOLS = 9;
public static int mROWS = 4;
    
public static int hCOLS = 10;
public static int hROWS = 5;
```

## Kelas `Board`
Di kelas Board ini adalah tempat di mana permainan berlangsung dan mengatur posisi dari setiap objek dalam game pada awal permainan. Lalu objek yang akan dibuat dalam permainan konfigurasi nya akan berbeda bergantung pada tingkat kesulitan yang di pilih yaitu **Easy**, **Medium**, ataupun **Hard**

Pada Cnstructor di kelas ini berisi Inisiasi objek bola dan paddle, untuk objek bola posisi koordinat x akan ditentukan secara random oleh sistem.
```JAVA
public Board() {
  ballposX = 150 + randomNumbers.nextInt(100);
  ball = new Ball(ballposX, ballposY, 20, 20, Color.yellow);
  paddle = new Paddle(playerX, 580, 100, 8, Color.green);

}
```

Berikutnya ada fungsi untuk menginisiasi objek brick atau batu bata yang akan dibuat berdasarkan level kesulitan yang dipilih. Pada fungsi ini diterapkan konsep polymorphism pada objek brick.
```JAVA
public void initBricks(int row, int col) {
  bricks = new ArrayList<>();
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
## Tampilan Game

**Tampilan Awal**

![MainMenu](https://raw.githubusercontent.com/mirzaq19/TugasFinalPBO/main/Final%20Project/TapTapBall/src/resources/menucredit.gif)

**Tampilan Easy Level**

![Easy](https://raw.githubusercontent.com/mirzaq19/TugasFinalPBO/main/Final%20Project/TapTapBall/src/resources/easylevel.gif)

**Tampilan Medium Level**

![Medium](https://raw.githubusercontent.com/mirzaq19/TugasFinalPBO/main/Final%20Project/TapTapBall/src/resources/mediumlevel.gif)

**Tampilan Hard Level**

![Hard](https://raw.githubusercontent.com/mirzaq19/TugasFinalPBO/main/Final%20Project/TapTapBall/src/resources/hardLevel.gif)

## `Class Diagram`
Hubungan antar Class dapat dilihat pada diagram berikut:
![Untitled](https://user-images.githubusercontent.com/65794806/103263485-9cf60880-49da-11eb-91d7-a90dfd7fd876.png)

Referensi:
1. [Github](https://github.com/awaismirza/Java-Game-Brick-Breaker)
2. [Youtube](https://youtu.be/K9qMm3JbOH0)
