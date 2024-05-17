
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.Semaphore;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Acer
 */
public class server {

    static private Socket clientSocket; //сокет дл€ общени€
    static private ServerSocket server; // серверсокет
    static int NumberOfClients = 0;//количество клиентов
    static int players = 0;//количество клиентов которые участвуют в игре
    static Semaphore sem;
    static Object lock = new Object();//объект синхронизации
    static ArrayList<String> res = new ArrayList();
    static ArrayList<String> nm = new ArrayList();
    static int game = 0;

    /**
     * @param args the command line arguments
     */
    static class Service extends Thread {

        private BufferedReader in; // поток чтени€ из сокета
        private BufferedWriter out; // поток записи в сокет
        Socket Client;
        String word;
        String name;
        boolean wait;

        Service(Socket Client) {
            this.Client = Client;
            try {
                System.out.println("Ќить запущена");
                out = new BufferedWriter(new OutputStreamWriter(Client.getOutputStream()));
                in = new BufferedReader(new InputStreamReader(Client.getInputStream()));
            } catch (IOException ex) {
                System.out.println("ѕроизошла ошибка получени€ буферов in и out");
            }
        }

        @Override
        public void run() {
            try {
                int number;
                word = in.readLine();//получаем
                name = word;
                System.out.println("ѕользователь " + name + " обслуживаетс€");
                while (true) {
                    word = in.readLine();
                    System.out.println("ѕользователь " + name + " отправил " + word);
                    switch (Integer.parseInt(word)) {
                        case 0://пользователь играет один
                     synchronized (lock) {
                                players--;
                                sem = new Semaphore(players);
                            }
                            break;
                        case 1://пользователь готов играть с другими
                    try {
                            synchronized (lock) {
                                if (game != 1) {
                                    game = 1;
                                }
                            }
                            //клиент готов к игре онлайн
                            number = players;//запоминаем количество игроков
                            sem.acquire();
                            while (sem.availablePermits() != 0) {
                                if (number != players) {
                                    sem.acquire();
                                    number = players;
                                }//если семафор был пересоздан то захватываем заново
                            }
                            this.sleep(10);
                            out.write("1\n");
                            out.flush();
                            sem.release();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;

                        case 2://пользователь готов получить результат
                try {
                            //клиент готов к игре онлайн
                            word = in.readLine();//получаем результат игрока
                            synchronized (lock) {
                                res.add(word);
                                nm.add(name);
                            }
                            sem.acquire();
                            while (sem.availablePermits() != 0) {
                            }
                            this.sleep(10);
                            out.write("2\n");
                            out.flush();
                            res.stream().forEach(s -> {
                                try {
                                    out.write(s + "\n");
                                    out.flush();
                                } catch (IOException ex) {
                                    Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            });
                            out.write("0\n");
                            out.flush();
                            nm.stream().forEach(s -> {
                                try {
                                    out.write(s + "\n");
                                    out.flush();
                                } catch (IOException ex) {
                                    Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            });
                            out.write("0\n");
                            out.flush();
                            if (sem.availablePermits() == players - 1) {
                                synchronized (lock) {
                                    if (game != 0) {
                                        game = 0;
                                    }
                                    if (res.size() != 0) {
                                        res = new ArrayList();
                                        nm = new ArrayList();
                                    }
                                }
                            }
                            sem.release();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                        case 3://пользователь хочет играть онлайн
                            while (game != 0) {
                                try {
                                    this.sleep(10);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            synchronized (lock) {
                                players++;
                                sem = new Semaphore(players);
                            }
                            out.write("3\n");
                            out.flush();
                            break;
                    }
                }
            } catch (IOException ex) {
                System.out.println("клиент отключилс€");
                NumberOfClients--;
                players--;
                sem = new Semaphore(players);
                System.out.println(" оличество клиентов:" + NumberOfClients);
            }
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("—ервер запущен");
        try {
            server = new ServerSocket(4004); // серверсокет прослушивает порт 4004
            while (true) {
                clientSocket = server.accept(); // accept() будет ждать пока
                NumberOfClients++;
                sem = new Semaphore(NumberOfClients);
                players++;
                new Service(clientSocket).start();
                System.out.println(" оличество клиентов:" + NumberOfClients);
            }
        } catch (IOException ex) {
            System.out.println("ѕроизошла ошибка добавлени€ клиента");
        }
    }
}
