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

### Kelas `SpaceShip`
Kelas ini merupakan anak dari kelas Sprite yang digunakan sebagai kapal luar angkasa yang dikendalikan oleh pemain. Kapal luar angkasa ini nantinya bisa bergerak ke atas, bawah, kiri, dan kanan yang digerakkan dengan tombol arrow pada keyboard dan menembakkan missile jika menekan tombol space.

Pertama adalah constructor dari kelas SpaceShip, disini akan memanggil constructor dari kelas parentnya yaitu Sprite yang akan set koordinat sesuai yang dimasukkan nanti. Lalu pada constructor juga akan memuat gambar kapal luar angkasanya serta menginstansiasi array dari objek missile.

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
Kelas ini merupakan kelas turunan dari kelas sprite yang digunakan sebagai insiasi misil atau roket serta pergerakannya yang diluncurkan dari kapal luar angkasa.

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
```

Lalu terdapat fungsi `move()` yang berguna untuk memberikan akses bagi objek dalam bergerak. Berbeda dengan fungsi `move()` yang ada di kelas SpaceShip, fungsi ini bergerak secara konstan berdasarkan `MISSILE_SPEED` dan objek akan dihancurkan apabila telah melewati batas dari `BOARD_WIDTH`.

```JAVA
public void move() {
    
    x += MISSILE_SPEED;
    
    if (x > BOARD_WIDTH)
        visible = false;
}
```

### Kelas `Board`
Kelas ini merupaka kelas yang menampung semua objek dari permainan ini. Mungkin jika bisa diibaratkan, kelas ini seperti papan dalam permainan monopoli.

Kelas Board memiliki beberapa variabel. Yang pertama, terdapat variabel `timer` yang berasal dari library `javax.swing.Timer` berfungsi untuk mengatur tampilan dari objek-objek yang bergerak agar tidak muncul secara bersamaan dengan lamanya berdasarkan variabel `DELAY`.

```JAVA
private Timer timer;
private final int DELAY = 15;
```

Kemudian terdapat variabel `spaceship` yang mensifati dari kelas SpaceShip dan ada variabel `aliens` yang berbentuk array dari kelas Alien. Juga terdapat variabel boolean `ingame` yang menentukan apakah permainan sudah selesai atau masih berlangsung.

```JAVA
private SpaceShip spaceship;
private List<Alien> aliens;
private boolean ingame;
```

Di kelas ini juga terdapat variabel yang nilainya digunakan untuk menentukan posisi dari pesawat luar angkasa ketika permainan baru dimulai, yakni variabel `ICRAFT_X` serta `ICRAFT_Y`. Juga terdapat variabel yang nilainya berfungsi dalam penentuan luas dari papan permainan, yaitu variabel `B_WIDTH` dan `B_HEIGHT`.

```JAVA
private final int ICRAFT_X = 40;
private final int ICRAFT_Y = 60;
private final int B_WIDTH = 400;
private final int B_HEIGHT = 300;
```

Terakhir dalam pembahasan variabel, ada variabel `pos` yang berbentuk array berguna untuk memasukkan koordinat X dan Y mengenai posisi awal dari masing-masing alien.

```JAVA
private final int[][] pos = {
    {2380, 29}, {2500, 59}, {1380, 89},
    {780, 109}, {580, 139}, {680, 239},
    {790, 259}, {760, 50}, {790, 150},
    {980, 209}, {560, 45}, {510, 70},
    {930, 159}, {590, 80}, {530, 60},
    {940, 59}, {990, 30}, {920, 200},
    {900, 259}, {660, 50}, {540, 90},
    {810, 220}, {860, 20}, {740, 180},
    {820, 128}, {490, 170}, {700, 30}
};
```

Lalu, di kelas ini terdapat cukup banyak fungsi. Pertama terdapat constructor `Board`. Kemudian, ada fungsi `initBoard()` untuk membuat tampilan awal dari permainan, seperti luas tampilan layar, warna latar dari layar, serta letak dari pesawat luar angkasa dan instansi alien berdasarkan array dari variabel `pos` menggunakan fungsi `initAliens()`.

```JAVA
private void initBoard() {

    addKeyListener(new TAdapter());
    setFocusable(true);
    setBackground(Color.BLACK);
    ingame = true;

    setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

    spaceship = new SpaceShip(ICRAFT_X, ICRAFT_Y);

    initAliens();

    timer = new Timer(DELAY, this);
    timer.start();
}

public void initAliens() {
    
    aliens = new ArrayList<>();

    for (int[] p : pos) {
        aliens.add(new Alien(p[0], p[1]));
    }
}
```

Kemudian, terdapat fungsi `paintComponent` yang akan menggambarkan sprite permainan atau menampilkan pesan gameover. Tampilan ini bergantung pada variabel `ingame`.

```JAVA
public void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (ingame) {

        drawObjects(g);

    } else {

        drawGameOver(g);
    }

    Toolkit.getDefaultToolkit().sync();
}
```

Fungsi selanjutnya adalah fungsi `drawObjects()` yang menampilkan perubahan sprite permainan di layar. Yang pertama di dalam fungsi ini menampilkan pesawat luar angkasa.

```JAVA
private void drawObjects(Graphics g) {

    if (spaceship.isVisible()) {
        g.drawImage(spaceship.getImage(), spaceship.getX(), spaceship.getY(),
                this);
    }
...
}
```

Juga ada loop yang menampilkan misil atau roket yang dikeluarkan dari pesawat luar angkasa. Karena setiap misil memiliki jangkauan yang terbatas, maka diperiksa menggunakan fungsi `isVisible()`.

```JAVA
List<Missile> ms = spaceship.getMissiles();

