
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import javax.imageio.*;
import javax.swing.*;

public class ImageGen {

    static JLabel jLabel1 = new javax.swing.JLabel("0");
    static int up, down, left, right, ctn, res, end, score = 0;
    static GameField GM = new GameField();
    static ImageFrame IT;
    static int mode;
    static MainMenu copy;
    // static ImageComponent component = new ImageComponent();

    public static void main(MainMenu args, int gameMode) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                mode = gameMode;
                GM = new GameField();
                copy = args;
                ImageFrame frame = new ImageFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                GM.NewElement();
                GM.NewElement();
            }
        });
    }

    static class ImageFrame extends JFrame {

        ImageComponent component = new ImageComponent();

        public ImageFrame() {
            setTitle("2048");
            setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            setResizable(false);
            // Добавление компонента к фрейму.
            //ImageComponent component = new ImageComponent();
            add(component);
            creatGUI();
            IT = this;
        }

        public void creatGUI() {

            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == 38) {//стрелка вверх
                        if (left == 0 & right == 0 & down == 0) {
                            up = 1;
                        }
                    }
                    if (e.getKeyCode() == 40) {//стрелка вниз
                        if (left == 0 & right == 0 & up == 0) {
                            down = 1;
                        }
                    }
                    if (e.getKeyCode() == 39) {//стрелка вправо
                        if (left == 0 & up == 0 & down == 0) {
                            right = 1;
                        }
                    }
                    if (e.getKeyCode() == 37) {//стрелка влево
                        if (right == 0 & up == 0 & down == 0) {
                            left = 1;
                        }
                    }
                }
            }
            );

        }
        public static final int DEFAULT_WIDTH = 615;
        public static final int DEFAULT_HEIGHT = 800;

        public void hide() {

        }

    }

    static class ImageComponent extends JComponent {

        ArrayList<Image> image = new ArrayList();
        Image BGImage;

        public ImageComponent() {
            // Получаем изображения.
            try {
                BGImage = ImageIO.read(new File("src/2048 game.png"));
                image.add(ImageIO.read(new File("src/tile_0.png")));
                image.add(ImageIO.read(new File("src/tile_1.png")));
                image.add(ImageIO.read(new File("src/tile_2.png")));
                image.add(ImageIO.read(new File("src/tile_3.png")));
                image.add(ImageIO.read(new File("src/tile_4.png")));
                image.add(ImageIO.read(new File("src/tile_5.png")));
                image.add(ImageIO.read(new File("src/tile_6.png")));
                image.add(ImageIO.read(new File("src/tile_7.png")));
                image.add(ImageIO.read(new File("src/tile_8.png")));
                image.add(ImageIO.read(new File("src/tile_9.png")));
                image.add(ImageIO.read(new File("src/tile_10.png")));
                image.add(ImageIO.read(new File("src/tile_11.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Timer timer = new Timer(100, new ActionListener() {//запускаем таймер.Раз в секунду проеряет значение в массиве и перерисовывает
                @Override
                public void actionPerformed(ActionEvent e) {
                    //GM.NewElement();
                    if (up == 1) {//если была нажиты кнопка вверх
                        res += GM.UP();//двигаем вверх и получаем информацию о сдвигах
                        ctn++;//увеличиваем счетчик сдвигов
                        if (ctn == 3) {//три раза сдвинули?
                            up = 0;//если да то заканчиваем сдвигать
                            ctn = 0;//обнуляем счетчик
                            if (res != 0) {//если сдвинули
                                end = GM.NewElement();
                                res = GM.test();
                                if (res == 0) {
                                    JOptionPane.showMessageDialog(null, "Вы проиграли. Итоговый счет:" + GM.score);
                                    if (mode == 1) {
                                        copy.res(GM.score);
                                    }
                                    setVisible(false);
                                    copy.setVisible(true);
                                    IT.dispose();
                                }
                                res = GM.MAX();
                                if (res == 11) {
                                    JOptionPane.showMessageDialog(null, "Поздравляю! Вы получили число 2048. Итоговый счет:" + GM.score);
                                    if (mode == 1) {
                                        copy.res(GM.score);
                                    }
                                    res = 0;
                                    IT.setVisible(false);
                                    copy.setVisible(true);
                                    IT.dispose();
                                }
                                res = 0;
                            }
                        }
                    }
                    if (down == 1) {
                        res += GM.DOWN();
                        ctn++;
                        if (ctn == 3) {
                            down = 0;
                            ctn = 0;
                            if (res != 0) {
                                end = GM.NewElement();
                                res = GM.test();
                                if (res == 0) {
                                    JOptionPane.showMessageDialog(null, "Вы проиграли. Итоговый счет:" + GM.score);
                                    if (mode == 1) {
                                        copy.res(GM.score);
                                    }
                                    setVisible(false);
                                    copy.setVisible(true);
                                    IT.dispose();
                                }
                                res = GM.MAX();
                                if (res == 11) {
                                    JOptionPane.showMessageDialog(null, "Поздравляю! Вы получили число 2048. Итоговый счет:" + GM.score);
                                    res = 0;
                                    if (mode == 1) {
                                        copy.res(GM.score);
                                    }
                                    setVisible(false);
                                    copy.setVisible(true);
                                    IT.dispose();
                                }
                                res = 0;
                            }
                        }
                    }
                    if (left == 1) {
                        res += GM.LEFT();
                        ctn++;
                        if (ctn == 3) {
                            left = 0;
                            ctn = 0;
                            if (res != 0) {
                                end = GM.NewElement();
                                res = GM.test();
                                if (res == 0) {
                                    JOptionPane.showMessageDialog(null, "Вы проиграли. Итоговый счет:" + GM.score);
                                    if (mode == 1) {
                                        copy.res(GM.score);
                                    }
                                    setVisible(false);
                                    copy.setVisible(true);
                                    IT.dispose();
                                }
                                res = GM.MAX();
                                if (res == 11) {
                                    JOptionPane.showMessageDialog(null, "Поздравляю! Вы получили число 2048. Итоговый счет:" + GM.score);
                                    res = 0;
                                    if (mode == 1) {
                                        copy.res(GM.score);
                                    }
                                    setVisible(false);
                                    copy.setVisible(true);
                                    IT.dispose();
                                }
                                res = 0;
                            }
                        }
                    }
                    if (right == 1) {
                        res += GM.RIGHT();
                        ctn++;
                        if (ctn == 3) {
                            right = 0;
                            ctn = 0;
                            if (res != 0) {
                                end = GM.NewElement();
                                res = GM.test();
                                if (res == 0) {
                                    JOptionPane.showMessageDialog(null, "Вы проиграли. Итоговый счет:" + GM.score);
                                    if (mode == 1) {
                                        copy.res(GM.score);
                                    }
                                    setVisible(false);
                                    copy.setVisible(true);
                                    IT.dispose();
                                }
                                res = GM.MAX();
                                if (res == 11) {
                                    JOptionPane.showMessageDialog(null, "Поздравляю! Вы получили число 2048. Итоговый счет:" + GM.score);
                                    if (mode == 1) {
                                        copy.res(GM.score);
                                    }
                                    res = 0;
                                    setVisible(false);
                                    copy.setVisible(true);
                                    IT.dispose();
                                }
                                res = 0;
                            }
                        }
                    }
                    repaint(); //To change body of generated methods, choose Tools | Templates.
                }
            });
            timer.start();
        }

        @Override
        public void paintComponent(Graphics g) {
            if (image == null) {
                return;
            }
            int imageWidth = 150;
            int imageHeight = 150;
            g.drawImage(BGImage, 0, 0, null);
            // Многократный вывод изображения в панели.
            for (int i = 0; i <= 3; i++) {
                for (int j = 0; j <= 3; j++) {
                    switch (GM.Id[i][j]) {
                        case (0):
                            g.drawImage(image.get(0), (i * imageWidth), (j * imageHeight) + 150, null);
                            break;
                        case (1):
                            g.drawImage(image.get(1), (i * imageWidth), (j * imageHeight) + 150, null);
                            break;
                        case (2):
                            g.drawImage(image.get(2), (i * imageWidth), (j * imageHeight) + 150, null);
                            break;
                        case (3):
                            g.drawImage(image.get(3), (i * imageWidth), (j * imageHeight) + 150, null);
                            break;
                        case (4):
                            g.drawImage(image.get(4), (i * imageWidth), (j * imageHeight) + 150, null);
                            break;
                        case (5):
                            g.drawImage(image.get(5), (i * imageWidth), (j * imageHeight) + 150, null);
                            break;
                        case (6):
                            g.drawImage(image.get(6), (i * imageWidth), (j * imageHeight) + 150, null);
                            break;
                        case (7):
                            g.drawImage(image.get(7), (i * imageWidth), (j * imageHeight) + 150, null);
                            break;
                        case (8):
                            g.drawImage(image.get(8), (i * imageWidth), (j * imageHeight) + 150, null);
                            break;
                        case (9):
                            g.drawImage(image.get(9), (i * imageWidth), (j * imageHeight) + 150, null);
                            break;
                        case (10):
                            g.drawImage(image.get(10), (i * imageWidth), (j * imageHeight) + 150, null);
                            break;
                        case (11):
                            g.drawImage(image.get(11), (i * imageWidth), (j * imageHeight) + 150, null);
                            break;
                    }
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                    g.drawString("" + GM.score, 400, 100);
                }
            }
        }
    }
}
