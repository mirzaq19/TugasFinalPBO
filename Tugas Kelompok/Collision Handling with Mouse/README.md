# Tentang Program Collision Handling with Mouse
Sama seperti tugas sebelumnya, [Collision Detection](https://github.com/mirzaq19/TugasFinalPBO/tree/main/Tugas%20Kelompok/Collision%20Detection/ "Menuju ke Repository Collision Detection"), Program ini merupakan sebuah permainan tembak-tembakan yang bertemakan luar angkasa. Pada permainan ini pemain berperan sebagai pengendali kapal luar angkasa untuk menembaki semua alien agar dapat memenangkan permainan ini.

## Kelas yang digunakan
Ada beberapa kelas yang digunakan dalam program ini, diantaranya yaitu:
1. Sprite
2. SpaceShip
3. Alien
4. Missile
5. Board
6. CollisionEx

Adapun perbedaan dengan tugas sebelumnya, di program ini menggunakan *mouse* dalam menggerakkan kapal luar angkasa serta menembakkan misilnya. Sehingga *source code*-nya ada yang berbeda. Berikut bagian *source code* yang membedakan dengan program sebelumnya.

### Kelas `SpaceShip`
Kelas ini merupakan anak dari kelas **Sprite** berfungsi sebagai kapal luar angkasa yang digunakan pemain dalam menjalankan permainannya. Kapal luar angkasa ini dapat bergerak ke semua arah, baik vertikal, horizontal, ataupun diagonal dengan menggunakan *mouse* yang digerakkan. Begitu juga untuk menembakkan misilnya menggunakan tombol mouse kiri atau kanan.

Untuk *source code* yang membedakan dengan yang sebelumnya terdapat pada digantikannya fungsi `keyPressed()` dan `keyReleased()` dengan fungsi `spaceMove()` dan `spaceShoot()`. Fungsi `spaceMove()` digunakan saat *mouse* digerakkan oleh pemain, maka kapal luar angkasa akan bergerak mengikuti arah gerak *mouse*. Dan untuk fungsi `spaceShoot()` berfungsi sebagai pemanggil fungsi `fire()`, yaitu untuk mengeluarkan misil.

```JAVA
// Before
public void fire() {
    missiles.add(new Missile(x + width, y + height / 2));
}

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

```JAVA
// After
public void fire() {
    missiles.add(new Missile(x + width, y + height / 2));
}

public void spaceMove(MouseEvent e) {
    dx = e.getX()-width/2;
    dy = e.getY()-height/2;
}
public void spaceShoot(MouseEvent e) {
    fire();
}
```
---
### Kelas `Board`
Kelas ini bisa dikatakan sebagai kelas utama yang berfungsi untuk menampung semua objek yang ada dalam permainan. Adapun terdapat beberapa fungsi yang dihilangkan dan diganti fungsi lain dalam kelas ini.

Kelas **TAdapter** menggunakan *interface* **KeyAdapter** yang berisikan fungsi `keyReleased()` dan `keyPressed()` digantikan dengan kelas **MouseHandler** yang menerapkan *interface* **MouseListener** dan **MouseMotionListener** yang berisikan fungsi `mouseDragged()`, `mouseMoved()`, `mouseClicked()`, `mousePressed()`, `mouseReleased()`, `mouseEntered()`, serta `mouseExited()`.

```JAVA
// Before
private class TAdapter extends KeyAdapter {
    @Override
    public void keyReleased(KeyEvent e) {
        spaceship.keyReleased(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        spaceship.keyPressed(e);
    }
}
```
```JAVA
// After
private class MouseHandler implements MouseListener, MouseMotionListener {

    @Override
    public void mouseDragged(MouseEvent e) {
        spaceship.spaceMove(e);
        spaceship.spaceShoot(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        spaceship.spaceMove(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        spaceship.spaceShoot(e);
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        spaceship.spaceMove(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        spaceship.spaceMove(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        spaceship.spaceMove(e);
    }
}
```
Penjelasan singkat mengenai kegunaan dari setiap fungsi,

| Fungsi | Interface | Kegunaan |
| --- | --- |--- |
| `mouseDragged()` | **MouseMotionListener** | Berfungsi saat tombol *mouse* ditekan serta *mouse* digerakkan, maka fungsi ini akan melakukan pergerakan kapal luar angkasa berdasarkan gerakan *mouse* dan menembakkan misil |
| `mouseMoved()` | **MouseMotionListener** | Berfungsi saat *mouse* dipindahkan atau digerakkan tanpa menekan tombol *mouse*, maka kapal luar angkasa akan bergerak mengikuti gerakan *mouse* |
| `mouseClicked()` | **MouseListener** | Berfungsi ketika tombol *mouse* ditekan lalu dilepas, pada program ini fungsinya tidak digunakan |
| `mousePressed()` | **MouseListener** | Berfungsi ketika tombol *mouse* ditekan, baik langsung dilepas atau ditahan. Maka di program akan mengeluarkan misil dari pesawat luar angkasa |
| `mouseReleased()` | **MouseListener** | Berfungsi saat tombol *mouse* dilepaskan pada sebuah komponen, maka kapal luar angkasa akan bergerak |
| `mouseEntered()` | **MouseListener** | Berfungsi saat *mouse* memasuki komponen, maka kapal luar angkasa akan bergerak |
| `mouseExited()` | **MouseListener** | Berfungsi saat *mouse* keluar dari komponen, maka kapal luar angkasa akan bergerak |

# Running Program
Lalu untuk tampilan saat program dijalankan bisa dilihat melalui link yang ada dibawah ini:

**Video Running Program** : [Klik Link ini](https://youtu.be/0NyuwcLOOHk "Video Running Program")



# Class Diagram
Untuk hubungan antar class sendiri dapat dilihat pada gambar berikut:
![Untitled](https://user-images.githubusercontent.com/65794806/102834443-a1448380-4426-11eb-8ec8-62fba76d2c18.png)
