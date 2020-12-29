# Tentang TapTapBall
TapTapGame merupakan sebuah game yang bertujuan untuk menghancurkan objek lain seperti batu bata dengan memantulkan sebuah bola. Pada game ini pemain akan bertugas untuk mengontrol sebuah objek mirip tongkat yang digunakan untuk memantulkan bola agar tidak jatuh ke bawah.

Kemudian ada beberapa kelas yang digunakan untuk membuat game ini :
1. GameObject
2. Paddle
3. Balls
4. Brick
5. Board
6. MainApp

## Kelas `GameObject`
Kelas ini merupakan kelas parent dari subclass Balls, Paddle, dan Brick yang berisi fungsi dasar dari sebuah objek yaitu koordinat, tinggi, lebar, dan warna.

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
