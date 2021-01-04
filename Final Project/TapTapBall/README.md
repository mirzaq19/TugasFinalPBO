# Tentang TapTapBall
TapTapGame merupakan sebuah permainan yang bertujuan untuk menghancurkan objek lain seperti batu bata dengan menggunakan bola. Pada permainan ini, pemain akan menggunakan sebuah objek *paddle* yang berfungsi untuk memantulkan bola agar tidak jatuh ke bawah. 

Permainan ini memiliki tiga level yang terbagi menjadi *easy*, *medium*, dan *hard* dengan masing-masing level terdapat *brick* yang memiliki tingkat kesulitan yang berbda.

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

## Kelas `Board`

## `Class Diagram`
Hubungan antar Class dapat dilihat pada diagram berikut:
![Untitled](https://user-images.githubusercontent.com/65794806/103263485-9cf60880-49da-11eb-91d7-a90dfd7fd876.png)

Referensi:
1. [Github](https://github.com/awaismirza/Java-Game-Brick-Breaker)
2. [Youtube](https://youtu.be/K9qMm3JbOH0)