for (Missile missile : ms) {
    if (missile.isVisible()) {
        g.drawImage(missile.getImage(), missile.getX(), 
                    missile.getY(), this);
        }
}
```

Sama halnya dengan loop sebelumnya, di loop ini menampilkan semua alien. Adapun alien yang ditampilkan hanya yang belum dihancurkan dengan cara diperiksa menggunakan fungsi `isVisible()`.

```JAVA
for (Alien alien : aliens) {
    if (alien.isVisible()) {
        g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
    }
}
```

Dan bagian terakhir dari fungsi `drawObjects()` ini menggambarkan jumlah alien yang masih tersisa di sisi kiri atas dari layar permainan.

```JAVA
g.setColor(Color.WHITE);
g.drawString("Aliens left: " + aliens.size(), 5, 15);
```

Lalu, terdapat fungsi `drawGameOver()` yang berguna untuk menampilkan pesan game over di tengah layar. Dan tampilan ini akan ditampilkan dalam akhir permainan, baik karena berhasil menghancurkan semua alien atau karena bertabrakan dengan salah satu dari alien tersebut.

```JAVA
private void drawGameOver(Graphics g) {

    String msg = "Game Over";
    Font small = new Font("Helvetica", Font.BOLD, 14);
    FontMetrics fm = getFontMetrics(small);

    g.setColor(Color.white);
    g.setFont(small);
    g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
            B_HEIGHT / 2);
}
```

Selanjutnya, terdapat fungsi `actionPerformed` yang berisikan kejadian yang terjadi dalam permainan dengan terbagi menjadi beberapa fungsi, dengan masing-masing fungsi menguraikan tentang logika dalam permainannya.


```JAVA
public void actionPerformed(ActionEvent e) {

    inGame();

    updateShip();
    updateMissiles();
    updateAliens();

    checkCollisions();

    repaint();
}
```

Contohnya, ada fungsi `inGame()` yang menjelaskan bahwa apabila variabel `ingame` bernilai `false`, maka permainan dihentikan. Selain itu, ada fungsi `updateShip()` yang berguna untuk menjalankan fungsi `move()` apabila pesawat luar angkasa sudah diperiksa berdasarkan fungsi `isVisible()`.


```JAVA
private void inGame() {

    if (!ingame) {
        timer.stop();
    }
}

private void updateShip() {

    if (spaceship.isVisible()) {
        
        spaceship.move();
    }
}
```

Juga ada fungsi `updateMissiles()` yang menggerakkan semua misil atau roket yang masih ada. Lalu, fungsi `updateAliens()` yang bertujuan untuk memeriksa apakah masih ada objek alien yang tersisa pada array dari variabel `aliens`. Jika sudah tidak ada, maka permainan selesai. Apabila masih ada, maka array dari variabel `aliens` diperiksa untuk menghapus indeks dari alien yang telah dihancurkan.

```JAVA
private void updateAliens() {

    if (aliens.isEmpty()) {

        ingame = false;
        return;
    }

    for (int i = 0; i < aliens.size(); i++) {

        Alien a = aliens.get(i);
        
        if (a.isVisible()) {
            a.move();
        } else {
            aliens.remove(i);
        }
    }
}
```

Lalu, fungsi `checkCollisions()` untuk memeriksa kemungkinan terjadinya tabrakan, baik antara pesawat luar angkasa dengan alien atau antara misil dengan alien. Pertama, diperiksa apakah objek pesawat luar angkasa bertabrakan dengan salah satu objek alien. Caranya dengan melihat bentuk rectangle dari setiap objek menggunakan fungsi `getBounds()`, kemudian diperiksa apakah terjadi singgungan dengan objek alien menggunakan fungsi `intersects()`. Hal ini juga berlaku pada tabrakan alien dengan misil yang mengakibatkan hancurnya alien tersebut.

```JAVA
public void checkCollisions() {

    Rectangle r3 = spaceship.getBounds();

    for (Alien alien : aliens) {
        
        Rectangle r2 = alien.getBounds();

        if (r3.intersects(r2)) {
            
            spaceship.setVisible(false);
            alien.setVisible(false);
            ingame = false;
        }
    }

    List<Missile> ms = spaceship.getMissiles();

    for (Missile m : ms) {

        Rectangle r1 = m.getBounds();

        for (Alien alien : aliens) {

            Rectangle r2 = alien.getBounds();

            if (r1.intersects(r2)) {
                
                m.setVisible(false);
                alien.setVisible(false);
            }
        }
    }
}
```