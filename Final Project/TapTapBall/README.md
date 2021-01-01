# Tentang TapTapBall
TapTapGame merupakan sebuah permainan yang bertujuan untuk menghancurkan objek lain seperti batu bata dengan menggunakan bola. Pada permainan ini, pemain akan menggunakan sebuah objek *paddle* yang berfungsi untuk memantulkan bola agar tidak jatuh ke bawah.

Kemudian ada beberapa kelas yang digunakan untuk membuat permainan ini :
1. GameObject
2. Paddle
3. Balls
4. Bricks
5. Board
6. MainApp

## Kelas `GameObject`
Kelas ini merupakan kelas parent dari *subclass* `Balls`, `Paddle`, dan `Bricks` yang berisi fungsi dasar dari objeknya, yakni koordinat, tinggi, lebar, dan warna.

```JAVA
public GameObject(int x, int y, int width, int height, Color color) {
  super();
  this.x = x;
  this.y = y;
  this.width = width;
  this.height = height;
  this.color = color;
}
```
Di atas merupakan *constructor* dari `GameObject` yang nantinya akan diterapkan di objek-objek yang lain. Lalu, di setiap variabel yang berada di dalam *constructor* dilakukan *generate* **Setter** dan **Getter**.

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

## Kelas `Balls`
Kelas ini merupakan anak dari kelas `GameObject` yang digunakan untuk mendefinisikan sifat dasar bola dalam permainan.

Terdapat inisiasi variabel **ballXdir** dan **ballYdir** yang berfungsi untuk mengatur kecepatan dan arah bola pada awal permainan. Untuk variabel y apabila kecepatan bernilai negatif, maka bola akan mengarah ke atas. Dan begitu juga sebaliknya, apabila kecepatan di variabel y bernilai positif, maka bola akan bergerak ke bawah. 

Sedangkan di variabel x berlaku pada umumnya. jika positif, maka bola akan bergerak ke kanan dan berlaku kebalikannya.


```JAVA
private int ballXdir = -6;
private int ballYdir = -6;
```

Kemudian, ada *constructor* di kelas ini yang memanggil *constructor* dari kelas utamanya, yakni `GameObject`. Selanjutnya, terdapat fungsi **move()** yang berguna untuk mengatur perpindahan atau perubahan gerak serta arah dari bola. 

Terdapat variabel x dan y yang mengatur perpindahan dari bola. Kemudian, ada dua percabangan untuk variabel x dan y juga, yang mana apabila bola mengenai di bagian atas, kanan, ataupun kiri *frame*, maka arah bola diubah ke arah yang berlawanan atau bisa dikatakan bola akan memantul.

```JAVA
public void move() {
  x+=ballXdir;
  y+=ballYdir;
  
  if(x<0 || x > 665) {
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

## Kelas `Bricks`
Kelas ini berfungsi untuk menampilkan *brick* atau semacam batu bata yang nantinya akan dihancurkan pemain menggunakan bola dalam permainan.

Terdapat *constructor* yang sama seperti *constructor* pada kelas utamanya. Lalu, ada fungsi **draw()** yang kegunaannya untuk mengatur warna, posisi, panjang, serta lebar dari *brick*.

```JAVA
public Bricks(int x, int y, int width, int height, Color color) {
  super(x, y, width, height, color);
}

public void draw(Graphics g) {
  g.setColor(color);
  g.fillRect(x, y, width, height);
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
  
  if(x>575) {
    x=575;
  }
}
```

Juga terdapat fungsi **draw()** yang kegunaannya sama seperti di subkelas yang lain, yakni menampilkan warna, posisi, serta luas dari *paddle*.

## Kelas `Board`

## `Class Diagram`
Hubungan antar Class dapat dilihat pada diagram berikut:
![Untitled](https://user-images.githubusercontent.com/65794806/103263485-9cf60880-49da-11eb-91d7-a90dfd7fd876.png)

Referensi:
1. [Github](https://github.com/awaismirza/Java-Game-Brick-Breaker)
2. [Youtube](https://youtu.be/K9qMm3JbOH0)
