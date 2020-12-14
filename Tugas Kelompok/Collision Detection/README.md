# Tentang Program Collision Detection
Program ini merupakan sebuah game tembak-tembakan bertemakan luar angkasa. Pada game ini pemain berperan sebagai pengendali kapal luar angkasa.
Pemain bertugas untuk menembaki semua kapal alien untuk memenangkan game ini.
## Kelas yang digunakan
Ada beberapa kelas yang digunakan dalam program ini, diantaranya yaitu:
1. Sprite
2. SpaceShip
3. Alien
4. Missile
5. Board

### Kelas `Sprite`
Kelas ini merupakan kelas yang digunakan sebagai parent dari kelas Spaceship,Alien, dan Missile. Dalam Kelas Sprite terdapat beberapa fungsi umum yang akan digunakan pada kelas anaknya. 

Pada constructor kelas sprite dimulai dengan memasukkan koordinat x dan y, serta mengatur `visible` nya menjadi `true`
```JAVA
public Sprite(int x, int y) {

    this.x = x;
    this.y = y;
    visible = true;
}
```
Kelas sprite memiliki fungsi `loadImage()` dan `getImageDimension()` untuk memuat gambar pada variabel dan mengetahui resolusi dari gambar yang dimuat.
```JAVA
protected void loadImage(String imageName) {

    ImageIcon ii = new ImageIcon(imageName);
    image = ii.getImage();
}

protected void getImageDimensions() {

    width = image.getWidth(null);
    height = image.getHeight(null);
}
```
Lalu juga terdapat fungsi `getBounds()` yang digunakan untuk mengembalikan sebuah objek rectangle berukuran sama dengan resolusi gambar yang dimuat. Ini nantinya akan digunakan untuk mengetahui pada saat sebuah objek bersinggungan dengan objek yang lain.
```JAVA
public Rectangle getBounds() {
    return new Rectangle(x, y, width, height);
}
```

### Kelas `Spaceship`
Kelas ini merupakan anak dari kelas Sprite yang digunakan sebagai kapal luar angkasa yang dikendalikan oleh pemain. Kapal luar angkasa ini nantinya bisa bergerak ke atas, bawah, kiri, dan kanan yang digerakkan dengan tombol arrow pada keyboard dan menembakkan missile jika menekan tombol space.

Pertama adalah constructor dari kelas Spaceship, disini akan memanggil constructor dari kelas parentnya yaitu Sprite yang akan set koordinat sesuai yang dimasukkan nanti. Lalu pada constructor juga akan memuat gambar kapal luar angkasanya serta menginstansiasi array dari objek missile.
```JAVA
public SpaceShip(int x, int y) {
    super(x, y);

    initCraft();
}

private void initCraft() {

    missiles = new ArrayList<>();
    loadImage("src/resources/spaceship30.png");
    getImageDimensions();
}

```

Kemudian ada fungsi `keyPressed()` dan `keyReleased()` yang digunakan sebagai handling event ketika pemain menekan tombol arrow untuk menggerakkan kapal atau menekan tombol space untuk menembakkan missile. 

Fungsi `keyPressed()` digunakan saat tombol ditekan oleh pemain dan kapal akan bergerak sebanyak `1 satuan`, sedangkan fungsi `keyReleased()` digunakan pada saat pemain melepas tombol tersebut dan kapal akan berhenti karena pergerakan menjadi `0 satuan`. Jadi kapal tidak akan bergerak ketika pemain tidak menekan tombol arrow.

```JAVA
public void keyPressed(KeyEvent e) {

    int key = e.getKeyCode();

    if (key == KeyEvent.VK_SPACE) {
        fire();
    }

    if (key == KeyEvent.VK_LEFT) {
        dx = -1;
    }

    if (key == KeyEvent.VK_RIGHT) {
        dx = 1;
    }

    if (key == KeyEvent.VK_UP) {
        dy = -1;
    }

    if (key == KeyEvent.VK_DOWN) {
        dy = 1;
    }
}

public void keyReleased(KeyEvent e) {

    int key = e.getKeyCode();

    if (key == KeyEvent.VK_LEFT) {
        dx = 0;
    }

    if (key == KeyEvent.VK_RIGHT) {
        dx = 0;
    }

    if (key == KeyEvent.VK_UP) {
        dy = 0;
    }

    if (key == KeyEvent.VK_DOWN) {
        dy = 0;
    }
}
```

Seperti yang bisa dilihat di atas, pada saat tombol space ditekan maka akan memanggil fungsi `fire()`. Fungsi ini digunakan untuk membuat objek missile dan menambahkan objek tersebut pada array dengan koordinat kapal luar angkasa saat ini.
```JAVA
public void fire() {
    missiles.add(new Missile(x + width, y + height / 2));
}
```

Lalu yang terakhir ada fungsi `move()` yang digunakan untuk menggerakkan pesawat. Jadi fungsi ini akan dijalankan saat pemain menekan tombol arrow untuk bergerak.
```JAVA
public void move() {

    x += dx;
    y += dy;

    if (x < 1) {
        x = 1;
    }

    if (y < 1) {
        y = 1;
    }
}
```

### Kelas `Alien`
Kelas ini merupakan kelas turunan dari kelas sprite. Kelas ini berfungsi sebagai wadah dari alien yang akan dilawan oleh pemain saat memainkan gamenya nanti. 

Pada kelas ini memiliki constructor seperti berikut yang memanggil contructor dari kelas parent nya, kemudian melakukan pemuatan gambar dari aliennya.
```JAVA
public Alien(int x, int y) {
    super(x, y);
    initAlien();
}
private void initAlien() {
    loadImage("src/resources/alien.png");
    getImageDimensions();
}
```
Kemudian Ada fungsi `move()` yang akan digunakan untuk menggerakkan gambar dari aliennya. Cara kerjanya adalah posisi koordinat x dari setiap alien akan dikurang 1 secara terus menerus sampai koordinat x dari alien tersebut kurang dari 0. 

Jika dilihat hasilnya saat program dijalankan maka alien akan terlihat bergerak ke kiri kemudian hilang dan muncul lagi dari sisi lain. Ketika alien koordinat x dari alien kurang dari 0, maka koordinat x dari alien tersebut akan kembali ke `INITIAL_X` yang bernilai `400`.

```JAVA
public void move() {
    if (x < 0) {
        x = INITIAL_X;
    }
    x -= 1;
}
```

### Kelas `Missile`
Kelas ini merupakan kelas turunan dari kelas sprite yang digunakan sebagai misil atau roket yang diluncurkan kapal luar angkasa.

Pada kelas ini memiliki properti dengan access modifier `private` dan keyword `final` yang bermakna hanya dapat diakses di kelas ini saja dan nilai yang dimasukkan hanya bisa sebanyak satu kali. Propertinya adalah `BOARD_WIDTH`sebagai flag dan `MISSILE_SPEED` yang digunakan sebagai kecepatan objek.

```JAVA
    private final int BOARD_WIDTH = 390;
    private final int MISSILE_SPEED = 2;
```

Sama seperti kelas-kelas sebelumnya, kelas ini memiliki constructor  yang memanggil constructor dari kelas parent-nya. Kemudian, cunstructor ini ditambah dengan fungsi `initMissile()` untuk melakukan render gambar dan dimensi dari objek tersebut.

```JAVA
   public Missile(int x, int y) {
        super(x, y);

        initMissile();
    }
    
    private void initMissile() {
        
        loadImage("src/resources/missile.png");
        getImageDimensions();        
    }
}
```

Lalu terdapat fungsi `move()` yang berguna untuk memberikan akses bagi objek dalam bergerak. Berbeda dengan fungsi `move()` yang ada di Class `Spaceship`, fungsi ini bergerak secara konstan berdasarkan `MISSILE_SPEED` dan objek akan dihancurkan apabila telah melewati batas dari `BOARD_WIDTH`.